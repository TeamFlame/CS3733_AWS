package edu.wpi.cs.cs3733.flame.http;

public class DeleteRemoteSiteRequest {
	public String apiURI;
	public String getURI() { return apiURI; }
	public void setURI(String n) { apiURI = n; }
	
	public DeleteRemoteSiteRequest() { // We actually need the null contstructor so the Jackson mapper understands the request
	}
	
	public DeleteRemoteSiteRequest(String apiURI) {
		this.apiURI = apiURI;
	}
	
	public String toString() {
		return "DeleteRemoteSite(" + apiURI + ")";
	}
}
