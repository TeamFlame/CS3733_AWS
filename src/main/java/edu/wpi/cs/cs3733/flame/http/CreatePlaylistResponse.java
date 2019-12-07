package edu.wpi.cs.cs3733.flame.http;

public class CreatePlaylistResponse {
	/**
	 * Response is the name of the playlist that was being created.
	 * 
	 * if an error of some sort, then the response describes that error.
	 * 
	 */
	public final String response;
	public final int httpCode;

	public CreatePlaylistResponse (String s, int code) {
			this.response = s;
			this.httpCode = code;
		}

	public CreatePlaylistResponse (String s) {
			this.response = s;
			this.httpCode = 200;
		}

	public String toString() {
		return "Response(" + response + ")";
	}
}
