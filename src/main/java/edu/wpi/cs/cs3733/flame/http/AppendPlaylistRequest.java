package edu.wpi.cs.cs3733.flame.http;

import edu.wpi.cs.cs3733.flame.model.Playlist;

public class AppendPlaylistRequest 
{
public String videoURI;
public Playlist workingPlaylist;

	public String getVideoURI()
	{
		return videoURI;
	}
	public Playlist getWorkingPlaylist()
	{
		return workingPlaylist;
	}
	public AppendPlaylistRequest()
	{
		
	}
	public AppendPlaylistRequest(String videoURI, Playlist workingPlaylist)
	{
		this.videoURI=videoURI;
		this.workingPlaylist=workingPlaylist;
	}
	
	public String toString()
	{
		return "AppendPlaylist(" + videoURI + workingPlaylist.getName() + ")";
	}

}
