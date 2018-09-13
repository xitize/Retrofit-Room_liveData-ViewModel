package com.xitiz.room_object_entity_demo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.xitiz.room_object_entity_demo.db.Response;

public class ResponseViewModel extends AndroidViewModel {
    private LiveData<Response> dbResponse = new MutableLiveData<>();
    private MutableLiveData<Response> webResponse = new MutableLiveData<>();

    public ResponseViewModel(@NonNull Application application) {
        super(application);
        ResponseRepository responseRepository = new ResponseRepository(application);
        dbResponse = responseRepository.getDbResponse();
        webResponse = responseRepository.getWebResponse();
        ResponseRepository.getWebResponseInfo();
    }

    LiveData<Response> getDbResponse() {
        return dbResponse;
    }

    MutableLiveData<Response> getWebResponse() {
        return webResponse;
    }
}
