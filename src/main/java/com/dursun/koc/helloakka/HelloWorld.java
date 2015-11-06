/**
 * 
 */
package com.dursun.koc.helloakka;

import com.dursun.koc.helloakka.Greeter.MSG;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author TTDKOC
 *
 */
public class HelloWorld extends UntypedActor {
	private final LoggingAdapter LOG = Logging.getLogger(getContext().system(), this);

	@Override
	public void preStart() throws Exception {
		super.preStart();
		ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
		greeter.tell(MSG.GREET, getSelf());
	}

	@Override
	public void aroundPostStop() {
		super.aroundPostStop();
		LOG.info("Stopping...");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
	 */
	@Override
	public void onReceive(Object message) throws Exception {
		LOG.info("Started {}", message);
		Thread.sleep(3000);
		if (message == MSG.DONE) {
			getContext().stop(getSelf());
		}
		LOG.info("Completed {}", message);
	}

}
