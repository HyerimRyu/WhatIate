package kr.co.teada.whatiate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class D4_SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d4__setting);

        Toast.makeText(this, "D4. 서비스 준비중", Toast.LENGTH_SHORT).show();
    }
}
