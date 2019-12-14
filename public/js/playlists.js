/**
 * Retrieve list of playlists in library from server
 */
function getPlaylists() {
  var xhr = new XMLHttpRequest();
  xhr.open('GET', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/playlists', true);
  xhr.send();

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      displayPlaylists(xhr.response);
    }
  };
};

/**
 * Displays available playlists on the page
 */
function displayPlaylists(playlistList) {
  console.log('Displaying playlists');
  var playlistSection = document.getElementById('playlistList');
  playlistSection.innerHTML = '';

  var js = JSON.parse(playlistList);
  console.log(js);
  var playlistList = js.list;
  updatePlaylistSelector(playlistList);

  // For each playlist 
  for(let i = 0; i < playlistList.length; i++) {
    let playlist = playlistList[i];
    console.log(playlist);

    // Add playlist name and buttons
    playlistSection.innerHTML += playlist.name + ': '
    let deleteButton = document.createElement('input');
    deleteButton.setAttribute('type', 'button');
    deleteButton.setAttribute('value', 'Delete');
    deleteButton.setAttribute('id', playlist.name);
    deleteButton.setAttribute('onclick', 'deletePlaylist(this.id)')
    playlistSection.appendChild(deleteButton);
    
    playlistSection.innerHTML += '<br><br>'
  };
};

/**
 * Add video segments for a given playlist to the webpage
 */
function displayContents(name) {
  // Clear old contents
  let contentSection = document.getElementById('playlists');
  contentSection.innerHTML = '';

  var xhr = new XMLHttpRequest();
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/playlistVideos', true);
  xhr.send(JSON.stringify({name: name}));

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      console.log('Displaying Working Playlist');
      displayVideos(xhr.response, false, 'playlists');
    }
  };
  
};

/**
 * Creates a new empty playlist with given name
 */
function createPlaylist(name) {
  if(name === '') {return;}
  console.log('Creating playlist:', name);
  var xhr = new XMLHttpRequest();
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/playlist', true);
  xhr.send(JSON.stringify({name: name}));

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      getPlaylists();
    }
  };
};

/**
 * Appends a given video to the end of a playlist
 */
function appendSegment(videoURI) {
  let index = document.getElementById('selector').selectedIndex;
  let workingPlaylist = selector[index].value;
  console.log('Appending to playlist:', workingPlaylist);

  var xhr = new XMLHttpRequest();
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/appendSegment', true);
  xhr.send(JSON.stringify({
    video: videoURI,
    playlist: workingPlaylist
  }));

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      getPlaylists();
    }
  };
};

/**
 * Removes a given segment from the working playlist
 */
function removeSegment(clipID) {
  let index = document.getElementById('selector').selectedIndex;
  let workingPlaylist = selector[index].value;
  console.log('Removing from playlist:', workingPlaylist);

  var xhr = new XMLHttpRequest();
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/removeSegment', true);
  xhr.send(JSON.stringify({
    video: clipID,
    playlist: workingPlaylist
  }));

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      getPlaylists();
    }
  };
};

/**
 * Deletes the playlist of the given name from the library
 */
function deletePlaylist(name) {
  console.log('Deleting playlist:', name);
  var xhr = new XMLHttpRequest();
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/deletePlaylist', true);
  xhr.send(JSON.stringify({name: name}));

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      getPlaylists();
    }
  };
};

/**
 * Updates the selector for the working playlist
 */
function updatePlaylistSelector(playlistList) {
  let newSelector = document.createElement('select');
  newSelector.setAttribute('onchange', 
  'displayContents(document.getElementById("selector")[document.getElementById("selector").selectedIndex].value)')
  newSelector.setAttribute('id', 'selector');
  let selectorDiv = document.getElementById('playlistSelector');

  for(let i = 0; i < playlistList.length; i++) {
    let playlist = playlistList[i];
    let option = document.createElement('option');
    option.value = playlist.name;
    option.text = playlist.name;
    newSelector.appendChild(option);
  }
  selectorDiv.innerHTML = '';
  selectorDiv.appendChild(newSelector);

  // Display contents if none are displayed
  if(document.getElementById('playlists'))
  displayContents(newSelector[newSelector.selectedIndex].value);
};
