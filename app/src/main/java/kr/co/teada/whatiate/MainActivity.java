package kr.co.teada.whatiate;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

import static kr.co.teada.whatiate.R.string.app_name;

public class MainActivity extends AppCompatActivity {

    private Uri imgUri;

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


//        String iVDrawerProfile=G.profileUrl;
//        String tvDrawerNickName=G.nickName;


    }//end of onCreate

    class navigationItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            //Drawer 눌렀을 때 반응
//            switch ( menuItem.getItemId() ){
//
//                case R.id.boomark:
//                    Intent intent= new Intent( MainActivity.this, BookmarkActivity.class);
//                    startActivity(intent);
//                    break;
//
//                case R.id.setting:
//                    intent= new Intent( MainActivity.this, SettingActivity.class);
//                    startActivity(intent);
//                    break;
//
//
//            }
            drawerLayout.closeDrawer( navigationView );
            return false;

        }
    }




        public void clickBtnSearch(View view) {
        //search Avtivity 이동
    }


    // + 버튼 누르면, 갤러리에서 사진 고르고, add 액티비티 실행
    public void clickBtnFab(View view) {
        //갤러리 이동
        openGallery();

        Intent intent=new Intent(this, AddActivity.class);
        startActivity(intent);
        finish();



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
