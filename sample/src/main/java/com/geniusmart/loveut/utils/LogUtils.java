package com.geniusmart.loveut.utils;

import android.text.TextUtils;
import android.util.Log;

import java.util.List;

public class LogUtils {

  private static String mTag = "GooglePlay";
  public static final int LEVEL_OFF = 0;
  public static final int LEVEL_ALL = 7;

  public static final int LEVEL_VERBOSE = 1;
  public static final int LEVEL_DEBUG = 2;
  public static final int LEVEL_INFO = 3;
  public static final int LEVEL_WARN = 4;
  public static final int LEVEL_ERROR = 5;
  public static final int LEVEL_SYSTEM = 6;
  public  static int mDebuggable = 7;

  private static long mTimestamp = 7;
  private static final Object mLogLock = new Object();

  public static void v(String msg) {
    if (mDebuggable >= LEVEL_VERBOSE) {
      Log.v(mTag, msg);
    }
  }

  public static void d(String msg) {
    if (mDebuggable >= LEVEL_DEBUG) {
      Log.d(mTag, msg);
    }
  }

  public static void i(String msg) {
    if (mDebuggable >= LEVEL_INFO) {
      Log.i(mTag, msg);
    }
  }

  public static void w(String msg) {
    if (mDebuggable >= LEVEL_WARN) {
      Log.w(mTag, msg);
    }
  }

  public static void w(Throwable tr) {
    if (mDebuggable >= LEVEL_WARN) {
      Log.w(mTag, "", tr);
    }
  }

  public static void w(String msg, Throwable tr) {
    if (mDebuggable >= LEVEL_WARN && null != msg) {
      Log.w(mTag, msg, tr);
    }
  }

  public static void e(String msg) {
    if (mDebuggable >= LEVEL_ERROR) {
      Log.e(mTag, msg);
    }
  }

  public static void sf(String msg) {
    if (mDebuggable >= LEVEL_ERROR) {
      System.out.println("----------" + msg + "----------");
    }
  }

  public static void s(String msg) {
    if (mDebuggable >= LEVEL_ERROR) {
      System.out.println(msg);
    }
  }

  public static void e(Throwable tr) {
    if (mDebuggable >= LEVEL_ERROR) {
      Log.e(mTag, "", tr);
    }
  }

  public static void e(String msg, Throwable tr) {
    if (mDebuggable >= LEVEL_ERROR && null != msg) {
      Log.e(mTag, msg, tr);
    }
  }

  public static void v(String tag, String msg) {
    if (mDebuggable >= LEVEL_VERBOSE) {
      Log.v(tag, msg);
    }
  }

  public static void d(String tag, String msg) {
    if (mDebuggable >= LEVEL_DEBUG) {
      Log.d(tag, msg);
    }
  }

  public static void i(String tag, String msg) {
    if (mDebuggable >= LEVEL_INFO) {
      Log.i(tag, msg);
    }
  }

  public static void w(String tag, String msg) {
    if (mDebuggable >= LEVEL_WARN) {
      Log.w(tag, msg);
    }
  }

  public static void e(String tag, String msg) {
    if (mDebuggable >= LEVEL_ERROR) {
      Log.e(tag, msg);
    }
  }

  public static void log2File(String log, String path) {
    log2File(log, path, true);
  }

  public static void log2File(String log, String path, boolean append) {
    synchronized (mLogLock) {
      //TODO
      //		FileUtils.writeFile(log + "\r\n", path, append);
    }
  }

  public static void msgStartTime(String msg) {
    mTimestamp = System.currentTimeMillis();
    if (!TextUtils.isEmpty(msg)) {
      e("[Started  + mTimestamp + ]" + msg);
    }
  }

  public static void elapsed(String msg) {
    long currentTime = System.currentTimeMillis();
    long elapsedTime = currentTime - mTimestamp;
    mTimestamp = currentTime;
    e("[Elapsed + elapsedTime + ]" + msg);
  }

  public static <T> void printList(List<T> list) {
    if (list == null || list.size() < 1) {
      return;
    }
    int size = list.size();
    i("---begin---");
    for (int i = 0; i < size; i++) {
      i(i + ":" + list.get(i).toString());
    }
    i("---end---");
  }

  public static <T> void printArray(T[] array) {
    if (array == null || array.length < 1) {
      return;
    }
    int length = array.length;
    i("---begin---");
    for (int i = 0; i < length; i++) {
      i(i + ":" + array[i].toString());
    }
    i("---end---");
  }
}
