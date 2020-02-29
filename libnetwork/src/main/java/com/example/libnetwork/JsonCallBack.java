package com.example.libnetwork;

public abstract class JsonCallBack<T > {

    public void success(ApiResponse<T> response){

    }



    public void error(ApiResponse<T> response){

    }


    public void cache(ApiResponse<T> response){

    }



}
