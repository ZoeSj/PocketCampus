package com.bzu.yhd.pocketcampus.view.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.FrameLayout;
import butterknife.BindView;
import com.bzu.yhd.pocketcampus.R;
import com.bzu.yhd.pocketcampus.view.fragment.base.BaseFragment;

public class SecondFragment extends BaseFragment {
  @BindView(R.id.app_bar)
  AppBarLayout mAppBar;
  @BindView(R.id.tab_layout)
  TabLayout mTabLayout;
  @BindView(R.id.view_pager)
  FrameLayout frameLayout;
  public SecondFragment() {
  }

  public static SecondFragment newInstance(String param1, String param2) {
    SecondFragment fragment = new SecondFragment();
    Bundle args = new Bundle();

    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null)
    {

    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_second, container, false);

    setHasOptionsMenu(true);
    Toolbar toolbar= initToolbar(R.id.tab_layout,1);

    return view;
  }

 }
