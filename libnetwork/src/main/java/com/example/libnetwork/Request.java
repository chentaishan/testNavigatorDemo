package com.example.libnetwork;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.IntDef;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public abstract class Request<T, R extends Request> {

    protected String mUrl;
    protected HashMap<String, String> headers = new HashMap<>();
    protected HashMap<String, Object> params = new HashMap<>();


    public static final int CACHE_ONLY = 1;
    public static final int NET_ONLY = 2;
    public static final int CACHE_FRIST = 3;
    public static final int NET_FIRST = 4;
    private String key;

    private Type mType;

    public Request(String url) {
        //user/list
        mUrl = url;
    }


    @IntDef({CACHE_ONLY, CACHE_FRIST, NET_FIRST, NET_ONLY})
    public @interface CacheStatgy {
    }


    public R addHeader(String key, String value) {
        headers.put(key, value);


        return (R) this;
    }


    public R addParam(String key, Object value) {

        try {
            final Field field = value.getClass().getField("type");
            final Class aClass = (Class) field.get(null);
            if (aClass.isPrimitive()) {

                params.put(key, value);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return (R) this;
    }

    public R responseType(Type type) {
        mType = type;
        return (R) this;
    }

    public R setCacheKey(String key) {

        this.key = key;
        return (R) this;
    }


    public void execute(final JsonCallBack<T> callback) {

        getCall().enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                ApiResponse<T> result = new ApiResponse<>();
                result.msg = e.getMessage();
                callback.error(result);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ApiResponse<T> result = parseResponse(response, callback);
                if (!result.isSuccess) {
                    callback.error(result);
                } else {
                    callback.success(result);
                }
            }
        });
    }

    private Call getCall() {
        final okhttp3.Request.Builder builder = new okhttp3.Request.Builder();

        addHeaders(builder);

        okhttp3.Request request = generateRequest(builder);


        return ApiSevice.okHttpClient.newCall(request);

    }

    protected abstract okhttp3.Request generateRequest(okhttp3.Request.Builder builder);

    private void addHeaders(okhttp3.Request.Builder builder) {

        for (Map.Entry<String, String> entry : headers.entrySet()
        ) {

            builder.addHeader(entry.getKey(), entry.getValue());
        }
    }

    private ApiResponse<T> parseResponse(Response response, JsonCallBack<T> callback) {
        String message = null;
        int status = response.code();
        boolean success = response.isSuccessful();
        ApiResponse<T> result = new ApiResponse<>();
        Convert convert = ApiSevice.sConvert;
        try {
            String content = response.body().string();
            if (success) {
                if (callback != null) {
                    ParameterizedType type = (ParameterizedType) callback.getClass().getGenericSuperclass();
                    Type argument = type.getActualTypeArguments()[0];
                    result.body = (T) convert.convert(content, argument);
                } else if (mType != null) {
                    result.body = (T) convert.convert(content, mType);
                }
//                } else if (mClaz != null) {
//                    result.body = (T) convert.convert(content, mClaz);
//                }
                else {
                    Log.e("request", "parseResponse: 无法解析 ");
                }
            } else {
                message = content;
            }
        } catch (Exception e) {
            message = e.getMessage();
            success = false;
            status = 0;
        }

        result.isSuccess = success;
        result.code = status;
        result.msg = message;

//        if (mCacheStrategy != NET_ONLY && result.success && result.body != null && result.body instanceof Serializable) {
//            saveCache(result.body);
//        }
        return result;
    }
}
