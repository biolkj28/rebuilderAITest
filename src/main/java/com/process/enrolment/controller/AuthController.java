package com.process.enrolment.controller;

import com.process.enrolment.dto.MemberDTO;
import com.process.enrolment.dto.TokenDTO;
import com.process.enrolment.security.JwtFilter;
import com.process.enrolment.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    /**
     * 로그인 및 토큰 발급 
     * @param memberDto 로그인 이메일과 비밀번호를 가진 객체 
     * @return 로그인 성공 시 토큰 반환
     */
    @PostMapping("/auth")
    public ResponseEntity<TokenDTO>auth(@RequestBody MemberDTO memberDto){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(memberDto.getEmail(), memberDto.getPassword());

        //UserDetailService load
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);

        //인증정보 컨테이너 적재
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.createToken(authentication);

        //헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtFilter.AUTH_HEADER,JwtFilter.PREFIX+token);

        return new ResponseEntity<>(new TokenDTO(token), headers, HttpStatus.OK);
    }
}
