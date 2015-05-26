package cnjun.robot.test;

import android.test.AndroidTestCase;
import android.test.InstrumentationTestRunner;
import android.util.Log;

import cnjun.robot.utils.HttpUtils;

/**
 * Created by chen on 15-5-25.
 */
public class TestHttpUtils extends InstrumentationTestRunner{
    public void testSend() {
        String res = HttpUtils.doGet("ni hao");
        Log.e("TAG", res);
        res = HttpUtils.doGet("你是谁");
        Log.e("TAG", res);
        res = HttpUtils.doGet("给我讲个鬼故事");
        Log.e("TAG", res);
        res = HttpUtils.doGet("你好");
        Log.e("TAG", res);
        res = HttpUtils.doGet("你真美");
        Log.e("TAG", res);
    }

}
