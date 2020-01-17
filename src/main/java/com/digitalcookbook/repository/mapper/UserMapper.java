package com.digitalcookbook.repository.mapper;


import com.digitalcookbook.domain.User;
import com.digitalcookbook.dto.UserDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(User user);

    User toEntity(UserDTO user);

}