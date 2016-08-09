package com.jade.library.utils;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;

/**
 * Created by cyxod on 2016/6/20.
 */
public class ShapeUtils {
    public static GradientDrawable getShape() {
        int strokeWidth = 5; // 3px not dp
        int strokeColor = Color.parseColor("#2E3135");
        int fillColor = Color.parseColor("#F00FF0");

        GradientDrawable drawable = new GradientDrawable();
        float[] radii = new float[]{16.0f, 16.0f, 16.0f, 16.0f, 0f, 0f, 0f, 0f};
        drawable.setCornerRadii(radii);
        drawable.setColor(fillColor);
//        drawable.setStroke(strokeWidth, strokeColor);
        return drawable;
    }

    public static GradientDrawable getCornerShape(float radius, String fillColor) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(radius);
        drawable.setColor(Color.parseColor(fillColor));
        return drawable;
    }

    public static GradientDrawable getCornerShape(float radius, int borderWidth, String borderColor, String fillColor) {
        if (TextUtils.isEmpty(borderColor)) {
            borderColor = "#00000000";
        }
        if (TextUtils.isEmpty(fillColor)) {
            fillColor = "#00000000";
        }
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(radius);
        drawable.setColor(Utils.getColorByString(fillColor));
        drawable.setStroke(borderWidth, Utils.getColorByString(borderColor));
        return drawable;
    }

    public static GradientDrawable getCornerShape(float lRadius, float tRadius, float rRadius, float bRadius, int borderWidth, String borderColor, String fillColor) {
        GradientDrawable drawable = new GradientDrawable();
//        top-left, top-right, bottom-right, bottom-left
        if (TextUtils.isEmpty(borderColor)) {
            borderColor = "#00000000";
        }
        if (TextUtils.isEmpty(fillColor)) {
            fillColor = "#00000000";
        }
        float[] radii = {tRadius, lRadius, tRadius, rRadius, bRadius, rRadius, bRadius, lRadius};
        drawable.setCornerRadii(radii);
        drawable.setColor(Color.parseColor(fillColor));
        drawable.setStroke(borderWidth, Color.parseColor(borderColor));
        return drawable;
    }

    public static GradientDrawable getCornerShape(float[] radii, int borderWidth, String borderColor, String fillColor) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadii(radii);
        if (TextUtils.isEmpty(borderColor)) {
            borderColor = "#00000000";
        }
        if (TextUtils.isEmpty(fillColor)) {
            fillColor = "#00000000";
        }
        drawable.setColor(Utils.getColorByString(fillColor));
        drawable.setStroke(borderWidth, Utils.getColorByString(borderColor));
        return drawable;
    }

}
