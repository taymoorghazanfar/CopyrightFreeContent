package com.aeperfect.cfc.data;

import android.content.Context;

import com.aeperfect.cfc.R;
import com.aeperfect.cfc.models.bonus.BonusFeature;
import com.aeperfect.cfc.models.bonus.BonusContent;
import com.aeperfect.cfc.models.editor.EditingTool;
import com.aeperfect.cfc.models.content.ContentCategory;
import com.aeperfect.cfc.models.content.ContentSubCategory;
import com.aeperfect.cfc.models.editor.PhotoEditor;
import com.aeperfect.cfc.utils.comparator.BonusFeatureComparator;
import com.aeperfect.cfc.utils.comparator.CategoryComparator;
import com.aeperfect.cfc.utils.JsonUtil;
import com.aeperfect.cfc.utils.comparator.EditingToolComparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class JsonDataManager {

    private final Context context;

    public JsonDataManager(Context context) {
        this.context = context;
    }

    //todo: all the methods are named after their respective JSON file in RAW directory

    public ArrayList<ContentCategory> getFreeContentData() {

        InputStream jsonStream = context.getResources().openRawResource(R.raw.free_content_data);

        ArrayList<ContentCategory> categories = new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject(JsonUtil.streamToString(jsonStream));
            JSONArray jsonFreeContentArray = jsonObject.getJSONArray("categories");

            for (int x = 0; x < jsonFreeContentArray.length(); x++) {

                JSONObject categoryJson = jsonFreeContentArray.getJSONObject(x);

                int catId = categoryJson.getInt("id");
                String catTitle = categoryJson.getString("title");
                ArrayList<ContentSubCategory> subCategories = new ArrayList<>();

                JSONArray subCategoriesJson = categoryJson.getJSONArray("sub-cats");

                for (int y = 0; y < subCategoriesJson.length(); y++) {

                    JSONObject subCategoryJson = subCategoriesJson.getJSONObject(y);

                    int subCatId = subCategoryJson.getInt("id");
                    String subCatTitle = subCategoryJson.getString("title");
                    String subCatUrl = subCategoryJson.getString("url");

                    ContentSubCategory subCategory =
                            new ContentSubCategory(subCatId, catId, subCatTitle, subCatUrl);

                    subCategories.add(subCategory);
                }

                ContentCategory category = new ContentCategory(catId, catTitle, subCategories);
                categories.add(category);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                jsonStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Collections.sort(categories, new CategoryComparator());

        return categories;
    }

//    public IFreeContent getFreeVideoDownloaders() {
//
//        InputStream jsonStream = context.getResources().openRawResource(R.raw.free_video_downloader);
//        IFreeContent videoDownloader = null;
//
//        try {
//
//            JSONObject jsonObject = new JSONObject(JsonUtil.streamToString(jsonStream));
//            JSONObject videoDownloaderJson = jsonObject.getJSONObject("video-downloader");
//
//            int id = videoDownloaderJson.getInt("id");
//            String title = videoDownloaderJson.getString("title");
//            ArrayList<IFreeContent> downloadSites = new ArrayList<>();
//
//            JSONArray downloadSitesJson = videoDownloaderJson.getJSONArray("download-sites");
//
//            for (int x = 0; x < downloadSitesJson.length(); x++) {
//
//                JSONObject downloadSiteJson = downloadSitesJson.getJSONObject(x);
//
//                int downloadSiteId = downloadSiteJson.getInt("id");
//                String downloadSiteTitle = downloadSiteJson.getString("title");
//                String downloadSiteUrl = downloadSiteJson.getString("url");
//
//                IFreeContent downloadSite =
//                        new DownloadSite(downloadSiteId, downloadSiteTitle, downloadSiteUrl);
//
//                downloadSites.add(downloadSite);
//            }
//
//            Collections.sort(downloadSites, new CategoryComparator());
//            videoDownloader = new VideoDownloader(id, title, downloadSites);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                jsonStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return videoDownloader;
//    }

    public PhotoEditor getFreePhotoEditor() {
        InputStream jsonStream = context.getResources().openRawResource(R.raw.free_photo_editor);
        PhotoEditor photoEditor = null;

        try {

            JSONObject jsonObject = new JSONObject(JsonUtil.streamToString(jsonStream));
            JSONArray editingToolsJson = jsonObject.getJSONArray("editing-tools");

            ArrayList<EditingTool> editingTools = new ArrayList<>();

            for (int x = 0; x < editingToolsJson.length(); x++) {

                JSONObject editingToolJson = editingToolsJson.getJSONObject(x);

                int id = editingToolJson.getInt("id");
                String title = editingToolJson.getString("title");
                String url = editingToolJson.getString("url");

                EditingTool editingTool = new EditingTool(id, title, url);
                editingTools.add(editingTool);
            }

            Collections.sort(editingTools, new EditingToolComparator());
            photoEditor = new PhotoEditor(editingTools);

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                jsonStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return photoEditor;
    }

    public BonusContent getFreeBonusContent() {

        InputStream jsonStream = context.getResources().openRawResource(R.raw.free_bonus_content);
        BonusContent bonusContent = null;

        try {

            JSONObject jsonObject = new JSONObject(JsonUtil.streamToString(jsonStream));
            JSONArray bonusFeaturesJson = jsonObject.getJSONArray("bonus-features");

            ArrayList<BonusFeature> bonusFeatures = new ArrayList<>();

            for (int x = 0; x < bonusFeaturesJson.length(); x++) {

                JSONObject bonusFeatureJson = bonusFeaturesJson.getJSONObject(x);

                int id = bonusFeatureJson.getInt("id");
                String title = bonusFeatureJson.getString("title");
                String url = bonusFeatureJson.getString("url");

                BonusFeature bonusFeature = new BonusFeature(id, title, url);
                bonusFeatures.add(bonusFeature);
            }

            Collections.sort(bonusFeatures, new BonusFeatureComparator());
            bonusContent = new BonusContent(bonusFeatures);

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                jsonStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bonusContent;
    }
}
