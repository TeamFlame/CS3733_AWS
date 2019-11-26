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
		this.items = item;
	}
	void appendClip(VideoClip clip)//I'm not too sure about this one
	{ 
		PlaylistItem item = new PlaylistItem(items.length, clip.bucketURI + clip.character +clip.text);//made this as the clipID since i don't know what 
		//we are using and this will definitely be unique
		items[items.length]=item;
	}
	void removeItem(PlaylistItem item)
	{
		boolean deletedFound = false;
		for(int i = 0; i <this.items.length; i++)
		{
			if(items[i].equals(item))
			{
				items[i]=items[i+1];
				deletedFound = true;
				//if deleted one is found, the next array spot replaces it, and flag is triggered
			}
			if (deletedFound == true && i < items.length-1)
			{
				items[i]=items[i+1];
				//shifts down all of the playlists after the deleted one as not to leave gaps
			}
			else if(deletedFound == true)
			{
				items[i]=null;
				//sets final array space to null to make sure its empty
				
			}
		}
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
