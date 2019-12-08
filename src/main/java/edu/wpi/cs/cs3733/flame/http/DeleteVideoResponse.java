package edu.wpi.cs.cs3733.flame.http;

public class DeleteVideoResponse {
	public final int statusCode;
	public final String error;
	
	public DeleteVideoResponse(int statusCode) {
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public DeleteVideoResponse(int statusCode, String error) {
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		//success
		if(statusCode == 200) {
			return "DeleteResponse()";
		}
		//failure
		else {
			return "ErrorResult(statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
}
