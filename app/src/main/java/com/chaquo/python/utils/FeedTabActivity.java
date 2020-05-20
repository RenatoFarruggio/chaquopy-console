package com.chaquo.python.utils;

import android.os.Bundle;

import com.chaquo.python.console.R;
import com.chaquo.python.utils.ui.main.MyFeedTab;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.chaquo.python.utils.ui.main.SectionsPagerAdapter;

public class FeedTabActivity extends AppCompatActivity {
    //private RecyclerView myFeedTab;
    //private RecyclerView.Adapter mAdapter;
    //private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_tab);

        //myFeedTab = findViewById(R.id.my_feed_recycle_tab);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }
}