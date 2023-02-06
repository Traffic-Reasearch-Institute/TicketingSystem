package com.large.ticketsystem.members.controller;

import com.large.ticketsystem.members.dto.MemberCachedDto;
import com.large.ticketsystem.members.dto.MembersRequestDto;
import com.large.ticketsystem.members.dto.MembersResponseMsgDto;
import com.large.ticketsystem.members.entity.Members;
import com.large.ticketsystem.members.service.MembersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MembersController {
    private final MembersService membersService;

    @PostMapping("/signup")
    public MembersResponseMsgDto signup(@RequestBody MembersRequestDto membersRequestDto, HttpServletResponse response) {
        return membersService.signup(membersRequestDto, response);
    }

    @PostMapping("/login")
    public MembersResponseMsgDto login(@RequestBody MembersRequestDto membersRequestDto, HttpServletResponse response) {
        return membersService.login(membersRequestDto, response);
    }

    @GetMapping("/{memberId}")
    public MemberCachedDto getMembers(@PathVariable(name = "memberId") Long memberid) {
        return membersService.findOneById(memberid);
    }
}
