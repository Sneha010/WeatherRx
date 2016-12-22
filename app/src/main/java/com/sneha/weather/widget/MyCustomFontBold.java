package com.sneha.weather.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.sneha.weather.utils.FontCache;


public class MyCustomFontBold extends TextView {
	private static Typeface defaultTypeface;

	public MyCustomFontBold(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		// TODO Auto-generated constructor stub
	}

	public MyCustomFontBold(Context context) {
		super(context);
		init();
		// TODO Auto-generated constructor stub
	}

	public MyCustomFontBold(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
		// TODO Auto-generated constructor stub
	}

	private void init() {
		if (!isInEditMode()) {
			setTypeface(getDefaultTypeface());

		}
	}

	public  Typeface getDefaultTypeface() {
		if (defaultTypeface == null)

			defaultTypeface = FontCache.getTypeface(getContext(), "fonts/Myriad_pro_bold.ttf");

		return defaultTypeface;
	}
}
