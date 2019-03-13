package kr.co.teada.whatiate;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import java.util.Locale;


public class SearchActivity extends AppCompatActivity {

    SearchView searchView;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar=findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

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


        //<- 백버튼 누르면 메인으로 가 up버튼 활성화(manifest 에서 parent 지정)
//        actionBar=getSupportActionBar();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        searchView=findViewById(R.id.searchView);
        searchView.setQueryHint("무엇을 찾고 계세요?");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(SearchActivity.this, s+"를 검색합니다.", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) { return false; }
        });

    }//end of onCreate



}//end of SearchActivity
