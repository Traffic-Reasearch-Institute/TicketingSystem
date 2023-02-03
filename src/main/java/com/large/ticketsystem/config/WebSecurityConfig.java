package com.large.ticketsystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //CSRF(Cross-Site Request Forgery) 보호를 비활성화
        http.csrf().disable();
        //다양한 엔드포인트 패턴에 대한 요청을 승인
        http.authorizeRequests()
                //URL이 "/member/" 및 "/members/"인 엔드포인트는 인증 없이 모두에게 개방
                //URL이 "/upload"인 엔드포인트는 인증 없이 모두에게 공개.
                .antMatchers("/*").permitAll();
        //http 보안 설정을 위 내용으로 해서 반환
        return http.build();
    }
}
