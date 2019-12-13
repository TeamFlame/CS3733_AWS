package edu.wpi.cs.cs3733.flame.http;

import edu.wpi.cs.cs3733.flame.model.Playlist;

public class AppendPlaylistRequest 
{
public String videoURI;
public String workingPlaylist;

	public String getVideoURI()
	{
		return videoURI;
	}
	public String getWorkingPlaylist()
	{
		return workingPlaylist;
	}
	public AppendPlaylistRequest()
	{
		
	}
	public AppendPlaylistRequest(String videoURI, String workingPlaylist)
	{
		this.videoURI=videoURI;
		this.workingPlaylist=workingPlaylist;
	}
	
	public String toString()
	{
		return "AppendPlaylist(" + videoURI + workingPlaylist + ")";
	}
	
	public Playlist getPlaylist() {
		Playlist play = new Playlist(workingPlaylist);
		
		return play;
	}

}
