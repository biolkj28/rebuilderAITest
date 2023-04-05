package com.process.enrolment.service;

import com.process.enrolment.controller.request.AdminRequest;
import com.process.enrolment.entity.UserRole;
import com.process.enrolment.repository.UserRoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceTest {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    AdminService adminService;

    @Test
    void createRole() {
    }

    @Test
    void approveRole() {
        Iterable<UserRole> all = userRoleRepository.findAll();
        for (UserRole userRole : all) {
            adminService.approveRole(new AdminRequest.RoleApprove(userRole.getUser_role_id()));
        }

    }
}