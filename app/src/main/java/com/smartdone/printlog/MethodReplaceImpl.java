package com.smartdone.printlog;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;

/**
 * Created by smartdone on 2017/4/17.
 */

public class MethodReplaceImpl extends XC_MethodReplacement {
    @Override
    protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
        String methodname = methodHookParam.method.getName();
        if(methodname.equals("v")){
            return Log.v((String) methodHookParam.args[0], (String) methodHookParam.args[1]);
        } else if(methodname.equals("i")){
            return Log.i((String) methodHookParam.args[0], (String) methodHookParam.args[1]);
        } else if(methodname.equals("d")){
            return Log.d((String) methodHookParam.args[0], (String) methodHookParam.args[1]);
        } else if(methodname.equals("w")){
            return Log.w((String) methodHookParam.args[0], (String) methodHookParam.args[1]);
        } else if(methodname.equals("e")){
            return Log.e((String) methodHookParam.args[0], (String) methodHookParam.args[1]);
        }
        return null;
    }
}
