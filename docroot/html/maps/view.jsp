<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>

<portlet:defineObjects />
<style type="text/css">
#map_canvas {
    height: 500px;
    width: 400px;
}
</style>
 
<script type="text/javascript">
var map;
var infowindow;
var geocoder;
var address = "Nike";
function initialize() {
  var pyrmont = new google.maps.LatLng(-33.8665433, 151.1956316);
  geocoder = new google.maps.Geocoder();
  map = new google.maps.Map(document.getElementById('map_canvas'), {
    center: pyrmont,
    zoom: 15,
    mapTypeId : google.maps.MapTypeId.ROADMAP
  });

  var request = {
    location: pyrmont,
    radius: 500,
    types: ['store']
  };
  //infowindow = new google.maps.InfoWindow();
  //var service = new google.maps.places.PlacesService(map);
  //service.nearbySearch(request, callback);
  codeAddress();
  
}

function callback(results, status) {
  if (status == google.maps.places.PlacesServiceStatus.OK) {
    for (var i = 0; i < results.length; i++) {
      createMarker(results[i]);
    }
  }
}

function codeAddress() {
geocoder.geocode( { 'address': address}, function(results, status) {
    if (status == google.maps.GeocoderStatus.OK) {
      map.setCenter(results[0].geometry.location);
      for (var i = 0; i < results.length; i++) {
    	  var place = results[i];
          createMarker(results[i]);
      }
      
      // var marker = new google.maps.Marker({
       //map: map,
       //position: results[0].geometry.location
     //});
   
   
    } 
    else {
     //initialize2();
    }
  });
}
  

function createMarker(place) {
  var placeLoc = place.geometry.location;
  var marker = new google.maps.Marker({
    map: map,
    position: place.geometry.location
  });

  google.maps.event.addListener(marker, 'click', function() {
    infowindow.setContent(place.name);
    infowindow.open(map, this);
  });
}

function <portlet:namespace />loadScript() {
    var script = document.createElement("script");
    script.type = "text/javascript";
    script.src = "http://maps.google.com/maps/api/js?sensor=false&callback=initialize";
    document.body.appendChild(script);
}

window.onload = <portlet:namespace />loadScript;
</script>
 
<div id="map_canvas"></div>

<aui:input id="search_loc" name="Enter Location:" type="text"></aui:input>
<aui:button name="submit_f" value="Submit:" onClick="createMarker('CentralPark')"></aui:button>


