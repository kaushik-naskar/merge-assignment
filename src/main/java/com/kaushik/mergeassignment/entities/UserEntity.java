package com.kaushik.mergeassignment.entities;

import com.kaushik.mergeassignment.models.Role;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "user")
public class UserEntity extends BaseEntity {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    private boolean enabled;
    private boolean credExpired;
}
