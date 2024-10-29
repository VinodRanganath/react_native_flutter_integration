package com.nativefluttermodule;

import android.app.Activity;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReactApplicationContext;

import com.nativefluttermodule.NativeFlutterModuleSpec;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.MethodChannel;

import java.util.concurrent.TimeUnit;
import java.util.HashMap;

public class NativeFlutterModule extends NativeFlutterModuleSpec {
    private static final String CHANNEL = "flutter.integration";
    private final ReactApplicationContext reactContext;
    private MethodChannel methodChannel;
    private FlutterEngine flutterEngine;

    public static final String NAME = "NativeFlutterModule";

    public NativeFlutterModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void launch(String value, Callback callback) {
        try {
            HashMap<String, String> launchValue = new HashMap<>();
            launchValue.put("value", value);

            Activity currentActivity = reactContext.getCurrentActivity();
            currentActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    flutterEngine = new FlutterEngine(reactContext.getApplicationContext());
                    flutterEngine
                        .getDartExecutor()
                        .executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault());
                    FlutterEngineCache.getInstance().put(CHANNEL, flutterEngine);
                    currentActivity.startActivity(FlutterActivity.withCachedEngine(CHANNEL).build(currentActivity));

                    methodChannel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL);
                    methodChannel.invokeMethod("launch", launchValue); // Native to flutter
                    methodChannel.setMethodCallHandler((call, result) -> { // Flutter to native
                        if (call.method.equals("pingAndroid")) {
                            try {
                                TimeUnit.SECONDS.sleep(5);
                                System.out.println("flutter_integration: NativeFlutterModule: pingAndroid: " + call.arguments);
                                result.success(call.arguments);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        } else {
                            result.notImplemented();
                        }
                    });

                    callback.invoke("success");
                }
            });
        } catch (Exception e) {
            System.out.println("flutter_integration: NativeFlutterModule: launch: failed with error:");
            System.out.println(e.getMessage());
        }
    }
}
