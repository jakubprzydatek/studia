var pinezki = [];
var punkty = [];
var layer;
var map;
var infobox;
function GetMap() {
    map = new Microsoft.Maps.Map('#myMap', {
        credentials: 'Aq1pHBuXcN4VABhVT2pZ4SrPJjNALwdXKP5O7O8zbrMtRxEZkbmt3HGGTh3wGG3G',
        mapTypeId: Microsoft.Maps.MapTypeId.a,
        center: new Microsoft.Maps.Location(51.0799, 17.0642),
        zoom: 10
    });

    Microsoft.Maps.Events.addHandler(map, 'dblclick', function (e) {
        map.setOptions({ disableZooming: true });
        setTimeout(function () { return map.setOptions({ disableZooming: false }); });
        var point = new Microsoft.Maps.Point(e.getX(), e.getY());
        var location = e.target.tryPixelToLocation(point);

        punkty.push(location);
        addPushPins(map);
    });
}

function addPushPins(map)
{
    map.layers.remove(layer);
    layer = new Microsoft.Maps.Layer();
    punkty.forEach(element => {
        var pin = new Microsoft.Maps.Pushpin(element, {
            title: 'Coords: '+element.latitude+', '+element.longitude,
            color: 'green',
            text: 'P'
        });
        layer.add(pin);
    });
    map.layers.insert(layer);
    refreshList();
}

function refreshList()
{
    $("#points option").remove();
    var i=0;
    punkty.forEach(element => {
        $( "#points" ).append("<option value='"+ i +"'>" + element.latitude+', '+element.longitude + "</option>" );
        i+=1;
    });

}

$( "#remove" ).click(function() {
    console.log($( "#points option:selected" ).val());
    removeFromListById($( "#points option:selected" ).val());
    addPushPins(map);
    console.log(punkty);
  });

  $( "#show" ).click(function() {
    console.log($( "#points option:selected" ).val());
    addInfoBox($( "#points option:selected" ).val());
  });


function removeFromListById(id)
{
    var tempArr = [];
    var i = 0;
    punkty.forEach(element => {
        if(i != id){
            tempArr.push(element);
        }
        console.log(tempArr);
        i+=1;
    });
    punkty = tempArr;
}


function addInfoBox(id)
{
    var i = 0;
    punkty.forEach(element => {
        if(i == id){
            createInfoBox(element);
        }
        i+=1;
    });

    
}

function createInfoBox(loc)
{
    if(infobox != null) infobox.setMap(null);
    infobox = new Microsoft.Maps.Infobox(loc, {
        title: 'To tutaj!',
        description: loc.latitude+', '+loc.longitude,
        showPointer: false, 
        showCloseButton: true
    });

    infobox.setMap(map);
}