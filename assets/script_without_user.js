$(document).ready(function() {
    govmap.createMap('map', {
        token: 'a98c8e1a-998b-4b80-a19d-0adbe8036244',
        layers: ["AGRIPARCELS"],
        showXY: true,
        identifyOnClick: true,
        isEmbeddedToggle: false,
        background: "1",
        layersMode: 2,
        zoomButtons: true
    });
});
