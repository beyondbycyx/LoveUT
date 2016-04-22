package com.geniusmart.loveut.net;

import android.util.Log;

import com.geniusmart.loveut.BuildConfig;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class GithubServiceTest {

    private static final String TAG = "GithubServiceTest";
    GithubService githubService;

    @Before
    public void setUp() throws URISyntaxException {
        //输出日志, Log.i 的使用
         ShadowLog.stream = System.out;
         githubService = GithubService.Factory.create();
    }


    /*
    * 请求网络上真的数据进行测试
    * */
    @Test
    public void publicRepositories() throws IOException {
        Call<List<Repository>> call = githubService.publicRepositories("geniusmart");


        Response<List<Repository>> execute = call.execute(); //这是同步的方法请求
        //不能重复发送请求：call.execute();

//        这是异步的方法请求，与上面的不同
/*        call.enqueue(new Callback<List<Repository>>() {

            //有网络响应，包括有数据的，无数据的 ，404 即有响应
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {

                int statusCode  = response.code();

            }

            //网络请求错误，或者是本地的错误
            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {

            }
        });*/

        //打印响应码
//        Log.i(TAG, "status = " + execute.code());
//        Log.i(TAG, "body = " + execute.body());
//        Log.i(TAG, "errorBody = " + execute.errorBody());
//        Log.i(TAG, "message = " + execute.message());
//        Log.i(TAG, "isSuccess = " + execute.isSuccess());
        Log.i(TAG, "headers = " + execute.headers());
        Log.i(TAG, "raw = " + execute.raw());


        List<Repository> list = execute.body();
        //可输出完整的响应结果，帮助我们调试代码
        Log.i(TAG,new Gson().toJson(list));
        assertTrue(list.size() > 0);
        assertNotNull(list.get(0).name);
    }

}