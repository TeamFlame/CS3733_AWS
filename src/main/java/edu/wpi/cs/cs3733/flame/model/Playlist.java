package edu.wpi.cs.cs3733.flame.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist 
{
	String uuid;
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
		PlaylistItem item = new PlaylistItem(items.size(), clip.bucketURI);//made this as the clipID since i don't know what 
		items.add(item);
	}
//	int index;
//	String clipID;
//	String uuid;
//	String bucketURI;
	
//	String bucketURI;
//	String character;
//	String text;
//	Boolean remoteAccess;
	void removeItem(PlaylistItem item)
	{
		boolean deletedFound = false;
		for(int i = 0; i <this.items.size(); i++)
		{
			if(items.get(i).equals(item))
			{
				items.set(i, items.get(i+1));
				deletedFound = true;
				//if deleted one is found, the next array spot replaces it, and flag is triggered
			}
			if (deletedFound == true && i < items.size()-1)
			{
				items.set(i, items.get(i+1));
				//shifts down all of the playlists after the deleted one as not to leave gaps
			}
			else if(deletedFound == true)
			{
				items.remove(i);
				//sets final array space to null to make sure its empty
				
			}
		}
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

	public String getUUID()
	{
		// TODO Auto-generated method stub
		return this.uuid;
	}
}
