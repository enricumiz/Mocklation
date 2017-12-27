package de.p72b.mocklation.dagger;


import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;

import de.p72b.mocklation.service.AppServices;
import de.p72b.mocklation.service.analytics.IAnalyticsService;


public final class MocklationApp extends Application {
    @SuppressLint("StaticFieldLeak")
    private static MocklationApp sInstance;
    private MocklationComponent mainComponent;

    @Override public void onCreate() {
        super.onCreate();
        sInstance = this;

        mainComponent = DaggerMocklationComponent.builder().mocklationModule(new MocklationModule(this)).build();
        ((IAnalyticsService) AppServices.getService(AppServices.ANALYTICS)).trackEvent(FirebaseAnalytics.Event.APP_OPEN);
    }

    public static MocklationApp getInstance() {
        return sInstance;
    }

    public static MocklationComponent getComponent(Context context) {
        return ((MocklationApp) context.getApplicationContext()).mainComponent;
    }
}
