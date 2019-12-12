package edu.wpi.cs.cs3733.flame.http;

public class CreatePlaylistResponse {
	/**
	 * Response is the name of the playlist that was being created.
	 * 
	 * if an error of some sort, then the response describes that error.
	 * 
	 */
	public final int statusCode;
	public String error;

	public CreatePlaylistResponse (int code) {
			this.statusCode = code;
		}

	public CreatePlaylistResponse () {
			this.statusCode = 200;
		}
	
	public CreatePlaylistResponse(String error, int statusCode) {
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		return "CreatePlaylistResponse()";
	}
}
