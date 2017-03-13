package com.bzu.yhd.pocketcampus.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bzu.yhd.pocketcampus.R;
import com.bzu.yhd.pocketcampus.view.fragment.dummy.DummyContent;
import com.bzu.yhd.pocketcampus.view.fragment.dummy.DummyContent.DummyItem;


public class FirstFragment extends Fragment {

  // TODO: Customize parameter argument names
  private static final String ARG_COLUMN_COUNT = "column-count";
  // TODO: Customize parameters
  private int mColumnCount = 1;

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the
   * fragment (e.g. upon screen orientation changes).
   */
  public FirstFragment() {
  }

  // TODO: Customize parameter initialization
  @SuppressWarnings("unused") public static FirstFragment newInstance(int columnCount) {
    FirstFragment fragment = new FirstFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_COLUMN_COUNT, columnCount);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments() != null) {
      mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_item_list, container, false);

    // Set the adapter
    if (view instanceof RecyclerView) {
      Context context = view.getContext();
      RecyclerView recyclerView = (RecyclerView) view;
      if (mColumnCount <= 1) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
      } else {
        recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
      }
      recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS));
    }
    return view;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);

  }

  @Override public void onDetach() {
    super.onDetach();
  }


  public interface OnListFragmentInteractionListener {
    // TODO: Update argument type and name
    void onListFragmentInteraction(DummyItem item);
  }
}
