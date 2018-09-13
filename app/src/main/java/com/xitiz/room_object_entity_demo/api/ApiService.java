package com.xitiz.room_object_entity_demo.api;

import com.xitiz.room_object_entity_demo.db.entity.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/api/users")
    Call<Response> getResponse(@Query("pages") int page);

}
