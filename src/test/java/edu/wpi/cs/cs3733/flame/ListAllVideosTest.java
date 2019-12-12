package edu.wpi.cs.cs3733.flame;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import edu.wpi.cs.cs3733.flame.http.*;
import edu.wpi.cs.cs3733.flame.model.VideoClip;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class ListAllVideosTest extends LambdaTest {
	
    @Test
    public void testGetList() throws IOException {
    	VideosHandler handler = new VideosHandler();

        AllClipsResponse resp = handler.handleRequest(null, createContext("list"));
        
        boolean hasE = false;
        for (VideoClip c : resp.list) {
        	System.out.println(c.getBucketURI());
        	if (c.getBucketURI().equals("e")) { hasE = true; break; }
        }
        
        Assert.assertEquals(200, resp.statusCode);
    }
    
    @Test
    public void testAllClipsResponse() {
    	AllClipsResponse res = new AllClipsResponse(400, "error");
    	Assert.assertEquals(res.toString(), "VideoClip(0)");
    	
    	AllClipsResponse res2 = new AllClipsResponse(null, 200);
    	Assert.assertEquals(res2.toString(), "EmptyClipList");
    }

}
