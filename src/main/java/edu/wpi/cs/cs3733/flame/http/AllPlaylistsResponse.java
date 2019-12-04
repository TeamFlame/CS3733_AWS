package edu.wpi.cs.cs3733.flame.http;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.cs3733.flame.model.Playlist;

public class AllPlaylistsResponse {
	public final List<Playlist> list;
	public final int statusCode;
	public final String error;
	
	public AllPlaylistsResponse (List<Playlist> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public AllPlaylistsResponse (int code, String errorMessage) {
		this.list = new ArrayList<Playlist>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyClipList"; }
		return "Playlist(" + list.size() + ")";
	}
}
