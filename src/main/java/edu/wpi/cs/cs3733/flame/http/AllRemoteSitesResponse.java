package edu.wpi.cs.cs3733.flame.http;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.cs3733.flame.model.Playlist;

public class AllRemoteSitesResponse {
	public final List<String> list;
	public final int statusCode;
	public final String error;
	
	public AllRemoteSitesResponse (List<String> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public AllRemoteSitesResponse (int code, String errorMessage) {
		this.list = new ArrayList<String>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyRemotesList"; }
		return "RemoteSites(" + list.size() + ")";
	}
}
