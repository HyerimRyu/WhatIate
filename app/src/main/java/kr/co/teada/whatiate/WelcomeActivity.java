package kr.co.teada.whatiate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class WelcomeActivity extends AppCompatActivity {

    EditText etName;
    CircleImageView ivProfile;

    Uri imgUri;
    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        etName=findViewById(R.id.et_name);
        ivProfile=findViewById(R.id.iv_profile);

        loadData();

        //그 전 사용 기록이 있으면 바로 메인 열어 ---> 실제 마켓에 올릴때는 이 코드 쓰기 //파이어베이스 DB 등록성공. 이미지 계정이랑 연결
        if (G.nickName != null){
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }//end of onCreate


    //프로필 사진 지정
    public void clickImage(View view) {

        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 10:
                if (resultCode==RESULT_OK){
                    imgUri=data.getData();
                    Picasso.get().load(imgUri).into(ivProfile);
                }
                break;
        }
    }

    //메인으로 이동
    public void clickNext(View view) {
        G.nickName=etName.getText().toString();

        //프로필 이미지 파이어베이스 저장소에 업로드: 그래야 여러군데서 이미지 프로필 사용가능
        saveData();
    }



    private void saveData() {
        //프로필 이미지 파이어베이스저장소에 업로드
        if (imgUri==null) return;

        firebaseStorage=FirebaseStorage.getInstance();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
        String fileName=sdf.format( new Date() )+".png";

        final StorageReference imgRef=firebaseStorage.getReference("profileImages/"+fileName);

        UploadTask uploadTask=imgRef.putFile(imgUri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //firebase 저장소에 있는 이미지 다운로드 주소 문자열로
                        G.profileUrl=uri.toString();
                        Toast.makeText(WelcomeActivity.this, "프로필 저장 완료", Toast.LENGTH_SHORT).show();

                        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                        DatabaseReference profileRef=firebaseDatabase.getReference("profiles");

                        profileRef.child(G.nickName).setValue(G.profileUrl);

                        //핸드폰에 영구적으로 닉네임과 프로필 이미지 경로 저장
                        SharedPreferences pref=getSharedPreferences("account", MODE_PRIVATE);
                        SharedPreferences.Editor editor=pref.edit();

                        editor.putString("nickName", G.nickName);
                        editor.putString("profileUrl", G.profileUrl);

                        editor.commit(); //이거 안하면 저장 안돼

                        Intent intent=new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }//end of  saveData()

    private void loadData() {
        SharedPreferences pref=getSharedPreferences("account", MODE_PRIVATE);
        G.nickName=pref.getString("nickName", null);
        G.profileUrl=pref.getString("profileUrl", null);
    }


}//end of WelcomeActivity
