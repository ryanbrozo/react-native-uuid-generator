package com.reactlibrary;

import android.util.Base64;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.nio.ByteBuffer;
import java.util.UUID;

public class RNUUIDGeneratorModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNUUIDGeneratorModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNUUIDGenerator";
  }

  @ReactMethod
  public void getRandomUUID(Callback callback) {
    String uuid = UUID.randomUUID().toString();

    callback.invoke(uuid);
  }
  
  @ReactMethod
  public void getRandomBase64UUID(Boolean urlSafe, Callback callback) {
    UUID uuid = UUID.randomUUID();
    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
    bb.putLong(uuid.getMostSignificantBits());
    bb.putLong(uuid.getLeastSignificantBits());

    int flag = 0;
    if (urlSafe) {
      flag = Base64.URL_SAFE | Base64.NO_WRAP | Base64.NO_PADDING;
    }
    else {
      flag = Base64.NO_WRAP | Base64.NO_PADDING;
    }
    callback.invoke(Base64.encodeToString(bb.array(), flag));

  }
}
