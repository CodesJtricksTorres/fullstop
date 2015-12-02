package org.zalando.stups.fullstop.plugin.scm.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zalando.kontrolletti.KontrollettiOperations;
import org.zalando.stups.clients.kio.KioOperations;
import org.zalando.stups.pierone.client.PieroneOperations;
import org.zalando.stups.fullstop.events.UserDataProvider;
import org.zalando.stups.fullstop.plugin.scm.ScmRepositoryPlugin;
import org.zalando.stups.fullstop.violation.ViolationSink;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration
public class ScmRepositoryPluginAutoConfigurationTest {

    @Autowired(required = false)
    private ScmRepositoryPlugin scmRepositoryPlugin;

    @Test
    public void testScmRepositoryPlugin() throws Exception {
        assertThat(scmRepositoryPlugin).isNotNull();
    }

    @Configuration
    @EnableAutoConfiguration
    static class TestConfig {

        @Bean ViolationSink violationSink() {
            return mock(ViolationSink.class);
        }

        @Bean KioOperations kioOperations() {
            return mock(KioOperations.class);
        }

        @Bean PieroneOperations pieroneOperations() {
            return mock(PieroneOperations.class);
        }

        @Bean KontrollettiOperations kontrollettiOperations() {
            return mock(KontrollettiOperations.class);
        }

        @Bean UserDataProvider userDataProvider() {
            return mock(UserDataProvider.class);
        }
    }
}
