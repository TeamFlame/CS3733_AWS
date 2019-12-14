package edu.wpi.cs.cs3733.flame;

import java.io.*;

import org.junit.Assert;
import org.junit.Test;

import edu.wpi.cs.cs3733.flame.http.*;

public class DeleteVideoSegmentTest extends LambdaTest{
	
	@Test
	public void deleteVideoHandlerTest() {
		DeleteVideoHandler handler = new DeleteVideoHandler();
		
		DeleteVideoRequest req = new DeleteVideoRequest("uri");
		req.setBucketURI("uri");
		DeleteVideoRequest req2 = new DeleteVideoRequest();
		
		DeleteVideoResponse res = handler.handleRequest(req, createContext("delete"));
		//Assert.assertFalse(200, res.statusCode);
	}
	
	@Test
	public void deleteVideoResponseRequestTest() {
		DeleteVideoResponse res = new DeleteVideoResponse(200);
		Assert.assertEquals(res.toString(), "DeleteResponse()");
		DeleteVideoResponse res2 = new DeleteVideoResponse(400, "error");
		Assert.assertEquals(res2.toString(), "ErrorResult(statusCode=400, err=error)");
	}
}
