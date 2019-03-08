package kr.co.teada.whatiate;

import android.net.Uri;

public class PicItem {

    //recycler item 에 들어가는 것들
    private String foodPic_Url;

    private String resName;
    private String memo;
    private String hash;
    private String time;


    //생성자 command n
    public PicItem(String foodPic_Url, String resName, String memo, String hash) {
        this.foodPic_Url = foodPic_Url;
        this.resName = resName;
        this.memo = memo;
        this.hash = hash;
    }

    public PicItem(String foodPic_Url, String resName, String memo, String hash, String time) {
        this.foodPic_Url = foodPic_Url;
        this.resName = resName;
        this.memo = memo;
        this.hash = hash;
        this.time = time;
    }

    //FB DB 저장용 빈 생성자

    public PicItem() {
    }


    //getter and setter


    public String getFoodPic_Url() {
        return foodPic_Url;
    }

    public void setFoodPic_Url(String foodPic_Url) {
        this.foodPic_Url = foodPic_Url;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}//end of PicItem
