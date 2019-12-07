package edu.wpi.cs.cs3733.flame;

import java.io.*;

import org.junit.Assert;
import org.junit.Test;

import edu.wpi.cs.cs3733.flame.http.*;

public class DeletePlaylistHandlerTest extends LambdaTest{

	@Test
	public void deletePlaylistHandlerTest() {
		DeletePlaylistHandler handler = new DeletePlaylistHandler();
		
		DeletePlaylistRequest req = new DeletePlaylistRequest("PlaylistCreateTest");
		
		DeletePlaylistResponse res = handler.handleRequest(req, createContext("delete"));
		Assert.assertEquals(422, res.statusCode);
	}
}
