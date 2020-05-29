package com.server.entities;

import java.util.concurrent.atomic.AtomicInteger;

public interface CLientDetailsIfc {

	public int getClinetId();
	public long getTimestamp();
	public void incCountOfConnections();
	public AtomicInteger getCountOfConnections();
	
}
