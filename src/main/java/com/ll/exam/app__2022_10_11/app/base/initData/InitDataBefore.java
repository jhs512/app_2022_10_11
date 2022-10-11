package com.ll.exam.app__2022_10_11.app.base.initData;

import com.ll.exam.app__2022_10_11.app.member.entity.Member;
import com.ll.exam.app__2022_10_11.app.member.service.MemberService;

public interface InitDataBefore {
    default void before(MemberService memberService) {
        Member member1 = memberService.join("user1", "1234", "user1@test.com");
        Member member2 = memberService.join("user2", "1234", "user2@test.com");
    }
}
