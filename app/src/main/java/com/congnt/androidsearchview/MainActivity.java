package com.congnt.androidsearchview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.miguelcatalan.materialsearchview.OnSearchViewFocusListener;
import com.miguelcatalan.materialsearchview.OnSearchViewListener;


public class MainActivity extends AppCompatActivity {

    private MaterialSearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        mSearchView = (MaterialSearchView) findViewById(R.id.search_view);
        mSearchView.setSearchViewType(false);
        mSearchView.setVoiceSearch(true); //or false
        mSearchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        mSearchView.setOnSearchViewListener(new OnSearchViewListener() {
            @Override
            public void onOpenSearchViewListener() {
                Log.d("AAaa", "AAAAAAA RUN HERERERERER");
            }

            @Override
            public void onQueryTextSubmit(String query) {
                Log.d("AAaa", "AAAAAAA RUN HERERERERER " + query);
            }

            @Override
            public void onQueryTextChange(String newText) {

            }
        });
        mSearchView.setHintTextStyle("Google Play", "Search on google play...",new OnSearchViewFocusListener() {
            @Override
            public void onSearchViewFocus(EditText view) {
            }

            @Override
            public void onSearchViewDefocus(EditText view) {
            }
        });

    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        MenuItem item = menu.findItem(R.id.action_search);
//        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                mSearchView.showSearch();
//                return true;
//            }
//        });
//
//        return true;
//    }

}
