package edu.wpi.cs.cs3733.flame.http;

public class MarkLocalSegmentRequest {
	public String bucketURI;
	public String getBucketURI() { return bucketURI; }
	public void setBucketURI(String b) { bucketURI = b; }
	
	public MarkLocalSegmentRequest() { // We actually need the null contstructor so the Jackson mapper understands the request
	}
	
	public MarkLocalSegmentRequest(String b) {
		bucketURI = b;
	}
	
	public String toString() {
		return "Mark Local Segment (" + bucketURI + ")";
	}
}
