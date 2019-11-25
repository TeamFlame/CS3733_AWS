package edu.wpi.cs.cs3733.flame.model;

public class VideoClip {

	String bucketURI;
	String character;
	String text;
	Boolean remoteAccess;
	
	public VideoClip(String bucketURI, String character, String text, Boolean remoteAccess) {
		this.character = character;
		this.text = text;
		this.bucketURI = bucketURI;
		this.remoteAccess = remoteAccess;
	}
	
	void setCharacter(String character)
	{
		this.character=character;
	}
	String getCharacter()
	{
		return this.character;
	}
	void setText(String text)
	{
		this.text=text;
	}
	String getText()
	{
		return this.text;
	}
	void setBucketURI(String newURI)
	{
		this.bucketURI=newURI;
	}
	String getBucketURI()
	{
		return this.bucketURI;
	}
	void setRemoteAccess(boolean access)
	{
		this.remoteAccess = access;
	}
	boolean getRemoteAccess()
	{
		return this.remoteAccess;
	}

}
