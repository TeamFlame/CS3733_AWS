package edu.wpi.cs.cs3733.flame;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs.cs3733.flame.db.PlaylistsDAO;
import edu.wpi.cs.cs3733.flame.model.Playlist;

public class PlaylistTest {

	@Test
	public void create() {
		PlaylistsDAO dao = new PlaylistsDAO();
		Playlist playlist = new Playlist("Test Case Playlist");
		try {
			dao.createPlaylist(playlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void delete() {
		PlaylistsDAO dao = new PlaylistsDAO();
		Playlist playlist = new Playlist("Test Case Playlist");
		try {
			dao.deletePlaylist(playlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
