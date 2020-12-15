package at.htl.mqtt;

import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.Flowable;
import io.reactivex.annotations.BackpressureSupport;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * A bean producing random values every 3 seconds.
 * The prices are written to a MQTT topic (prices). The MQTT configuration is specified in the application configuration.
 */
@ApplicationScoped
public class ValueGenerator {

    private Random random = new Random();
    private double temperature = 20;

    @Outgoing("4ahif")
    public Flowable<Double> generate() {
        return Flowable.interval(3, TimeUnit.SECONDS)
                .onBackpressureDrop()
                .map(tick -> {
                    double addToTemperature = random.nextInt(6);
                    int plusOrMinus = random.nextInt(2);

                    if (plusOrMinus == 0){
                        temperature += addToTemperature/10;
                    }
                    else{
                        temperature -= addToTemperature/10;
                    }

                    return temperature;
                });
    }
}
