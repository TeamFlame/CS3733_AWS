package edu.wpi.cs.cs3733.flame;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import edu.wpi.cs.cs3733.flame.http.*;

public class RemotesHandlerTest extends LambdaTest{
	
	@Test
	public void remotesHandlerTest() {
		RemotesHandler h = new RemotesHandler();
		AllRemoteSitesResponse res = new AllRemoteSitesResponse(400, "error");
		
		h.handleRequest("input", createContext("Create"));
		
		List<String>list;
		try{
			list = h.getRemotes();
		}
		catch (Exception e) {
			
		}
	}
}
