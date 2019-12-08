package edu.wpi.cs.cs3733.flame.http;

public class CreateRemoteSiteResponse {
	public final int statusCode;
	public String error;

	public CreateRemoteSiteResponse (int code) {
			this.statusCode = code;
		}

	public CreateRemoteSiteResponse () {
			this.statusCode = 200;
		}
	
	public CreateRemoteSiteResponse(String error, int statusCode) {
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		return "CreateRemoteSiteResponse()";
	}
}
