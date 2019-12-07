/**
 * Adds a remote site to the list of sites in the library
 */
function addRemote(url) {
  console.log('Adding remote site:', url);
  var xhr = new XMLHttpRequest();
  // TODO Define URL used here
  //xhr.open('POST', getPlaylists_url, true);
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/remote', true);
  //xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
  //xhr.setRequestHeader('content-type', 'application/json');
  xhr.send(JSON.stringify({url: url}));

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
 * TODO
 */
function removeRemote() {

};

/**
 * Gets the list of remote sites in the library
 */
function getRemotes() {
  var xhr = new XMLHttpRequest();
  // TODO Define URL used here
  //xhr.open('GET', getPlaylists_url, true);
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
 * Displays the remote sites onto the page
 */
function displayRemotes(remoteList) {
  console.log('Displaying remotes');
  var remoteSection = document.getElementById('remotes');
  remoteSection.innerHTML = '';

  // TODO confirm grabbing list correctly
  var js = JSON.parse(remoteList);
  console.log(js);
  var remoteList = js.list;

  // For each remote 
  for(let i = 0; i < remoteList.length; i++) {
    let remote = remoteList[i];
    console.log(remote);

    // Add remote name and buttons
    remoteSection.innerHTML += "Site:" + remote;    
    remoteSection.innerHTML += '<br>';
  };
};