package com.bzu.yhd.pocketcampus;

import android.app.Application;

import com.bzu.yhd.pocketcampus.server.ApiHelper;
import com.bzu.yhd.pocketcampus.util.Logy;
import com.bzu.yhd.pocketcampus.util.SharedPrefHelper;

import org.polaric.colorful.Colorful;

/**
 * <p/>
 * Created by woxingxiao on 2017-01-25.
 */
public class GMApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Colorful.defaults()
                .primaryColor(Colorful.ThemeColor.DARK)
                .accentColor(Colorful.ThemeColor.DEEP_ORANGE)
                .translucent(false)
                .night(false);
        Colorful.init(this);

        SharedPrefHelper.init(this);
        Logy.init(true);
        ApiHelper.init(""); // TODO: 2017-02-24 add your api key to request data
//        CrashHandler.getInstance().init(this);
    }
}
