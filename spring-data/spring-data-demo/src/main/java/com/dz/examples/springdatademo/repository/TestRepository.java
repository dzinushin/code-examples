package com.dz.examples.springdatademo.repository;

import com.dz.examples.springdatademo.entities.TestEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends ListCrudRepository<TestEntity, String> {
}
