package ru.panyukovnn.mdmsystemmentoring.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponseBody {

    private UUID id;
    private String status;
    private String errorMessage;
    private List<ValidationError> validationErrors;
}
