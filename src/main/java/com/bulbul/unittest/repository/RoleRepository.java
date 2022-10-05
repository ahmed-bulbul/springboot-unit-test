package com.bulbul.unittest.repository;

import com.bulbul.unittest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>,JpaSpecificationExecutor<Role> {

    Role getRoleByAuthority(String role_user);
    Optional<Role> findByAuthority(String authority);
    Role getByAuthority(String authority);

}