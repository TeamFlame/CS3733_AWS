package edu.wpi.cs.cs3733.flame.model;

public class RemoteVideoClip {

	String url;
	String character;
	String text;
	
	public RemoteVideoClip(String url, String character, String text) {
		this.character = character;
		this.text = text;
		this.url = url;
	}
	
	public RemoteVideoClip(VideoClip c) {
		this.character = c.getCharacter();
		this.text = c.getText();
		this.url = c.getBucketURI();
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
	public void seturl(String newURI)
	{
		this.url=newURI;
	}
	public String geturl()
	{
		return this.url;
	}
}
