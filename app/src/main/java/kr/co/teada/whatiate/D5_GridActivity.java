package kr.co.teada.whatiate;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class D5_GridActivity extends AppCompatActivity {

    Context context;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d5__grid);

        recyclerView=findViewById(R.id.recyclerView);

        //에러나!!! 리사이클러

        GridLayoutManager layoutManager=new GridLayoutManager(context,3);
        recyclerView.setLayoutManager(layoutManager);

    }
}
