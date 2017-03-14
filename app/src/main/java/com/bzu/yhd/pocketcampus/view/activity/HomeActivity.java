package com.bzu.yhd.pocketcampus.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bzu.yhd.pocketcampus.R;
import com.bzu.yhd.pocketcampus.view.fragment.FirstFragment;
import com.bzu.yhd.pocketcampus.view.fragment.MovieListFragment;
import com.bzu.yhd.pocketcampus.view.fragment.SecondFragment;
import com.bzu.yhd.pocketcampus.view.fragment.UserInfoFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import org.polaric.colorful.Colorful;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 * @CreateBy Administrator
 * </p>
 * @CreateOn 2017/3/14 10:07
 */
public class HomeActivity extends BaseActivity {
    private List<Fragment> fragments = new ArrayList<>();
    private int currentTabIndex;
    private int position;
    private BottomBarTab nearby;

    public static void navigation(Activity activity) {
        activity.startActivity(new Intent(activity, HomeActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initAppBar(savedInstanceState);
        initFragment(savedInstanceState);
        initBottomBar(savedInstanceState);
        showFragment(fragments.get(0));
    }

    private void initAppBar(Bundle savedInstanceState) {
        if( null == savedInstanceState)
        {
            final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            final ViewGroup root = (ViewGroup) findViewById(R.id.drawer_layout);
            final CoordinatorLayout coordinatorLayout;
            if (root instanceof CoordinatorLayout) {
                coordinatorLayout = (CoordinatorLayout) root;
            } else {
                coordinatorLayout = null;
            }
            setSupportActionBar(toolbar);

            final int statusbarHeight = getStatusBarHeight();
            final boolean translucentStatus = hasTranslucentStatusBar();

            if (translucentStatus) {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) root.getLayoutParams();
                params.topMargin = -statusbarHeight;
                params = (ViewGroup.MarginLayoutParams) toolbar.getLayoutParams();
                params.topMargin = statusbarHeight;
            }
        }
    }

    private void initFragment(Bundle savedInstanceState)
    {
        fragments.add(MovieListFragment.newInstance(0));
        fragments.add(FirstFragment.newInstance(2));
        fragments.add(SecondFragment.newInstance("sss","sss"));
        fragments.add(UserInfoFragment.newInstance("sss","sss"));
    }

    private void initBottomBar(Bundle savedInstanceState)
    {
        final BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        if(Colorful.getThemeDelegate().isNight())
        {
            bottomBar.backgroundCrossfadeAnimation(Color.BLACK);
            bottomBar.setActiveTabColor(Color.RED);
        }

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                switch (tabId)
                {
                    case R.id.bbn_item1:
                        position=0;
                        break;
                    case R.id.bbn_item2:
                        position=1;
                        nearby.setBadgeCount(0);
                        break;
                    case R.id.bbn_item3:
                        position=2;
                        break;
                    case R.id.bbn_item4:
                        position=3;
                        break;
                }
                if (currentTabIndex != position) {
                    FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
                    trx.hide(fragments.get(currentTabIndex));
                    if (!fragments.get(position).isAdded()) {
                        trx.add(R.id.main_content, fragments.get(position));
                    }
                    trx.show(fragments.get(position)).commit();
                }
                currentTabIndex = position;
            }
        });
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId)
            {
                Toast.makeText(getApplicationContext(), "ssssreselet", Toast.LENGTH_SHORT).show();
            }
        });
        nearby = bottomBar.getTabWithId(R.id.bbn_item2);
        nearby.setBadgeCount(5);
    }


    private void showFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, fragment)
                .commit();
    }
}
