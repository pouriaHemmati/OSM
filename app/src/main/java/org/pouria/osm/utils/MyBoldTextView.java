package org.pouria.osm.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class MyBoldTextView extends AppCompatTextView {


    public MyBoldTextView(Context context) {
        super(context);
        customize();
    }

    public MyBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        customize();
    }

    public MyBoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        customize();
    }

    private void customize() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "font/IRANSansMobile(FaNum)_Bold.ttf");
        this.setTypeface(typeface);

    }

}