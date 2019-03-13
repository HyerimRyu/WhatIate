package kr.co.teada.whatiate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class D3_MyListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d3_mylist);

        Toast.makeText(this, "D3. 서비스 준비중", Toast.LENGTH_SHORT).show();
    }
}
