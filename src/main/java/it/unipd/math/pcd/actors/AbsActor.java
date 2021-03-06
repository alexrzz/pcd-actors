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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Defines common properties of all actors.
 *
 * @author Riccardo Cardin
 * @version 1.0
 * @since 1.0
 */
public abstract class AbsActor<T extends Message> implements Actor<T> {

    /**
     * The mailbox mechanism
     */
    private final ExecutorService exService = Executors.newSingleThreadExecutor();

    /**
     * Self-reference of the actor
     */
    protected ActorRef<T> self;

    /**
     * Sender of the current message
     */
    protected ActorRef<T> sender;

    /**
     * Sets the self-reference.
     *
     * @param self The reference to itself
     * @return The actor.
     */
    protected final Actor<T> setSelf(ActorRef<T> self) {
        this.self = self;
        return this;
    }

    /**
     * Shutdowns the Actor.
     */
    protected final void shutdownActor() {
        exService.shutdown();
        try {
            if(!exService.awaitTermination(120, TimeUnit.SECONDS))
                exService.shutdownNow();
            if(!exService.awaitTermination(60, TimeUnit.SECONDS))
                System.err.println("Actor did not terminate after 3 minutes, use the force Luke!");
        } catch (InterruptedException e) {
            exService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Sets the sender of the current message
     *
     * @param sender The sender of the current message
     */
    protected final void setSender(ActorRef<T> sender) {
        this.sender = sender;
    }

    /**
     * Accepts an incoming message and queues it for elaboration
     *
     * @param couple The couple formed of a Message and the Message's sender
     */
    protected final void acceptMessage(final Couple<T, ActorRef<T>> couple) {
        exService.execute(new Runnable() {
            @Override
            public void run() {
                setSender(couple.getSender());
                receive(couple.getMessage());
            }
        });
    }

}
