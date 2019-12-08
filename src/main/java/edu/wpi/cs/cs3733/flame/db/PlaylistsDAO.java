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
	
	public Playlist getPlaylist(String name) throws Exception {
		Playlist p = null;
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Playlists WHERE name = ?;");
		ps.setString(1,name);
		ResultSet resultSet = ps.executeQuery();
		while (resultSet.next()){
			p = getPlaylist(resultSet);
		}
		resultSet.close();
		ps.close();
		return p;
		
	}

	public List<PlaylistItem> getPlaylistItems(String name) throws Exception {

		List<PlaylistItem> allItems = new ArrayList<PlaylistItem>();
		Playlist p;
		try {
			p = getPlaylist(name);
		} catch (Exception e) {
			throw new Exception("Failed in getting playlist uuid: " + e.getLocalizedMessage());
		}
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM PlaylistItems WHERE playlistUUID = ?;");
			ps.setString(1, p.uuid);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()){
				PlaylistItem item = getPlaylistItem(resultSet);
				allItems.add(item);
			}
			resultSet.close();
			ps.close();
			return allItems;
		}
		catch (Exception e) {
			throw new Exception("correct playlist uuid: " + p.uuid + " Error: " + e.getMessage());
		}
	}

	private PlaylistItem getPlaylistItem(ResultSet resultSet) throws Exception {
		String playlistuuid = resultSet.getString("playlistUUID");
		String URI = resultSet.getString("clipURI");
		int index = resultSet.getInt("clipIndex");
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
}
