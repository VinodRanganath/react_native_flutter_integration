package com.nativefluttermodule;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;

import java.util.HashMap;
import java.util.Map;

public class NativeFlutterPackage extends TurboReactPackage {

  @Override
  public NativeModule getModule(String name, ReactApplicationContext reactContext) {
    if (name.equals(NativeFlutterModule.NAME)) {
      return new NativeFlutterModule(reactContext);
    } else {
      return null;
    }
  }

  @Override
  public ReactModuleInfoProvider getReactModuleInfoProvider() {
    return () -> {
        Map<String, ReactModuleInfo> map = new HashMap<>();
        map.put(NativeFlutterModule.NAME, new ReactModuleInfo(
          NativeFlutterModule.NAME, // name
          NativeFlutterModule.NAME, // className
          false, // canOverrideExistingModule
          false, // needsEagerInit
          false, // isCXXModule
          true // isTurboModule
        ));
        return map;
    };
  }
}