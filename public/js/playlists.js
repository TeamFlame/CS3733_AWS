/**
 * Retrieve list of playlists in library from server
 * 
 * GET getPlaylists_url
 * Response array of playlist JSON objects
 */
function getPlaylists() {
  var xhr = new XMLHttpRequest();
  // TODO Define URL used here
  //xhr.open('GET', getPlaylists_url, true);
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
 * 
 * TODO Add functionality to display videos within playlists
 */
function displayPlaylists(playlistList) {
  console.log('Displaying playlists');
  var playlistSection = document.getElementById('playlists');
  playlistSection.innerHTML = '';

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
    deleteButton.setAttribute('type', 'button');
    deleteButton.setAttribute('value', 'Delete');
    deleteButton.setAttribute('id', playlist.name);
    //deleteButton.setAttribute('disabled', 'disabled');
    deleteButton.setAttribute('onclick', 'deletePlaylist(this.id)')
    playlistSection.appendChild(deleteButton);

    // Add videos inside playlist
    // TODO deal with async JS stuff here
    //displayContents(playlist);
    
    playlistSection.innerHTML += '<br><br>'
  };
};

/**
 * Add video segments for a given playlist to the webpage
 * 
 * TODO CORS error from POST request
 */
function displayContents(playlistName) {
  var xhr = new XMLHttpRequest();
  // TODO Define URL used here
  //xhr.open('POST', getPlaylists_url, true);
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/playlistVideos', true);
  xhr.send(JSON.stringify({name: name}));

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      displayVideos(xhr.response, false, 'playlists');
    }
  };
  
};

/**
 * Creates a new empty playlist with given name
 * 
 * TODO Test with lambda
 */
function createPlaylist(name) {
  if(name === '') {return;}
  console.log('Creating playlist:', name);
  var xhr = new XMLHttpRequest();
  // TODO Define URL used here
  //xhr.open('POST', getPlaylists_url, true);
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/playlist', true);
  //xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
  //xhr.setRequestHeader('content-type', 'application/json');
  xhr.send(JSON.stringify({name: name}));

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      getPlaylists(); // TODO get specific playlist to add???
    }
  };
};

/**
 * Appends a given video to the end of a playlist
 * 
 * TODO
 */
function appendSegment(video, playlistName) {
  console.log('Appending to playlist');
  var xhr = new XMLHttpRequest();
  // TODO define URL used here
  //xhr.open('POST', getPlaylists_url, true);
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/appendSegment', true);
  xhr.send({
    video: video,
    playlist: playlistName
  });

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
  // TODO Define URL used here
  //xhr.open('POST', getPlaylists_url, true);
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/deletePlaylist', true);
  xhr.send(JSON.stringify({name: name}));

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      getPlaylists();
    }
  };
};