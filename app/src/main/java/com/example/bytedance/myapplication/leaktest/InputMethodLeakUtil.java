package com.example.bytedance.myapplication.leaktest;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import java.lang.reflect.Field;

/**
 * Created by xiaoxiaoyu on 2020-05-12.
 */
public class InputMethodLeakUtil {
    public static void fixInputMethodManagerLeak(Context context) {
        if (context == null) {
            return;
        }
        try {
            InputMethodManager imm =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm == null) {
                return;
            }
            Field f_mLastSrvView = imm.getClass().getDeclaredField("mLastSrvView");
            if (f_mLastSrvView != null) {
                if (f_mLastSrvView.isAccessible() == false) {
                    f_mLastSrvView.setAccessible(true);
                }
                if (f_mLastSrvView.get(imm) != null) {
                    f_mLastSrvView.set(imm, null);
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
