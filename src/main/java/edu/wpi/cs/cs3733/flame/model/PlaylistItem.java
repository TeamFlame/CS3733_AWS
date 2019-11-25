package edu.wpi.cs.cs3733.flame.model;

public class PlaylistItem 
{
	int index;
	String clipID;
	
	public PlaylistItem()
	{
		
	}
	public PlaylistItem(int index, String clipID)
	{
		this.index = index;
		this.clipID = clipID;
	}
	void setIndex(int index)
	{
		this.index = index;
	}
	int getIndex()
	{
		return this.index;
	}
	void setClipID(String newID)
	{
		this.clipID=newID;
	}
	String getClipID()
	{
		return this.clipID;
	}
}
