package edu.wpi.cs.cs3733.flame;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733.flame.db.*;
import edu.wpi.cs.cs3733.flame.http.*;

public class DeleteRemoteSiteHandler implements RequestHandler<DeleteRemoteSiteRequest, DeleteRemoteSiteResponse>{
	
	LambdaLogger logger = null;
	
	@Override
	public DeleteRemoteSiteResponse handleRequest(DeleteRemoteSiteRequest req, Context context){
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete remote site");
		
		DeleteRemoteSiteResponse response = null;
		logger.log(req.toString());
		
		RemoteSitesDAO dao = new RemoteSitesDAO();
		
		try {
			if(dao.deleteRemote(req.getURI())) {
				response = new DeleteRemoteSiteResponse(200);
			}
			else {
				response = new DeleteRemoteSiteResponse(422, "Unable to delete remote site.");
			}
		}
		catch (Exception e) {
			response = new DeleteRemoteSiteResponse(403, "Unable to delete remote site: " + req.getURI() + "(" + e.getMessage() + ")");
		}
		
		return response;
	}
}
