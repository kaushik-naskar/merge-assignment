package com.kaushik.mergeassignment.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.ZonedDateTime;

@Data
@Entity(name = "blocked_inventory")
public class BlockedInventory extends BaseEntity{

    @ManyToOne
    private ItemEntity item;

    @ManyToOne
    private UserEntity user;

    private int quantity;

    private ZonedDateTime blockedTill;
}
