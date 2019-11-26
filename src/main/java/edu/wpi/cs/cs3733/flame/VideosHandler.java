package edu.wpi.cs.cs3733.flame;

import java.util.List;

import com.amazonaws.services.lambda.runtime.*;

import edu.wpi.cs.cs3733.flame.db.VideoClipsDAO;
import edu.wpi.cs.cs3733.flame.http.AllClipsResponse;
import edu.wpi.cs.cs3733.flame.model.VideoClip;

/**
 * Eliminated need to work with JSON
 */
public class VideosHandler implements RequestHandler<Object,AllClipsResponse> {

	public LambdaLogger logger;

	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	List<VideoClip> getVideoClips() throws Exception {
		logger.log("getVideoClips\n");
		VideoClipsDAO dao = new VideoClipsDAO();
		
		return dao.getAllClips();
	}
	
	@Override
	public AllClipsResponse handleRequest(Object input, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all video clips\n");

		AllClipsResponse response;
		
		try {
			List<VideoClip> list = getVideoClips();
			response = new AllClipsResponse(list, 200);
		} catch (Exception e) {
			response = new AllClipsResponse(403, e.getMessage());
		}
		
		return response;
	}
}
