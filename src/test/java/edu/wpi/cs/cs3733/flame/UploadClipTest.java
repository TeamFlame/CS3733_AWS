package edu.wpi.cs.cs3733.flame;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import edu.wpi.cs.cs3733.flame.db.VideoClipsDAO;
import edu.wpi.cs.cs3733.flame.http.*;
import edu.wpi.cs.cs3733.flame.model.VideoClip;

public class UploadClipTest extends LambdaTest {

	@Test
	public void UploadClip() {
		VideoClipsDAO dao = new VideoClipsDAO();
		try {
			dao.addClip(new VideoClip("uri", "char", "text", false));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UploadClipResponse up = new UploadClipResponse("error", 400);
		UploadClipResponse up2 = new UploadClipResponse(200);
		Assert.assertEquals(up.toString(), "UploadClipResponse()");
		
		UploadClipRequest req = new UploadClipRequest("Char", "Hi", true, "base64");
		UploadClipRequest req2 = new UploadClipRequest();
		Assert.assertEquals(req.toString(), "UploadClipRequest()");
		
		UploadClipResponse res;
		
		UploadClipHandler h = new UploadClipHandler();
		res = h.handleRequest(req, createContext("upload"));
		Assert.assertEquals(res.toString(), "UploadClipResponse()");
	}

}
