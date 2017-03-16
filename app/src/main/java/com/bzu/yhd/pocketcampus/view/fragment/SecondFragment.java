package com.bzu.yhd.pocketcampus.view.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bzu.yhd.pocketcampus.R;
import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

  public SecondFragment()
  {
  }

  private  TabLayout mTabLayout;
  private  ViewPager mViewPager;
  private FragmentPagerAdapter fAdapter;
  private List<Fragment> list_fragment;
  private List<String> list_title;
  private FirstFragment firstFragment;
  private  MovieListFragment movieListFragment;

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
    initControls(view);
    return view;
  }
  /**
   * 初始化各控件
   * @param view
   */
  private void initControls(View view)
  {
    mTabLayout= (TabLayout) view.findViewById(R.id.tab_layout);
    mViewPager= (ViewPager) view.findViewById(R.id.fragment_vp);
    firstFragment= new FirstFragment();
    movieListFragment = new MovieListFragment();

    list_fragment = new ArrayList<>();
    list_fragment.add(firstFragment);
    list_fragment.add(movieListFragment.newInstance(0));

    list_title = new ArrayList<>();
    list_title.add("校内中心");
    list_title.add("新鲜圈子");

    mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(0)));
    mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(1)));

    fAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(),
        list_fragment,list_title);
    mViewPager.setAdapter(fAdapter);
    mTabLayout.setupWithViewPager(mViewPager);
  }

}
