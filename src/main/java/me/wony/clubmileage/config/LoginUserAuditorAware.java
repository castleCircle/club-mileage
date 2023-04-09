package me.wony.clubmileage.config;

import java.util.Optional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class LoginUserAuditorAware implements AuditorAware<Long> {

  @Override
  public Optional<Long> getCurrentAuditor() {
    return Optional.ofNullable(1L);
  }
}
