package ru.panyukovnn.mdmsystemmentoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.panyukovnn.mdmsystemmentoring.model.MdmEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public interface MdmEventRepository extends JpaRepository<MdmEvent, UUID> {

    void deleteByCreateTimeBefore(LocalDateTime createTimeBefore);
}
