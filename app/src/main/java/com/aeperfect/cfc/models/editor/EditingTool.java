package com.aeperfect.cfc.models.editor;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class EditingTool implements Parcelable {

    public static final Creator<EditingTool> CREATOR = new Creator<EditingTool>() {
        @Override
        public EditingTool createFromParcel(Parcel in) {
            return new EditingTool(in);
        }

        @Override
        public EditingTool[] newArray(int size) {
            return new EditingTool[size];
        }
    };
    private int id;
    private String title;
    private final String url;

    public EditingTool(int id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    protected EditingTool(Parcel in) {
        id = in.readInt();
        title = in.readString();
        url = in.readString();
    }

    public int getId() {
        return this.id;
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

    @NonNull
    @Override
    public String toString() {
        return "EditingTool{" +
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
