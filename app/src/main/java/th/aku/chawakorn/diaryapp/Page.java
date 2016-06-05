package th.aku.chawakorn.diaryapp;

import android.media.Image;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by DELL on 6/3/2016.
 */
public class Page implements Serializable{

    public int getD() {
        return d;
    }


    public int getM() {
        return m;
    }


    public int getY() {
        return y;
    }


    public String getTitle() {
        return title;
    }


    public String getDetail() {
        return detail;
    }


    private int d;
    private int m;
    private int y;

    public Page(int d, int m, int y, String title, String detail) {
        this.d = d;
        this.detail = detail;
        this.title = title;
        this.y = y;
        this.m = m;
    }

    public int compareTo(Page p){
        if(this.getY()<p.getY()) return -1;
        else if(this.getY()>p.getY()) return 1;
        else{
            if(this.getM()<p.getM()) return -1;
            else if(this.getM()>p.getM()) return 1;
            else{
                if(this.getD()<p.getD()) return -1;
                else if(this.getD()>p.getD()) return 1;
                else{
                    return this.getTitle().compareTo(p.getTitle());
                }
            }
        }
    }


    public String getDate(){
        return (new StringBuilder().append(String.format("%02d",d)).append("/").append(String.format("%02d",m)).append("/").append(String.format("%04d",y))).toString();
    }
    private String title,detail;

    @Override
    public String toString() {
        return getDate()+": "+title;
    }
}
