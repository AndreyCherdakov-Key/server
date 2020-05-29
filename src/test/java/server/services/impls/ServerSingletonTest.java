package server.services.impls;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.server.services.impls.ServerSingleton;

class ServerSingletonTest {

	@Test
	void testGetInstance() {
		assertNotNull(ServerSingleton.getInstance());
		ServerSingleton.class.isInstance(ServerSingleton.getInstance());
	}

	@Test
	void testCheckClientId() {
		assertTrue(ServerSingleton.getInstance().checkClientId(1));
		Executable ex = () -> ServerSingleton.getInstance().checkClientId(1);
		assertAll(ex);
		assertDoesNotThrow(ex);
	}

}
