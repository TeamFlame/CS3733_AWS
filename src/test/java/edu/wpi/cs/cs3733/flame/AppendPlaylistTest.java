package edu.wpi.cs.cs3733.flame;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import edu.wpi.cs.cs3733.flame.http.*;

public class AppendPlaylistTest extends LambdaTest{

	@Test
	public void appendTest() {
		AppendPlaylistHandler h = new AppendPlaylistHandler();
		
		AppendPlaylistRequest req = new AppendPlaylistRequest();
		AppendPlaylistRequest req2 = new AppendPlaylistRequest("Vid", "Playlist");
		
		Assert.assertEquals(req2.toString(), "AppendPlaylist(Vid, Playlist)");
		Assert.assertEquals(req2.getWorkingPlaylist(), "Playlist");
		Assert.assertEquals(req2.getVideoURI(), "Vid");
		
		AppendPlaylistResponse res = new AppendPlaylistResponse();
		AppendPlaylistResponse res2 = new AppendPlaylistResponse(400);
		AppendPlaylistResponse res3 = new AppendPlaylistResponse("error", 400);
		
		Assert.assertEquals(res.toString(), "AppendPlaylistResponse()");
		h.handleRequest(req, createContext("Create"));
	}

}
