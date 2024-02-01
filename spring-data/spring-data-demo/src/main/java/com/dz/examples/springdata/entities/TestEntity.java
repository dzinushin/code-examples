package com.dz.examples.springdata.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "test")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TestEntity {
    @Id
    private String id;
    private String value;
}
