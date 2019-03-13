package kr.co.teada.whatiate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class IntroActivity extends AppCompatActivity {

    Timer timer=new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        timer.schedule(task, 2000);
    }

    TimerTask task=new TimerTask() {
        @Override
        public void run() {
            Intent intent=new Intent(IntroActivity.this, WelcomeActivity.class );
            startActivity(intent);

        }
    };
}
