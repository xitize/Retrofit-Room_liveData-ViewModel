package com.xitiz.room_object_entity_demo.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface ResponseDao {
    /**
     * returns response
     *
     * @return
     */
    @Query("SELECT * FROM Response")
    LiveData<Response> getResponse();

    /**
     * inserts response
     *
     * @param response
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Response response);


}
