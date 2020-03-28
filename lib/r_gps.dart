import 'dart:async';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class RGps {
  static const MethodChannel _channel = const MethodChannel('com.rhyme/r_gps');

  static Future<bool> get isOpenGPS async {
    assert(Platform.isAndroid, "not support ios");
    final bool isOpen = await _channel.invokeMethod('isOpenGPS');
    return isOpen;
  }

  static Future<void> openGPSSetting() async {
    assert(Platform.isAndroid, "not support ios");
    await _channel.invokeMethod('openGPSSetting');
  }
}

mixin RGPSMixin<T extends StatefulWidget> on State<T>, WidgetsBindingObserver {
  StreamController controller = StreamController.broadcast();
  StreamSubscription streamSubscription;

  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addObserver(this);
  }

  @override
  void dispose() {
    super.dispose();
    WidgetsBinding.instance.removeObserver(this);
  }

  @override
  void didChangeAppLifecycleState(AppLifecycleState state) async {
    super.didChangeAppLifecycleState(state);
    switch (state) {
      case AppLifecycleState.resumed:
        controller.add(await RGps.isOpenGPS);
        break;
      default:
        break;
    }
  }
}
