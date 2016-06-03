package th.aku.chawakorn.diaryapp;

import android.media.Image;

import java.util.Date;

/**
 * Created by DELL on 6/3/2016.
 */
public class Page {
    public Page(Date date, String title, String detail) {
        this.date = date;
        this.title = title;
        this.detail = detail;
    }

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title,detail;
    private Image image;
}
