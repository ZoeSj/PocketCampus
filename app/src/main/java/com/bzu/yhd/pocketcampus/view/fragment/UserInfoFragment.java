package com.bzu.yhd.pocketcampus.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bzu.yhd.pocketcampus.R;
import com.bzu.yhd.pocketcampus.util.PrefUtil;
import com.bzu.yhd.pocketcampus.view.activity.MainActivity;
import com.bzu.yhd.pocketcampus.view.activity.SettingsActivity;
import com.bzu.yhd.pocketcampus.view.activity.ThemeActivity;
import org.polaric.colorful.Colorful;

/**
 *
 * @CreateBy Administrator
 * </p>
 * @CreateOn 2017/3/14 13:32
 */
public class UserInfoFragment extends Fragment {

  private RelativeLayout rel_theme,rel_setting;
  private Intent intent;
  public UserInfoFragment() {
  }
  public static UserInfoFragment newInstance(String param1, String param2) {
    UserInfoFragment fragment = new UserInfoFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

  }
  @Override public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_user, container, false);
    rel_theme = (RelativeLayout) view.findViewById(R.id.layout_theme_change);
    rel_setting = (RelativeLayout) view.findViewById(R.id.layout_setting);
    rel_theme.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v)
      {
        if(v.getId() == R.id.layout_theme_change){
          intent = new Intent();
          intent.setClass(getActivity(),ThemeActivity.class);
          getActivity().startActivity(intent);
        }
      }
    });
    rel_setting.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if(v.getId() == R.id.layout_setting){
          intent = new Intent();
          intent.setClass(getActivity(),SettingsActivity.class);
          getActivity().startActivity(intent);
        }
      }
    });
    return view;
  }

}
