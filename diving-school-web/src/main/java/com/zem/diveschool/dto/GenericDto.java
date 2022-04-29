package com.zem.diveschool.dto;

import org.jetbrains.annotations.NotNull;

public abstract class GenericDto<T> implements Comparable<T> {
    public int compareTo(@NotNull T o) {
        return 0;
    }
}
