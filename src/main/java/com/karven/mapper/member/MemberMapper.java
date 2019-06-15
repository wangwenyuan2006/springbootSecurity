package com.karven.mapper.member;

import com.karven.entity.member.Member;
import com.karven.entity.member.MemberExample;

import java.util.List;

public interface MemberMapper {

    int insert(Member member);

    List<Member> selectByExample(MemberExample memberExample);
}
