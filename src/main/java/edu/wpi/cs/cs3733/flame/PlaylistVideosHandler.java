package edu.wpi.cs.cs3733.flame;

import java.util.List;

import com.amazonaws.services.lambda.runtime.*;

import edu.wpi.cs.cs3733.flame.db.PlaylistsDAO;
import edu.wpi.cs.cs3733.flame.http.*;
import edu.wpi.cs.cs3733.flame.model.Playlist;
import edu.wpi.cs.cs3733.flame.model.PlaylistItem;

public class PlaylistVideosHandler implements RequestHandler<PlaylistVideosRequest, PlaylistVideosResponse> {
	
	LambdaLogger logger;
	
	List<PlaylistItem> getPlaylistItems(String name) throws Exception{
		if (logger != null) { logger.log("in getPlaylistVideos"); }
		PlaylistsDAO dao = new PlaylistsDAO();
        List<PlaylistItem> list = dao.getPlaylistItems(name);
        logger.log("reached end of parsing playlist items");
        return list;
        
	}
	
	@Override
	public PlaylistVideosResponse handleRequest(PlaylistVideosRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		
		PlaylistVideosResponse response;
		
		try {
			 List<PlaylistItem> list = getPlaylistItems(req.name);
			 logger.log(list.toString());
			 logger.log("Test String after get playlist items");
            response = new PlaylistVideosResponse(200, list);
		}
		catch (Exception e) {
			response = new PlaylistVideosResponse("Unable to get: " + req.name + "(" + e.getMessage() + ")", 400);
		}
		
		return response;
	}
}
