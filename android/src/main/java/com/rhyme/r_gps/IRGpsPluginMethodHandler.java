package com.rhyme.r_gps;

import androidx.annotation.NonNull;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public interface IRGpsPluginMethodHandler {

    void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result);

}
