package com.carDealership.Application.repository;

import com.carDealership.Application.entity.User;
import com.carDealership.Application.entity.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRole(UserRoleEnum userRoleEnum);
    Optional<User> findByIdAndRole(Long idUser, UserRoleEnum userRoleEnum);

}
