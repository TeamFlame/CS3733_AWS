package edu.wpi.cs.cs3733.flame;

import org.junit.Assert;
import org.junit.Test;

import edu.wpi.cs.cs3733.flame.http.*;

public class DeleteClipFromPlaylistTest extends LambdaTest{
	@Test
	public void deleteClipFromPlaylistTest() {
		DeleteClipFromPlaylistResponse res = new DeleteClipFromPlaylistResponse();
		DeleteClipFromPlaylistResponse res2 = new DeleteClipFromPlaylistResponse(200);
		DeleteClipFromPlaylistResponse res3 = new DeleteClipFromPlaylistResponse("error", 400);
		
		Assert.assertEquals(res.toString(), "DeleteClipFromPlaylistResponse()");
		
		DeleteClipFromPlaylistRequest req = new DeleteClipFromPlaylistRequest();
		DeleteClipFromPlaylistRequest req2 = new DeleteClipFromPlaylistRequest("Blarf", "BlarfPlaylist22");
		Assert.assertEquals(req2.getVideoURI(), "Blarf");
		Assert.assertEquals(req2.getWorkingPlaylist(), "BlarfPlaylist22");
		Assert.assertEquals(req2.toString(), "DeleteClipFromPlaylistRequest(Blarf, BlarfPlaylist22)");
		
		DeleteClipFromPlaylistHandler h = new DeleteClipFromPlaylistHandler();
		h.handleRequest(req, createContext("Create"));
	}
}
