package com.karven.service.member;

import com.karven.entity.member.Member;

import java.util.List;

public interface MemberService {
    String getToken(String appId);

    int insert(Member member);

    int save(Member member);

    List<Member> selectAll();
}
