package com.server.entities;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;

/*
 * Information about client
 * Several threads can work with data on this client. Therefore, I use volatile and AtomicInteger
 */
public class ClientDetail implements CLientDetailsIfc {

	@Getter
	private volatile int clinetId;
	@Getter
	private long timestamp;
	@Getter
	private AtomicInteger countOfConnections;
	
	/*
	 * When creating a new instance, I save the current time
	 */
	public ClientDetail(int clinetId) {
		super();
		this.clinetId = clinetId;
		this.countOfConnections = new AtomicInteger();
		this.timestamp = Instant.now().toEpochMilli();
	}
	
	public ClientDetail() {
		super();
		this.clinetId = 0;
		this.countOfConnections = new AtomicInteger();
	}
	
	public void incCountOfConnections() {
		countOfConnections.incrementAndGet();
	}

	public int getClinetId() {
		return clinetId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public AtomicInteger getCountOfConnections() {
		return countOfConnections;
	}

	@Override
	public String toString() {
		return "ClientDetail [clinetId=" + clinetId + ", timestamp=" + timestamp + ", countOfConnections="
				+ countOfConnections + "]";
	}
	
}
