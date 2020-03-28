import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:r_gps/r_gps.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp>
    with WidgetsBindingObserver, RGPSMixin<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: <Widget>[
              FutureBuilder<bool>(
                future: RGps.isOpenGPS,
                builder: (BuildContext context, AsyncSnapshot snapshot) {
                  if (snapshot.hasData)
                    return FlatButton(
                        onPressed: () async {
                          Completer completer = Completer();
                          RGps.openGPSSetting();
                          streamSubscription = controller.stream.listen((_) {
                            completer.complete();
                            setState(() {});
                            streamSubscription.cancel();
                          });
                          return completer.future;
                        },
                        child: Text(
                            '当前GPS状态：${snapshot.data == true ? '打开' : '关闭'}'));
                  return Container();
                },
              ),
              SizedBox(
                height: 20,
              ),
            ],
          ),
        ),
      ),
    );
  }
}
