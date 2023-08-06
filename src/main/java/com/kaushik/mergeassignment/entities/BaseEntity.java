package com.kaushik.mergeassignment.entities;

import lombok.Data;
import org.hibernate.annotations.Target;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ZonedDateTime createdAt;

    private String createdBy;

    private String updatedBy;

    private ZonedDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = ZonedDateTime.now();
        updatedAt = ZonedDateTime.now();
        createdBy =  SecurityContextHolder.getContext().getAuthentication().getName();
        updatedBy =  SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = ZonedDateTime.now();
        updatedBy =  SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
