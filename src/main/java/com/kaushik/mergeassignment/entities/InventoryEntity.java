package com.kaushik.mergeassignment.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity(name = "inventory")
public class InventoryEntity extends BaseEntity{

    private int quantity;
}
