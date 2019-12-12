package edu.wpi.cs.cs3733.flame.http;

public class AppendPlaylistResponse 
{
	/**
	 * Response is the name of the playlist appended to,
	 * as well as the URI of the video added.
	 * 
	 * If there is an error, the response describes it
	 */
	
	public final int statusCode;
	public String error;
	
	public AppendPlaylistResponse()
	{
		this.statusCode=200;
	}
	
	public AppendPlaylistResponse (int statusCode)
	{
		this.statusCode = statusCode;
	}
	
	public AppendPlaylistResponse (String error,int statusCode)
	{
		this.statusCode = statusCode;
		this.error = error;
	}
	public String toString()
	{
		return "AppendPlaylistResponse()";
	}

}
