package com.example.libnetwork.db;

import com.example.libcommon.AppGlobals;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * 注解：
 * @Database 数据库对象
 * @columnInfo 更改列的名字
 * @Dao 访问数据库对象
 * @Embedded 嵌套对象
 * @Entity 表的对象
 * @PrimartKey 主键
 * @Delete 删除
 * @Ingore 忽略
 * @Index 加快查询操作，副作用减慢插入或者更新
 * @Foreignkey 外键关联
 * @Tranation
 * @TypeConverter
 */
@Database(entities = {Cache.class},version = 1,exportSchema = true)
public abstract class CacheDatabase extends RoomDatabase {
    static {



        cacheDatabase = Room.databaseBuilder(AppGlobals.getApplication(), CacheDatabase.class ,"cache")
                .addMigrations()
//                .allowMainThreadQueries()//允许主线程进行查询
//                .openHelperFactory()
//                .setJournalMode() //数据库日志
                .build();
    }

    private static CacheDatabase cacheDatabase;

    public abstract CacheDao  getCacheDao();

    public static CacheDatabase getCacheDatabase() {
        return cacheDatabase;
    }
}
