package kr.co.teada.whatiate;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Uri imgUri;

    RecyclerView recyclerView;
    ArrayList<PicItem> picItems=new ArrayList<>();
    MyAdapter myAdapter;


    private FirebaseStorage firebaseStorage;
    private FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter=new MyAdapter(picItems, this);
        recyclerView.setAdapter(myAdapter);
    }//end of onCreate


    public void clickBtnSearch(View view) {
        //search Avtivity 이동
    }


    public void clickBtnFab(View view) {
        //갤러리 이동
        openGallery();


    }

    //갤러리 열어
    public void openGallery(){
        Intent photoPicintent=new Intent(Intent.ACTION_PICK);
        photoPicintent.setType("image/*");
        startActivityForResult(photoPicintent, 10);

        saveData();

        //Intent 불러서 upload Activity ->et
    }

    //파이어베이스에 데이터 저장
    public void saveData(){


    }

}//end of  MainActivity
