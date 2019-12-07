package edu.wpi.cs.cs3733.flame;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733.flame.db.PlaylistsDAO;
import edu.wpi.cs.cs3733.flame.http.*;
import edu.wpi.cs.cs3733.flame.model.Playlist;

public class DeletePlaylistHandler implements RequestHandler<DeletePlaylistRequest, DeletePlaylistResponse>{
	
	LambdaLogger logger = null;
	
	@Override
	public DeletePlaylistResponse handleRequest(DeletePlaylistRequest req, Context context){
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete playlist");
		
		DeletePlaylistResponse response = null;
		logger.log(req.toString());
		
		PlaylistsDAO dao = new PlaylistsDAO();
		Playlist playlist = new Playlist(req.name);
		
		try {
			if(dao.deletePlaylist(playlist)) {
				response = new DeletePlaylistResponse(req.name, 200);
			}
			else {
				response = new DeletePlaylistResponse(req.name, 422, "Unable to delete playlist.");
			}
		}
		catch (Exception e) {
			response = new DeletePlaylistResponse(req.name, 403, "Unable to delete constant: " + req.name + "(" + e.getMessage() + ")");
		}
		
		return response;
	}
}
