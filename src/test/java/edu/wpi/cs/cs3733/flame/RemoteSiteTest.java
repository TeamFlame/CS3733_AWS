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
	}
}
