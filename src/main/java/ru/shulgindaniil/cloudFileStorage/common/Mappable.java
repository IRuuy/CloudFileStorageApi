package ru.shulgindaniil.cloudFileStorage.common;
public interface Mappable<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
}
