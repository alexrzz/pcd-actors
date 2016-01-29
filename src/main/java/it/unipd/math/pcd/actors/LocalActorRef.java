package it.unipd.math.pcd.actors;

/**
 * Created by Alex on 26/01/2016.
 * it.unipd.math.pcd.actors
 */
public class LocalActorRef implements ActorRef<Message> {

    @Override
    public void send(Message message, ActorRef to) {
        //this è l'attore che manda il messaggio
        //costruisco la coppia Messaggio, Mittente
        Couple couple = new Couple<Message, ActorRef<Message>>(message, this);
        //otteniamo l'istanza dell'actorsystem
        //otteniamo l'attore corrispondente a "to"
        //mettiamo il msg nella mailbox con acceptMessage()
        ((ConcreteActorSystem.getInstance()).whoIs(to)).acceptMessage(couple);
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
