package edu.wpi.cs.cs3733.flame.db;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.wpi.cs.cs3733.flame.db.DatabaseUtil;
import edu.wpi.cs.cs3733.flame.model.Playlist;
import edu.wpi.cs.cs3733.flame.model.PlaylistItem;
import edu.wpi.cs.cs3733.flame.model.VideoClip;
import java.sql.*;

public class PlaylistsDAO {

	java.sql.Connection conn;

	public PlaylistsDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	public List<Playlist> getAllPlaylists() throws Exception {

		List<Playlist> allPlaylists = new ArrayList<>();
		try {
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM Playlists";
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Playlist v = getPlaylist(resultSet);
				allPlaylists.add(v);
			}
			resultSet.close();
			statement.close();
			return allPlaylists;

		} catch (Exception e) {
			throw new Exception("Failed in getting clip list: " + e.getMessage());
		}
	}
	
	public Playlist getPlaylist(String name) throws Exception {

		Playlist playlist = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Playlists WHERE name=? LIMIT 1;");
			ps.setObject(1, name);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				playlist = getPlaylist(resultSet);
			}
			resultSet.close();
			ps.close();
			return playlist;

		} catch (Exception e) {
			throw new Exception("Failed in getting playlist: " + e.getMessage());
		}
	}

	private Playlist getPlaylist(ResultSet resultSet) throws Exception {
		String uuid = resultSet.getString("uuid");
		String name = resultSet.getString("name");
		return new Playlist(uuid, name);
	}

	public List<PlaylistItem> getAllPlaylistItems() throws Exception {

		List<PlaylistItem> allItems = new ArrayList<>();
		try {
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM Playlists";
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				PlaylistItem i = getPlaylistItem(resultSet);
				allItems.add(i);
			}
			resultSet.close();
			statement.close();
			return allItems;

		} catch (Exception e) {
			throw new Exception("Failed in getting clip list: " + e.getMessage());
		}
	}

	private PlaylistItem getPlaylistItem(ResultSet resultSet) throws Exception {
		String uuid = resultSet.getString("uuid");
		String playlistuuid = resultSet.getString("playlistUUID");
		String URI = resultSet.getString("clipURI");
		int index = resultSet.getInt("index");
		return new PlaylistItem(index, URI);
	}
	
	public boolean createPlaylist(Playlist playlist) throws Exception {
		try {
//			Statement statement = conn.createStatement();
//			String query = "SELECT * FROM Playlists WHERE name = ?;";
//			ResultSet resultSet = statement.executeQuery(query);
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Playlists (uuid,name) values (?,?);");
			ps.setString(1, UUID.randomUUID().toString());
			ps.setString(2, playlist.getName());
			ps.execute();
			return true;
		}
		catch (Exception e) {
			throw new Exception("Failed to create playlist: " + e.getMessage());
		}
	}
	
	public boolean deletePlaylist(Playlist playlist) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM Playlists WHERE name=?;");
			ps.setString(1, playlist.getName());
			int numAffected = ps.executeUpdate();
			ps.close();
            return (numAffected == 1);
		}
		catch (Exception e) {
			throw new Exception("Failed to delete playlist: " + e.getMessage());
		}
	}
	
	public int getPlaylistMaxIdx(Playlist p) throws Exception{
		try {
			int maxId = 0;
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(clipIndex) AS maxId FROM PlaylistItems WHERE playlistUUID=?;");
			ps.setObject(1, p.uuid);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				maxId = resultSet.getInt("maxId");
			}
			resultSet.close();
			ps.close();
			return maxId;

		} catch (Exception e) {
			throw new Exception("Failed in getting maxId: " + e.getMessage() + " Playlist uuid: " + p.uuid);
		}
	}

	public boolean insertIntoPlaylist(String videoURI, Playlist workingPlaylist, int index)throws Exception {
		try {
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO PlaylistItems (playlistUUID, clipURI, clipIndex) values (?,?,?);");
			ps.setObject(1, workingPlaylist.uuid);
			ps.setObject(2, videoURI);
			ps.setObject(3, index);
			ps.execute();
            return true;
		}
		catch (Exception e) {
			throw new Exception("Failed to append to playlist: " + e.getMessage());
		}
	}
	
	public boolean deleteFromPlaylist(String videoURI, Playlist workingPlaylist) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM PlaylistItems WHERE (playlistUUID, clipURI) values (?,?);");
			ps.setObject(1, workingPlaylist.uuid);
			ps.setObject(2, videoURI);
			ps.execute();
            return true;
		}
		catch (Exception e) {
			throw new Exception("Failed to delete clip from playlist: " + e.getMessage());
		}
	}
}
