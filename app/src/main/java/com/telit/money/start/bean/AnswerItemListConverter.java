package com.telit.money.start.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * greendao 保存集合
 */
public class AnswerItemListConverter implements PropertyConverter<List<AdviceBean.Computer>, String> {

    private final Gson mGson;

    public AnswerItemListConverter() {
        mGson = new Gson();
    }

    @Override
    public List<AdviceBean.Computer> convertToEntityProperty(String databaseValue) {
        Type type = new TypeToken<ArrayList<AdviceBean.Computer>>() {
        }.getType();
        ArrayList<AdviceBean.Computer> list = mGson.fromJson(databaseValue, type);
        return list;
    }

    @Override
    public String convertToDatabaseValue(List<AdviceBean.Computer> entityProperty) {
        String dbString = mGson.toJson(entityProperty);
        return dbString;
    }


}
 