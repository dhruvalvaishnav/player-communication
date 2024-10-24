package org.demo.player;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Represents a Player that can send and receive messages.
 * Each player maintains a message counter to track sent messages.
 */
public class Player {

    // name of the player
    private final String name;

    // used blocking queue because it is concurrent execution of player's messages, so this maintains the thread safety and also follows first in first out (FIFO) order.
    private final BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

    // message counter initialize as 0 to increment based on the communication
    private int messageCounter = 0;

    // for creation of the player object by its name
    public Player(String name) {
        this.name = name;
    }

    /*
        This method is used to send message to the receiver, and also increment the counter as communication go.
     */
    public void sendMessage(Player receiver) {
        String message = "Message : " + messageCounter++;
        System.out.println(name + " sending: " + message);
        receiver.receiveMessage(message);
    }

    /*
        This method is used to processes an incoming message and sends a reply.
        This method is invoked when the player receives a message from another player.
        It prints the reply in format is: "<received message> | Reply <message counter>", (where <message counter> represents the number of replies sent by this player)

        and adds the reply to the message queue for the sender to retrieve.
     */
    public void receiveMessage(String message) {
        System.out.println(name + " received: " + message);
        String reply = message + " | Reply " + messageCounter;
        messageCounter++;
        System.out.println(name + " replying: " + reply);
        messageQueue.add(reply);
    }

    /*
         This method waits for a reply from the message queue.
         This method blocks the current thread until a message is available in the queue.
         It retrieves and removes the head of the queue, ensuring that the player can process incoming messages in a FIFO (First In, First Out) order.
         If the queue is empty, the thread will wait until a message is added to the queue by the sender.
     */
    public String waitForReply() throws InterruptedException {
        // take method: Retrieves and removes the head of this queue, waiting if necessary until an element becomes available.
        return messageQueue.take();
    }


    public int getMessageCounter() {
        return messageCounter;
    }
}
