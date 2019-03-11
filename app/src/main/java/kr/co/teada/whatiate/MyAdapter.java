package kr.co.teada.whatiate;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter {

    //1. 대량의 데이터 참조 변수 new 아니야. 이미 있는거 데려오는거라
    ArrayList<PicItem> picItems;
    Context context;

    //2. 생성자

    public MyAdapter(ArrayList<PicItem> picItems, Context context) {
        this.picItems = picItems;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        //3. 리사이클러에 들어갈 아이템(PicItem)을 View 객체로 만들기
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.recycler_item, viewGroup, false);

        //4. 뷰홀더 객체 생성(아래에 이너클래스로 )
        VH holder=new VH(itemView);
        return holder;  //홀더 객체 리턴: 홀더가 갖고 있는거 리사이클러 뷰에 보여줄꺼야
    }

    //여기 어렵다 어디서 바인드 하는건지
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        PicItem picItem=picItems.get(position);
        String foodPicImg=picItem.getFoodPic_Url();

        //5. 바인딩 작업: 바인딩 안하면 똑같은 사진만 계속 반복 : 매개변수 뷰홀더, 포지션
        //도그인데 애니멀인지 알고 있어 너 도그 맞아!(VH)
        VH holder= (VH) viewHolder;
        ImageView ivFoodPic=((VH) viewHolder).ivFoodPic;

        if(foodPicImg != null) {
            Uri uri = Uri.parse(foodPicImg);

            Picasso.get().load(uri).into(ivFoodPic, new Callback() {
                @Override
                public void onSuccess() {
                }
                @Override
                public void onError(Exception e) {
                    // Log.d("picPath ", "pIntor img: load failed "+ img);
                }
            });
        }
        else{
            Glide.with(context).load(R.drawable.ic_launcher_background).into(ivFoodPic);}

        holder.tvResName.setText(picItem.getResName());
        holder.tvMemo.setText(picItem.getMemo());
        holder.tvHash.setText(picItem.getHash());
    }

    @Override
    public int getItemCount() {return picItems.size();}


    //innerClass VH

    class VH extends RecyclerView.ViewHolder{

        ImageView ivFoodPic;
        TextView tvResName, tvMemo, tvHash, tvTime;


        public VH(@NonNull View itemView) {
            super(itemView);

                //bind View
                ivFoodPic=itemView.findViewById(R.id.ivFoodPic);
                tvResName=itemView.findViewById(R.id.tvResName);
                tvMemo=itemView.findViewById(R.id.tvMemo);
                tvHash=itemView.findViewById(R.id.tvHash);
                tvTime=itemView.findViewById(R.id.tvTime);

                //값 넣기 setText, Glide 이미지

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //사진 눌렀을 때 보낼 데이터 -> 상세 액티비티로 띄우기 (원피스 예제 참고 )
                        //Intent intent=new Intent(MyAdapter.this, DetailActivity.class);

                        //추가 데이터 전달요청 바인드 한거 5개--->디테일 액티비티에서 Intent intent=getIntent(); 해야해 ex56.recycler DetailActivity 봐
                        //intent.putExtra("Img", ivFoodPic);
                        //intent.putExtra()..

                        //코드 제대로 쓰기
                        //startActivity(intent)


                    }
                });
            }
        }

}
