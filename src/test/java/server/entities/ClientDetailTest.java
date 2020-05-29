package server.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.server.entities.ClientDetail;

class ClientDetailTest {

	private ClientDetail cd = new ClientDetail(1);
	
	@Test
	void testIncCountOfConnections() {
		assertEquals(cd.getCountOfConnections().get(), 0);
		cd.incCountOfConnections();
		assertNotEquals(cd.getCountOfConnections().get(), 0);
		assertEquals(cd.getCountOfConnections().get(), 1);
	}

	@Test
	void testGetClinetId() {
		assertEquals(cd.getClinetId(), 1);
	}

	@Test
	void testGetTimestamp() {
		assertTrue(cd.getTimestamp() > 0);
	}

	@Test
	void testGetCountOfConnections() {
		assertEquals(cd.getCountOfConnections().get(), 0);
	}

}
