package edu.wpi.cs.cs3733.flame.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist 
{
	public String uuid;
	String name;
	public List<PlaylistItem> items = new ArrayList<PlaylistItem>();


	public Playlist(String name)
	{
		//the constructor only asks for a name since it 
		//expects that the user will decide on a name, and then 
		//append the PlaylistItems to it with the function
		this.name = name;
	}

	public Playlist(String uuid, String name) {
		this.uuid = uuid;
		this.name = name;
	}

	void updateOrder(List<PlaylistItem> item)
	{
		this.items = item;
	}
	void appendClip(VideoClip clip)//I'm not too sure about this one
	{ 
		PlaylistItem item = new PlaylistItem(items.size(), clip.bucketURI + clip.character +clip.text);//made this as the clipID since i don't know what 
		//we are using and this will definitely be unique
		items.add(item);
	}
	
	void removeItemList(PlaylistItem delItem) {
		this.items.remove(delItem);
	}
	void setName(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return this.name;
	}
	void setItems(List<PlaylistItem> items)
	{
		this.items = items;
	}
	List<PlaylistItem> getItems()
	{
		return this.items;
	}
}
