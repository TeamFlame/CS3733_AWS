package edu.wpi.cs.cs3733.flame;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.cs3733.flame.db.PlaylistsDAO;
import edu.wpi.cs.cs3733.flame.http.*;
import edu.wpi.cs.cs3733.flame.model.Playlist;
import edu.wpi.cs.cs3733.flame.model.PlaylistItem;

public class PlaylistTest extends LambdaTest{

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
	
	@Test
	public void getAllPlaylistItemsTest() {
		PlaylistsDAO dao = new PlaylistsDAO();
		List<PlaylistItem> allItems;
		try {
			allItems = dao.getAllPlaylistItems();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void createPlaylistHandlerTest() {
		CreatePlaylistHandler createPlaylistHandler = new CreatePlaylistHandler();
		try {
			createPlaylistHandler.createPlaylist("PlaylistCreateTest");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		CreatePlaylistRequest req = new CreatePlaylistRequest("PlaylistCreateTest");
		CreatePlaylistRequest req2 = new CreatePlaylistRequest();
		assertEquals(req.getName(), "PlaylistCreateTest");
		assertEquals(req.toString(), "CreatePlaylist(PlaylistCreateTest)");
		
		CreatePlaylistResponse res = new CreatePlaylistResponse(200);
		CreatePlaylistResponse res2 = new CreatePlaylistResponse();
		CreatePlaylistResponse res3 = new CreatePlaylistResponse("error", 200);
		assertEquals(res.toString(), "CreatePlaylistResponse()");
	}
	
	@Test
	public void deletePlaylistHandlerTest() {
		//DeletePlaylistHandler deletePlaylistHandler = new DeletePlaylistHandler();
		
		DeletePlaylistRequest req = new DeletePlaylistRequest("PlaylistDeleteTest");
		DeletePlaylistRequest req2 = new DeletePlaylistRequest();
		assertEquals(req.getName(), "PlaylistDeleteTest");
		assertEquals(req.toString(), "DeletePlaylist(PlaylistDeleteTest)");
		req.setName("PlaylistTest");
		
		DeletePlaylistResponse res = new DeletePlaylistResponse(400, "big error");
		DeletePlaylistResponse res2 = new DeletePlaylistResponse(200);

		assertEquals(res.toString(), "ErrorResult(statusCode=400, err=big error)");
		assertEquals(res2.toString(), "DeleteResponse()");
	}
	
	@Test
	public void allPlaylistsResponseTest() {
		AllPlaylistsResponse res = new AllPlaylistsResponse(null, 200);
		assertEquals(res.toString(), "EmptyClipList");
		
		AllPlaylistsResponse res2 = new AllPlaylistsResponse(400, "error");
		
		Playlist playlist = new Playlist("testPlaylist");
		List<Playlist>list = new ArrayList<Playlist>();
		list.add(playlist);
		AllPlaylistsResponse res3 = new AllPlaylistsResponse(list, 200);
		assertEquals(res3.toString(), "Playlist(1)");
	}
	
	@Test
	public void playlistHandlerTest() {
		PlaylistsHandler h = new PlaylistsHandler();
		List<Playlist>list;
		h.handleRequest("input", createContext("Create"));
		
		try {
			list = h.getPlaylists();
		}
		catch (Exception e) {
			
		}
	}
}
