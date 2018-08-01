/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zjwam.qualification.callback;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.zjwam.qualification.bean.ResponseBean;
import com.zjwam.qualification.bean.SimpleResponse;
import com.zjwam.qualification.utils.MyException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class JsonCallback<T> extends AbsCallback<T> {

    @Override
    public T convertResponse(Response response) {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = params[0];
        if (!(type instanceof ParameterizedType)) throw new IllegalStateException("没有填写泛型!");
        Type rawType = ((ParameterizedType) type).getRawType();
        Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
        ResponseBody body = response.body();
        if (body == null) return null;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (rawType != ResponseBean.class) {
            T data = gson.fromJson(jsonReader, type);
            response.close();
            return data;
        } else {
            if (typeArgument == Void.class) {
                SimpleResponse simpleResponse = gson.fromJson(jsonReader, SimpleResponse.class);
                response.close();
                return (T) simpleResponse.toLzyResponse();
            } else {
                ResponseBean responseBean = gson.fromJson(jsonReader, type);
                response.close();
                int code = responseBean.code;
                //与服务器约定的各种code
                if (code == 1) {
                    return (T) responseBean;
                } else {
                    throw new MyException(responseBean.toString());
                }
            }
        }
    }
}
