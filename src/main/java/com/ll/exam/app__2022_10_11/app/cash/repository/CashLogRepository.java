package com.ll.exam.app__2022_10_11.app.cash.repository;

import com.ll.exam.app__2022_10_11.app.cash.entity.CashLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashLogRepository extends JpaRepository<CashLog, Long> {
}
