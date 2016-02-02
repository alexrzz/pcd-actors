package it.unipd.math.pcd.actors;

/**
 * Created by Alex on 29/01/2016.
 * it.unipd.math.pcd.actors
 */
public class Couple<T extends Message, A extends ActorRef<T>> {

    /**
     * Class composed by a message and its sender
     */

    private T message;
    private A sender;

    /**
     * Creates a new Couple with message and sender fields based on the given message and sender
     *
     * @param message The message to use for attribute initialization
     * @param sender The sender to assign for attribute initialization
     */
    public Couple(T message, A sender){
        this.message = message;
        this.sender = sender;
    }

    /**
     * Returns the message
     *
     * @return The message
     */
    protected T getMessage() {
        return message;
    }

    /**
     * Returns the sender
     *
     * @return The sender
     */
    protected ActorRef<T> getSender() {
        return sender;
    }

}
