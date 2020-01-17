package com.digitalcookbook.repository;

import com.digitalcookbook.domain.AuthProvider;
import com.digitalcookbook.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findOneByEmailAndProviderAndEmailVerified(String email, AuthProvider provider, Boolean emailVerified);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByIdAndEmailVerified(Long id, Boolean emailVerified);


}
