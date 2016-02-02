package it.unipd.math.pcd.actors;

/**
 * Created by Alex on 29/01/2016.
 * it.unipd.math.pcd.actors
 */
public class Couple<T extends Message, A extends ActorRef<T>> {
    private T message;
    private A sender;
    public Couple(T message, A sender){
        this.message = message;
        this.sender = sender;
    }
    protected T getMessage() {
        return message;
    }
    protected ActorRef<T> getSender() {
        return sender;
    }
}
