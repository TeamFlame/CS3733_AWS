/**
 * Retrieve list of videos in library from server
 * 
 * GET getVideos_url
 * Response array of VideoClip JSON objects
 */
function getVideos(isAdmin) {
  var xhr = new XMLHttpRequest();
  // TODO Define URL used here
  //xhr.open('GET', getVideos_url, true);
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
  segmentSection.innerHTML = '';
  var js = JSON.parse(videoList);
  console.log(js);
  var videoList = js.list;

  // For each video 
  for(let i = 0; i < videoList.length; i++) {
    if(i % 4 === 0) {
      segmentSection.innerHTML += '<br><br>'
    }
    let video = videoList[i];
    console.log(video);
    //  create and append a new video element
    var videoElement = document.createElement('video');

    // Set attributes
    videoElement.setAttribute('src', video.bucketURI);
    videoElement.setAttribute('width', '400');
    videoElement.setAttribute('height', '300');
    videoElement.setAttribute('controls', 'controls');

    // Append to doc
    segmentSection.appendChild(videoElement);

    
    // Add marking button for admins
    if(isAdmin) {
      let markButton = document.createElement('input');
      markButton.setAttribute('type', 'button');
      markButton.setAttribute('value', 'Mark for Remote Access');
      markButton.setAttribute('disabled', 'disabled');
      markButton.style.marginTop = '50px';
      segmentSection.appendChild(markButton);
      segmentSection.innerHTML += '<t>';
    }
    else {
      // Add delete button for users
      let deleteButton = document.createElement('input');
      deleteButton.setAttribute('type', 'button');
      deleteButton.setAttribute('value', 'Delete');
      deleteButton.setAttribute('id', video.bucketURI);
      deleteButton.setAttribute('onclick', 'deleteSegment(this.id)')
      segmentSection.appendChild(deleteButton);
      segmentSection.innerHTML += '<t>';
    }
  };
};

/**
 * Searches for a videoclip with given dialogue (text) and/or characters (char)
 * 
 * TODO
 */
function searchSegment(char, text, isAdmin) {
  console.log('Searching text:', text);
  console.log('Searching character:', char);

  // Determine if search is valid
  // TODO check character exists?
  if(char === '' && text === '') {return;}

  var xhr = new XMLHttpRequest();
  // TODO Define URL used here
  //xhr.open('GET', getVideos_url, true);
  xhr.open('GET', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/videos', true);
  xhr.send();

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      displaySearch(xhr.response, char, text, isAdmin);
    }
  };

};

/**
 * Searches for and displaysz results of a given search query
 */
function displaySearch(videoList, char, text, isAdmin) {
  console.log('Displaying search results');
  var segmentSection = document.getElementById('segments');
  segmentSection.innerHTML = '';
  var js = JSON.parse(videoList);
  console.log(js);
  var videoList = js.list;

  // Search using character and text fields
  var charSearch = searchByType(videoList, char, 'char');
  var textSearch = searchByType(videoList, text, 'text');
  var searchList = [];

  // Get overlap if both have videos (search via both vs by one)
  if(charSearch && charSearch.length === 0) {
    if(textSearch.length === 0) {return;}
    searchList = textSearch;
  }
  else if(textSearch && textSearch.length === 0) {searchList = charSearch;}
  else charSearch.forEach(videoURI => {
    if(textSearch.includes(videoURI)) {
      searchList.push(videoURI);
    }
  })

  // For each video 
  for(let i = 0; i < searchList.length; i++) {
    if(i % 5 === 0) {
      segmentSection.innerHTML += '<br><br>'
    }
    let videoURI = searchList[i];
    console.log(videoURI);
    //  create and append a new video element
    var videoElement = document.createElement('video');

    // Set attributes
    videoElement.setAttribute('src', videoURI);
    videoElement.setAttribute('width', '400');
    videoElement.setAttribute('height', '300');
    videoElement.setAttribute('controls', 'controls');

    // Append to doc
    segmentSection.appendChild(videoElement);

    
    // Add marking button for admins
    if(isAdmin) {
      let markButton = document.createElement('input');
      markButton.setAttribute('type', 'button');
      markButton.setAttribute('value', 'Mark for Remote Access');
      markButton.setAttribute('disabled', 'disabled');
      segmentSection.appendChild(markButton);
      segmentSection.innerHTML += '<br>';
    }
  };
}

/**
 * Searches given clips for a given string defined as a character name or dialogue
 * 
 * type should be 'char' for character or 'text' for dialogue
 * 
 * TODO
 */
function searchByType(videoList, query, type) {
  let result = [];
  if(query != '') {
    for(let i = 0; i < videoList.length; i++) {
      let video = videoList[i];

      if(type === 'char' && video.character == query) {
        result.push(video.bucketURI);
      }
      if(type === 'text' && video.text.includes(query)) {
        result.push(video.bucketURI);
      }
    }
  }
  return result;
};

/**
 * Handler function for uploading a file
 */
function handleUpload(file, char, text) {
  if(file.name === '') {return;}
  console.log('Handling upload');
  console.log(file);
  uploadSegment(getBase64(file), char, text);
};

/**
 * Converts video file to base64 string
 */
function getBase64(file) {
  console.log("Converting to base64");
  var reader = new FileReader();
  reader.readAsDataURL(file);
  
  reader.onload = function() {
    console.log('Reader result:', reader.result).then(() => {
      return reader.result;
    });
  };
};

/**
 * Uploads a given video segment tot he application library
 */
function uploadSegment(base64, char, text) {
  console.log("Sending Upload request for string:", base64);
  var xhr = new XMLHttpRequest();
  // TODO Define URL used here
  //xhr.open('GET', getVideos_url, true);
  xhr.open('POST', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/videos', true);
  xhr.send(JSON.stringify({
    base64EncodedString: base64,
    character: char,
    transcript: text,
    remoteAccess: false
  }));

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('Response:' + xhr.response);
      getVideos(false);
    }
  };
};



/**
 * Delete a given video segment from the library
 * 
 * TODO 
 */
function deleteSegment(video) {

};