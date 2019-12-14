package edu.wpi.cs.cs3733.flame.http;

public class DeleteClipFromPlaylistResponse {
	
	public final int statusCode;
	public String error;
	
	public DeleteClipFromPlaylistResponse()
	{
		this.statusCode=200;
	}
	
	public DeleteClipFromPlaylistResponse (int statusCode)
	{
		this.statusCode = statusCode;
	}
	
	public DeleteClipFromPlaylistResponse (String error, int statusCode)
	{
		this.statusCode = statusCode;
		this.error = error;
	}
	public String toString()
	{
		return "DeleteClipFromPlaylistResponse()";
	}
}
