package com.dz.examples.springdata.repository;

import com.dz.examples.springdata.entities.TestEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends ListCrudRepository<TestEntity, String> {
}
