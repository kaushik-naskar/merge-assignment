package com.kaushik.mergeassignment.configurations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        String author = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("AuditorAwareImpl: {}", author);
        return Optional.of(author);
    }
}
