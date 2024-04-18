package ru.shulgindaniil.cloudFileStorage.common;

import java.util.Collection;

public interface Mappable<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
    Collection<D> toDto(Collection<E> entities);
}
