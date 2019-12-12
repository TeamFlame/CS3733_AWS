package edu.wpi.cs.cs3733.flame;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733.flame.db.PlaylistsDAO;
import edu.wpi.cs.cs3733.flame.http.*;
import edu.wpi.cs.cs3733.flame.model.Playlist;

public class CreatePlaylistHandler implements RequestHandler<CreatePlaylistRequest, CreatePlaylistResponse> {
	
	LambdaLogger logger;
	
	boolean createPlaylist(String name) throws Exception{
		if (logger != null) { logger.log("in createPlaylist"); }
		PlaylistsDAO dao = new PlaylistsDAO();
		
		Playlist playlist = new Playlist(name);
		return dao.createPlaylist(playlist);
	}
	
	@Override
	public CreatePlaylistResponse handleRequest(CreatePlaylistRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		
		CreatePlaylistResponse response;
		
		try {
			if(createPlaylist(req.name)) {
				response = new CreatePlaylistResponse();
			}
			else {
				response = new CreatePlaylistResponse(422);
			}
		}
		catch (Exception e) {
			response = new CreatePlaylistResponse("Unable to create playlist: " + req.name + "(" + e.getMessage() + ")", 400);
		}
		
		return response;
	}
}
