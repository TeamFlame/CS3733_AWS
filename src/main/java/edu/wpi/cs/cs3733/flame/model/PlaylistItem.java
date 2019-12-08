package edu.wpi.cs.cs3733.flame.model;

public class PlaylistItem 
{
	public int index;
	public String clipID;
	
	public PlaylistItem(int index, String clipID)
	{
		this.index = index;
		this.clipID = clipID;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean ret = false;
		if(getClass() == o.getClass()) {
			PlaylistItem other = (PlaylistItem) o;
			if(this.clipID.equals(other.clipID)) {
				ret = true;
			}
		}
		return ret;
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
