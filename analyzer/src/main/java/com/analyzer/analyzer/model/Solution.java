package com.analyzer.analyzer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Solution {
    private Long id;

    private String description;
    private Long cost, duration;
}
