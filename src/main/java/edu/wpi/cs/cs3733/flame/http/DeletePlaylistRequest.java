package edu.wpi.cs.cs3733.flame.http;

public class DeletePlaylistRequest {
	public String name;
	public String getName() { return name; }
	public void setName(String n) { name = n; }
	
	public DeletePlaylistRequest(String n) {
		name = n;
	}
	
	public String toString() {
		return "DeletePlaylist(" + name + ")";
	}
}
