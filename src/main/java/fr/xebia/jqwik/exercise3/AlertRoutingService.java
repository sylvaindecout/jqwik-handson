package fr.xebia.jqwik.exercise3;

class AlertRoutingService {

    private final NotificationServiceForItaly notificationServiceForItaly;
    private final NotificationServiceForUsa notificationServiceForUsa;
    private final DefaultNotificationService defaultNotificationService;

    AlertRoutingService(final NotificationServiceForItaly notificationServiceForItaly,
                        final NotificationServiceForUsa notificationServiceForUsa,
                        final DefaultNotificationService defaultNotificationService) {
        this.notificationServiceForItaly = notificationServiceForItaly;
        this.notificationServiceForUsa = notificationServiceForUsa;
        this.defaultNotificationService = defaultNotificationService;
    }

    void send(final Alert alert) {
        switch (alert.getCountry()) {
            case "IT":
                final int codeForItaly = alert.getType().getCodeForItaly();
                this.notificationServiceForItaly.notify(codeForItaly);
                break;
            case "US":
                final String codeForUsa = alert.getType().getCodeForUsa();
                this.notificationServiceForUsa.notify(codeForUsa);
                break;
            default:
                final String defaultMessage = alert.getType().getDefaultMessage();
                this.defaultNotificationService.notify(defaultMessage);
        }
    }

}
