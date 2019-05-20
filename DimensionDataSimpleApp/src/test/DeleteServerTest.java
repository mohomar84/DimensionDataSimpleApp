package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.sqlUtils;

public class DeleteServerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDeleteServer() {
		
		sqlUtils test = new sqlUtils();
		 String results = test.deleteServer("2");
		 assertEquals("2", results);
	}

}
