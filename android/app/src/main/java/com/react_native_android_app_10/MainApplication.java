package com.react_native_android_app_10;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.soloader.SoLoader;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.izooto.FirebaseAnalyticsTrack;
import com.izooto.NotificationHelperListener;
import com.izooto.Payload;
import com.izooto.iZooto;

import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost =
      new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
          return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
          @SuppressWarnings("UnnecessaryLocalVariable")
          List<ReactPackage> packages = new PackageList(this).getPackages();
          // Packages that cannot be autolinked yet can be added manually here, for example:
          // packages.add(new MyReactNativePackage());
          return packages;
        }

        @Override
        protected String getJSMainModuleName() {
          return "index";
        }
      };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);
    initializeFlipper(this, getReactNativeHost().getReactInstanceManager());

      iZooto.initialize(this)
              .inAppNotificationBehaviour(iZooto.OSInAppDisplayOption.Notification) // to control how notification is displayed when app is in focus
              .setNotificationReceiveListener(new ExampleNotificationReceived())    // to call two methods when a notification is received and tapped
              .unsubscribeWhenNotificationsAreDisabled(true)   // unsubscribe when notifications are disabled
              .build();


      // set badge icon
      iZooto.setIcon(R.mipmap.badge_icon);

      // ability to choose how the notification will be displayed when the app is in focus
      iZooto.setInAppNotificationBehaviour(iZooto.OSInAppDisplayOption.InAppAlert);

      // set Firebase analytics
      iZooto.setFirebaseAnalytics(true);

      FirebaseAnalytics mFirebaseAnalytics;
      // Obtain the FirebaseAnalytics instance
      mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
  }

  /**
   * Loads Flipper in React Native templates. Call this in the onCreate method with something like
   * initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
   *
   * @param context
   * @param reactInstanceManager
   */
  private static void initializeFlipper(
      Context context, ReactInstanceManager reactInstanceManager) {
    if (BuildConfig.DEBUG) {
      try {
        /*
         We use reflection here to pick up the class that initializes Flipper,
        since Flipper library is not available in release mode
        */
        Class<?> aClass = Class.forName("com.react_native_android_app_10.ReactNativeFlipper");
        aClass
            .getMethod("initializeFlipper", Context.class, ReactInstanceManager.class)
            .invoke(null, context, reactInstanceManager);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }

    private class ExampleNotificationReceived implements NotificationHelperListener {
        @Override

        // fires when a notification is received
        public void onNotificationReceived(Payload payload) {
            Log.e("Payload", payload.getTitle());
        }

        @Override

        // fires when a notification is tapped
        public void onNotificationOpened(String data) {
            Log.e
        }
    }
}
