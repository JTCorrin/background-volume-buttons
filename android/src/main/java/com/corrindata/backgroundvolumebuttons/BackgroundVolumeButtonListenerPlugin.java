package com.corrindata.backgroundvolumebuttons;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;
import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "BackgroundVolumeButtonListener")
public class BackgroundVolumeButtonListenerPlugin extends Plugin {

    private BackgroundVolumeButtonListener implementation = new BackgroundVolumeButtonListener();

    private static final String TAG = "BackgroundVolumeButtonListener";
    private int buttonPressCount = 0;
    private BroadcastReceiver receiver;
    private Handler resetHandler;
    private Runnable resetRunnable;

    @PluginMethod
    public void startListening(PluginCall call) {
        if (!call.getData().has("listenerName")) {
            call.reject("Must provide an listener name");
            return;
        }
        var triggerCount = call.getInt("triggerCount", 3);
        var clickTimeout = call.getInt("timeout", 1000);
        var bringToForeground = call.getBool("bringToForeground", true);
        var listenerName = call.getString("listenerName");
        receiver =
            new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")) {
                        buttonPressCount++;
                        resetHandler.removeCallbacks(resetRunnable);
                        resetHandler.postDelayed(resetRunnable, clickTimeout);

                        if (buttonPressCount == triggerCount) {
                            buttonPressCount = 0;
                            JSObject ret = new JSObject();
                            notifyListeners(listenerName, ret);
                            if (bringToForeground) {
                                Intent bringToForegroundIntent = new Intent(context, RootActivity.class);
                                bringToForegroundIntent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(bringToForegroundIntent);
                            }
                        }
                    }
                }
            };

        resetHandler = new Handler();
        resetRunnable =
            new Runnable() {
                @Override
                public void run() {
                    buttonPressCount = 0;
                }
            };

        IntentFilter filter = new IntentFilter("android.media.VOLUME_CHANGED_ACTION");
        getContext().registerReceiver(receiver, filter);

        call.resolve();
    }

    @PluginMethod
    public void stopListening(PluginCall call) {
        getContext().unregisterReceiver(receiver);
        buttonPressCount = 0;

        call.resolve();
    }
}
