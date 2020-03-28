package com.rhyme.r_gps;

import androidx.annotation.NonNull;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public enum RGpsPluginMethodHandler implements IRGpsPluginMethodHandler {
    isOpenGPS {
        @Override
        public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
            boolean isOpen = RGpsHelper.instance.isOpen();
            result.success(isOpen);
        }
    },
    openGPSSetting {
        @Override
        public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
            RGpsHelper.instance.openGPSSetting();
            result.success(null);
        }
    }

}
