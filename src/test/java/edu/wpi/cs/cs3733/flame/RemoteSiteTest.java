package edu.wpi.cs.cs3733.flame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import edu.wpi.cs.cs3733.flame.http.*;

import com.amazonaws.util.json.Jackson;

public class RemoteSiteTest extends LambdaTest{

	@Test
	public void remoteSiteTest() {
		CreateRemoteSiteHandler handle = new CreateRemoteSiteHandler();
		
		CreateRemoteSiteRequest req = new CreateRemoteSiteRequest("google.com");
		CreateRemoteSiteRequest req2 = new CreateRemoteSiteRequest();
		Assert.assertEquals(req.getURI(), "google.com");
		Assert.assertEquals(req.toString(), "CreateRemoteSite(google.com)");
		
		CreateRemoteSiteResponse res = new CreateRemoteSiteResponse();
		CreateRemoteSiteResponse res2 = new CreateRemoteSiteResponse(400);
		CreateRemoteSiteResponse res3 = new CreateRemoteSiteResponse("error", 402);
		Assert.assertEquals(res.toString(), "CreateRemoteSiteResponse()");
		
		handle.handleRequest(req, createContext("Create"));
		
		CreateRemoteSiteHandler handle2 = new CreateRemoteSiteHandler();
		try{
			handle.handleRequest(null, createContext("Create"));
		}
		catch (Exception e){
			
		}
		
		DeleteRemoteSiteRequest reqq = new DeleteRemoteSiteRequest();
		DeleteRemoteSiteRequest reqq2 = new DeleteRemoteSiteRequest("google.com");
		reqq.setURI("google.com");
		Assert.assertEquals(reqq2.toString(), "DeleteRemoteSite(google.com)");
		
		DeleteRemoteSiteResponse ress = new DeleteRemoteSiteResponse(200);
		DeleteRemoteSiteResponse ress2 = new DeleteRemoteSiteResponse(400, "error");
		Assert.assertEquals(ress.toString(), "DeleteRemoteSiteResponse()");
		Assert.assertEquals(ress2.toString(), "ErrorResult(statusCode=400, err=error)");
		
		DeleteRemoteSiteHandler h = new DeleteRemoteSiteHandler();
		h.handleRequest(reqq2, createContext("delete"));
	}
	
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
