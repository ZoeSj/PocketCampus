package com.bzu.yhd.pocketcampus.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import com.bzu.yhd.pocketcampus.R;
import com.bzu.yhd.pocketcampus.util.PrefUtil;
import com.bzu.yhd.pocketcampus.view.fragment.FirstFragment;
import com.bzu.yhd.pocketcampus.view.fragment.MovieListFragment;
import com.bzu.yhd.pocketcampus.view.fragment.SecondFragment;
import com.bzu.yhd.pocketcampus.view.fragment.UserInfoFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.Calendar;
import org.polaric.colorful.Colorful;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 * </p>
 *
 * @CreateBy Yhd On 2017/2/15 8:40
 */
public class HomeActivity extends BaseActivity {
  private List<Fragment> fragments = new ArrayList<>();
  private int currentTabIndex;
  private int position;
  private BottomBarTab nearby;
  private String mAutoSwitchedHint;

  public static void navigation(Activity activity) {
    activity.startActivity(new Intent(activity, HomeActivity.class));
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    initFragment(savedInstanceState);
    initBottomBar(savedInstanceState);
    showFragment(fragments.get(0));
  }


  private void initFragment(Bundle savedInstanceState) {
    fragments.add(MovieListFragment.newInstance(0));
    fragments.add(FirstFragment.newInstance(2));
    fragments.add(SecondFragment.newInstance("ss","ss"));
    fragments.add(UserInfoFragment.newInstance("sss", "sss"));
  }

  private void initBottomBar(Bundle savedInstanceState) {
    if (savedInstanceState == null) checkAutoDayNightMode();
    final BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
    if (Colorful.getThemeDelegate().isNight()) {
      bottomBar.backgroundCrossfadeAnimation(Color.BLACK);
      bottomBar.setActiveTabColor(ContextCompat.getColor(HomeActivity.this,
          Colorful.getThemeDelegate().getAccentColor().getColorRes()));
    }

    bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
      @Override public void onTabSelected(@IdRes int tabId) {

        switch (tabId) {
          case R.id.bbn_item1:
            position = 0;
            break;
          case R.id.bbn_item2:
            position = 1;
            nearby.setBadgeCount(0);
            break;
          case R.id.bbn_item3:
            position = 2;
            break;
          case R.id.bbn_item4:
            position = 3;
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
      @Override public void onTabReSelected(@IdRes int tabId) {
        Toast.makeText(getApplicationContext(), "ssssreselet", Toast.LENGTH_SHORT).show();
      }
    });
    nearby = bottomBar.getTabWithId(R.id.bbn_item2);
    nearby.setBadgeCount(5);
  }

  private void showFragment(Fragment fragment) {

    getSupportFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();
  }

  /**
   * 检测是否自动日夜模式，如果是自动将根据时间判断是否切换
   */
  private void checkAutoDayNightMode() {
    boolean firstTime = PrefUtil.checkFirstTime(this);
    if (firstTime) PrefUtil.setNotFirstTime(HomeActivity.this);
    boolean auto = PrefUtil.isAutoDayNightMode(this);
    if (firstTime || !auto) return;

    int[] dayTime = PrefUtil.getDayNightModeStartTime(this, true);
    int[] nightTime = PrefUtil.getDayNightModeStartTime(this, false);
    Calendar cal = Calendar.getInstance();
    int h = cal.get(Calendar.HOUR_OF_DAY);
    int m = cal.get(Calendar.MINUTE);
    if (Colorful.getThemeDelegate().isNight()) {
      if ((dayTime[0] < h && h < nightTime[0]) || (dayTime[0] == h && dayTime[1] <= m)) {
        switchDayNightModeSmoothly(false, true);
      }
    } else {
      if ((nightTime[0] < h) || (nightTime[0] == h && nightTime[1] <= m)) {
        switchDayNightModeSmoothly(true, true);
      }
    }
  }

  private void switchDayNightModeSmoothly(final boolean isDark, boolean delay) {
    if (delay) {
      new Handler().postDelayed(new Runnable() {
        @Override public void run() {
          Colorful.config(HomeActivity.this).night(isDark).apply();
          getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
          mAutoSwitchedHint =
              "已自动切换为" + (isDark ? getString(R.string.night_mode) : getString(R.string.day_mode));
          showToast(mAutoSwitchedHint);
          recreate();
        }
      }, 1000);
    } else {
      Colorful.config(HomeActivity.this).night(isDark).apply();
      getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
      recreate();
    }
  }

  long mStartMills;

  @Override public void onBackPressed() {
    if (System.currentTimeMillis() - mStartMills > 1000) {
      showToast("再按一次，退出程序");
      mStartMills = System.currentTimeMillis();
    } else {
      super.onBackPressed();
    }
  }


  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString("hint", mAutoSwitchedHint);
  }
}
