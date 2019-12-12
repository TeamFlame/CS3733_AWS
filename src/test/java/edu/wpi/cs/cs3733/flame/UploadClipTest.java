package edu.wpi.cs.cs3733.flame;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs.cs3733.flame.db.VideoClipsDAO;
import edu.wpi.cs.cs3733.flame.model.VideoClip;

public class UploadClipTest {

	@Test
	public void UploadClip() {
		VideoClipsDAO dao = new VideoClipsDAO();
		try {
			dao.addClip(new VideoClip("uri", "char", "text", false));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
