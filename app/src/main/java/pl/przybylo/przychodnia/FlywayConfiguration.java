package pl.przybylo.przychodnia;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static pl.przybylo.przychodnia.Profile.DROP_DB;

@Configuration
public class FlywayConfiguration {

    @Bean
    @Profile(DROP_DB)
    public FlywayMigrationStrategy clean() {
        return (flyway) -> {
            flyway.clean();
            flyway.migrate();
        };
    }

}
