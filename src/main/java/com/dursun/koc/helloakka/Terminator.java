/**
 * 
 */
package com.dursun.koc.helloakka;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author TTDKOC
 *
 */
public class Terminator extends UntypedActor {

	private final LoggingAdapter LOG = Logging.getLogger(getContext().system(), this);
	private final ActorRef ref;

	public Terminator(ActorRef ref) {
		this.ref = ref;
		getContext().watch(ref);
	}

	@Override
	public void onReceive(Object msg) {
		if (msg instanceof Terminated) {
			LOG.info("{} has terminated, shutting down system", ref.path());
			getContext().system().terminate();
		} else {
			LOG.info("{} Terminator received from {}", msg, ref.path());
			unhandled(msg);
		}
	}
}