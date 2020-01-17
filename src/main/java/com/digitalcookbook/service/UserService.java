package com.digitalcookbook.service;

import com.digitalcookbook.dto.UserDTO;
import com.digitalcookbook.repository.UserRepo;
import com.digitalcookbook.repository.mapper.UserMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class UserService {

    private final UserRepo userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepo userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO save(UserDTO userDTO){
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDTO)));
    }

    @Transactional
    public Optional<UserDTO> findByEmail(String email){
        return userRepository.findOneByEmail(email).map(userMapper::toDto);

    }

    @Transactional
    public Optional<UserDTO> findByIdWithEmailNotVerified(String id){
        if(StringUtils.isEmpty(id)){
            return Optional.empty();
        }else{
            return userRepository.findOneByIdAndEmailVerified(Long.valueOf(id), false).map(userMapper::toDto);
        }
    }

}
