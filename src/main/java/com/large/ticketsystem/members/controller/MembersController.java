package com.large.ticketsystem.members.controller;

import com.large.ticketsystem.members.dto.MembersSignupRequestDto;
import com.large.ticketsystem.members.dto.MembersResponseMsgDto;
import com.large.ticketsystem.members.service.MembersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MembersController {
    private final MembersService membersService;
    @PostMapping("/signup")
    public MembersResponseMsgDto signup(@RequestBody MembersSignupRequestDto membersSignupRequestDto, HttpServletResponse response) {
        return membersService.signup(membersSignupRequestDto, response);
    }
}
