package com.kaushik.mergeassignment.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@Entity(name = "cart")
public class CartEntity extends BaseEntity{

    @OneToOne
    private UserEntity user;

    @ManyToMany
    private List<ItemEntity> items;
}
