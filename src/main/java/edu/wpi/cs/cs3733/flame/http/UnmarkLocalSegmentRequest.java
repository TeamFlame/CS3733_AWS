package edu.wpi.cs.cs3733.flame.http;

public class UnmarkLocalSegmentRequest {
	public String bucketURI;
	public String getBucketURI() { return bucketURI; }
	public void setBucketURI(String b) { bucketURI = b; }
	
	public UnmarkLocalSegmentRequest() { // We actually need the null contstructor so the Jackson mapper understands the request
	}
	
	public UnmarkLocalSegmentRequest(String bucketURI) {
		this.bucketURI = bucketURI;
	}
	
	public String toString() {
		return "Unmark Local Segment (" + bucketURI + ")";
	}
}
