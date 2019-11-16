package fr.xebia.jqwik.exercise4;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class AlertRoutingService {

    private final NotificationServiceForItaly notificationServiceForItaly;
    private final NotificationServiceForUsa notificationServiceForUsa;
    private final DefaultNotificationService defautNotificationService;

    void send(final Alert alert) {
        switch (alert.getCountry().getCode()) {
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
