package com.smartdone.printlog;

import android.app.Application;
import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by smartdone on 2017/4/5.
 */

public class Main implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.equals("test.packer.naja.com.demo")) {
            XposedHelpers.findAndHookMethod("com.shell.SuperApplication", loadPackageParam.classLoader, "attachBaseContext", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Context context = (Context) param.args[0];
                    Context plicontext = context.createPackageContext("com.smartdone.printlog", Context.CONTEXT_IGNORE_SECURITY);
                    InputStream in = plicontext.getAssets().open("libslog.so");
                    File so = new File(context.getFilesDir(), "libslog.so");
                    if (!so.getParentFile().exists()) {
                        so.getParentFile().mkdirs();
                    }
                    FileOutputStream fout = new FileOutputStream(so);
                    byte[] buffer = new byte[1024];
                    int len = in.read(buffer);
                    while (len > 0) {
                        fout.write(buffer);
                        len = in.read(buffer);
                    }
                    fout.flush();
                    fout.close();
                    in.close();
                    android.util.Log.e("LOGTEST", "write so to /data/data/... success");
                    System.load(so.getAbsolutePath());
                    Log.i("LOGTEST", "this is a test");
                    Log.v("LOGTEST", "this is a test");
                    Log.d("LOGTEST", "this is a test");
                    Log.w("LOGTEST", "this is a test");
                    Log.e("LOGTEST", "this is a test");

                    android.util.Log.i("LOGTEST", "this is a test from origin log");
                    android.util.Log.v("LOGTEST", "this is a test from origin log");
                    android.util.Log.d("LOGTEST", "this is a test from origin log");
                    android.util.Log.w("LOGTEST", "this is a test from origin log");
                    android.util.Log.e("LOGTEST", "this is a test from origin log");
                }
            });
        }
    }
}
