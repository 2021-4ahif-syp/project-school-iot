package at.htl.mqtt;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

/**
 * A bean producing random values every 10 seconds.
 * The prices are written to a MQTT topic (prices). The MQTT configuration is specified in the application configuration.
 */
@ApplicationScoped
public class ValueGenerator {

    @Outgoing("4ahif")
    public Flowable<String> produce() {
        return Flowable.interval(10, TimeUnit.SECONDS)
                .map(tick -> {
                    var timestamp = new Timestamp(System.currentTimeMillis());
                    var json = Json.createObjectBuilder()
                            .add("timestamp", timestamp.toString())
                            .add("co2Indoor", Math.random())
                            .add("humidityIndoor", Math.random())
                            .add("temperatureOutdoor", Math.random())
                            .add("window1open", Math.random() % 0.2 == 0)
                            .add("window2open", Math.random() % 0.2 == 0)
                            .add("window3open", Math.random() % 0.2 == 0)
                            .add("window3open", Math.random() % 0.2 == 0)
                            .build();

                    return json.toString();
                });
    }
}
