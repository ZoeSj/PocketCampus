package com.bzu.yhd.pocketcampus.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.KeyEvent;

import com.bzu.yhd.pocketcampus.view.fragment.NightModeFragment;
import com.bzu.yhd.pocketcampus.R;
import com.bzu.yhd.pocketcampus.view.fragment.DayModeFragment;

import org.polaric.colorful.Colorful;

import java.util.List;

/**
 * 主题选择
 * </p>
 * @CreateBy Yhd On 2017/3/16 20:01
 */
public class ThemeActivity extends BaseActivity {

    public static void navigation(Activity activity) {
        activity.startActivity(new Intent(activity, ThemeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        transparentStatusBar();

        setContentView(R.layout.activity_theme);

        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (Colorful.getThemeDelegate().isNight()) {
                ft.add(R.id.container, NightModeFragment.newInstance(), NightModeFragment.TAG);
            } else {
                ft.add(R.id.container, DayModeFragment.newInstance(), DayModeFragment.TAG);
            }
            ft.commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void switchModeFragment(int cx, int cy, boolean appBarExpanded, String which) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment;
        switch (which) {
            case DayModeFragment.TAG:
                fragment = DayModeFragment.newInstance(cx, cy, appBarExpanded);
                break;
            case NightModeFragment.TAG:
                fragment = NightModeFragment.newInstance(cx, cy, appBarExpanded);
                break;
            default:
                throw new IllegalStateException();
        }
        ft.add(R.id.container, fragment, which).commit();
    }

    public void removeAllFragmentExcept(String tag) {
        List<Fragment> frags = getSupportFragmentManager().getFragments();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment frag;
        for (int i = 0; i < frags.size(); i++) {
            frag = frags.get(i);
            if (frag == null) {
                continue;
            }
            if (tag == null || !tag.equals(frag.getTag())) {
                ft.remove(frag);
            }
        }
        ft.commit();
    }

    /**
     * 监听Back键按下事件,方法1:
     * 注意:
     * super.onBackPressed()会自动调用finish()方法,关闭
     * 当前Activity.
     * 若要屏蔽Back键盘,注释该行代码即可
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent i=new Intent();
        i.setClass(ThemeActivity.this,HomeActivity.class);
        startActivity(i);
    }
}
