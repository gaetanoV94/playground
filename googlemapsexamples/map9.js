
google.load('maps', 3, {
  'other_params': 'sensor=false&language=de'
});

window.onload = function() {   
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(positionFound, positionError);
  } else {
    if (google.loader.ClientLocation && google.loader.ClientLocation.latitude && google.loader.ClientLocation.longitude) {
      var latLng = new google.maps.LatLng(google.loader.ClientLocation.latitude,
                                        google.loader.ClientLocation.longitude);    
      showMap(latLng, 10, "navigator.geolocation nicht vorhanden<br/>Geolocation von google.loader.ClientLocation");
    } else {
      showMap(new google.maps.LatLng(26.4,7.0), 2, "Geolocation nicht unterstützt");
    }
  }  
}

function positionFound(position) {
  showMap(new google.maps.LatLng(position.coords.latitude, position.coords.longitude), 
           15, "Deine Position");   
}

function positionError(error) {
  if (google.loader.ClientLocation && google.loader.ClientLocation.latitude && google.loader.ClientLocation.longitude) {
    var latLng = new google.maps.LatLng(google.loader.ClientLocation.latitude,
                                        google.loader.ClientLocation.longitude);
    
    showMap(latLng, 10, "Fehler: <br/>" + error.message + "<br/>Geolocation von google.loader.ClientLocation");
  } else {  
    showMap(new google.maps.LatLng(26.4,7.0), 2, "Fehler: <br/>" + error.message);
  }
}

function showMap(latLng, zoom, msg) {
  var mapContainer = document.getElementById('mapContainer');
  
  var options = {
    center: latLng,
    zoom: zoom,
    mapTypeControl: false,
    navigationControlOptions: {
      style: google.maps.NavigationControlStyle.SMALL
    },
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };
  
  var map = new google.maps.Map(mapContainer, options);
  
  var marker = new google.maps.Marker({
      position: latLng, 
      map: map, 
      title: msg
  });
  
  var infoWindow = new google.maps.InfoWindow({
      content: msg + "<br/>Lat: " + latLng.lat() + "<br/>Lang: " + latLng.lng()
  });  
  infoWindow.open(map, marker);
}
