package it.unipd.math.pcd.actors;

/**
 * Created by Alex on 26/01/2016.
 * it.unipd.math.pcd.actors
 */
public class ConcreteActorSystem extends AbsActorSystem {

    @Override
    protected ActorRef createActorReference(ActorMode mode) {
        if (mode == ActorMode.LOCAL)
            return new LocalActorRef();
        else
            throw new IllegalArgumentException();
    }

    @Override
    public void stop(ActorRef<?> actor) {

    }

    @Override
    public void stop() {

    }
}
