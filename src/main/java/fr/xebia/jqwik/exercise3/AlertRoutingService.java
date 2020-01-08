package fr.xebia.jqwik.exercise3;

class AlertRoutingService {

    private final NotificationServiceForItaly notificationServiceForItaly;
    private final NotificationServiceForUsa notificationServiceForUsa;
    private final DefaultNotificationService defautNotificationService;

    AlertRoutingService(final NotificationServiceForItaly notificationServiceForItaly,
                        final NotificationServiceForUsa notificationServiceForUsa,
                        final DefaultNotificationService defautNotificationService) {
        this.notificationServiceForItaly = notificationServiceForItaly;
        this.notificationServiceForUsa = notificationServiceForUsa;
        this.defautNotificationService = defautNotificationService;
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
                this.defautNotificationService.notify(defaultMessage);
        }
    }

}
