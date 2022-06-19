package de.sportfest22.service.sportfestservice;

import net.myplayplanet.services.cluster.SpringClusterBuilder;
import net.myplayplanet.services.cluster.config.SpringConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(
        scanBasePackageClasses = {SpringConfiguration.class},
        scanBasePackages = {"de.sportfest22.service"}
)
@EnableSwagger2
@ConfigurationPropertiesScan("de.sportfest22.service.sportfestservice.config")
public class SportfestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportfestServiceApplication.class, args);
    }

    @Bean
    public SpringClusterBuilder springClusterBuilder() {
        return (SpringClusterBuilder) new SpringClusterBuilder().withDefault();
    }
}
