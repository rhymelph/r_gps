package com.rhyme.r_gps;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * RGpsPlugin
 */
public class RGpsPlugin implements FlutterPlugin, MethodCallHandler {
    private static final String TAG = "RGpsPlugin";

    public RGpsPlugin() {

    }

    private RGpsPlugin(Context context) {
        RGpsHelper.initial(context);
    }

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        final MethodChannel channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "com.rhyme/r_gps");
        channel.setMethodCallHandler(new RGpsPlugin(flutterPluginBinding.getApplicationContext()));
    }

    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "com.rhyme/r_gps");
        channel.setMethodCallHandler(new RGpsPlugin(registrar.context()));
    }


    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        try {
            RGpsPluginMethodHandler handler = RGpsPluginMethodHandler.valueOf(call.method);
            handler.onMethodCall(call, result);
        } catch (Exception e) {
            e.printStackTrace();
            result.notImplemented();

        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    }
}
