package edu.wpi.cs.cs3733.flame.http;

public class UploadClipResponse {
	/**
	 * Response is the name of the playlist that was being created.
	 * 
	 * if an error of some sort, then the response describes that error.
	 * 
	 */
	public int httpCode;
	public String error;

	public UploadClipResponse (String err, int code) {
			this.error = err;
			this.httpCode = code;
	}

	public UploadClipResponse (int httpCode){
		this.httpCode = httpCode;
	}

	public String toString() {
		return "UploadClipResponse()";
	}
}
