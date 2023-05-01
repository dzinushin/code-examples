package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SlowService {
    public String getString(long value) {
        try {
            log.info("sleep for {} seconds", value);
            Thread.sleep(value*1000);
        } catch (Exception e) {
            ///
        }
        return String.format("slow result: %d%n", value);
    }
}
