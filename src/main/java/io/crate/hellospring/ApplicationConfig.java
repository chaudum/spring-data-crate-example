package io.crate.hellospring;

import io.crate.client.CrateClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.crate.config.AbstractCrateConfiguration;
import org.springframework.data.crate.core.CrateTemplate;
import org.springframework.data.crate.core.mapping.schema.CratePersistentEntitySchemaManager;
import org.springframework.data.crate.core.mapping.schema.SchemaExportOption;
import org.springframework.data.crate.repository.config.EnableCrateRepositories;

@Configuration
@EnableCrateRepositories(basePackages = "io.crate")
class ApplicationConfig extends AbstractCrateConfiguration {

    @Bean
    public CrateClient crateClient() {
        return new CrateClient("localhost:4300");
    }

    @Bean
    public CrateTemplate crateTemplate(CrateClient crateClient) throws Exception {
        return new CrateTemplate(crateClient);
    }

    @Bean
    public CratePersistentEntitySchemaManager cratePersistentEntitySchemaManager() throws Exception {
        return new CratePersistentEntitySchemaManager(crateTemplate(crateClient()), SchemaExportOption.CREATE);
    }

}
