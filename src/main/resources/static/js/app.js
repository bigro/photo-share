var stompClient = null;

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/images', function (image) {
            showGreeting(JSON.parse(image.body).url);
        });
    });
}

function showGreeting(image) {
    $("#images").append("<li><img src=\"" + image + "\"/></li>");
}

$(function () {
    connect();
});