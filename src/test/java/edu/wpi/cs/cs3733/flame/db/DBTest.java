package edu.wpi.cs.cs3733.flame.db;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import edu.wpi.cs.cs3733.flame.model.VideoClip;

public class DBTest {

	@Test
	public void test() {
		try {
			Connection conn = DatabaseUtil.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getStackTrace().toString());
		}

	}
	
	@Test
	public void test2() {
		VideoClipsDAO dao = new VideoClipsDAO();
		try {
			List<VideoClip> clips = dao.getAllClips();
			for(VideoClip c : clips) {
				System.out.println("URI: " + c.getBucketURI());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail(e.getMessage().toString());
		}
	}	

}
