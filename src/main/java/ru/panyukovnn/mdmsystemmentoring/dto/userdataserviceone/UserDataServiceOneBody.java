package ru.panyukovnn.mdmsystemmentoring.dto.userdataserviceone;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDataServiceOneBody {

    @NotNull(message = "Поле body.id не может быть пустым")
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "Некорректный формат body.id")
    private String id;

    @NotNull(message = "Поле body.guid не может быть пустым")
    @Pattern(regexp = "^[A-Z0-9]{32}$", message = "Некорректный формат body.guid")
    private String guid;

    @NotNull(message = "Поле body.phone не может быть пустым")
    @Pattern(regexp = "^\\+[0-9]{11}$", message = "Некорректный формат body.phone")
    private String phone;

}
