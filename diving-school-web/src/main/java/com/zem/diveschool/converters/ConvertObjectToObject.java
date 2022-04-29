package com.zem.diveschool.converters;

import java.util.Set;

public interface ConvertObjectToObject <F, T>{
    public T convert(F f);
    public Set<T> convert(Set <F> fs);
}
