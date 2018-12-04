package com.pro.ahmed.football;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.pro.ahmed.football.ui.fragments.leaguefragments.FixturesFragment;
import com.pro.ahmed.football.ui.fragments.leaguefragments.NewsFragment;
import com.pro.ahmed.football.ui.fragments.leaguefragments.SoccerFragment;
import com.pro.ahmed.football.ui.fragments.leaguefragments.StandingsFragment;
;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeagueInfoActivity extends AppCompatActivity {

    @BindView(R.id.ivHome)
    ImageView ivHome;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    public static EditText etSearch;
    public static TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_info);
        ButterKnife.bind(this);
        etSearch = (EditText) findViewById(R.id.etSearch);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        //suppress the soft-keyboard until the user actually touches the editText View
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tabLayout.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        viewPager.setCurrentItem(0);
                        tvTitle.setText(R.string.standings);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        tvTitle.setText(R.string.fixture);
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        tvTitle.setText(R.string.soccer);
                        break;

                    case 3:
                        viewPager.setCurrentItem(3);
                        tvTitle.setText(R.string.news);
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new StandingsFragment(), "Standings");
        adapter.addFragment(new FixturesFragment(), "Fixtures");
        adapter.addFragment(new SoccerFragment(), "Soccer");
        adapter.addFragment(new NewsFragment(), "News");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
