package com.example.bytedance.myapplication.lambda;

import android.util.Log;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xiaoxiaoyu on 2020-05-29.
 */
public class TestLambda {
    private static final TestLambda ourInstance = new TestLambda();
    private static final String TAG = "TestLambda";

    public static TestLambda getInstance() {
        return ourInstance;
    }

    private TestLambda() {
    }

    public void filter(Filter f, List<Integer> integerList) {
        for (Integer i : integerList) {
            if (f.test(i)) {
                Log.i(TAG, i + "");
            }
        }
    }
    // 定义一个 SAM
    interface Filter {
        boolean test(int x);
    }

    public void lambdaDemo() {
        filter((x) -> x % 2 == 1, Arrays.asList(1, 2, 3, 4, 5, 6, 7));
    }
}
