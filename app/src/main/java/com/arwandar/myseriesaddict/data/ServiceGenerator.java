package com.arwandar.myseriesaddict.data;

import com.arwandar.myseriesaddict.common.util.SharedPrefsSingleton;

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


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        if (!SharedPrefsSingleton.getAccessToken().isEmpty()) {
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Accept", "application/json")
                            .header("X-BetaSeries-Version", SharedPrefsSingleton.getVersion())
                            .header("X-BetaSeries-Key", SharedPrefsSingleton.getClientId())
                            .header("X-BetaSeries-Token", SharedPrefsSingleton.getAccessToken())
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.baseUrl(SharedPrefsSingleton.getBaseUrl()).client(client).build();
        return retrofit.create(serviceClass);
    }

}

