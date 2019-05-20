package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.sqlUtils;

public class UpdateServerTest {


	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testUpdateServer() {
		sqlUtils test = new sqlUtils();
		 String results = test.updateServer("2", "London Servers");
		 assertEquals("2", results);
	}

}
