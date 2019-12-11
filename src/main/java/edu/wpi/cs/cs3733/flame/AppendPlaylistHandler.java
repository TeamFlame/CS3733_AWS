package edu.wpi.cs.cs3733.flame;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733.flame.db.PlaylistsDAO;
import edu.wpi.cs.cs3733.flame.http.*;
import edu.wpi.cs.cs3733.flame.model.Playlist;


public class AppendPlaylistHandler implements RequestHandler<AppendPlaylistRequest, AppendPlaylistRequest> 
{
	LambdaLogger logger;
	
	boolean appendPlaylist(String videoURI, Playlist workingPlaylist) throws Exception{
		if (logger != null) { logger.log("in appendPlaylist"); }
		PlaylistsDAO dao = new PlaylistsDAO();
		
		Playlist playlist = workingPlaylist.items.add(videoURI);
		return dao.createPlaylist(playlist);
	}
	
	@Override
	public AppendPlaylistResponse handleRequest(AppendPlaylistRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		
		AppendPlaylistResponse response;
		
		try {
			if(appendPlaylist(req.videoURI, req.workingPlaylist)) {
				response = new AppendPlaylistResponse();
			}
			else {
				response = new AppendPlaylistResponse(422);
			}
		}
		catch (Exception e) {
			response = new AppendPlaylistResponse("Unable to append video" + req.videoURI + " to playlist" + req.workingPlaylist.getName() + ".", 400);
		}
		
		return response;
	}
}
