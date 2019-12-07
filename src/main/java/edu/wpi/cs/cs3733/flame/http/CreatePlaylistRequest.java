package edu.wpi.cs.cs3733.flame.http;

public class CreatePlaylistRequest {
	public String name;
	public String getName() { return name; }
	
	public CreatePlaylistRequest() {
		
	}
	
	public CreatePlaylistRequest(String n) {
		name = n;
	}
	
	public String toString() {
		return "CreatePlaylist(" + name + ")";
	}
}