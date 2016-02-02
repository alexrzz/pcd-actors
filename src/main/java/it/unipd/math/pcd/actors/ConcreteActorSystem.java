package it.unipd.math.pcd.actors;

import java.util.Collection;

/**
 * Created by Alex on 26/01/2016.
 * it.unipd.math.pcd.actors
 */
public final class ConcreteActorSystem extends AbsActorSystem {

    private static ConcreteActorSystem instance = null;

    public ConcreteActorSystem() {
        instance = this;
    }

    public static ConcreteActorSystem getInstance() {
        if(instance == null) {
            instance = new ConcreteActorSystem();
        }
        return instance;
    }

    @Override
    protected ActorRef createActorReference(ActorMode mode) {
        if (mode == ActorMode.LOCAL)
            return new LocalActorRef();
        else
            throw new IllegalArgumentException();
    }

    @Override
    public void stop(ActorRef<?> actor) {
        getCorrespondingActor(actor).shutdownActor();
        removeActor(actor);
    }

    @Override
    public void stop() {
        Collection<Actor<?>> collection = getAllActors();
        for(Actor<?> actor : collection) {
            ((AbsActor<?>)actor).shutdownActor();
        }
        collection.clear();
    }

}
