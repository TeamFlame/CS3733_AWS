package edu.wpi.cs.cs3733.flame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.Test;

import edu.wpi.cs.cs3733.flame.http.*;

public class MarkSegmentTest extends LambdaTest{

	@Test
	public void markSegTest() {
		MarkLocalSegmentResponse res = new MarkLocalSegmentResponse(200);
		MarkLocalSegmentResponse res2 = new MarkLocalSegmentResponse(400, "error");
		Assert.assertEquals(res.toString(), "Mark Local Segment Response()");
		Assert.assertEquals(res2.toString(), "ErrorResult(statusCode=400, err=error)");
		
		MarkLocalSegmentRequest req = new MarkLocalSegmentRequest();
		MarkLocalSegmentRequest req2 = new MarkLocalSegmentRequest("google.com");
		req2.setBucketURI("bing.com");
		Assert.assertEquals(req2.toString(), "Mark Local Segment (bing.com)");
		
		MarkLocalSegmentHandler handler = new MarkLocalSegmentHandler();
		handler.handleRequest(req2, createContext("Create"));
	}
}
