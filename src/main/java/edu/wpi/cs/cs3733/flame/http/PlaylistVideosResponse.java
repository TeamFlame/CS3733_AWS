package edu.wpi.cs.cs3733.flame.http;

import java.util.List;

import edu.wpi.cs.cs3733.flame.model.PlaylistItem;

public class PlaylistVideosResponse {
	/**
	 * Response is the name of the playlist that was being created.
	 * 
	 * if an error of some sort, then the response describes that error.
	 * 
	 */
	public final int statusCode;
	public List<PlaylistItem> list;
	public String error;

	public PlaylistVideosResponse (int code, List<PlaylistItem> list) {
			this.statusCode = code;
			this.list = list;
		}
	
	public PlaylistVideosResponse(String error, int statusCode) {
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		return "PlaylistVideosResponse()";
	}
}
