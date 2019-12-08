package edu.wpi.cs.cs3733.flame.http;

public class PlaylistVideosRequest {
	public String name;
	public String getName() { return name; }
	
	public PlaylistVideosRequest() { // We actually need the null contstructor so the Jackson mapper understands the request
	}
	
	public PlaylistVideosRequest(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "PlaylistVideos(" + name + ")";
	}
}
