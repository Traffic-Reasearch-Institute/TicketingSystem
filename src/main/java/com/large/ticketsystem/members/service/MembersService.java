package com.large.ticketsystem.members.service;

import com.large.ticketsystem.jwt.JwtUtil;
import com.large.ticketsystem.members.dto.MembersResponseMsgDto;
import com.large.ticketsystem.members.dto.MembersSignupRequestDto;
import com.large.ticketsystem.members.entity.Members;
import com.large.ticketsystem.members.entity.MembersRoleEnum;
import com.large.ticketsystem.members.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Transient;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MembersService {
    private final MembersRepository membersRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    //클라이언트 상태코드 수정을 위해 사용함
    public void membersExceptionHandler(HttpServletResponse response, int statusCode) {
        //setStatus를 통해 response의 상태 코드 set
        response.setStatus(statusCode);
        //콘텐츠 유형을 json으로 바꿔줌
        response.setContentType("application/json");
    }

    //위 핸들러를 쓰고 dto로 리턴하기
    private MembersResponseMsgDto handleMemberException(String message, HttpStatus status, HttpServletResponse response) {
        membersExceptionHandler(response, status.value());
        return new MembersResponseMsgDto(message, status.value());
    }
    @Transactional
    public MembersResponseMsgDto signup(MembersSignupRequestDto membersSignupRequestDto, HttpServletResponse response) {
        String username = membersSignupRequestDto.getUsername();
//        String password = membersSignupRequestDto.getPassword();
        String password = passwordEncoder.encode(membersSignupRequestDto.getPassword());

        //회원 중복 확인
        Optional<Members> found = membersRepository.findByUsername(username);
        if (found.isPresent()) {
            return handleMemberException("중복된 사용자가 존재합니다", HttpStatus.BAD_REQUEST, response);
        }

        //아이디 양식 확인
        if (!membersSignupRequestDto.getUsername().matches("^[a-zA-Z0-9]{5,10}$")) {
            return handleMemberException("아이디 양식을 지켜주세요!", HttpStatus.BAD_REQUEST, response);
        }

        //비밀번호 양식
        if (!membersSignupRequestDto.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,12}$")) {
            return handleMemberException("비밀번호는 영어 대소문자, 숫자의 최소 8자에서 최대 12자리여야 합니다.", HttpStatus.BAD_REQUEST, response);
        }

        MembersRoleEnum role = MembersRoleEnum.MEMBER;

        Members members = new Members(username, password, role);
        membersRepository.save(members);

        return handleMemberException("회원가입 성공", HttpStatus.OK, response);
    }
}
