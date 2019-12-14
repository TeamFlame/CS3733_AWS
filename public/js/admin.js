/**
 * Gets the list of remote sites in the library
 */
function getRemotes() {
  var xhr = new XMLHttpRequest();
  xhr.open('GET', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/remotes', true);
  xhr.send();

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      displayRemotes(xhr.response);
    }
  };
};

/**
 * Adds a remote site to the list of sites in the library
 */
function addRemote(url) {
  console.log('Adding remote site:', url);
  var xhr = new XMLHttpRequest();
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/remote', true);
  xhr.send(JSON.stringify({apiURI: url}));

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      getRemotes();
    }
  };
};

/**
 * Removes the given remote site from the library
 * 
 * TODO Test
 */
function removeRemote(url) {
  console.log('Deleting remote site:', url);
  var xhr = new XMLHttpRequest();
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/removeRemote', true);
  xhr.send(JSON.stringify({apiURI: url}));

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      getRemotes();
    }
  };
};

/**
 * Displays the remote sites onto the page
 */
function displayRemotes(remoteList) {
  console.log('Displaying remotes');
  var remoteSection = document.getElementById('remotes');
  remoteSection.innerHTML = '';

  var js = JSON.parse(remoteList);
  console.log(js);
  var remoteList = js.list;

  // For each remote 
  for(let i = 0; i < remoteList.length; i++) {
    let remoteURL = remoteList[i];
    console.log(remoteURL);

    // Add remote name and buttons
    remoteSection.innerHTML += "Remote Site URL: " + remoteURL;    
    let deleteButton = document.createElement('input');
    deleteButton.setAttribute('type', 'button');
    deleteButton.setAttribute('value', 'Delete');
    deleteButton.setAttribute('id', remoteURL);
    deleteButton.setAttribute('onclick', 'removeRemote(this.id)')
    remoteSection.appendChild(deleteButton);
    remoteSection.innerHTML += '<br>';
  };
};

/**
 * Marks a local segment for remote access
 * 
 * TODO Test
 */
function markSegment(videoURI) {
  console.log("Marking video:", videoURI);
  var xhr = new XMLHttpRequest();
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/markSegment', true);
  xhr.send(JSON.stringify({bucketURI: videoURI}));

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      getVideos(true);
    }
  };
};

/**
 * Unmarks a local segment for remote access
 */
function unmarkSegment(videoURI) {
  console.log("Unmarking video:", videoURI);
  var xhr = new XMLHttpRequest();
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/unmarkSegment', true);
  xhr.send(JSON.stringify({bucketURI: videoURI}));

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      getVideos(true);
    }
  };
};