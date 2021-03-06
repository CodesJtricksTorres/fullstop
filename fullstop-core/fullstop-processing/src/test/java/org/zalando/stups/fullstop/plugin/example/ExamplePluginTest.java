package org.zalando.stups.fullstop.plugin.example;

import com.amazonaws.services.cloudtrail.processinglibrary.model.CloudTrailEvent;
import com.amazonaws.services.cloudtrail.processinglibrary.model.CloudTrailEventData;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.plugin.metadata.PluginMetadata;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Simple test for the plugin itself. It should be possible to test a plugin completely.
 *
 * @author jbellmann
 */
// @Ignore
public class ExamplePluginTest {

    private CloudTrailEvent event;

    private CloudTrailEventData eventData;

    @Before
    public void setUp() {
        event = Mockito.mock(CloudTrailEvent.class);
        eventData = Mockito.mock(CloudTrailEventData.class);
    }

    @Test
    public void pluginDoesNotSupportEvent() {
        Mockito.when(eventData.getEventName()).thenReturn("Persist");
        Mockito.when(eventData.getEventSource()).thenReturn("ec2.amazonaws.com");
        Mockito.when(event.getEventData()).thenReturn(eventData);

        final ExamplePlugin plugin = new ExamplePlugin();

        Assertions.assertThat(plugin.supports(event)).isFalse();

    }

    @Test
    public void pluginSupportsEvent() {
        Mockito.when(eventData.getEventName()).thenReturn("Delete");
        Mockito.when(eventData.getEventSource()).thenReturn("ec2.amazonaws.com");
        Mockito.when(event.getEventData()).thenReturn(eventData);

        final ExamplePlugin plugin = new ExamplePlugin();
        Assertions.assertThat(plugin.supports(event)).isTrue();

        final PluginMetadata metadata = plugin.getMetadata();
        assertThat(metadata).isNotNull();
        assertThat(metadata.getName()).isNotNull();
        assertThat(metadata.getName()).isNotEmpty();
        assertThat(metadata.getVersion()).isNotNull();
        assertThat(metadata.getName()).isEqualTo(plugin.getClass().getName());
        assertThat(metadata.getVersion()).isNotEmpty();
        assertThat(metadata.getVersion()).isEqualTo("0.5.6");
    }

}
