package com.example.henrique.list;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.content.Context;

/**
 * Created by Vitor on 21/02/2015.
 */
public class ResizeAnimation extends Animation {
    private int startWidth;
    private int targetWidth;
    private View view;

    public ResizeAnimation (View v, int targetWidth){
        this.view = v;
        this.targetWidth = targetWidth;
        startWidth = v.getWidth();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
 //       DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();

        int newWidth = (int) (targetWidth * interpolatedTime);
//        int newWidth = (int) ((targetWidth - startWidth) * interpolatedTime + startWidth);
//        int newWidthSp = (int) ((newWidth/displayMetrics.density)+0.5);
        view.getLayoutParams().width = newWidth;
        view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }

    public int getTargetWidth() {
        return targetWidth;
    }

    public void setTargetWidth(int targetWidth) {
        this.targetWidth = targetWidth;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
