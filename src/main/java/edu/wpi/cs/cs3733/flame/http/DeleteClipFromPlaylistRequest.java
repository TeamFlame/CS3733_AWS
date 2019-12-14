package edu.wpi.cs.cs3733.flame.http;

public class DeleteClipFromPlaylistRequest {
	
	public String video;
	public String playlist;

	public String getVideoURI() {
		return video;
	}

	public String getWorkingPlaylist() {
		return playlist;
	}

	public DeleteClipFromPlaylistRequest()
		{
			
		}

	public DeleteClipFromPlaylistRequest(String video, String playlist)
		{
			this.video=video;
			this.playlist=playlist;
		}

	public String toString() {
		return "DeleteClipFromPlaylistRequest(" + video + ", " + playlist + ")";
	}
}
