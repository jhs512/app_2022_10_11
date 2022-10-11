package com.ll.exam.app__2022_10_11.app.cash.service;

import com.ll.exam.app__2022_10_11.app.cash.entity.CashLog;
import com.ll.exam.app__2022_10_11.app.cash.repository.CashLogRepository;
import com.ll.exam.app__2022_10_11.app.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashService {
    private final CashLogRepository cashLogRepository;

    public CashLog addCash(Member member, long price, String eventType) {
        CashLog cashLog = CashLog.builder()
                .member(member)
                .price(price)
                .eventType(eventType)
                .build();

        cashLogRepository.save(cashLog);

        return cashLog;
    }
}
