package com.process.enrolment.security;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

//JWT 토큰 확인 및 유효성 검증 필터
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTH_HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = resolveToken(request);
        //String requestURI = request.getRequestURI();
        if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)){
            Authentication authentication = jwtTokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //LOGGER.info("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
        }else{
           // LOGGER.info("유효한 JWT 토큰이 없습니다., uri: {}", requestURI);
        }
        filterChain.doFilter(request, response);
    }

    /**토큰 정보 추출 */
    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTH_HEADER);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(PREFIX)){
            return bearerToken.substring(7);
        }
        return null;
    }

}
