package edu.wpi.cs.cs3733.flame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.Test;

import edu.wpi.cs.cs3733.flame.http.*;

public class UnmarkSegmentTest extends LambdaTest{
	
	@Test
	public void unmarkSegTest() {
		UnmarkLocalSegmentResponse res = new UnmarkLocalSegmentResponse(200);
		UnmarkLocalSegmentResponse res2 = new UnmarkLocalSegmentResponse(400, "error");
		Assert.assertEquals(res.toString(), "Unmark Local Segment Response()");
		Assert.assertEquals(res2.toString(), "Unmark Local Segment Response ErrorResult(statusCode=400, err=error)");
		
		UnmarkLocalSegmentRequest req = new UnmarkLocalSegmentRequest();
		UnmarkLocalSegmentRequest req2 = new UnmarkLocalSegmentRequest("google.com");
		req2.setBucketURI("bing.com");
		Assert.assertEquals(req2.toString(), "Unmark Local Segment (bing.com)");
		
		UnmarkLocalSegmentHandler handler = new UnmarkLocalSegmentHandler();
		handler.handleRequest(req2, createContext("Create"));
	}
}
