package ru.panyukovnn.mdmsystemmentoring.dto.userdataservicetwo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDataServiceTwoMdmEvent {

    @NotEmpty(message = "Полсе events.eventType не может быть пустым")
    @Pattern(regexp = "change_phone")
    private String eventType;

    @NotNull(message = "Поле body.guid не может быть пустым")
    @Pattern(regexp = "^[A-Z0-9]{32}$", message = "Некорректный формат events.guid")
    private String guid;

    @NotNull(message = "Поле body.phone не может быть пустым")
    @Pattern(regexp = "^\\+[0-9]{11}$", message = "Некорректный формат events.phone")
    private String phone;
}
