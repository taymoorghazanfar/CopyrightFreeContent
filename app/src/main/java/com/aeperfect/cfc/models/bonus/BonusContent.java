package com.aeperfect.cfc.models.bonus;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class BonusContent implements Parcelable {

    public static final Creator<BonusContent> CREATOR = new Creator<BonusContent>() {
        @Override
        public BonusContent createFromParcel(Parcel in) {
            return new BonusContent(in);
        }

        @Override
        public BonusContent[] newArray(int size) {
            return new BonusContent[size];
        }
    };
    private final ArrayList<BonusFeature> bonusFeatures;

    public BonusContent(ArrayList<BonusFeature> bonusFeatures) {
        this.bonusFeatures = bonusFeatures;
    }

    protected BonusContent(Parcel in) {
        bonusFeatures = in.createTypedArrayList(BonusFeature.CREATOR);
    }

    public ArrayList<BonusFeature> getBonusFeatures() {
        return bonusFeatures;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(bonusFeatures);
    }
}
