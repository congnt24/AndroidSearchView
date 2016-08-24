package com.miguelcatalan.materialsearchview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.utils.DimensionUtil;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Congnt24
 */
public class FloatingSearchView extends BaseSearchView implements Filter.FilterListener {

    private String hintTextWhenDefocus;
    private String hintTextWhenFocus;
    //Views
    private OnSearchViewListener mOnSearchViewListener;
    private OnSearchViewFocusListener mOnSearchViewFocusListener;


    @Override
    protected void initialize() {
        mSearchLayout.setVisibility(VISIBLE);
        switchToSearchOrBackMode(SEARCH);
        //Change height of Search view
        mSearchTopBar.setLayoutParams(new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, (int) DimensionUtil.dpToPx(mContext, 48)));
        //Change to rounded background
        mSearchTopBar.setBackgroundResource(R.drawable.bg_rounded_layout);
    }

    @Override
    protected void onBackClickListener() {
        if (isBackPressed) {
            switchToSearchOrBackMode(BACK);
            mOnSearchViewListener.onOpenSearchViewListener();
        } else {
            switchToSearchOrBackMode(SEARCH);
        }
    }

    @Override
    protected void onTintClickListener() {
        switchToSearchOrBackMode(SEARCH);
    }

    @Override
    protected void onSearchViewFocusListener(boolean hasFocus) {
        if (hasFocus) {
            showKeyboard(mSearchSrcTextView);
            switchToSearchOrBackMode(BACK);
            if (mOnSearchViewFocusListener != null) {
                mSearchSrcTextView.setHint(hintTextWhenFocus);
                mSearchSrcTextView.setTextSize(16);
                mOnSearchViewFocusListener.onSearchViewFocus(mSearchSrcTextView);
            }
            mOnSearchViewListener.onOpenSearchViewListener();
        } else {
            if (mOnSearchViewFocusListener != null) {
                mSearchSrcTextView.setHint(hintTextWhenDefocus);
                mSearchSrcTextView.setTextSize(22);
                mOnSearchViewFocusListener.onSearchViewDefocus(mSearchSrcTextView);
            }
        }
    }

    @Override
    protected void onQueryTextChange(String text) {

    }

    @Override
    protected void onQueryTextSubmit(String text) {
        switchToSearchOrBackMode(SEARCH);
    }

    public FloatingSearchView(Context context) {
        this(context, null);
    }

    public FloatingSearchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatingSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnSearchViewListener(OnSearchViewListener mOnSearchViewListener) {
        this.mOnSearchViewListener = mOnSearchViewListener;
    }

    public void setHintTextStyle(String hintTextWhenDefocus, String hintTextWhenFocus, OnSearchViewFocusListener onSearchViewFocusListener) {
        mSearchSrcTextView.setHint(hintTextWhenDefocus);
        this.hintTextWhenDefocus = hintTextWhenDefocus;
        this.hintTextWhenFocus = hintTextWhenFocus;
        this.mOnSearchViewFocusListener = onSearchViewFocusListener;
    }

    //Util Method

    protected void switchToSearchOrBackMode(int searchOrBack) {
        switch (searchOrBack) {
            case BACK:  //Open search view
                setBackIcon(BACK);
                mSearchSrcTextView.requestFocus();
                isBackPressed = false;
                //Show tintcolor
                mTintView.setVisibility(VISIBLE);
                break;
            case SEARCH:
                setBackIcon(SEARCH);
                clearFocus();
                mSearchSrcTextView.setText(null);
                isBackPressed = true;
                mTintView.setVisibility(GONE);
                break;
        }
    }

}