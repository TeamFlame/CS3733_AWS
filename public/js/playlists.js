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
    playlistSection.appendChild(deleteButton);
    playlistSection.innerHTML += '<br><br>'

    // Add videos inside playlist
    // TODO write this
  };
};

function createPlaylist() {

};

function deletePlaylist() {

};

function searchSegment() {
  
};