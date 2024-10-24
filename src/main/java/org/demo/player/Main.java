package org.demo.player;

/**
 * Entry point for the application. Initializes Player instances and starts communication.
 */
public class Main {
    public static void main(String[] args) {

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        // here player1 is sender and player2 is responder
        // this is PlayerCommunicator is created to manage the messaging between the two players.
        PlayerCommunicator communicator = new PlayerCommunicator(player1, player2);

        try {
            communicator.startCommunication();
        } catch (InterruptedException e) {
            // If the communication is interrupted, the exception is caught, the current thread's interrupt status is set,
            // and a message is printed to indicate the interruption.
            Thread.currentThread().interrupt();
            System.out.println("Communication interrupted.");
        }
    }
}
