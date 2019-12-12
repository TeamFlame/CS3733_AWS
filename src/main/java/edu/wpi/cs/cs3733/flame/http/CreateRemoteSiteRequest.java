package edu.wpi.cs.cs3733.flame.http;

public class CreateRemoteSiteRequest {
	public String apiURI;
	public String getURI() { return apiURI; }
	
	public CreateRemoteSiteRequest() { // We actually need the null contstructor so the Jackson mapper understands the request
	}
	
	public CreateRemoteSiteRequest(String apiURI) {
		this.apiURI = apiURI;
	}
	
	public String toString() {
		return "CreateRemoteSite(" + apiURI + ")";
	}
}
