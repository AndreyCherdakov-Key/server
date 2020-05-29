package com.server.services.impls;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.server.constants.ConstsCfg;
import com.server.entities.CLientDetailsIfc;
import com.server.entities.ClientDetail;

/*
 * Management system
 * @author Andrey Cherdakov
 */
@Component
@Lazy
public class ServerSingleton {

	private static Logger logger;
	private static volatile ServerSingleton serverSingleton;
	private Map<Integer, CLientDetailsIfc> thresholds;
	
	private ServerSingleton() {
		logger = LoggerFactory.getLogger(ServerSingleton.class);
		thresholds = new ConcurrentHashMap<Integer, CLientDetailsIfc>();
	}
	
	/*
	 * My Singleton works with threads, a situation may arise when several threads start creating my Singleton, so 
	 * the class variable is declared as volatail so that it does not use the cache. 
	 * In the method, I used synchronized so that creating a Singleton would do one thread.
	 */
	public static ServerSingleton getInstance() {
		if (serverSingleton == null) {
			synchronized (ServerSingleton.class) {
				if (serverSingleton == null) {
					serverSingleton = new ServerSingleton();
				}
			}
		}
		return serverSingleton;
	}
	
	/*
	 * Main method
	 */
	public boolean checkClientId(int clientId) {
		
		Map<Integer, CLientDetailsIfc> tmpThresholds = getInstance().thresholds;
		
		/*
		 * I get the client id. I am looking for this id in the map
		 * If there is no such id, a new record is created in the map: key - client id; value - new instance of the ClientDetails class.
		 */
		
		if (!tmpThresholds.containsKey(clientId)) {
			addNewClient(clientId);
			return true;
		}
		
		logger.info("handle request in new thread " + Thread.currentThread().getName() + " :: " + tmpThresholds.get(clientId));
		
		/*
		 * If my map have key client id i get it
		 */
		CLientDetailsIfc client = tmpThresholds.get(clientId);
		long currentTimestamp = Instant.now().toEpochMilli();
		
		/*
		 * I compare the current time with the time stored in this client’s class instance
		 * If more than 5 seconds have passed, I delete the instance of this client and create a new instance 
		 * in which the new time value will be saved and all counters will be reset.
		 */
		
		if (Long.compare(currentTimestamp - client.getTimestamp(), ConstsCfg.THRESHOLD_TIMEOUT) > 0) {
			tmpThresholds.remove(clientId);
			addNewClient(clientId);
			return true;
		}
		
		/*
		 * If the request came within 5 seconds from the same client, I check the connection counter with the maximum allowable value. 
		 * I increase the connection count by one
		 */
		if (client.getCountOfConnections().get() <= ConstsCfg.MAX_CONNECTIONS) {
			updClient(client);
			return true;
		}
		/*
		 * If within 5 seconds the client sent a request more than 5 times, I increase the connection counter by one
		 */
		updClient(client);
		return false;
	}
	
	private static void addNewClient(int clientId) {
		getInstance().thresholds.put(clientId, new ClientDetail(clientId));
	}
	
	private static void updClient(CLientDetailsIfc client) {
		client.incCountOfConnections();
		getInstance().thresholds.put(client.getClinetId(), client);
	}

}
