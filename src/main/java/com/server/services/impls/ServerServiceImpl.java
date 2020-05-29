package com.server.services.impls;

import java.util.concurrent.CompletableFuture;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.server.services.ifcs.ServerServiceIfc;

@Service
public class ServerServiceImpl implements ServerServiceIfc {
	
	/*
	 * Async - start that method in new thread. I handle each HTTP request in separate thread
	 */
	@Async("CustomAsyncExecutor")
	public CompletableFuture<HttpStatus> handleHTTPRequest(int clientId) {
		/*
		 * Request processing from the same client is carried out in different threads. I must to have a management system (ServerSingleton).
		 * Management system it s a Singleton class, he have one instance.
		 */
		return ServerSingleton.getInstance().checkClientId(clientId) ? 
				CompletableFuture.completedFuture(HttpStatus.OK) : 
					CompletableFuture.completedFuture(HttpStatus.SERVICE_UNAVAILABLE);
	}

}
