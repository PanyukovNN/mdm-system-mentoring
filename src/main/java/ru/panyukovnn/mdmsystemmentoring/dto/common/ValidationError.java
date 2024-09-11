package ru.panyukovnn.mdmsystemmentoring.dto.common;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ValidationError {

    private String field;
    private String message;
}
