package com.process.enrolment.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_role_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ColumnDefault("false")
    private boolean isApprove;

    @Builder
    private UserRole(Role role, Member member) {
        this.role = role;
        this.member = member;
    }

    public void updateRoleApprove(){
        this.isApprove = true;
    }
}
