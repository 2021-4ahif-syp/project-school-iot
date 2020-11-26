
package at.htl.mqtt;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.reactive.messaging.annotations.Broadcast;

/**
 * A bean consuming data from the "prices" MQTT topic and applying some conversion.
 * The result is pushed to the "my-data-stream" stream which is an in-memory stream.
 */
@ApplicationScoped
public class ValueProcessor {

    @Incoming("values")
    @Outgoing("my-data-stream")
    @Broadcast
    public double process(double value) {
        return value;
    }
}
