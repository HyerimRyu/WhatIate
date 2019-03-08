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

    ArrayList<PicItem> picItems;
    Context context;

    //생성자

    public MyAdapter(ArrayList<PicItem> picItems, Context context) {
        this.picItems = picItems;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.recycler_item, viewGroup, false);

        VH holder=new VH(itemView);
        return holder;
    }

    //여기 어렵다 어디서 바인드 하는건지
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        PicItem picItem=picItems.get(position);
        String foodPicImg=picItem.getFoodPic_Url();

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
            Glide.with(context).load(R.drawable.ic_launcher_background).into(ivFoodPic);}3

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

                ivFoodPic=itemView.findViewById(R.id.ivFoodPic);
                tvResName=itemView.findViewById(R.id.tvResName);
                tvMemo=itemView.findViewById(R.id.tvMemo);
                tvHash=itemView.findViewById(R.id.tvHash);
                tvTime=itemView.findViewById(R.id.tvTime);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //사진 눌렀을 때 보낼 데이터


                    }
                });
            }
        }

}
