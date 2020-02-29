package com.example.libnetwork.db;

import com.example.libcommon.AppGlobals;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Cache.class},version = 1,exportSchema = true)
public abstract class CacheDatabase extends RoomDatabase {
    static {


        cacheDatabase = Room.inMemoryDatabaseBuilder(AppGlobals.getApplication(), CacheDatabase.class )
//                .allowMainThreadQueries()
//                .openHelperFactory()
//                .setJournalMode() //数据库日志
                .build();
    }

    private static CacheDatabase cacheDatabase;
}
