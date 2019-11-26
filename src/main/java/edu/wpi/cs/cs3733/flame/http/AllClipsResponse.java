package edu.wpi.cs.cs3733.flame.http;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.cs3733.flame.model.VideoClip;

public class AllClipsResponse {
	public final List<VideoClip> list;
	public final int statusCode;
	public final String error;
	
	public AllClipsResponse (List<VideoClip> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public AllClipsResponse (int code, String errorMessage) {
		this.list = new ArrayList<VideoClip>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyClipList"; }
		return "VideoClip(" + list.size() + ")";
	}
}
