package it.unipd.math.pcd.actors;

/**
 * Created by Alex on 29/01/2016.
 * it.unipd.math.pcd.actors
 */
public class Couple<M extends Message, A extends ActorRef<M>> {
    private M message;
    private A sender;
    public Couple(M message, A sender){
        this.message = message;
        this.sender = sender;
    }
    protected M getMessage() {
        return message;
    }
    protected ActorRef<M> getSender() {
        return sender;
    }
}
