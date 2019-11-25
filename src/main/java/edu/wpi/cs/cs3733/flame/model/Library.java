package edu.wpi.cs.cs3733.flame.model;

public class Library 
{
	Playlist[] playlists;
	VideoClip[] clipList;
	String[] remoteSites;

	public Library()
	{
		
	}
	
	void createPlaylist()
	{
		
	}
	void deletePlaylist(Playlist playlist)
	{
		
	}
	void uploadClip(VideoClip clip)
	{
		
	}
	void deleteClip(VideoClip clip)
	{
		
	}
	VideoClip[] getAllClips()
	{
		return this.clipList;
	}
	VideoClip[] searchClips(String query)
	{
		return this.clipList;
		//The above is definitely not permanent, just here to get the error to shut up
	}
	/*Playlist[] playlists;
	VideoClip[] clipList;
	String[] remoteSites;*/
	void setPlaylists(Playlist[] playlists)
	{
		this.playlists=playlists;
	}
	Playlist[] getPlaylists()
	{
		return this.playlists;
	}
	void setClipList(VideoClip[] clips)
	{
		this.clipList=clips;
	}
	VideoClip[] getClipList()
	{
		return this.clipList;
	}
	void setRemoteSites(String[] rs)
	{
		this.remoteSites = rs;
	}
	String[] getRemoteSites()
	{
		return this.remoteSites;
	}
}
