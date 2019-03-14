package kr.co.teada.whatiate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private Uri imgUri; //fab 의 선택된 이미지
    ImageView iv_add_photo_img; //addAct 의 이미지뷰 피카소 안 쓰면 필요 없어

    //리사이클러뷰 3개 필요 대량의 데이터, 뷰, 어댑터
    RecyclerView recyclerView;
    ArrayList<PicItem> picItems=new ArrayList<>();
    MyAdapter myAdapter;


    private FirebaseStorage firebaseStorage;
    private FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;


    //Drawer
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    //DrawerHeader의 2개 welcome과 연결
    CircleImageView dProfileImg;
    TextView dNickName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled( false );

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter=new MyAdapter(picItems, this);
        recyclerView.setAdapter(myAdapter);

        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navi);
        navigationView.setItemIconTintList(null);


        navigationView.setNavigationItemSelectedListener( new navigationItemSelectedListener() );


        drawerToggle=new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        //Drawer 네비에 프로필이랑 닉네임 연동
        dProfileImg=findViewById(R.id.ivDrawerHeader);
        dNickName=findViewById(R.id.tvDrawerNickName);

        iv_add_photo_img=findViewById(R.id.iv_add_photo_img);
    }//end of onCreate


    public void clickBtnSearch(View view) {

        Intent intent=new Intent(this,SearchActivity.class);
        startActivity(intent);
        finish();

    }


    // + 버튼 누르면, 갤러리에서 사진 고르고, add 액티비티 실행
    public void clickBtnFab(View view) {
        //갤러리 이동
        openGallery();
    }


//    //앱션바에 있는 서치 눌렀을 때
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search_menu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    //앱바의 액션을 클릭하면 호출되는 메소드
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.menu_search:
//                Intent intent=new Intent(this, SearchActivity.class);
//                startActivity(intent);
//                return true;
//
//                default:
//                    return super.onOptionsItemSelected(item);
//        }
//
//    }

    class navigationItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            //Drawer 눌렀을 때 반응
            switch ( menuItem.getItemId() ){

                case R.id.d1_myAccount:
                    Intent intent= new Intent( MainActivity.this, D1_MyAccountActivity.class);
                    startActivity(intent);
                    break;

                case R.id.d2whatIeat:
                    intent= new Intent( MainActivity.this, D2_WhatIEatActivity.class);
                    startActivity(intent);
                    break;

                case R.id.d3MyList:
                    intent= new Intent( MainActivity.this, D3_MyListActivity.class);
                    startActivity(intent);
                    break;

                case R.id.d4Setting:
                    intent= new Intent( MainActivity.this, D4_SettingsActivity.class);
                    startActivity(intent);
                    break;

                case R.id.d5Grid:
                    intent=new Intent(MainActivity.this, D5_GridActivity.class);
                    startActivity(intent);
                    break;


            }
            drawerLayout.closeDrawer( navigationView );
            return false;

        }
    }




    //갤러리 열어
    public void openGallery(){

        Intent photoPicintent=new Intent(Intent.ACTION_PICK);
        photoPicintent.setType("image/*");
        startActivityForResult(photoPicintent, 10);
    }

    //사진 선택 하면!! ********* request code 받고 작업

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 10:
                if (resultCode==RESULT_OK){

                    //addAc 계속 반복됨 수정하기
                    Intent intent=new Intent(MainActivity.this, AddActivity.class);
                    startActivity(intent);

                    imgUri=data.getData(); //-----> 갤러리에서 선택한 사진 저장
                    //Picasso.get().load(imgUri).into(iv_add_photo_img);
                }
                break;
        }
    }

}//end of  MainActivity
