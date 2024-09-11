package ru.panyukovnn.mdmsystemmentoring.dto.userdataservicetwo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDataServiceTwoRequest {

    @NotNull(message = "Поле id не может быть пустым")
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "Некорректный формат body.id")
    private String id;

    @NotEmpty(message = "Поле systemId не может быть пустым")
    private String systemId;

    @NotEmpty(message = "Массив events не может быть пустым")
    private List<@Valid UserDataServiceTwoMdmEvent> events;
}
