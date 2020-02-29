package com.example.libnetwork.db;

import java.io.Serializable;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "cache"  )
public class Cache implements Serializable {

    @PrimaryKey
    String key;
    byte[] data;


}
