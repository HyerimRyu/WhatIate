package kr.co.teada.whatiate;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

public class AddActivity extends AppCompatActivity {

    private Uri imgUri; //갤러리에 선택한 이미지의 uri

    private ImageView ivAddPhotoImg; //선택한 이미지 보는 Iv
    EditText etAdd_resName;
    EditText etAddPhoto_content; //recycler 에서 imageview 로 보여줄 텍스트

    //선택한 이미지와 내용 파이어 베이스에 저장해야 하니까 : 이미지, 식당명, 내용
    private FirebaseStorage firebaseStorage;
    private FirebaseDatabase firebaseDatabase;

    DatabaseReference addRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ivAddPhotoImg=findViewById(R.id.iv_add_photo_img);
        etAdd_resName=findViewById(R.id.et_add_resName);
        etAddPhoto_content=findViewById(R.id.et_add_photo_content);

        addRef=FirebaseDatabase.getInstance().getReference("addUpload");

        //addAct 2번 실행됨 ->코드 수정 필요 : Intent 가 결과 가져오는거 : 현재 코드상으로는 AddAct에 갤러리에서 선택한 이미지가 붙기는 한다
        Intent photoPicIntent=new Intent(Intent.ACTION_PICK);
        photoPicIntent.setType("image/*");
        startActivityForResult(photoPicIntent,10);
    }//end of onCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 10:
                if (resultCode ==RESULT_OK){
                    imgUri=data.getData();
                    Picasso.get().load(imgUri).into(ivAddPhotoImg);
                }
                break;
        }
    }

    //사진 올리기 버튼 누르면 --> 메인의 recycler 뷰에 업로드 버튼
    public void clickUpload(View view) {
        //파아이 베이스 DB 에 사진, 이름, 내용 다 저장 통째로
        saveData();
    }

    //    //파이어베이스에 데이터 저장 확인!
    public void saveData(){
        if (imgUri == null) return;

        FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
        String fileName=sdf.format(new Date())+".png";

        final StorageReference foodPicStorageImgRef=firebaseStorage.getReference("whatIateImages/"+fileName);

        //이미지 업로드
        UploadTask uploadTask=foodPicStorageImgRef.putFile(imgUri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //이미지 업로드 성공, 업로드된 이미지의 주소 얻어오기
                foodPicStorageImgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //firebase 저장소에 있는 이미지의 다운로드 주소를 문자열로
                        G.pickImage=uri.toString();
                        Toast.makeText(AddActivity.this, "이미지 저장 완료", Toast.LENGTH_SHORT).show();

                        //firebase DB에 저장(위에는 storage)
                        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                        DatabaseReference foodPicDBRef=firebaseDatabase.getReference("foodPics");

                        foodPicDBRef.child(G.pickImage);



                        Intent intent=new Intent(AddActivity.this, MainActivity.class);
                        startActivity(intent);

                    }
                });
            }
        });


    }





}//end of AddActivity
