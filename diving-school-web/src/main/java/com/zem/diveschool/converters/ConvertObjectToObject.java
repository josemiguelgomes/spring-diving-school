package com.zem.diveschool.converters;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public interface ConvertObjectToObject <F, T>{
    T convert(F f);
    Set<T> convert(Set <F> fs);

    default Optional<T> convert(Optional<F> f) {
        // TODO : clean this code
         if(f.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(convert(f.get()));
    }
}
