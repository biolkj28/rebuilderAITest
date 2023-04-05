package com.process.enrolment.service;

import com.process.enrolment.controller.request.AdminRequest;
import com.process.enrolment.entity.Role;
import com.process.enrolment.entity.UserRole;
import com.process.enrolment.repository.RoleRepository;
import com.process.enrolment.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    /**
     * 권한 생성 서비스
     * @param createRole: 권한 명, 권한 설명을 가진 객체
     * @apiNote : 권한명으로 존재 여부 확인, 없을 시 생성
     */
    public void createRole(AdminRequest.CreateRole createRole) {
        Role role = new Role(createRole.getName(), createRole.getDesc());

        if (roleRepository.existsRoleByName(role.getName()))
            throw new RuntimeException("이미 존재하는 권한입니다.");

        roleRepository.save(role);
    }

    /**
     * 권한 승인
     * @param roleApprove: 사용자 권한 테이블 대기 id 를 담은 객체
     * @apiNote : 계정 생성과 함께 권한 테이블에 미승인 상태로 등록 됨으로 데이터가 없다면 미존재 계정, 권한 승인
     */
    public void approveRole(AdminRequest.RoleApprove roleApprove) {
        UserRole userRole = userRoleRepository.findById(roleApprove.getUserRoleId()).orElseThrow(() -> new UsernameNotFoundException("존재 하지 않는 계정입니다."));
        userRole.updateRoleApprove();

    }


}
