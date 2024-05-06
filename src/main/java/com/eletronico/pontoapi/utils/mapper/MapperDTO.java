package com.eletronico.pontoapi.utils.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class MapperDTO {

    private static ModelMapper mapper = new ModelMapper();

    public static <T, D> D parseObject(T origin, Class<D> destination){
        return mapper.map(origin, destination);
    }
    public static <T, D> List<D> parseListObjects(List<T> origin, Class<D> destination){
        List<D> destinationObjects = new ArrayList<D>();
        for (T o: origin){
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }
}
