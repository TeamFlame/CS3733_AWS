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
	
	public void setCharacter(String character)
	{
		this.character=character;
	}
	public String getCharacter()
	{
		return this.character;
	}
	public void setText(String text)
	{
		this.text=text;
	}
	public String getText()
	{
		return this.text;
	}
	public void setBucketURI(String newURI)
	{
		this.bucketURI=newURI;
	}
	public String getBucketURI()
	{
		return this.bucketURI;
	}
	public void setRemoteAccess(boolean access)
	{
		this.remoteAccess = access;
	}
	public boolean getRemoteAccess()
	{
		return this.remoteAccess;
	}

}
