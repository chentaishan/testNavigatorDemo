package com.example.libnetwork.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CacheDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Cache cache);

    @Query(("select * from cache where 'key'= :key"))
    Cache getCache(String key);

    @Delete
    long delete(Cache cache);

    @Update(onConflict = OnConflictStrategy.ABORT)
    void update(Cache cache);
}
