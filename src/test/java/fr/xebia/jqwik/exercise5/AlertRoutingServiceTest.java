package fr.xebia.jqwik.exercise5;

import net.jqwik.api.*;

import java.util.Objects;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;

class AlertRoutingServiceTest {

    private static final Country ITALY = new Country("IT");
    private static final Country USA = new Country("US");

    private final NotificationServiceForItaly notificationServiceForItaly = mock(NotificationServiceForItaly.class);
    private final NotificationServiceForUsa notificationServiceForUsa = mock(NotificationServiceForUsa.class);
    private final DefaultNotificationService defaultNotificationService = mock(DefaultNotificationService.class);
    private final AlertRoutingService routingService = new AlertRoutingService(notificationServiceForItaly, notificationServiceForUsa, defaultNotificationService);

    @Property
    void should_send_alert_to_specific_service_for_Italian_alert(@ForAll Alert.Type type) {
        final Alert alert = new Alert(type, ITALY);

        routingService.send(alert);

        then(notificationServiceForItaly).should().notify(type.getCodeForItaly());
        then(notificationServiceForUsa).shouldHaveZeroInteractions();
        then(defaultNotificationService).shouldHaveZeroInteractions();
    }

    @Property
    void should_send_alert_code_to_specific_service_for_US_alert(@ForAll Alert.Type type) {
        final Alert alert = new Alert(type, USA);

        routingService.send(alert);

        then(notificationServiceForItaly).shouldHaveZeroInteractions();
        then(notificationServiceForUsa).should().notify(type.getCodeForUsa());
        then(defaultNotificationService).shouldHaveZeroInteractions();
    }

    /**
     * TODO: Fix test so that it does not have to initialize alerts.
     * <br/>
     * <p>Hint #1: <a href="https://jqwik.net/docs/current/user-guide.html#combining-arbitraries">Combining arbitraries</a></p>
     * <p>Hint #2: <a href="https://jqwik.net/docs/current/user-guide.html#create-your-own-annotations-for-arbitrary-configuration">Create your own annotations for arbitrary configuration</a></p>
     */
    @Property
    void should_send_alert_message_to_default_service_for_alert_of_standard_country(@ForAll Alert.Type type, @ForAll Country country) {
        Assume.that(! Objects.equals(country, ITALY));
        Assume.that(! Objects.equals(country, USA));
        final Alert alert = new Alert(type, country);

        reset(defaultNotificationService);
        routingService.send(alert);

        then(notificationServiceForItaly).shouldHaveZeroInteractions();
        then(notificationServiceForUsa).shouldHaveZeroInteractions();
        then(defaultNotificationService).should().notify(alert.getType().getDefaultMessage());
    }

}
