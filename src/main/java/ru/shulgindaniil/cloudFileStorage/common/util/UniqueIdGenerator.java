package ru.shulgindaniil.cloudFileStorage.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueIdGenerator<T> {
    private final JpaRepository<T, String> repository;

    public String generate(Integer countBytes) {
        String generatedId;
        do {
            generatedId = IdGenerator.generate(countBytes);
        } while (repository.findById(generatedId).isPresent());

        return generatedId;
    }
}
