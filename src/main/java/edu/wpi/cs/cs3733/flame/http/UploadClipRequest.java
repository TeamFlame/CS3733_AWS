package edu.wpi.cs.cs3733.flame.http;

public class UploadClipRequest {
    public String character;
    public String transcript;
    public String base64EncodedString;
    public boolean remoteAccess;

    public UploadClipRequest(){

    }

    public UploadClipRequest(String character, String transcript, Boolean remoteAccess, String base64EncodedString) {
        this.character = character;
        this.transcript = transcript;
        this.remoteAccess = remoteAccess;
        this.base64EncodedString = base64EncodedString;
    }

    public String toString(){
        return "UploadClipRequest()";
    }

}
