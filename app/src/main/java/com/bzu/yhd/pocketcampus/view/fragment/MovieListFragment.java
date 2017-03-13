package com.bzu.yhd.pocketcampus.view.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bzu.yhd.pocketcampus.model.MovieModel;
import com.bzu.yhd.pocketcampus.presenter.impl.MovieFragmentPresenterImpl;
import com.bzu.yhd.pocketcampus.view.adapter.MovieListAdapter;
import com.bzu.yhd.pocketcampus.view.iview.IMovieListFragment;
import com.bzu.yhd.pocketcampus.R;
import com.bzu.yhd.pocketcampus.presenter.IMovieFragmentPresenter;

import org.polaric.colorful.Colorful;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 影片列表Fragment
 * <p/>
 * Created by McQueen on 2017-01-23.
 */
public class MovieListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        IMovieListFragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private MovieListAdapter mAdapter;
    private IMovieFragmentPresenter mPresenter;
    private int mReleaseType;

    public static MovieListFragment newInstance(int type) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new MovieFragmentPresenterImpl();
        mPresenter.register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, view);

        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getContext(), Colorful.getThemeDelegate().getAccentColor().getColorRes()),
                ContextCompat.getColor(getContext(), Colorful.getThemeDelegate().getPrimaryColor().getColorRes())
        );
        mSwipeRefreshLayout.setProgressViewEndTarget(false, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 80, Resources.getSystem().getDisplayMetrics()));
        mSwipeRefreshLayout.setRefreshing(savedInstanceState == null);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MovieListAdapter();
        mAdapter.setShowAnim(savedInstanceState == null);
        mAdapter.setLoading(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(null);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mReleaseType = getArguments().getInt("type");
        mPresenter.loadMovieData(mReleaseType);
    }

    @Override
    public void onRefresh() {
        mPresenter.loadMovieData(mReleaseType);
        mAdapter.setLoading(true);
    }

    @Override
    public void onDataReady(List<MovieModel> movieModels) {
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setData(movieModels);
    }

    @Override
    public void onDataError(int code, String msg) {
        if (code == 209405) { // "查询不到热映电影相关信息"，以上一级城市名进行查询
            if (mReleaseType == 0) {
                Intent intent = new Intent(getString(R.string.action_locate_succeed));
                intent.putExtra(getString(R.string.param_is_upper_city), true);
                getContext().sendBroadcast(intent);
            }

            mSwipeRefreshLayout.setRefreshing(false);
            mAdapter.clearData();
            mAdapter.setLoading(false);

            return;
        }

        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.clearData();
        mAdapter.setLoading(false);
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void scrollToTop() {
        mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mPresenter.unregister();
        mPresenter = null;
    }

}
