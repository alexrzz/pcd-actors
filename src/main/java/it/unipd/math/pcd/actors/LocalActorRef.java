package it.unipd.math.pcd.actors;

/**
 * Created by Alex on 26/01/2016.
 * it.unipd.math.pcd.actors
 */
public class LocalActorRef implements ActorRef {

    @Override
    public void send(Message message, ActorRef to) {
    }

    @Override
    public int compareTo(Object o) {
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
