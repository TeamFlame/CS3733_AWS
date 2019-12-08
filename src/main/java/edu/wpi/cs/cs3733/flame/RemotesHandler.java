package edu.wpi.cs.cs3733.flame;

import java.util.List;

import com.amazonaws.services.lambda.runtime.*;

import edu.wpi.cs.cs3733.flame.db.RemoteSitesDAO;
import edu.wpi.cs.cs3733.flame.http.*;

public class RemotesHandler implements RequestHandler<Object,AllRemoteSitesResponse> {

    public LambdaLogger logger;

    	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	List<String> getRemotes() throws Exception {
		logger.log("getRemotes\n");
		RemoteSitesDAO dao = new RemoteSitesDAO();
		
		return dao.getRemotes();
	}
	
	@Override
	public AllRemoteSitesResponse handleRequest(Object input, Context context) {
		logger = context.getLogger();
		
		AllRemoteSitesResponse response;
		
		try {
			List<String> list = getRemotes();
			response = new AllRemoteSitesResponse(list, 200);
		} catch (Exception e) {
			response = new AllRemoteSitesResponse(400, e.getMessage());
		}
		
		return response;
	}

}