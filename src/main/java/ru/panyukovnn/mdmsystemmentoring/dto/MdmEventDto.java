package ru.panyukovnn.mdmsystemmentoring.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class MdmEventDto {

    private UUID id;
    @Builder.Default
    private String type = "USER_PHONE_CHANGE";
    private String guid;
    private String phone;
}
