package com.geniusmart.loveut.net;



import com.geniusmart.loveut.utils.LogUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GithubService {

    public static final String TAG = "GithubService";

    String BASE_URL = "https://api.github.com/";

    @GET("users/{username}/repos")
    Call<List<Repository>> publicRepositories(@Path("username") String username);

    @GET("users/{username}/following")
    Call<List<User>> followingUser(@Path("username") String username);

    @GET("users/{username}")
    Call<User> user(@Path("username") String username);


    class Factory {
        public static GithubService create() {

            //注意：不能直接new出来，否者会包不支持操作异常，需要用builder创建
            OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(
                    new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            Response response = chain.proceed(request);

                            //网络请求打印
                            LogUtils.d(TAG ,
                                    "当前线程：" + Thread.currentThread().getName() + "," + request.url() + ",请求头：" + request.headers());

                            LogUtils.d(TAG ,
                                    "当前线程：" + Thread.currentThread().getName() + "," + request.url() + ",请求体：" + request.body());



                            //网络响应打印
                            LogUtils.d(TAG, "当前线程："
                                    + Thread.currentThread().getName()
                                    + ","
                                    + request.url()
                                    + ",响应码："
                                    + response.code()
                                    + ",响应头："
                                    // + response.headers()
                            );


                                LogUtils.d(TAG,
                                        "当前线程：" + Thread.currentThread().getName() + "," + request.url() + ",响应体：" +response.body());


                            return response;
                        }
                    }).build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).client(client)
                    .build();
            return retrofit.create(GithubService.class);
        }

    }
}



