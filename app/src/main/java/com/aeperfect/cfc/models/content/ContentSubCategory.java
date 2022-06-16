package com.aeperfect.cfc.models.content;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ContentSubCategory implements Parcelable {

    public static final Creator<ContentSubCategory> CREATOR = new Creator<ContentSubCategory>() {
        @Override
        public ContentSubCategory createFromParcel(Parcel in) {
            return new ContentSubCategory(in);
        }

        @Override
        public ContentSubCategory[] newArray(int size) {
            return new ContentSubCategory[size];
        }
    };
    private int id;
    private final int parentId;
    private String title;
    private String url;

    public ContentSubCategory(int id, int parentId, String title, String url) {
        this.id = id;
        this.parentId = parentId;
        this.title = title;
        this.url = url;
    }

    protected ContentSubCategory(Parcel in) {
        id = in.readInt();
        parentId = in.readInt();
        title = in.readString();
        url = in.readString();
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
        return "FreeContentSubCategory{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(parentId);
        parcel.writeString(title);
        parcel.writeString(url);
    }
}
