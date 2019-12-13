package edu.wpi.cs.cs3733.flame;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733.flame.db.VideoClipsDAO;
import edu.wpi.cs.cs3733.flame.http.*;

public class UnmarkLocalSegmentHandler implements RequestHandler<UnmarkLocalSegmentRequest, UnmarkLocalSegmentResponse>{
	LambdaLogger logger;
	
	@Override
	public UnmarkLocalSegmentResponse handleRequest(UnmarkLocalSegmentRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		
		UnmarkLocalSegmentResponse response = null;
		
		VideoClipsDAO dao = new VideoClipsDAO();
		try {
			if(dao.unmarkRemoteSegment(req.getBucketURI())) {
				response = new UnmarkLocalSegmentResponse(200);
			}
			else {
				response = new UnmarkLocalSegmentResponse(422, "Unable to unmark local video segment:");
			}
		}
		catch (Exception e){
			response = new UnmarkLocalSegmentResponse(403, "Unable to unmark local video segment: " + req.bucketURI + "(" + e.getMessage() + ")");
		}
		
		return response;
	}
}
