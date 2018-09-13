package com.xitiz.room_object_entity_demo.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.xitiz.room_object_entity_demo.db.entity.DataItem;
import com.xitiz.room_object_entity_demo.db.entity.ListDataItemTypeConverter;
import com.xitiz.room_object_entity_demo.db.entity.Response;

@Database(entities = {Response.class, DataItem.class}, version = 1, exportSchema = false)
@TypeConverters({ListDataItemTypeConverter.class})
public abstract class ResponseDatabase extends RoomDatabase {
    public abstract ResponseDao responseDao();

    static private volatile ResponseDatabase responseDatabase;


    public static ResponseDatabase getInstance(Context context) {
        if (responseDatabase == null) {
            synchronized (ResponseDatabase.class) {
                if (responseDatabase == null) {
                    responseDatabase = Room
                            .databaseBuilder(context.getApplicationContext(), ResponseDatabase.class, "database_response")
                            .build();
                }
            }
        }
        return responseDatabase;
    }

}
