package edu.wpi.cs.cs3733.flame.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PlaylistTest {
	
	@Test
	public void PlaylistTest() {
		Playlist playlist = new Playlist("TestPlay");
		PlaylistItem item = new PlaylistItem(1, "Test", "TestUUID");
		List<PlaylistItem>list = new ArrayList<PlaylistItem>();
		playlist.setName("TestPlaylist");
		Assert.assertEquals(playlist.getName(), "TestPlaylist");
		playlist.setItems(list);
		List<PlaylistItem>list2 = playlist.getItems();
		list.add(item);
		playlist.updateOrder(list);
		playlist.removeItemList(item);
		VideoClip clip = new VideoClip("google.com", "Spock", "Hi", true);
		playlist.appendClip(clip);
		
		
		List<PlaylistItem>testList = new ArrayList<PlaylistItem>();
		testList.add(item);
		testList.add(item);
		testList.add(item);
		testList.add(item);
		Playlist playlist2 = new Playlist("TestBench");
		playlist2.updateOrder(testList);
		
	}
	
	@Test
	public void playlistItemTest() {
		PlaylistItem item = new PlaylistItem(1, "Test", "TestUUID");
		item.setIndex(2);
		Assert.assertEquals(item.getIndex(), 2);
		item.setClipID("ID4");
		Assert.assertEquals(item.getClipID(), "ID4");
	}
}
