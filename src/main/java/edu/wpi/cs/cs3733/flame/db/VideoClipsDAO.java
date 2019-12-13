package edu.wpi.cs.cs3733.flame.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.cs3733.flame.db.DatabaseUtil;
import edu.wpi.cs.cs3733.flame.model.VideoClip;

public class VideoClipsDAO {

	java.sql.Connection conn;

    public VideoClipsDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }

    public void addClip(VideoClip clip) throws Exception {
    	System.out.println(clip.getBucketURI());
    	System.out.println(clip.getText());
    	System.out.println(clip.getCharacter());
    	System.out.println(clip.getRemoteAccess());
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO clipList (clipURI, text, `character`, remoteAccess) values (?,?,?,?);");
            ps.setString(1, clip.getBucketURI());
            ps.setString(2, clip.getText());
            ps.setString(3, clip.getCharacter());
            ps.setBoolean(4, clip.getRemoteAccess());
            ps.execute();
        }
        catch (Exception e) {
            throw new Exception("Failed to create clip: " + e.getStackTrace()[0].toString());
        }
    }
	
    public List<VideoClip> getAllClips() throws Exception {    
        List<VideoClip> allClips = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM clipList";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
            	VideoClip v = getClip(resultSet);
            	allClips.add(v);
            }
            resultSet.close();
            statement.close();
            return allClips;

        } catch (Exception e) {
            throw new Exception("Failed in getting clip list: " + e.getMessage());
        }
    }

private VideoClip getClip(ResultSet resultSet) throws Exception {
    String URI  = resultSet.getString("clipURI");
    String text = resultSet.getString("text");
    String character = resultSet.getString("character");
    Boolean remoteAccess = resultSet.getBoolean("remoteAccess");
    return new VideoClip (URI, character, text, remoteAccess);
}

	public boolean removeSegment(VideoClip clip) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM clipList WHERE clipURI=?;");
			ps.setString(1, clip.getBucketURI());
			int numAffected = ps.executeUpdate();
			ps.close();
            return (numAffected == 1);
		}
		catch (Exception e) {
			throw new Exception("Failed to remove local segment: " + e.getMessage());
		}
	}
	
	public boolean markRemoteSegment(String clipURI) throws Exception{
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE clipList SET remoteAccess=1 WHERE clipURI=?");
			ps.setString(1, clipURI);
			int numAffected = ps.executeUpdate();
			ps.close();
			return (numAffected == 1);
		}
		catch (Exception e) {
			throw new Exception("Failed to mark local segment: " + e.getMessage());
		}

	}
    
}
