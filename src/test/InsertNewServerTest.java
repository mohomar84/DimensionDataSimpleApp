package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.sqlUtils;
import model.Server;

public class InsertNewServerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsertNewServer() {
		sqlUtils test = new sqlUtils();
		Server newserver = new Server();
		
		newserver.setId(5);
		newserver.setName("NewYork Servers");
		
		 int results = test.insertNewServer(newserver);
		 assertEquals(5, results);
	}

}
