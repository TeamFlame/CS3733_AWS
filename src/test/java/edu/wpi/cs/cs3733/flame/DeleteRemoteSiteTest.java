package edu.wpi.cs.cs3733.flame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.Test;

import edu.wpi.cs.cs3733.flame.http.*;

public class DeleteRemoteSiteTest extends LambdaTest{
	
	@Test
	public void deleteRemoteSiteTest() {
		DeleteRemoteSiteRequest req = new DeleteRemoteSiteRequest();
		DeleteRemoteSiteRequest req2 = new DeleteRemoteSiteRequest("google.com");
		req.setURI("google.com");
		Assert.assertEquals(req2.toString(), "DeleteRemoteSite(google.com)");
		
		DeleteRemoteSiteResponse res = new DeleteRemoteSiteResponse(200);
		DeleteRemoteSiteResponse res2 = new DeleteRemoteSiteResponse(400, "error");
		Assert.assertEquals(res.toString(), "DeleteRemoteSiteResponse()");
		Assert.assertEquals(res2.toString(), "ErrorResult(statusCode=400, err=error)");
		
		DeleteRemoteSiteHandler h = new DeleteRemoteSiteHandler();
		h.handleRequest(req2, createContext("delete"));
	}
}
