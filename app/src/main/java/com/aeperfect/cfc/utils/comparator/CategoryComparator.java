package com.aeperfect.cfc.utils.comparator;

import com.aeperfect.cfc.models.content.ContentCategory;

import java.util.Comparator;

public class CategoryComparator implements Comparator<ContentCategory> {
    @Override
    public int compare(ContentCategory content1, ContentCategory content2) {
        return content1.getId() - content2.getId();
    }
}
