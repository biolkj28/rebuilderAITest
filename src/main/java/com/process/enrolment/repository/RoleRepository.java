package com.process.enrolment.repository;

import com.process.enrolment.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findRoleByRoleCode(String roleCode);

    boolean existsRoleByName(String name);
}
