package com.example.henrique.list;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Vitor on 21/02/2015.
 */
public class ResizeAnimation extends Animation {
    int startWidth;
    int targetWidth;
    View view;

    public ResizeAnimation (View v, int targetWidth){
        this.view = v;
        this.targetWidth = targetWidth;
        startWidth = v.getWidth();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newWidth = (int) (targetWidth * interpolatedTime);
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
}
