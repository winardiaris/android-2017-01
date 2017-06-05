package xyz.winardiaris.android.mypocket;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by ars on 6/4/17.
 */

public class MyPocket extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(this);

        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );
        Stetho.initialize(initializerBuilder.build());
    }
}
