/**
 * Retrieve list of videos in library from server
 * 
 * GET getVideos_url
 * Response array of VideoClip JSON objects
 */
function getVideos(isAdmin) {
  console.log('click')
  var xhr = new XMLHttpRequest();
  // TODO Define URL used here
  //xhr.open('GET', getVideos_url, true);
  xhr.open('GET', 'https://sl9n39xipj.execute-api.us-east-1.amazonaws.com/alpha/videos', true);
  xhr.send();

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('XHR:' + xhr.response);
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
    if(i % 5 === 0) {
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
      segmentSection.appendChild(markButton);
      segmentSection.innerHTML += '<br>';
    }
  };
};

/**
 * Searches for a videoclip with given dialogue (text) and/or characters (char)
 * 
 * TODO
 */
function searchSegment(char, text) {
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
      console.log('XHR:' + xhr.response);
      displaySearch(xhr.response, char, text, searchType);
    }
  };

};

/**
 * Searches for and displaysz results of a given search query
 * 
 * TODO
 */
function displaySearch(videoList, char, text, searchType) {
  console.log('Displaying search results');
  var segmentSection = document.getElementById(section);
  segmentSection.innerHTML = '';
  var js = JSON.parse(videoList);
  console.log(js);
  var videoList = js.list;

  // Search using character and text fields
  var charSearch = searchByChar(videoList, char);
  var textSearch = searchByText(videoList, text);

  // Get overlap if both have videos (search via both vs by one)

  // Conduct search and update here
  for(let x = 0; x < videoList.length; x++) {

  }

  // For each video 
  for(let i = 0; i < searchList.length; i++) {
    if(i % 5 === 0) {
      segmentSection.innerHTML += '<br><br>'
    }
    let video = searchList[i];
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
      segmentSection.appendChild(markButton);
      segmentSection.innerHTML += '<br>';
    }
  };
}

/**
 * Uploads a given videosegment tot he application library
 * 
 * TODO determine what form the input will come in (string?)
 */
function uploadSegment() {

};

/**
 * Delete a given vcideo segment from the library
 * 
 * TODO 
 */
function deleteSegment(video) {

};
