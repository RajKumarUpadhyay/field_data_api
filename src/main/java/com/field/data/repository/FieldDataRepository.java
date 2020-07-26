package com.field.data.repository;

import com.field.data.entity.Field;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FieldDataRepository extends CrudRepository<Field, UUID> {
    List<Field> findAll();
    Optional<Field> findFieldById(UUID id);
    Field findFieldByName(String name);
}
