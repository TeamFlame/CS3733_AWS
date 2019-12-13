package edu.wpi.cs.cs3733.flame.http;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.cs3733.flame.model.RemoteVideoClip;
import edu.wpi.cs.cs3733.flame.model.VideoClip;

public class RemoteVideosResponse {
	public final List<RemoteVideoClip> segments;
	public final int statusCode;
	public final String error;
	
	public RemoteVideosResponse (List<RemoteVideoClip> list, int code) {
		this.segments = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public RemoteVideosResponse (int code, String errorMessage) {
		this.segments = new ArrayList<RemoteVideoClip>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (segments == null) { return "EmptyClipList"; }
		return "VideoClip(" + segments.size() + ")";
	}
}
