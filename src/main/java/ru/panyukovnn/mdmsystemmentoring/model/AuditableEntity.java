package ru.panyukovnn.mdmsystemmentoring.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
public class AuditableEntity {

    private ZonedDateTime createTime;
    private ZonedDateTime updateTime;

    @PrePersist
    public void prePersist() {
        this.createTime = ZonedDateTime.now(ZoneOffset.systemDefault());
        this.updateTime = ZonedDateTime.now(ZoneOffset.systemDefault());
    }

    @PreUpdate
    public void preUpdate() {
        this.updateTime = ZonedDateTime.now(ZoneOffset.systemDefault());
    }

}
