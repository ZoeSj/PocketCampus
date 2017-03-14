package com.bzu.yhd.pocketcampus;

import android.app.Application;

import com.bzu.yhd.pocketcampus.server.ApiHelper;
import com.bzu.yhd.pocketcampus.util.Logy;
import com.bzu.yhd.pocketcampus.util.SharedPrefHelper;

import org.polaric.colorful.Colorful;

/**
 * 
 * @CreateBy Administrator
 * </p>
 * @CreateOn 2017/3/14 9:57
 */
public class GMApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Colorful.defaults()
                .primaryColor(Colorful.ThemeColor.BLUE)
                .accentColor(Colorful.ThemeColor.DEEP_ORANGE)
                .translucent(false)
                .night(false);
        Colorful.init(this);

        SharedPrefHelper.init(this);
        Logy.init(true);
        ApiHelper.init("");
        //CrashHandler.getInstance().init(this);
    }
}
