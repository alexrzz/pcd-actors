/**
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2015 Riccardo Cardin
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * <p/>
 * Please, insert description here.
 *
 * @author Riccardo Cardin
 * @version 1.0
 * @since 1.0
 */

/**
 * Please, insert description here.
 *
 * @author Riccardo Cardin
 * @version 1.0
 * @since 1.0
 */
package it.unipd.math.pcd.actors;

import it.unipd.math.pcd.actors.exceptions.NoSuchActorException;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A map-based implementation of the actor system.
 *
 * @author Riccardo Cardin
 * @version 1.0
 * @since 1.0
 */
public abstract class AbsActorSystem implements ActorSystem {

    /**
     * Associates every Actor created with an identifier.
     */
    private Map<ActorRef<?>, Actor<?>> actors;

    public AbsActorSystem() {
        actors = new ConcurrentHashMap<>();
    }

    @Override
    public ActorRef<? extends Message> actorOf(Class<? extends Actor> actor, ActorMode mode) {

        // ActorRef instance
        ActorRef<?> reference;
        try {
            // Create the reference to the actor
            reference = this.createActorReference(mode);
            // Create the new instance of the actor
            Actor actorInstance = ((AbsActor) actor.newInstance()).setSelf(reference);
            // Associate the reference to the actor
            actors.put(reference, actorInstance);

        } catch (InstantiationException | IllegalAccessException e) {
            throw new NoSuchActorException(e);
        }
        return reference;
    }

    @Override
    public ActorRef<? extends Message> actorOf(Class<? extends Actor> actor) {
        return this.actorOf(actor, ActorMode.LOCAL);
    }

    /**
     * Returns the Actor corresponding to the ActorRef parameter
     *
     * @param actorRef The ActorRef about which the corresponding Actor has to be returned
     * @return The Actor
     * @throws it.unipd.math.pcd.actors.exceptions.NoSuchActorException If there's no Actor corresponding
     * to the argument
     */
    protected AbsActor<?> getCorrespondingActor(ActorRef<?> actorRef) {
        if (actors.containsKey(actorRef))
            return (AbsActor<?>) actors.get(actorRef);
        else
            throw new NoSuchActorException();
    }

    /**
     * Returns all the actors contained in actors map
     *
     * @return A Collection view of the values contained in the actors map
     */
    protected Collection<Actor<?>> getAllActors() {
        return actors.values();
    }

    /**
     * Removes the actorRef key and the corresponding Actor value from the actors map
     * @param actorRef The key that needs to be removed
     */
    protected void removeActor(ActorRef<?> actorRef) {
        actors.remove(actorRef);
    }

    /**
     * Creates an ActorRef of the mode type
     *
     * @param mode The mode of the actor
     * @return An ActorRef of the specified mode
     * @throws IllegalArgumentException If the mode argument is invalid
     */
    protected abstract ActorRef createActorReference(ActorMode mode);

}