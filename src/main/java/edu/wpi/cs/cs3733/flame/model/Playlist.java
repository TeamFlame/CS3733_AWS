package edu.wpi.cs.cs3733.flame.model;

public class Playlist 
{
	String name;
	PlaylistItem[] items;


	public Playlist(String name)
	{
		//the constructor only asks for a name since it 
		//expects that the user will decide on a name, and then 
		//append the PlaylistItems to it with the function
		this.name = name;
	}

	void updateOrder(PlaylistItem[] item)
	{
		
	}
	void appendClip(VideoClip clip)
	{
		
	}
	void removeItem(PlaylistItem item)
	{
		
	}
	void setName(String name)
	{
		this.name=name;
	}
	String getName()
	{
		return this.name;
	}
	void setItems(PlaylistItem[] items)
	{
		this.items = items;
	}
	PlaylistItem[] getItems()
	{
		return this.items;
	}
}
