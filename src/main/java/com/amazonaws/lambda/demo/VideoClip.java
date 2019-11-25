package com.amazonaws.lambda.demo;

public class VideoClip 
{
String[] characters;
String transcript;
String bucketURI;
String UID;
boolean remoteAccess;


public VideoClip(String[] characters, String transcript, 
		String bucketURI, String UID, boolean remoteAccess)
	{
		this.characters=characters;
		this.transcript=transcript;
		this.bucketURI=bucketURI;
		this.UID=UID;
		this.remoteAccess=remoteAccess;
				
	}
	//String[] characters;
	//String transcript;
	//String bucketURI;
	//String UID;
	//boolean remoteAccess;
	void setCharacters(String[] characters)
	{
		this.characters=characters;
	}
	String[] getCharacters()
	{
		return this.characters;
	}
	void setTranscript(String transcript)
	{
		this.transcript=transcript;
	}
	String getTranscript()
	{
		return this.transcript;
	}
	void setBucketURI(String newURI)
	{
		this.bucketURI=newURI;
	}
	String getBucketURI()
	{
		return this.bucketURI;
	}
	void setUID(String UID)
	{
		this.UID = UID;
	}
	String getUID()
	{
		return this.UID;
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
