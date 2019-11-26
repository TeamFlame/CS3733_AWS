package edu.wpi.cs.cs3733.flame.model;

public class Library 
{
	Playlist[] playlists;
	VideoClip[] clipList;
	String[] remoteSites;

	public Library()
	{
		
	}
	
	void createPlaylist(String name)
	{
		playlists[playlists.length] = new Playlist(name);
	}
	void deletePlaylist(Playlist playlist)
	{
		boolean deletedFound = false;
		//true if one that is to be deleted has been found
		for(int i = 0; i <this.playlists.length; i++)
		{
			if(playlists[i].equals(playlist))
			{
				playlists[i]=playlists[i+1];
				deletedFound = true;
				//if deleted one is found, the next array spot replaces it, and flag is triggered
			}
			if (deletedFound == true && i < playlists.length-1)
			{
				playlists[i]=playlists[i+1];
				//shifts down all of the playlists after the deleted one as not to leave gaps
			}
			else if(deletedFound == true)
			{
				playlists[i]=null;
				//sets final array space to null to make sure its empty
				
			}
		}
	}
	void uploadClip(VideoClip clip)
	{

		clipList[clipList.length] = clip;
	}
	void deleteClip(VideoClip clip)
	{
		
		//didnt know if this was sipposed to remove a clip from a playlist once deleted so i assumed not.
		boolean deletedFound = false;
		//true if one that is to be deleted has been found
		for(int i = 0; i <this.clipList.length; i++)
		{
			if(clipList[i].equals(clip))
			{
				clipList[i]=clipList[i+1];
				deletedFound = true;
				//if deleted one is found, the next array spot replaces it, and flag is triggered
			}
			if (deletedFound == true && i < clipList.length-1)
			{
				clipList[i]=clipList[i+1];
				//shifts down all of the playlists after the deleted one as not to leave gaps
			}
			else if(deletedFound == true)
			{
				clipList[i]=null;
				//sets final array space to null to make sure its empty
				
			}
		}
	}
	VideoClip[] getAllClips()
	{
		return this.clipList;
	}
	VideoClip[] searchClips(String query)
	{
		return this.clipList;
		//The above is definitely not permanent, just here to get the error to shut up
	}
	/*Playlist[] playlists;
	VideoClip[] clipList;
	String[] remoteSites;*/
	void setPlaylists(Playlist[] playlists)
	{
		this.playlists=playlists;
	}
	Playlist[] getPlaylists()
	{
		return this.playlists;
	}
	void setClipList(VideoClip[] clips)
	{
		this.clipList=clips;
	}
	VideoClip[] getClipList()
	{
		return this.clipList;
	}
	void setRemoteSites(String[] rs)
	{
		this.remoteSites = rs;
	}
	String[] getRemoteSites()
	{
		return this.remoteSites;
	}
}
