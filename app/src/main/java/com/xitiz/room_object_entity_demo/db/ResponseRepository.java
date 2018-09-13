package com.xitiz.room_object_entity_demo.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.xitiz.room_object_entity_demo.api.ApiService;
import com.xitiz.room_object_entity_demo.api.RetrofitApi;
import com.xitiz.room_object_entity_demo.db.entity.Response;
import com.xitiz.room_object_entity_demo.db.ResponseDao;
import com.xitiz.room_object_entity_demo.db.ResponseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class ResponseRepository {
    private static LiveData<Response> dbResponse = new MutableLiveData<>();
    private static MutableLiveData<Response> webResponse = new MutableLiveData<>();
    static ResponseDao responseDao;
    private static Retrofit retrofit;
    private static ApiService apiService;

    public ResponseRepository(Application application) {
        //instantiate db
        ResponseDatabase responseDatabase = ResponseDatabase.getInstance(application);
        responseDao = responseDatabase.responseDao();
        //instantiate webservice
        retrofit = RetrofitApi.getClient(application);
        apiService = retrofit.create(ApiService.class);
    }

    public static void getWebResponseInfo() {
        apiService.getResponse(2).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response response1 = response.body();
                Log.d("TAG", "data is loaded");
                //saves to the webService liveData
                webResponse.setValue(response1);
                //saved to the db

                new insertSync(responseDao).execute(response1);
                // responseDao.insert(response.body());

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d("TAG", "Error ");

            }
        });
    }


    public MutableLiveData<Response> getWebResponse() {
        return webResponse;
    }

    public LiveData<Response> getDbResponse() {
        dbResponse = responseDao.getResponse();
        return dbResponse;
    }

    private static class insertSync extends AsyncTask<Response, Void, Void> {
        ResponseDao responseDao;

        insertSync(ResponseDao responseDao) {
            this.responseDao = responseDao;
        }

        @Override
        protected Void doInBackground(Response... responses) {
            responseDao.insert(responses[0]);
            return null;
        }
    }
}
