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
      displayVideos(xhr.response, isAdmin);
    }
  };
};
/**
 * Search Function, displays videos that match criteria
 * 
 * The entirety of the search is designed to work as follows:
 * 1. User enters query, which is passed into JS once button is pressed
 * 2. If invalid query, a window appears, telling user that they entered something invalid
 * 3. If valid, the appropriate search function is called
 * 4. The search functions are the display video function, but with a filter applied to the array, which removes those not fitting the criteria
 * 5.displayvideo function uses the filtered array
 */
function doSearch(name,dialogue)
{
	if ((name.equals("")||name.equals(null))&&(!(dialogue.equals("")||dialogue.equals(null)))//did not know what javascript has for its nothing here value
		{
		//if name not entered, and dialogue is, do dialogue search
		}

	else if ((dialogue.equals("")||dialogue.equals(null))&&(!(name.equals("")||name.equals(null)))
		{
		//if there was no dialogue, but a name, do name search
		}
	else
		{
		var myWindow = window.open("", "MsgWindow", "width=200, height=100");
		    myWindow.document.write("<p>You have entered an invalid search input. Please try again.</p>");
		}
}

function nameSearch(name)
{
	  console.log('Doing name search');
	  var segmentSection = document.getElementById('segments');
	  var js = JSON.parse(videoList);
	  console.log(js);
	  var videoList = js.list;

	  // For each video 
	  for(let i = 0; i < videoList.length; i++) {
	    if(i % 5 === 0) {
	      segmentSection.innerHTML += '<br><br>'
	    }
	    let video = videoList[i];
	    //changed code from default display video starts here
	    //I did it here since i interpret this code as having video hold all of the video segments, so using
	    //a for each through all of them, deleting all not containing the criteria from the list.
	    console.log("Starting search..."); 
	    var filteredVideo= video.filter(namecheck(item))
	    function namecheck(item)
	    {
	    	return item.name.equals(name);
	    };
	    console.log("Search Complete!"); 
	    console.log(filteredVideo);
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

	function nameSearch(name)
	{
		  console.log('Doing name search');
		  var segmentSection = document.getElementById('segments');
		  var js = JSON.parse(videoList);
		  console.log(js);
		  var videoList = js.list;

		  // For each video 
		  for(let i = 0; i < videoList.length; i++) {
		    if(i % 5 === 0) {
		      segmentSection.innerHTML += '<br><br>'
		    }
		    let video = videoList[i];
		    //changed code from default display video starts here
		    //I did it here since i interpret this code as having video hold all of the video segments, so using
		    //a for each through all of them, deleting all not containing the criteria from the list.
		    console.log("Starting search..."); 
		    var filteredVideo= video.filter(dialogueCheck(item))
		    function dialogueCheck(item)
		    {
		    	return item.dialogue.equals(name);
		    };
		    console.log("Search Complete!"); 
		    console.log(filteredVideo);
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
 * Displays available videos on the page
 */
function displayVideos(videoList, isAdmin) {
  console.log('Displaying videos');
  var segmentSection = document.getElementById('segments');
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

window.onload = function() {
  getVideoButton = document.getElementById(videosButton);
  getVideoButton.onclick = this.getVideos();
  this.console.log('click setup done');
};