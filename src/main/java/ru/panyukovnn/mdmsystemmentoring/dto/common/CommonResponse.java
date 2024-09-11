package ru.panyukovnn.mdmsystemmentoring.dto.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponse {

    private CommonResponseBody body;
}
