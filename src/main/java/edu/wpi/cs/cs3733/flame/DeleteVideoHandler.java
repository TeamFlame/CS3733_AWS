package edu.wpi.cs.cs3733.flame;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733.flame.http.DeletePlaylistRequest;
import edu.wpi.cs.cs3733.flame.http.DeletePlaylistResponse;
import edu.wpi.cs.cs3733.flame.model.VideoClip;
import edu.wpi.cs.cs3733.flame.db.VideoClipsDAO;
import edu.wpi.cs.cs3733.flame.http.*;

public class DeleteVideoHandler implements RequestHandler<DeleteVideoRequest, DeleteVideoResponse>{
	LambdaLogger logger = null;
	
	@Override
	public DeleteVideoResponse handleRequest(DeleteVideoRequest req, Context context){
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete video segment");
		
		DeleteVideoResponse response = null;
		logger.log(req.toString());
		
		VideoClipsDAO dao = new VideoClipsDAO();
		VideoClip clip = new VideoClip(req.getBucketURI(), "", "", true); //I believe this will work using just the bucketURI
		//Because the DAO only uses the bucketURI to remove the segment
		
		try {
			if(dao.removeSegment(clip)) {
				response = new DeleteVideoResponse(200);
			}
			else {
				response = new DeleteVideoResponse(422, "Unable to delete video segment.");
			}
		}
		catch (Exception e) {
			response = new DeleteVideoResponse(403, "Unable to delete video segment: " + req.bucketURI + "(" + e.getMessage() + ")");
		}
		
		return response;
	}
}
