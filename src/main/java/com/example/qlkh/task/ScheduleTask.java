package com.example.qlkh.task;

import com.example.qlkh.config.JwtTokenBlacklist;
import com.example.qlkh.repository.FileCacheRepository;
import com.example.qlkh.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class ScheduleTask {
    private final VerificationCodeRepository verificationCodeRepository;
    private final JwtTokenBlacklist tokenBlacklist;
    private final FileCacheRepository fileCacheRepository;
    private final Environment env;

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();

        scheduler.setPoolSize(2);
        scheduler.setThreadNamePrefix("scheduled-task-");
        scheduler.setDaemon(true);

        return scheduler;
    }

    @Transactional
    @Scheduled(fixedRateString = "${schedule-config.auto-delete-verification-code: 18000000}", initialDelayString = "60000")// 5 phút một lần
    public void autoDeleteVerificationCode() {
        verificationCodeRepository.removeVerificationCodeExpired();
    }

    @Transactional
    @Scheduled(fixedRateString = "${schedule-config.auto-delete-black-list: 43200000}") // 0.5 ngày một lần
    public void autoCleanupTokenBlacklist() {
        tokenBlacklist.removeExpiredTokens();
    }

    @Transactional
    @Scheduled(fixedRateString = "${schedule-config.auto-delete-file-old: 86400000}", initialDelayString = "60000") // 1 ngày một lần
    public void autoDeleteFileOld() {
        Long threshold = env.getProperty("schedule-config.auto-delete-file-old", Long.class, 86400000L);
        LocalDateTime thresholdTime = LocalDateTime.now().minusSeconds(threshold);
        fileCacheRepository.deleteByCreatedAtBefore(thresholdTime);
    }
}
