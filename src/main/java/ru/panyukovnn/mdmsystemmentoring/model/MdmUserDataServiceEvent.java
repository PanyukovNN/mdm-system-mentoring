package ru.panyukovnn.mdmsystemmentoring.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MdmUserDataServiceEvent extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String type;
    private String guid;
    private String phone;

    private String systemId;

    @Enumerated(value = EnumType.STRING)
    private UserDataServiceId serviceId;
}
