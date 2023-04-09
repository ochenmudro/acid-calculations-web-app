package com.example.acidcalculationswebapp.mapper;

import java.util.List;

public interface BaseMapper<T, V> {

    V toDto(T entity);

    T toEntity(V dto);

    List<V> toDtoList(List<T> list);

}
