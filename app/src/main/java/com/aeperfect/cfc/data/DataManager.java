package com.aeperfect.cfc.data;

import android.content.Context;

import com.aeperfect.cfc.models.bonus.BonusFeature;
import com.aeperfect.cfc.models.content.ContentCategory;
import com.aeperfect.cfc.models.editor.EditingTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class DataManager {

    private final JsonDataManager jsonDataManager;

    public DataManager(Context context) {
        jsonDataManager = new JsonDataManager(context);
    }

    public ArrayList<ContentCategory> getContentCategories() {

        return jsonDataManager.getFreeContentData();
    }

    public HashMap<String, List<EditingTool>> getEditingTools() {

        HashMap<String, List<EditingTool>> hashMap = new LinkedHashMap<>();
        hashMap.put("Photo Editing Tools", jsonDataManager.getFreePhotoEditor().getEditingTools());

        return hashMap;
    }

    public HashMap<String, List<BonusFeature>> getBonusContent() {

        HashMap<String, List<BonusFeature>> hashMap = new LinkedHashMap<>();
        hashMap.put("Bonus Features", jsonDataManager.getFreeBonusContent().getBonusFeatures());

        return hashMap;
    }
}
