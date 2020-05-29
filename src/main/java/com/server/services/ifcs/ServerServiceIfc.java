package com.server.services.ifcs;

import java.util.concurrent.CompletableFuture;
import org.springframework.http.HttpStatus;

public interface ServerServiceIfc {
	
	public CompletableFuture<HttpStatus> handleHTTPRequest(int clientId);

}
