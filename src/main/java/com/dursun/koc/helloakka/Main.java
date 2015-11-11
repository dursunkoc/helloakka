/**
 * 
 */
package com.dursun.koc.helloakka;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author TTDKOC
 *
 */
public class Main {
	private static final ActorSystem system = ActorSystem.create("Main", ConfigFactory.load().getConfig("Main"));
	private static final LoggingAdapter LOG = Logging.getLogger(system, Main.class);
	public static void main(String[] args) {
		LOG.info("MAIN started!");
		LOG.debug("ANY DEBUG LOG");
		ActorRef a = system.actorOf(Props.create(HelloWorld.class), "helloWorld");
		system.actorOf(Props.create(Terminator.class, a), "terminator");
		ActorSelection actorSelection = system.actorSelection("user/helloWorld/greeter");
		actorSelection.tell("HOLA", null);
		LOG.info("MAIN completed!");
	}
}
