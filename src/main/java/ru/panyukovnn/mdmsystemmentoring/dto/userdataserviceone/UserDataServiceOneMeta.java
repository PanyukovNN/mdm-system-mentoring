package ru.panyukovnn.mdmsystemmentoring.dto.userdataserviceone;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDataServiceOneMeta {

    @NotEmpty(message = "Поле meta.systemId не может быть пустым")
    private String systemId;

    @NotEmpty(message = "Поле meta.sender не может быть пустым")
    private String sender;
}
