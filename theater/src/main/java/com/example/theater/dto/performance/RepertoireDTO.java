package com.example.theater.dto.performance;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RepertoireDTO {
    int id;
    int count;
    String price;
}
