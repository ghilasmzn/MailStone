package com.analyzer.analyzer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Issue {
    private Long id;
    private Product product;
    private Solution solution;

    private String description;
    private boolean resolved;
}
