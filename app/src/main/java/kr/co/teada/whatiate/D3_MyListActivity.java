package kr.co.teada.whatiate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class D3_MyListActivity extends AppCompatActivity {

    private int id;
    private String date;
    private String event;

    public D3_MyListActivity() {
    }

    public D3_MyListActivity(int id, String date, String event) {
        this.id = id;
        this.date = date;
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d3_mylist);

        Toast.makeText(this, "D3. 서비스 준비중", Toast.LENGTH_SHORT).show();



    }
}
