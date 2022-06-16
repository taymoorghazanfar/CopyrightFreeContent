package com.aeperfect.cfc.models.editor;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class PhotoEditor implements Parcelable {

    public static final Creator<PhotoEditor> CREATOR = new Creator<PhotoEditor>() {
        @Override
        public PhotoEditor createFromParcel(Parcel in) {
            return new PhotoEditor(in);
        }

        @Override
        public PhotoEditor[] newArray(int size) {
            return new PhotoEditor[size];
        }
    };
    private final ArrayList<EditingTool> editingTools;

    public PhotoEditor(ArrayList<EditingTool> editingTools) {
        this.editingTools = editingTools;
    }

    protected PhotoEditor(Parcel in) {
        editingTools = in.createTypedArrayList(EditingTool.CREATOR);
    }

    public ArrayList<EditingTool> getEditingTools() {
        return editingTools;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(editingTools);
    }
}
