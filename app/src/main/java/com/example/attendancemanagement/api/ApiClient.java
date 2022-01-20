package com.example.attendancemanagement.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    static ConnectionSpec spec = new
            ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .cipherSuites(
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
            .build();

    private static Retrofit retrofit = null;


    // Install the all-trusting trust manager



    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS).connectionSpecs(Collections.singletonList(spec))
                .readTimeout(5,TimeUnit.SECONDS).addInterceptor(interceptor)
                .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT)).build();





        retrofit = new Retrofit.Builder()
                .baseUrl(Config.getInstance().getBASE_URL()).client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();



        return retrofit;


    }
    public static Retrofit getClient(String url) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectionSpecs(Arrays.asList(ConnectionSpec.COMPATIBLE_TLS,ConnectionSpec.MODERN_TLS))
                .connectTimeout(500, TimeUnit.SECONDS)
                .readTimeout(500,TimeUnit.SECONDS).addInterceptor(interceptor).build();



        retrofit = new Retrofit.Builder()
                .baseUrl(url).client(client)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();



        return retrofit;
    }

}
