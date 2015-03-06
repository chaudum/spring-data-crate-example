package io.crate.hellospring;

import io.crate.client.CrateClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.crate.config.AbstractCrateConfiguration;
import org.springframework.data.crate.core.mapping.event.ValidatingCrateEventListener;
import org.springframework.data.crate.core.mapping.schema.CratePersistentEntitySchemaManager;
import org.springframework.data.crate.core.mapping.schema.SchemaExportOption;
import org.springframework.data.crate.repository.config.EnableCrateRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EnableCrateRepositories
class ApplicationConfig extends AbstractCrateConfiguration {

    @Bean
    public CratePersistentEntitySchemaManager cratePersistentEntitySchemaManager() throws Exception {
        return new CratePersistentEntitySchemaManager(crateTemplate(), SchemaExportOption.CREATE);
    }

    // optional
    // only required if you're using different host/port
    @Bean
    public CrateClient crateClient() {
        return new CrateClient("localhost:4300");
    }

    // optional
    // only required because we are using @Email and @NotBlank annotations for validation
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    // optional
    // only required because we are using @Email and @NotBlank annotations for validation
    @Bean
    public ValidatingCrateEventListener validatingCrateEventListener() {
        return new ValidatingCrateEventListener(validator());
    }

}
