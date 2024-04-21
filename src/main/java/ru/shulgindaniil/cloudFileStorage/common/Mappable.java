package ru.shulgindaniil.cloudFileStorage.common;

import java.util.Collection;

public interface Mappable<S, T> {
    T toTarget(S source);
    S fromTarget(T target);
    Collection<T> toTarget(Collection<S> sources);
}
