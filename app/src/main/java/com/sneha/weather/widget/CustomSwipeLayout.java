package com.sneha.weather.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;

public class CustomSwipeLayout extends SwipeRefreshLayout {
    private OnChildScrollUpListener mScrollListenerNeeded;

    public interface OnChildScrollUpListener {
        boolean canChildScrollUp();
    }

    public CustomSwipeLayout(Context context) {
        super(context);
    }
    public CustomSwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Listener that controls if scrolling up is allowed to child views or not
     */
    public void setOnChildScrollUpListener(OnChildScrollUpListener listener) {
        mScrollListenerNeeded = listener;
    }

    @Override
    public boolean canChildScrollUp() {
        if (mScrollListenerNeeded == null) {
            Log.e(CustomSwipeLayout.class.getSimpleName(), "listener is not defined!");
        }
        return mScrollListenerNeeded != null && mScrollListenerNeeded.canChildScrollUp();
    }
}