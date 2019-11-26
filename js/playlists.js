/**
 * Retrieve list of playlists in library from server
 * 
 * GET getPlaylists_url
 * Response array of playlist JSON objects
 */
function getPlaylists() {
  var xhr = new XMLHttpRequest();
  // TODO Define URL used here
  xhr.open('GET', getPlaylists_url, true);
  xhr.send();

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('XHR:' + xhr.response);
      displayPlaylists(xhr.response);
    }
  };
};


/**
 * Displays available Playlists on the page
 * 
 * 
 */
function displayPlaylists(playlists) {
  var playlistSection = document.getElementById('playlists');
  var js = JSON.parse(playlists);

  // For each video 
  js.forEach(playlist => {
    console.log(playlist);

    // Name of playlist
    let newHTML = playlist.name + '<br>';
    playlistSection.innerHTML += newHTML;


    // TODO actually show the contents of the playlist, not just the names
  });
};

function createPlaylist() {

};

function deletePlaylist() {

};

function searchSegment() {
  
};