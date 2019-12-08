package edu.wpi.cs.cs3733.flame.model;

import java.util.UUID;

public class PlaylistItem 
{
	int index;
	String clipID;
	String uuid;
	String bucketURI;
	
	public PlaylistItem(int index, String clipID)
	{
		new PlaylistItem(index, clipID, UUID.randomUUID().toString());	
	}
	public PlaylistItem(int index, String clipID, String uuid)
	{
		this.index = index;
		this.clipID = clipID;
		this.uuid = uuid;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean ret = false;
		if(getClass() == o.getClass()) {
			PlaylistItem other = (PlaylistItem) o;
			if(this.uuid.equals(other.uuid)) {
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
	public String getClipID()
	{
		return this.clipID;
	}
	public String getBucketURI() {
		// TODO Auto-generated method stub
		return this.bucketURI;
	}
	public void setBucketURI(String URI) {
		// TODO Auto-generated method stub
		this.bucketURI = URI;
	}
}
