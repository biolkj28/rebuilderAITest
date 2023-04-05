package com.process.enrolment.security;

import com.process.enrolment.dto.MemberDTO;
import com.process.enrolment.entity.Member;
import com.process.enrolment.repository.MemberRepository;
import com.process.enrolment.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        Member member = memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자입니다."));

        return userRoleRepository.findUserRoleByMember(member)
                .map(role -> createSecurityUser(member, role.getRole().getName()))
                .orElseThrow(() -> new NullPointerException("유효하지 않은 계정입니다."));

    }

    private User createSecurityUser(Member member, String authority) {
        System.out.println(authority);
        List<GrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority(authority));
        System.out.println(grantedAuthorities.get(0).getAuthority());
        return new User(member.getEmail(), member.getPassword(), grantedAuthorities);
    }
}
