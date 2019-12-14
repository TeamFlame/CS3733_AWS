package edu.wpi.cs.cs3733.flame;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import edu.wpi.cs.cs3733.flame.http.*;
import edu.wpi.cs.cs3733.flame.model.RemoteVideoClip;
import edu.wpi.cs.cs3733.flame.model.VideoClip;
public class RemoteVideosResponseTest extends LambdaTest{
	
	@Test
	public void remoteVideosResponseTest() {
		RemoteVideosResponse res = new RemoteVideosResponse(400, "error!");
		RemoteVideosResponse res2 = new RemoteVideosResponse(null, 402);
		
		RemoteVideoClip clip = new RemoteVideoClip("test.com", "Kirk", "hi");
		List<RemoteVideoClip>lists = new ArrayList<RemoteVideoClip>();
		clip.seturl("test2.com");
		clip.setCharacter("Spock");
		clip.setText("Logic");
		lists.add(clip);
		
		Assert.assertEquals(clip.geturl(), "test2.com");
		Assert.assertEquals(clip.getCharacter(), "Spock");
		Assert.assertEquals(clip.getText(), "Logic");
		
		RemoteVideosResponse res3 = new RemoteVideosResponse(lists, 200);
		
		Assert.assertEquals(res2.toString(), "EmptyClipList");
		Assert.assertEquals(res3.toString(), "VideoClip(1)");
		
		VideoClip vid = new VideoClip("fake.com", "John", "Sup", true);
		vid.setBucketURI("fake2.com");
		vid.setCharacter("Jim");
		vid.setText("Hello");
		RemoteVideoClip clip2 = new RemoteVideoClip(vid);
	}

}
