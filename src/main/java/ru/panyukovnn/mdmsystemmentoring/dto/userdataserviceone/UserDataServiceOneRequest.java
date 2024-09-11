package ru.panyukovnn.mdmsystemmentoring.dto.userdataserviceone;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDataServiceOneRequest {

    @Valid
    @NotNull(message = "Поле meta не может быть пустым")
    private UserDataServiceOneMeta meta;

    @Valid
    @NotNull(message = "Поле body не может быть пустым")
    private UserDataServiceOneBody body;
}
