package edu.wpi.cs.cs3733.flame.model;

import java.util.ArrayList;
import java.util.List;

public class Library 
{
	List<Playlist> playlists;
	List<VideoClip> clipList;
	List<String> remoteSites;
	
	Library singleton = null;

	private Library()
	{
		playlists = new ArrayList<Playlist>();
		clipList = new ArrayList<VideoClip>();
		remoteSites = new ArrayList<String>();
	}
	
	public Library getInstance() {
		if (singleton == null) {
			singleton = new Library();
		}
		return singleton;
	}
	
	void createPlaylist(String name)
	{
		playlists.add(new Playlist(name));
	}
	
	void deletePlaylist(Playlist playlist)
	{
		playlists.remove(playlist);
	}
	
	void uploadClip(VideoClip clip)
	{
		clipList.add(clip);
	}
	void deleteClip(VideoClip clip)
	{
		clipList.remove(clip);
	}
	List<VideoClip> getAllClips()
	{
		return this.clipList;
	}
	List<VideoClip> searchClips(String characterQuery, String textQuery)
	{
		// TODO: actually query for the correct clips
		return this.clipList;
	}
	void setPlaylists(List<Playlist> playlists)
	{
		this.playlists=playlists;
	}
	List<Playlist> getPlaylists()
	{
		return this.playlists;
	}
	void setClipList(List<VideoClip> clips)
	{
		this.clipList=clips;
	}
	List<VideoClip> getClipList()
	{
		return this.clipList;
	}
	void setRemoteSites(List<String> rs)
	{
		this.remoteSites = rs;
	}
	List<String> getRemoteSites()
	{
		return this.remoteSites;
	}
}
