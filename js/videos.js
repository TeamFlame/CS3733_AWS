/**
 * Retrieve list of videos in library from server
 * 
 * GET getVideos_url
 * Response array of VideoClip JSON objects
 */
function getVideos() {
  var xhr = new XMLHttpRequest();
  // TODO Define URL used here
  xhr.open('GET', getVideos_url, true);
  xhr.send();

  xhr.onloadend = function() {    
    if(xhr.readyState == XMLHttpRequest.DONE) {
      console.log('XHR:' + xhr.response);
      displayVideos(xhr.response);
    }
  };
};

/**
 * Displays available videos on the page
 * 
 * 
 */
function displayVideos(videoList) {
  var segmentSection = document.getElementById('segments');
  var js = JSON.parse(videoList);

  // For each video 
  js.forEach(video => {
    console.log(video);
    //  create and append a new video element
    var videoElement = document.createElement('video');

    // Set attributes
    videoElement.setAttribute('src', video.bucketURI); // TODO see if the bucketURI is the correct source URL
    videoElement.setAttribute('width', '400');
    videoElement.setAttribute('height', '300');
    videoElement.setAttribute('controls', 'controls'); // TODO necessary???

    // Append to doc
    segmentSection.appendChild(videoElement);
  });
};