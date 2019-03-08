package kr.co.teada.whatiate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//end of onCreate


    public void clickBtnSearch(View view) {
        //search Avtivity 이동
    }


    public void clickBtnFab(View view) {
        //갤러리 이동
        openGallery();


    }

    public void openGallery(){
        Intent photoPicintent=new Intent(Intent.ACTION_PICK);
        photoPicintent.setType("image/*");
        startActivityForResult(photoPicintent, 10);

        saveData();
    }

    public void saveData(){

    }

}//end of  MainActivity
