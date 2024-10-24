package org.demo.player;

/**
 * Manages the communication process between two Player instances.
 * It orchestrates sending and receiving messages until a stop condition is met (here the stop condition is - maximum of 10 messages).
 */
public class PlayerCommunicator {
    private final Player initiator;
    private final Player responder;

    public PlayerCommunicator(Player initiator, Player responder) {
        this.initiator = initiator;
        this.responder = responder;
    }

    /*
        Initiates communication between two players, sending and receiving messages.
        It sends a total of up to 10 messages, with each message followed by a corresponding reply from the responder. The method will terminate early if a reply is not received.
        like a null reply is encountered, the method will exit the communication loop, and a message will indicate that the communication has completed.
    */
    public void startCommunication() throws InterruptedException {
        // Loop for a maximum of 10 messages
        for (int i = 0; i < 10; i++) {
            // Initiator sends a message to the responder
            initiator.sendMessage(responder);

            // Responder waits for a reply
            String reply = responder.waitForReply();
            System.out.println("Round : " + (i + 1) + ": Received reply: " + reply);

            if (reply == null) {
                System.out.println("No reply received. Exiting communication.");
                break;
            }
        }
        System.out.println("Communication completed.");
    }


}
