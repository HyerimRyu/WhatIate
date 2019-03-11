package kr.co.teada.whatiate;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class MyDecoration extends RecyclerView.ItemDecoration {

    //option + enter : alt + insult  생성자 말고 오버라이드에서 필요한 것만 추가

    //리사이클러 뷰의 배경 그림 그리기

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    //리사이클러 뷰의 전경 그림 (아이템 위에 그려져)
    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    //여기가 중요! outRect : 바깥쪽 마진 담당 --> ex56 Recycler MyDecoration class 참고
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);


    }
}
