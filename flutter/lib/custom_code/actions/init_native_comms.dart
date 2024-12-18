// Automatic FlutterFlow imports
import '/backend/schema/enums/enums.dart';
import 'package:ff_theme/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'index.dart'; // Imports other custom actions
import 'package:flutter/material.dart';
// Begin custom action code
// DO NOT REMOVE OR MODIFY THE CODE ABOVE!

Future initNativeComms() async {
  // Add your function code here!
  if (FFAppState().isWeb) {
    initJSInterop();
    return;
  }
  initNativeMethodChannel();
}
