package ru.panyukovnn.mdmsystemmentoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.panyukovnn.mdmsystemmentoring.model.MdmUserDataServiceEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public interface MdmUserDataServiceEventRepository extends JpaRepository<MdmUserDataServiceEvent, UUID> {

    void deleteByCreateTimeBefore(LocalDateTime createTimeBefore);
}
