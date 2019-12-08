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
//<<<<<<< Updated upstream
//=======
	
	public boolean deletePlaylist(Playlist playlist) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM Playlists name = ?;");
			ps.setString(1, playlist.getName());
			int numAffected = ps.executeUpdate();
			ps.close();
            return (numAffected == 1);
		}
		catch (Exception e) {
			throw new Exception("Failed to delete playlist: " + e.getMessage());
		}
	}
	
	public boolean appendPlaylist(Playlist playlist, PlaylistItem playlistItem) throws Exception
	{
		try {
			PreparedStatement ps = conn.prepareStatement("APPEND TO PLAYLIST THE ITEM (Playlist, PlaylistItem) values (?,?);");
			ps.setString(1, playlist.getName());
			ps.setObject(2, playlistItem);
			ps.execute();
			int numAffected = ps.executeUpdate();
			ps.close();
            return (numAffected == 1);
		}
		catch (Exception e) {
			throw new Exception("Failed to delete playlist: " + e.getMessage());
		}
	}
	
//>>>>>>> Stashed changes
}
