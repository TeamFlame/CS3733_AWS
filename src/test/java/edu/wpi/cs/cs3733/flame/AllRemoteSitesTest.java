package edu.wpi.cs.cs3733.flame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import edu.wpi.cs.cs3733.flame.http.AllRemoteSitesResponse;

public class AllRemoteSitesTest {
	
	@Test
	public void allRemoteSitesTest() {
		AllRemoteSitesResponse res = new AllRemoteSitesResponse(null, 400);
		List<String>list = new ArrayList<String>();
		list.add("Test");
		AllRemoteSitesResponse res2 = new AllRemoteSitesResponse(list, 200);
		AllRemoteSitesResponse res3 = new AllRemoteSitesResponse(400, "error");
		
		Assert.assertEquals(res.toString(), "EmptyRemotesList");
		Assert.assertEquals(res2.toString(), "RemoteSites(1)");
	}
}
