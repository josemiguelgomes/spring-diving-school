package com.zem.diveschool.converters.impl;

import com.zem.diveschool.converters.ConvertObjectToObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeSet;

@Service
public abstract class ConvertObject<F, T>
        implements Converter<F, T>,
        ConvertObjectToObject<F, T> {

    // Iterate thru all objects and apply a Converter
    public Set<T> convert(Set<F> fs) {
        Set<T> ts = new TreeSet<T>();
        fs.forEach( (f) -> ts.add(convert(f)) );
        return ts;
    }

}
