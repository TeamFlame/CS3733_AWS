package edu.wpi.cs.cs3733.flame.http;

public class DeleteRemoteSiteResponse {
	public final int statusCode;
	public final String error;
	
	public DeleteRemoteSiteResponse(int statusCode) {
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public DeleteRemoteSiteResponse(int statusCode, String error) {
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		//success
		if(statusCode == 200) {
			return "DeleteRemoteSiteResponse()";
		}
		//failure
		else {
			return "ErrorResult(statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
}
