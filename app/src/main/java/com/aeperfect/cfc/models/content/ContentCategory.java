package com.aeperfect.cfc.models.content;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ContentCategory implements Parcelable {

    public static final Creator<ContentCategory> CREATOR = new Creator<ContentCategory>() {
        @Override
        public ContentCategory createFromParcel(Parcel in) {
            return new ContentCategory(in);
        }

        @Override
        public ContentCategory[] newArray(int size) {
            return new ContentCategory[size];
        }
    };
    private int id;
    private String title;
    private final ArrayList<ContentSubCategory> subCategories;

    public ContentCategory(int id, String title, ArrayList<ContentSubCategory> subCategories) {
        this.id = id;
        this.title = title;
        this.subCategories = subCategories;
    }

    protected ContentCategory(Parcel in) {
        id = in.readInt();
        title = in.readString();
        subCategories = in.createTypedArrayList(ContentSubCategory.CREATOR);
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

    public ArrayList<ContentSubCategory> getSubCategories() {
        return subCategories;
    }

    @NonNull
    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id: ").append(getId()).append("\n");
        stringBuilder.append("Title: ").append(getTitle()).append("\n");

        for (ContentSubCategory subCategory : getSubCategories()) {

            stringBuilder.append(subCategory.toString()).append("\n");
        }

        return stringBuilder.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeTypedList(subCategories);
    }
}
