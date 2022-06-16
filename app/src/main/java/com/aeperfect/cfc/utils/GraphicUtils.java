package com.aeperfect.cfc.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.aeperfect.cfc.R;

import java.util.HashMap;

public class GraphicUtils {

    private static HashMap<Integer, Drawable> logos;
    private static HashMap<Integer, Drawable> socialLogos;
    private static HashMap<Integer, Drawable> toolLogos;
    private static HashMap<Integer, Drawable> backgrounds;

    @SuppressLint("UseCompatLoadingForDrawables")
    public static void initLogosMap(Context context) {

        logos = new HashMap<>();
        logos.put(1, context.getDrawable(R.drawable.ic_social_downloader));
        logos.put(2, context.getDrawable(R.drawable.ic_video_free));
        logos.put(3, context.getDrawable(R.drawable.ic_image_free));
        logos.put(4, context.getDrawable(R.drawable.ic_music));
        logos.put(5, context.getDrawable(R.drawable.ic_fonts));
        logos.put(6, context.getDrawable(R.drawable.ic_icons));
        logos.put(7, context.getDrawable(R.drawable.ic_image_paid));
        logos.put(8, context.getDrawable(R.drawable.ic_video_paid));

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public static void initBackgroundsMap(Context context) {

        backgrounds = new HashMap<>();
        backgrounds.put(1, context.getDrawable(R.drawable.bg_social_downloader));
        backgrounds.put(2, context.getDrawable(R.drawable.bg_video_free));
        backgrounds.put(3, context.getDrawable(R.drawable.bg_image_free));
        backgrounds.put(4, context.getDrawable(R.drawable.bg_music));
        backgrounds.put(5, context.getDrawable(R.drawable.bg_font));
        backgrounds.put(6, context.getDrawable(R.drawable.bg_icon));
        backgrounds.put(7, context.getDrawable(R.drawable.bg_image_paid));
        backgrounds.put(8, context.getDrawable(R.drawable.bg_video_paid));

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public static void initSocialLogosMap(Context context) {

        socialLogos = new HashMap<>();
        socialLogos.put(1, context.getDrawable(R.drawable.ic_facebook));
        socialLogos.put(2, context.getDrawable(R.drawable.ic_instagram));
        socialLogos.put(3, context.getDrawable(R.drawable.ic_tiktok));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public static void initToolLogosMap(Context context) {

        toolLogos = new HashMap<>();
        toolLogos.put(1, context.getDrawable(R.drawable.ic_photo_editor));
        toolLogos.put(2, context.getDrawable(R.drawable.ic_compress_image));
        toolLogos.put(3, context.getDrawable(R.drawable.ic_resize_image));
        toolLogos.put(4, context.getDrawable(R.drawable.ic_crop_image));
        toolLogos.put(5, context.getDrawable(R.drawable.ic_convert_to_jpg));
        toolLogos.put(6, context.getDrawable(R.drawable.ic_remove_bg));
        toolLogos.put(7, context.getDrawable(R.drawable.ic_rotate_image));
        toolLogos.put(8, context.getDrawable(R.drawable.ic_watermark));
        toolLogos.put(9, context.getDrawable(R.drawable.ic_meme));
        toolLogos.put(10, context.getDrawable(R.drawable.ic_html_to_image));
    }

    public static Drawable getLogo(int id) {

        return logos.get(id);
    }

    public static Drawable getBackground(int id) {

        return backgrounds.get(id);
    }

    public static Drawable getSocialLogo(int id) {

        return socialLogos.get(id);
    }

    public static Drawable getToolLogo(int id) {

        return toolLogos.get(id);
    }
}
