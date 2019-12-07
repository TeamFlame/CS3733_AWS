package edu.wpi.cs.cs3733.flame.http;

public class DeletePlaylistRequest {
	public String name;
	public String getName() { return name; }
	public void setName(String n) { name = n; }
	
	public DeletePlaylistRequest() { // We actually need the null contstructor so the Jackson mapper understands the request
	}
	
	public DeletePlaylistRequest(String n) {
		name = n;
	}
	
	public String toString() {
		return "DeletePlaylist(" + name + ")";
	}
}
