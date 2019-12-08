package edu.wpi.cs.cs3733.flame;

import com.amazonaws.services.lambda.runtime.*;

import edu.wpi.cs.cs3733.flame.db.RemoteSitesDAO;
import edu.wpi.cs.cs3733.flame.http.*;
import edu.wpi.cs.cs3733.flame.model.Library;

public class CreateRemoteSiteHandler implements RequestHandler<CreateRemoteSiteRequest, CreateRemoteSiteResponse> {

	LambdaLogger logger;

	@Override
	public CreateRemoteSiteResponse handleRequest(CreateRemoteSiteRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		
		RemoteSitesDAO dao = new RemoteSitesDAO();

		CreateRemoteSiteResponse response;
		
		try {
			if(dao.addRemote(req.apiURI)) {
				response = new CreateRemoteSiteResponse(200);
			}
			else {
				response = new CreateRemoteSiteResponse(422);
			}
		}
		catch (Exception e) {
			response = new CreateRemoteSiteResponse("Unable to add remote: " + req.apiURI + "(" + e.getMessage() + ")", 400);
		}
		
		return response;
	}
}
