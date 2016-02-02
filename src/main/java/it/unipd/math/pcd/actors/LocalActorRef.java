package it.unipd.math.pcd.actors;

/**
 * Created by Alex on 26/01/2016.
 * it.unipd.math.pcd.actors
 */
public class LocalActorRef<T extends Message> implements ActorRef<T> {

    @Override
    public void send(T message, ActorRef to) {
        Couple couple = new Couple<T, ActorRef<T>>(message, this);
        ((ConcreteActorSystem.getInstance()).getCorrespondingActor(to)).acceptMessage(couple);
    }

    @Override
    public int compareTo(ActorRef o) {
        if (o != null) {
            if(this.equals(o))
                return 0;
            else
                return -1;
        }
        else
            throw new NullPointerException();
    }

}
