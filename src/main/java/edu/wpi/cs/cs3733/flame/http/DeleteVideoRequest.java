package edu.wpi.cs.cs3733.flame.http;

public class DeleteVideoRequest {
	public String bucketURI;
	public String getBucketURI() { return bucketURI; }
	public void setBucketURI(String b) { bucketURI = b; }
	
	public DeleteVideoRequest() { // We actually need the null contstructor so the Jackson mapper understands the request
	}
	
	public DeleteVideoRequest(String b) {
		bucketURI = b;
	}
	
	public String toString() {
		return "DeleteVideo(" + bucketURI + ")";
	}
}
