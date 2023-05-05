package com.javarush.jira.login.internal;

import com.javarush.jira.common.BaseMapper;
import com.javarush.jira.login.Role;
import com.javarush.jira.login.User;
import com.javarush.jira.login.UserTo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

@Component
@Mapper(componentModel = "spring", imports = {EnumSet.class, Role.class})
public interface UserMapper extends BaseMapper<User, UserTo> {
    @Override
    @Mapping(target = "roles", expression = "java(EnumSet.of(Role.DEV))")
    User toEntity(UserTo to);

    @Override
    @Mapping(target = "password", ignore = true)
    User updateFromTo(@MappingTarget User entity, UserTo to);
}
