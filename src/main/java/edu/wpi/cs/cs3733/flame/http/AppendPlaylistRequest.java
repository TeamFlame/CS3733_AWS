package edu.wpi.cs.cs3733.flame.http;

import java.util.List;
import java.util.ArrayList;

import edu.wpi.cs.cs3733.flame.db.PlaylistsDAO;
import edu.wpi.cs.cs3733.flame.model.Playlist;

public class AppendPlaylistRequest 
{
public String video;
public String playlist;

	public String getVideoURI()
	{
		return video;
	}
	public String getWorkingPlaylist()
	{
		return playlist;
	}
	public AppendPlaylistRequest()
	{
		
	}
	public AppendPlaylistRequest(String video, String playlist)
	{
		this.video=video;
		this.playlist=playlist;
	}
	
	public String toString()
	{
		return "AppendPlaylist(" + video + ", " + playlist + ")";
	}

}
