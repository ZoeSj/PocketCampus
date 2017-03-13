package com.bzu.yhd.pocketcampus.view.iview;

import android.content.Context;

import com.bzu.yhd.pocketcampus.model.MovieModel;

import java.util.List;

/**
 * <p/>
 * Created by woxingxiao on 2017-02-11.
 */
public interface IMovieListFragment {

    Context getContext();

    void onDataReady(List<MovieModel> movieModels);

    void onDataError(int code, String msg);
}
