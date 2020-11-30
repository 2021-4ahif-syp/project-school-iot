
package at.htl.mqtt;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

/**
 * A bean consuming data from the "values" MQTT topic and applying some conversion.
 * The result is pushed to the "my-data-stream" stream which is an in-memory stream.
 */

@ApplicationScoped
public class ValueProcessor {

    private static final Logger LOG = Logger.getLogger(ValueProcessor.class);

    @Incoming("sample_data")
    public void process(byte[] priceRaw) {
        LOG.info("sample_data: " + new String(priceRaw));
    }
}
