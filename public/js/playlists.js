/**
 * Retrieve list of playlists in library from server
 * 
 * GET getPlaylists_url
 * Response array of playlist JSON objects
 */
function getPlaylists() {
  console.log('click')
  var xhr = new XMLHttpRequest();
  // TODO Define URL used here
  //xhr.open('GET', getPlaylists_url, true);
  xhr.open('GET', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/playlists', true);
  xhr.send();

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('XHR:' + xhr.response);
      displayPlaylists(xhr.response);
    }
  };
};

/**
 * Displays available playlists on the page
 * 
 * TODO Add functionality to display videos within playlists
 */
function displayPlaylists(playlistList) {
  console.log('Displaying playlists');
  var playlistSection = document.getElementById('playlists');

  // TODO figure how to access list of playlists from response 
  var js = JSON.parse(playlistList);
  console.log(js);
  var playlistList = js.list;

  // For each playlist 
  for(let i = 0; i < playlistList.length; i++) {
    let playlist = playlistList[i];
    console.log(playlist);

    // Add playlist name and buttons
    playlistSection.innerHTML += playlist.name + ': '
    let deleteButton = document.createElement('input');
    // TODO add delete button functionality
    deleteButton.setAttribute('type', 'button');
    deleteButton.setAttribute('value', 'Delete');
    deleteButton.setAttribute('id', playlist.name);
    deleteButton.setAttribute('disabled', 'disabled');
    playlistSection.appendChild(deleteButton);

    // Add videos inside playlist
    // TODO write this

    playlistSection.innerHTML += '<br><br>'
  };
};

/**
 * Creates a new empty playlist with given name
 * 
 * TODO
 */
function createPlaylist(name) {
  console.log('Creating playlist:', name);
  var xhr = new XMLHttpRequest();
  // TODO Define URL used here
  //xhr.open('POST', getPlaylists_url, true);
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/playlist', true);
  xhr.send({PlaylistName: name});

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('XHR:' + xhr.response);
      getPlaylists();
    }
  };
};

/**
 * Deletes the playlist of the given name from the library
 * 
 * TODO
 */
function deletePlaylist(name) {
  console.log('Deleting playlist:', name);
  var xhr = new XMLHttpRequest();
  // TODO Define URL used here
  //xhr.open('POST', getPlaylists_url, true);
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/deletePlaylist', true);
  xhr.send({PlaylistName: name});

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('XHR:' + xhr.response);
      getPlaylists();
    }
  };
};

/**
 * Searches for a videoclip witrh given dialogue (text) and/or characters (char)
 * 
 * TODO
 */
function searchSegment(text, char) {
  console.log('Searching text:', text);
  console.log('Searching character:', char);
  var xhr = new XMLHttpRequest();
  // TODO Define URL used here
  //xhr.open('POST', getPlaylists_url, true);
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/searchVideo', true);
  xhr.send(text, char);

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('XHR:' + xhr.response);
      // Do something
    }
  };

};