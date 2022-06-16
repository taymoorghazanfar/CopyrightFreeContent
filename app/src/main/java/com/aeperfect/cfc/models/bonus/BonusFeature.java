package com.aeperfect.cfc.models.bonus;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class BonusFeature implements Parcelable {

    public static final Creator<BonusFeature> CREATOR = new Creator<BonusFeature>() {
        @Override
        public BonusFeature createFromParcel(Parcel in) {
            return new BonusFeature(in);
        }

        @Override
        public BonusFeature[] newArray(int size) {
            return new BonusFeature[size];
        }
    };
    private int id;
    private String title;
    private String url;

    public BonusFeature(int id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    protected BonusFeature(Parcel in) {
        id = in.readInt();
        title = in.readString();
        url = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @NonNull
    @Override
    public String toString() {
        return "BonusFeature{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(url);
    }
}
