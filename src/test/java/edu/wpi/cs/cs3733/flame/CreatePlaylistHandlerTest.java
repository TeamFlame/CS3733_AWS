package edu.wpi.cs.cs3733.flame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.util.json.Jackson;

import edu.wpi.cs.cs3733.flame.http.CreatePlaylistRequest;
import edu.wpi.cs.cs3733.flame.http.CreatePlaylistResponse;
import edu.wpi.cs.cs3733.flame.http.DeletePlaylistRequest;
import edu.wpi.cs.cs3733.flame.http.DeletePlaylistResponse;

public class CreatePlaylistHandlerTest extends LambdaTest{
	
	@Test
	public void createPlaylistHandler() {
		CreatePlaylistHandler createPlaylistHandler = new CreatePlaylistHandler();
		
		CreatePlaylistRequest req = new CreatePlaylistRequest("PlaylistCreateTest");
		
		CreatePlaylistResponse res = createPlaylistHandler.handleRequest(req, createContext("Create"));
		Assert.assertEquals(200, res.statusCode);
		
		DeletePlaylistHandler handler = new DeletePlaylistHandler();
		
		DeletePlaylistRequest reqq = new DeletePlaylistRequest("PlaylistCreateTest");
		
		DeletePlaylistResponse ress = handler.handleRequest(reqq, createContext("delete"));
	}
}
