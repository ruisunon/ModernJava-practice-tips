package com.rydata.reactive;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
@AllArgsConstructor
public class Customer {
    @Id
    private Long id;
    @Column
    private String name;
}