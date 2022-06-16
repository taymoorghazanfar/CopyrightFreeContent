package com.aeperfect.cfc.utils.comparator;

import com.aeperfect.cfc.models.bonus.BonusFeature;

import java.util.Comparator;

public class BonusFeatureComparator implements Comparator<BonusFeature> {
    @Override
    public int compare(BonusFeature feature1, BonusFeature feature2) {
        return feature1.getId() - feature2.getId();
    }
}