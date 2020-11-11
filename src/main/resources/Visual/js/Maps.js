//Documentacion: https://developers.google.com/maps/documentation/javascript/examples

let map;
function success(pos){
    var crd = pos.coords
    initMap(crd.latitude, crd.longitude)
}

navigator.geolocation.getCurrentPosition(success)


function initMap(lat, long) {
    var coord = {lat:lat, lng: long},
    map = new google.maps.Map(document.getElementById("map"), {
        center: coord,
        zoom: 8.1,
    });

    addMarker({lat:lat ,lng: long}, map, "Sucursal1");
    addMarker({lat:19.444934,lng: -70.685534}, map, "Sucursal2");
}


function addMarker(location, map, sucursal) {
    // Add the marker at the clicked location, and add the next-available label
    // from the array of alphabetical characters.
    new google.maps.Marker({
        position: location,
        label: sucursal,
        map: map
    });
}
