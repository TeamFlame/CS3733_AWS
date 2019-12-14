package edu.wpi.cs.cs3733.flame.http;

public class UnmarkLocalSegmentResponse {
	public final int statusCode;
	public final String error;
	
	public UnmarkLocalSegmentResponse(int statusCode) {
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public UnmarkLocalSegmentResponse(int statusCode, String error) {
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		//success
		if(statusCode == 200) {
			return "Unmark Local Segment Response()";
		}
		//failure
		else {
			return "Unmark Local Segment Response ErrorResult(statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
}
