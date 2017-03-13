package com.bzu.yhd.pocketcampus.presenter;

import com.bzu.yhd.pocketcampus.view.fragment.MovieListFragment;

/**
 * <p/>
 * Created by woxingxiao on 2017-02-11.
 */
public interface IMovieFragmentPresenter {

    void register(MovieListFragment fragment);

    void loadMovieData(int releaseType);

    void unregister();
}
