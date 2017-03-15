package com.bzu.yhd.pocketcampus.view.fragment.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.bzu.yhd.pocketcampus.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import org.polaric.colorful.ColorfulActivity;
/**
 * BaseFragment
 * </p>
 * @CreateBy Yhd On 2017/3/15 10:52
 */
       public class BaseFragment extends Fragment {
        private SystemBarTintManager mSystemBarTint;
        private boolean mTranslucentStatus;
        private boolean mTranslucentStatusSet;
        Activity mActivity;
        AppCompatActivity mAppCompatActivity;

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            mActivity = getActivity();
            setHasOptionsMenu(true);
        }


        static {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }

        /**
         * 检测系统版本并使状态栏全透明
         */
        protected void transparentStatusBar() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = mActivity.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }
        }
        public int getNavigationBarHeight() {
            return getSystemBarTint().getConfig().getNavigationBarHeight();
        }

        public SystemBarTintManager getSystemBarTint() {
            if (null == mSystemBarTint) {
                mSystemBarTint = new SystemBarTintManager(mActivity);
            }
            return mSystemBarTint;
        }

        public int getStatusBarHeight() {
            return getSystemBarTint().getConfig().getStatusBarHeight();
        }

        /**
         * 初始化Toolbar的功能
         */
        protected void initializeToolbar() {
            Toolbar toolbar = (Toolbar) mAppCompatActivity.findViewById(R.id.toolbar);
            mAppCompatActivity.setSupportActionBar(toolbar);
            if (mAppCompatActivity.getSupportActionBar() != null) {
                mAppCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

        public Toolbar initToolbar(int toolbarId, int title) {
            AppCompatActivity mAppCompatActivity = (AppCompatActivity) mActivity;
            Toolbar toolbar = (Toolbar) mAppCompatActivity.findViewById(toolbarId);
            mAppCompatActivity.setSupportActionBar(toolbar);
            ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(false);
                actionBar.setDisplayShowTitleEnabled(false);
            }
            return toolbar;
        }
        public Toolbar initToolbar(CharSequence title) {
            mAppCompatActivity = (AppCompatActivity) mActivity;
            Toolbar toolbar = (Toolbar) mAppCompatActivity.findViewById(R.id.toolbar);
            mAppCompatActivity.setSupportActionBar(toolbar);
            ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(false);
            }
            return toolbar;
        }

    @TargetApi(19)
        public boolean hasTranslucentStatusBar() {
            if (!mTranslucentStatusSet) {
                if (Build.VERSION.SDK_INT >= 19) {
                    mTranslucentStatus =
                        ((mActivity.getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                            == WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                } else {
                    mTranslucentStatus = false;
                }
                mTranslucentStatusSet = true;
            }
            return mTranslucentStatus;
        }

        protected void showToast(String msg) {
            Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
        }
    }
