/**
 * Retrieve list of videos in library from server
 */
function getVideos(isAdmin) {
  var xhr = new XMLHttpRequest();
  xhr.open('GET', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/videos', true);
  xhr.send();

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      displayVideos(xhr.response, isAdmin, 'segments');
    }
  };
};

/**
 * Displays available videos on the page in the given section
 * 
 * param section must be either 'segments' or 'playlists'
 */
function displayVideos(videoList, isAdmin, section) {
  console.log('Displaying videos');
  var segmentSection = document.getElementById(section);
  if(section === 'segments') {segmentSection.innerHTML = '';}
  var js = JSON.parse(videoList);
  console.log(js);
  var videoList = js.list;

  // For each video 
  for(let i = 0; i < videoList.length; i++) {
    let video = videoList[i];
    console.log(video);

    // Create new container for video and elements
    var container = document.createElement('section');
    container.setAttribute('class', 'd-inline-flex container');

    // Create and append a new video element
    var videoElement = document.createElement('video');

    // Set attributes
    if(section === 'segments') {
      videoElement.setAttribute('src', video.bucketURI);
    }
    else videoElement.setAttribute('src', video.clipID);

    videoElement.setAttribute('width', '320');
    videoElement.setAttribute('height', '240');
    videoElement.setAttribute('controls', 'controls');

    // Append to container
    container.appendChild(videoElement);
    
    // Add marking button for admins
    if(isAdmin) {
      let markButton = document.createElement('input');
      markButton.setAttribute('type', 'button');
      markButton.setAttribute('value', 'Mark for remote access');
      markButton.setAttribute('id', video.bucketURI);
      markButton.setAttribute('onclick', 'markSegment(this.id)')
      container.appendChild(markButton);
      
      let unmarkButton = document.createElement('input');
      unmarkButton.setAttribute('type', 'button');
      unmarkButton.setAttribute('value', 'Unmark');
      unmarkButton.setAttribute('id', video.bucketURI);
      unmarkButton.setAttribute('onclick', 'unmarkSegment(this.id)')
      container.appendChild(unmarkButton);

      // Add character and text fields
      let info = document.createElement('p');
      info.innerHTML += 'Character: ' + video.character + '<br><br>';
      info.innerHTML += 'Transcript: ' + video.text;
      container.appendChild(info);

      if(video.remoteAccess) {
        markButton.setAttribute('disabled', 'disabled');
      } else unmarkButton.setAttribute('disabled', 'disabled');
    }
    else if(section === 'segments'){
      // Add delete and append button for users
      let deleteButton = document.createElement('input');
      deleteButton.setAttribute('type', 'button');
      deleteButton.setAttribute('value', 'Delete');
      deleteButton.setAttribute('id', video.bucketURI);
      deleteButton.setAttribute('onclick', 'deleteSegment(this.id)')
      container.appendChild(deleteButton);
      let appendButton = document.createElement('input');
      appendButton.setAttribute('type', 'button');
      appendButton.setAttribute('value', 'Append');
      appendButton.setAttribute('id', video.bucketURI);
      appendButton.setAttribute('onclick', 'appendSegment(this.id)')
      container.appendChild(appendButton);
      
      // Add character and text fields
      let info = document.createElement('p');
      // TODO set id???
      info.innerHTML += 'Character: ' + video.character + '<br><br>';
      info.innerHTML += 'Transcript: ' + video.text;
      container.appendChild(info);
    }
    else if(section === 'playlists'){
      // Tag video with id and callback for playing
      videoElement.setAttribute('id', 'video' + i);
      playNextFunction = playFunction('video' + (i + 1));
      videoElement.addEventListener('ended', playNextFunction);

      // Add delete and append button for users
      let deleteButton = document.createElement('input');
      deleteButton.setAttribute('type', 'button');
      deleteButton.setAttribute('value', 'Remove');
      deleteButton.setAttribute('id', video.clipID);
      deleteButton.setAttribute('onclick', 'removeSegment(this.id)')
      container.appendChild(deleteButton);
    }

    // Append container to doc
    segmentSection.appendChild(container);
  };
  if(!isAdmin) {getRemoteVideos()};
};

/**
 * Searches for a videoclip with given dialogue (text) and/or characters (char)
 */
function searchSegment(char, text) {
  console.log('Searching text:', text);
  console.log('Searching character:', char);

  // Determine if search is valid
  if(char === '' && text === '') {return;}

  var segmentContainers = document.getElementById('segments').childNodes;
  for(let i = 0; i < segmentContainers.length; i++) {
    let container = segmentContainers[i];
    // The element that has character/text info in it
    console.log(container.childNodes);
    let infoNode = container.childNodes[3];
    if(!infoNode) {
      // Remote videos don't have a delete button so one less element
      infoNode = container.childNodes[2];
    }
    if((infoNode.innerText.includes(char) && char != '') || (infoNode.innerText.includes(text) && text != '')) {
      console.log('Not this one')
      continue;
    }
    else {
      //container.style.visibility = 'hidden';
      container.setAttribute('class', 'd-none');
    }
  }
};

/**
 * Handler function for converting a newly selected file for upload
 */
function handleNewFile(event) {
  var files = event.target.files;
  console.log(files)
  getBase64(files[0]);
};

/**
 * Converts video file to base64 string
 */
function getBase64(file) {
  console.log("Converting to base64");
  var reader = new FileReader();
  reader.readAsDataURL(file);
  
  reader.onload = function() {
    document.uploadForm.base64Encoding.value = reader.result.split(',')[1];
    document.uploadForm.uploadButton.disabled = false;
  };
};

/**
 * Uploads a given video segment to the application library
 */
function uploadSegment(base64, char, text) {
  var xhr = new XMLHttpRequest();
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/videos', true);
  xhr.send(JSON.stringify({
    base64EncodedString: base64,
    character: char,
    transcript: text,
    remoteAccess: false
  }));

  console.log(xhr);

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      getVideos(false);
    }
  };
};

/**
 * Delete a given video segment from the library
 */
function deleteSegment(videoURI) {
  console.log("Deleting video id:", videoURI);
  var xhr = new XMLHttpRequest();
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/deleteSegment', true);
  xhr.send(JSON.stringify({bucketURI: videoURI}));

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      getVideos(false);
    }
  };
};

/**
 * Returns a function for use in playing the contents of a playlist
 */
function playFunction(id) {
  return function(e) {
    console.log('Playing:', id)
    document.getElementById(id).play();
  };
};

/**
 * Retrieves videos from registered remote sites and displays trhem
 */
function getRemoteVideos() {
  var xhr = new XMLHttpRequest();
  xhr.open('GET', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/remotes', true);
  xhr.send();

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      iterateRemoteSites(xhr.response);
    }
  };
};

/**
 * Iterates over remote sites to display videos
 */
function iterateRemoteSites(remoteList) {
  var js = JSON.parse(remoteList);
  console.log(js);
  var remotes = js.list;

  for(let i = 0; i < remotes.length; i++) {
    let remoteURL = remotes[i];
    validate(remoteURL);
  }
};

/**
 * Validates a remote URL by separating the url and api key
 */
function validate(urlapi) {
  var q = urlapi.indexOf("?apikey=");
  if (q == -1) {
    alert("Your input must be of the form 'url?apikey=...'");
  } else {
    var url = urlapi.substring(0, q);
    var api = urlapi.substring(q+8);

    requestSegments(url, api);
  }
}

/**
 * Gets the segments from a given remote site
 */
function requestSegments(url, apikey) {
  var xhr = new XMLHttpRequest();
  xhr.open("GET", url, true);
  xhr.setRequestHeader("x-api-key", apikey);
  xhr.send();
   
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:', xhr.response);
      displayRemoteVideos(xhr.response);
    }
  }
}

/**
 * Displays videos from the requestSegments function
 */
function displayRemoteVideos(segmentsResponse) {
  var segmentSection = document.getElementById('segments');
  var js = JSON.parse(segmentsResponse);
  console.log(js);
  var videos = js.segments;
  console.log('RemoteVideos');
  console.log(videos);

  // For each video 
  for(let i = 0; i < videos.length; i++) {
    let video = videos[i];
    console.log(video);

    // Create new container for video and elements
    var container = document.createElement('section');
    container.setAttribute('class', 'd-inline-flex container');

    // Create and append a new video element
    var videoElement = document.createElement('video');

    // Set attributes
    videoElement.setAttribute('src', video.url);
    videoElement.setAttribute('width', '320');
    videoElement.setAttribute('height', '240');
    videoElement.setAttribute('controls', 'controls');

    // Append to container
    container.appendChild(videoElement);
    

    // Add append button for users
    let appendButton = document.createElement('input');
    appendButton.setAttribute('type', 'button');
    appendButton.setAttribute('value', 'Append');
    appendButton.setAttribute('id', video.url);
    appendButton.setAttribute('onclick', 'appendSegment(this.id)')
    container.appendChild(appendButton);
    
    // Add character and text fields
    let info = document.createElement('p');
    info.innerHTML += 'Character: ' + video.character + '<br><br>';
    info.innerHTML += 'Transcript: ' + video.text;
    container.appendChild(info);

    // Append container to doc
    segmentSection.appendChild(container);
  };
};