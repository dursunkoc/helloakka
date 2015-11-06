/**
 * 
 */
package com.dursun.koc.helloakka;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author TTDKOC
 *
 */
public class Greeter extends UntypedActor {
	private final LoggingAdapter LOG = Logging.getLogger(getContext().system(), this);

	public static enum MSG {
		GREET, DONE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
	 */
	@Override
	public void onReceive(Object message) throws Exception {
		LOG.info("{} received.", message);
		if (message == MSG.GREET) {
			LOG.info("Hello World!");
			getSender().tell(MSG.DONE, this.getSelf());
		} else {
			unhandled(message);
		}
	}

	@Override
	public void aroundPostStop() {
		super.aroundPostStop();
		LOG.info("Stopping...");
	}

}
