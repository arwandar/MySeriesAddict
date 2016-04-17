package com.arwandar.myseriesaddict.data;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by olivi on 31/03/2016.
 */
public class ServiceGenerator {

    public static final String API_BASE_URL = "https://api.betaseries.com";
    private static final String API_VERSION = "2.4";
    private static final String API_KEY = "a93691358c05";
    private static AccessToken mApiToken;


    public static void setApiToken(AccessToken token) {
        mApiToken = token;
    }

    public static AccessToken getApiToken() {
        return mApiToken;
    }


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());


    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, mApiToken);
    }

    public static <S> S createService(Class<S> serviceClass, final AccessToken token) {
        if (token != null) {
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Accept", "application/json")
                            .header("X-BetaSeries-Version", API_VERSION)
                            .header("X-BetaSeries-Key", API_KEY)
                            .header("X-BetaSeries-Token", token.getAccessToken())
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

}

