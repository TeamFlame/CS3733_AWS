package edu.wpi.cs.cs3733.flame;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs.cs3733.flame.db.PlaylistsDAO;
import edu.wpi.cs.cs3733.flame.model.*;

public class PlaylistVideosTest {

	@Test
	public void getItems() {
		PlaylistsDAO dao = new PlaylistsDAO();
		try {
			dao.getPlaylistItems("Playlist");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getPlaylist() {
		PlaylistsDAO dao = new PlaylistsDAO();
		try {
			dao.getPlaylist("Playlist");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
