package fr.xebia.jqwik.exercise5;

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
        switch (alert.country().code()) {
            case "IT" -> {
                final int codeForItaly = alert.type().getCodeForItaly();
                this.notificationServiceForItaly.notify(codeForItaly);
            }
            case "US" -> {
                final String codeForUsa = alert.type().getCodeForUsa();
                this.notificationServiceForUsa.notify(codeForUsa);
            }
            default -> {
                final String defaultMessage = alert.type().getDefaultMessage();
                this.defaultNotificationService.notify(defaultMessage);
            }
        }
    }

}
