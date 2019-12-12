package edu.wpi.cs.cs3733.flame.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.wpi.cs.cs3733.flame.db.DatabaseUtil;
import edu.wpi.cs.cs3733.flame.model.Playlist;

public class RemoteSitesDAO {

	java.sql.Connection conn;

	public RemoteSitesDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
	
	public List<String> getRemotes() throws Exception {

		List<String> remotes = new ArrayList<String>();
		try {
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM remoteSites";
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				remotes.add(resultSet.getString("remoteURL"));
			}
			resultSet.close();
			statement.close();
			return remotes;

		} catch (Exception e) {
			throw new Exception("Failed in getting remotes: " + e.getMessage());
		}
	}
	
	public boolean addRemote(String apiURI) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO remoteSites (uuid,remoteURL) values (?,?);");
			ps.setString(1, UUID.randomUUID().toString());
			ps.setString(2, apiURI);
			ps.execute();
			return true;
		}
		catch (Exception e) {
			throw new Exception("Failed to add remote: " + e.getMessage());
		}
	}
	
	public boolean deleteRemote(String apiURI) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM remoteSites WHERE remoteURI=?;");
			ps.setString(1, apiURI);
			int numAffected = ps.executeUpdate();
			ps.close();
            return (numAffected == 1);
		}
		catch (Exception e) {
			throw new Exception("Failed to delete remote: " + e.getMessage());
		}
	}
}
