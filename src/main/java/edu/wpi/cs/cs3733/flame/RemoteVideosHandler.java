package edu.wpi.cs.cs3733.flame;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.*;

import edu.wpi.cs.cs3733.flame.db.VideoClipsDAO;
import edu.wpi.cs.cs3733.flame.http.RemoteVideosResponse;
import edu.wpi.cs.cs3733.flame.model.VideoClip;
import edu.wpi.cs.cs3733.flame.model.RemoteVideoClip;

/**
 * Eliminated need to work with JSON
 */
public class RemoteVideosHandler implements RequestHandler<Object,RemoteVideosResponse> {

	public LambdaLogger logger;

	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	List<VideoClip> getVideoClips() throws Exception {
		logger.log("getVideoClips\n");
		VideoClipsDAO dao = new VideoClipsDAO();
		
		return dao.getPublicClips();
	}
	
	@Override
	public RemoteVideosResponse handleRequest(Object input, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list public video clips\n");

		RemoteVideosResponse response;
		
		try {
			List<VideoClip> list = getVideoClips();
			List<RemoteVideoClip> remotes = new ArrayList<RemoteVideoClip>();
			for(VideoClip c : list) {
				remotes.add(new RemoteVideoClip(c));
			}
			for(RemoteVideoClip r : remotes) {
				logger.log("public access to clip: " + r.geturl());
			}
			response = new RemoteVideosResponse(remotes, 200);
		} catch (Exception e) {
			response = new RemoteVideosResponse(403, e.getMessage());
		}
		
		return response;
	}
}
