package com.pusher;

import org.jboss.aerogear.unifiedpush.JavaSender;
import org.jboss.aerogear.unifiedpush.SenderClient;
import org.jboss.aerogear.unifiedpush.message.MessageResponseCallback;
import org.jboss.aerogear.unifiedpush.message.UnifiedMessage;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Thread safe
 */
public class PushNotificationService {

    private static final Logger LOG = Logger.getLogger(PushNotificationService.class.getName());

    private static final PushNotificationService INSTANCE = new PushNotificationService();

    private final BlockingQueue<PushMessage> workQueue;

    private PushNotificationService() {
        workQueue = new LinkedBlockingQueue<>(1000);
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.submit(new PushMessageSender(workQueue));
    }

    public static PushNotificationService getInstance() {
        return INSTANCE;
    }

    public void sendPushNotificationAsync(String deviceToken, String message) {
        try {
            workQueue.put(new PushMessage(deviceToken, message));
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private static class PushMessageSender implements Runnable {
        private final BlockingQueue<PushMessage> workQueue;

        public PushMessageSender(BlockingQueue<PushMessage> workQueue) {
            this.workQueue = workQueue;
        }

        @Override
        public void run() {

            while (!Thread.currentThread().isInterrupted()) {
                try {

                    PushMessage pushMessage = workQueue.take();

                    LOG.log(Level.FINE, "Sending PushMessage to " + pushMessage);

                    JavaSender defaultJavaSender = new SenderClient.Builder("https://jbossunifiedpush-miguelk.rhcloud.com/ag-push/").build();
                    UnifiedMessage unifiedMessage = new UnifiedMessage.Builder()
                            .pushApplicationId("e7c28757-b4a4-4b63-81ae-5602bedbc5c6")
                            .masterSecret("22041a9b-f5a9-40ee-bf03-aadc28809ca3").aliases(Arrays.asList(pushMessage.getDeviceToken())) //Target device
                            .alert(pushMessage.getMessage())
                            .contentAvailable()
                            .badge("+1") //Always increase the badge with +1
                                    // To receive push notification when app is in the background you need to format notification like below:
                           // .attribute("UpdatedStationId", pushMessage.getUpdatedStationId()) //Changed Station/Channel
                            // .attribute("UpdatedChannelId", pushMessage.getUpdatedChannelName()) //Changed Station/Channel
                         //   .attribute("DeviceToken", pushMessage.getDeviceToken())
                            .build();

                    defaultJavaSender.send(unifiedMessage, new MessageResponseCallback() {
                        @Override
                        public void onComplete(int statusCode) {
                            LOG.fine("Done sending push message");
                       //     Statistics.getInstance().increaseAsync(Statistics.PUSH_MESSAGES_SUCCESS_COUNTER);
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            LOG.log(Level.SEVERE, "Failed sending push message", throwable);
                            //     Statistics.getInstance().increaseAsync(Statistics.PUSH_MESSAGES_FAILED_COUNTER);
                        }
                    });
                } catch (InterruptedException ex) {
                    LOG.warning("Failed sending");
                    Thread.currentThread().interrupt();
                    break;
                } catch (Exception e) {
                    LOG.log(Level.SEVERE, "Error in PushNotificationService job", e);
                }
            }
        }
    }

    private static class PushMessage {

        private final String deviceToken;
        private final String message;

        private PushMessage(String deviceToken, String message) {
            this.deviceToken = deviceToken;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public String getDeviceToken() {
            return deviceToken;
        }
    }

}
