package edu.wpi.cs.cs3733.flame;

import java.util.List;

import com.amazonaws.services.lambda.runtime.*;

import edu.wpi.cs.cs3733.flame.db.PlaylistsDAO;
import edu.wpi.cs.cs3733.flame.http.AllPlaylistsResponse;
import edu.wpi.cs.cs3733.flame.model.Playlist;

public class PlaylistsHandler implements RequestHandler<Object, AllPlaylistsResponse>{

	public LambdaLogger logger;

	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	List<Playlist> getPlaylists() throws Exception {
		logger.log("getPlaylists\n");
		PlaylistsDAO dao = new PlaylistsDAO();
		
		return dao.getAllPlaylists();
	}
	
	@Override
	public AllPlaylistsResponse handleRequest(Object input, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all playlists\n");

		AllPlaylistsResponse response;
		
		try {
			List<Playlist> list = getPlaylists();
			response = new AllPlaylistsResponse(list, 200);
		} catch (Exception e) {
			response = new AllPlaylistsResponse(403, e.getMessage());
		}
		
		return response;
	}

}
