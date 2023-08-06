package com.kaushik.mergeassignment.mappers;

import com.kaushik.mergeassignment.commons.Constant;
import com.kaushik.mergeassignment.entities.UserEntity;
import com.kaushik.mergeassignment.models.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {Constant.class, Role.class})
public interface UserMapper {


    // createUserEntity(String username, String password, String firstName, String lastName);
}
