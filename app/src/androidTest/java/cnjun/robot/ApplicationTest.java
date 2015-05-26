package cnjun.robot;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import cnjun.robot.utils.HttpUtils;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testSend() {
        String res = HttpUtils.doGet("ni hao");
        Log.i("TAG", "++++++++++++++++++++++++++" + res);
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