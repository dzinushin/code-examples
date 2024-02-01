package com.dz.examples.springdatademo.service;

import com.dz.examples.springdatademo.entities.TestEntity;
import com.dz.examples.springdatademo.repository.TestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SomeService {
    private final TestRepository testRepository;

    @Autowired
    private SomeService self;

    @Transactional
    public void saveMany1(int count) {
        for (int i = 0; i < count; i++) {
            saveOne(i);
        }
    }
    @Transactional
    public void saveMany2(int count) {
        for (int i = 0; i < count; i++) {
            self.saveOne(i);
        }
    }

    public void saveMany3(int count) {
        for (int i = 0; i < count; i++) {
            saveOne(i);
        }
    }

    public void saveMany4(int count) {
        for (int i = 0; i < count; i++) {
            self.saveOne(i);
        }
    }

    @Transactional
    public void saveOne(int i) {
        if (i>=10) {
            throw new RuntimeException("i must be less than 10");
        }
        testRepository.save(new TestEntity("id" + i, "value" + i));
    }

}
