package edu.wpi.cs.cs3733.flame.http;

public class MarkLocalSegmentResponse {
	public final int statusCode;
	public final String error;
	
	public MarkLocalSegmentResponse(int statusCode) {
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public MarkLocalSegmentResponse(int statusCode, String error) {
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		//success
		if(statusCode == 200) {
			return "Mark Local Segment Response()";
		}
		//failure
		else {
			return "ErrorResult(statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
}
