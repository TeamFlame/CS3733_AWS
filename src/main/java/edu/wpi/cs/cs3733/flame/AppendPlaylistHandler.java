package edu.wpi.cs.cs3733.flame;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733.flame.db.PlaylistsDAO;
import edu.wpi.cs.cs3733.flame.http.*;
import edu.wpi.cs.cs3733.flame.model.Playlist;


public class AppendPlaylistHandler implements RequestHandler<AppendPlaylistRequest, AppendPlaylistResponse> 
{
	LambdaLogger logger;
	
	boolean appendPlaylist(String videoURI, Playlist workingPlaylist) throws Exception{
		if (logger != null) { logger.log("in appendPlaylist \n"); }
		PlaylistsDAO dao = new PlaylistsDAO();
		int idx = dao.getPlaylistMaxIdx(workingPlaylist) + 1;
		
		return dao.insertIntoPlaylist(videoURI, workingPlaylist, idx);
	}

	
	public Playlist getPlaylist(String workingPlaylist) throws Exception {
		PlaylistsDAO dao = new PlaylistsDAO();
		Playlist play = dao.getPlaylist(workingPlaylist);
		return play;
	}
	
	
	@Override
	public AppendPlaylistResponse handleRequest(AppendPlaylistRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		
		AppendPlaylistResponse response;
		
		try {
			appendPlaylist(req.video, getPlaylist(req.playlist));
			response = new AppendPlaylistResponse();
		}
		catch (Exception e) {
			logger.log(e.getLocalizedMessage());
			response = new AppendPlaylistResponse("Unable to append video" + req.video + " to playlist" + req.playlist + ".", 400);
		}
		
		return response;
	}
}
