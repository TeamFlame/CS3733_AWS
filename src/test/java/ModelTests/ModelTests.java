package ModelTests;

import edu.wpi.cs.cs3733.flame.model.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class ModelTests 
{
	//||    =||=   ||=\  ||=\\    //=\\   ||=\\ \\  //
	//||	 ||    ||==  || //   //===\\  || //  \\//
	//|==== =||=   ||=/  || \\  //     \\ || \\   ||
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void createPlaylistTest()
	{
		Library library = new Library();
		library.createPlaylist("Test");
		assertTrue(library.playlists[0].getName().equals("Test"));	
	}
	
	@Test
	public void deletePlaylistTest()
	{
		Library library = new Library();
		library.createPlaylist("Test");
		library.createPlaylist("not me");
		library.deletePlaylist(new Playlist("Test"));
		assertTrue(library.playlists[0].getName().equals("not me"));
	}
	
	
}
