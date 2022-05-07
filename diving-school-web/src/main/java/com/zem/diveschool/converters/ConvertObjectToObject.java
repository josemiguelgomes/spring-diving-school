package com.zem.diveschool.converters;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public interface ConvertObjectToObject <F, T>{
    T convert(F f);
    Set<T> convert(Set <F> fs);

    /* This code is noncompliant, we should not use Optional as parameter
    default Optional<T> convert(Optional<F> f) {
        return f.isEmpty() ? Optional.empty() : Optional.of(convert(f.get()));
    }
     */
}
