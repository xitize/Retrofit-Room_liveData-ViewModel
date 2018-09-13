package com.xitiz.room_object_entity_demo.api;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi{
        private static Retrofit retrofit = null;

        public static Retrofit getClient(Context context) {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://reqres.in").build();
            }

            return retrofit;
        }
}