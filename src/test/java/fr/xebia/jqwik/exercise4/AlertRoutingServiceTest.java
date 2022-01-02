package fr.xebia.jqwik.exercise4;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.lifecycle.BeforeTry;

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

    @BeforeTry
    void resetInteractionCount() {
        // Calling Mockito.reset between each try is necessary so that interaction count is reset between examples generated by Jqwik.
        reset(defaultNotificationService);
    }

    @Property
    void should_send_alert_to_specific_service_for_Italian_alert(@ForAll Alert.Type type) {
        final var alert = new Alert(type, ITALY);

        routingService.send(alert);

        then(notificationServiceForItaly).should().notify(type.getCodeForItaly());
        then(notificationServiceForUsa).shouldHaveNoInteractions();
        then(defaultNotificationService).shouldHaveNoInteractions();
    }

    @Property
    void should_send_alert_code_to_specific_service_for_US_alert(@ForAll Alert.Type type) {
        final var alert = new Alert(type, USA);

        routingService.send(alert);

        then(notificationServiceForItaly).shouldHaveNoInteractions();
        then(notificationServiceForUsa).should().notify(type.getCodeForUsa());
        then(defaultNotificationService).shouldHaveNoInteractions();
    }

    /**
     * TODO: Replace 'country' variable by adding an argument to the test method, so that the test does not rely on an example (new Country("JP")) anymore.
     * <hr/>
     * <p>Hint #1: Define a provider class in order to generate parameter with non-primitive type. Cf. <a href="https://jqwik.net/docs/current/user-guide.html#simple-arbitrary-providers">Simple arbitrary providers</a></p>
     * <p>Hint #2: You can use assumptions in order to skip generated values that do not match standard countries. Cf. <a href="https://jqwik.net/docs/current/user-guide.html#assumptions">Assumptions</a></p>
     */
    @Property
    void should_send_alert_message_to_default_service_for_alert_of_standard_country(@ForAll Alert.Type type) {
        final var country = new Country("JP");
        final var alert = new Alert(type, country);

        routingService.send(alert);

        then(notificationServiceForItaly).shouldHaveNoInteractions();
        then(notificationServiceForUsa).shouldHaveNoInteractions();
        then(defaultNotificationService).should().notify(type.getDefaultMessage());
    }

}
