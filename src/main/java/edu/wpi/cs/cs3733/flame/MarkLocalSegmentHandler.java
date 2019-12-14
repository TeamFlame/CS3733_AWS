package edu.wpi.cs.cs3733.flame;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733.flame.db.VideoClipsDAO;
import edu.wpi.cs.cs3733.flame.http.*;

public class MarkLocalSegmentHandler implements RequestHandler<MarkLocalSegmentRequest, MarkLocalSegmentResponse> {
	LambdaLogger logger;
	
	@Override
	public MarkLocalSegmentResponse handleRequest(MarkLocalSegmentRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		
		MarkLocalSegmentResponse response = null;
		logger.log(req.toString());
		
		VideoClipsDAO dao = new VideoClipsDAO();
		try {
			if(dao.markRemoteSegment(req.getBucketURI())) {
				response = new MarkLocalSegmentResponse(200);
			}
			else {
				response = new MarkLocalSegmentResponse(422, "Unable to mark local video segment:");
			}
		}
		catch (Exception e){
			response = new MarkLocalSegmentResponse(403, "Unable to mark local video segment: " + req.bucketURI + "(" + e.getMessage() + ")");
		}
		
		return response;
	}
}
