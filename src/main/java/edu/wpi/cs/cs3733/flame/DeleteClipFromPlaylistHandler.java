package edu.wpi.cs.cs3733.flame;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733.flame.db.PlaylistsDAO;
import edu.wpi.cs.cs3733.flame.http.DeleteClipFromPlaylistRequest;
import edu.wpi.cs.cs3733.flame.http.DeleteClipFromPlaylistResponse;
import edu.wpi.cs.cs3733.flame.model.Playlist;

public class DeleteClipFromPlaylistHandler implements RequestHandler<DeleteClipFromPlaylistRequest, DeleteClipFromPlaylistResponse> {
	LambdaLogger logger;
	
	boolean deleteFromPlaylist(String videoURI, Playlist workingPlaylist) throws Exception{
		if (logger != null) { logger.log("in appendPlaylist \n"); }
		PlaylistsDAO dao = new PlaylistsDAO();
		
		return dao.deleteFromPlaylist(videoURI, workingPlaylist);
	}
	
	public Playlist getPlaylist(String workingPlaylist) throws Exception {
		PlaylistsDAO dao = new PlaylistsDAO();
		Playlist play = dao.getPlaylist(workingPlaylist);
		return play;
	}
	
	@Override
	public DeleteClipFromPlaylistResponse handleRequest(DeleteClipFromPlaylistRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		
		DeleteClipFromPlaylistResponse response;
		
		try {
			deleteFromPlaylist(req.video, getPlaylist(req.playlist));
			response = new DeleteClipFromPlaylistResponse();
		}
		catch (Exception e) {
			logger.log(e.getLocalizedMessage());
			response = new DeleteClipFromPlaylistResponse("Unable to delete video clip" + req.video + " from playlist" + req.playlist + ".", 400);
		}
		
		return response;
	}
}
