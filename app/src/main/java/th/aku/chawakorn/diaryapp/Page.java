package th.aku.chawakorn.diaryapp;

import android.media.Image;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by DELL on 6/3/2016.
 */
public class Page implements Serializable{
    public Page(Date date, String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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



    private String title,detail;
    private Image image;

    @Override
    public String toString() {
        return ""+d+","+m+","+y+": "+title;
    }
}
