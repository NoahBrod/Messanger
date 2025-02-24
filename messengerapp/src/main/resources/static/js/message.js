var receiver;
var sender;

var socket = new SockJS('/Mappchat');
var stompClient = Stomp.over(socket);

/**
 * Retrieves chat for the current selected chat
 */
function loadChat() {
    let empty = $("#empty");
    empty.hide();

    let chatBox = $("#chatBox");

    let messages;

    $.get("/api/v1/messages/chat",
        {
            sender: sender,
            receiver: receiver
        },
        function (data, status) {
            if (status == 'success') {
                messages = data;
                messages.forEach(element => {
                    let chat = $("<div>");
                    if (element.senderId == sender) {
                        chat.addClass("message sent");
                    } else {
                        chat.addClass("message received");
                    }
                    chat.text(element.message);
                    chat.addClass("p-3 ")
                    chatBox.append(chat);
                });
                console.log(messages);

                chatBox.scrollTop(chatBox.prop("scrollHeight"));
            }
        });
}

function showMessage(message) {
    let chatBox = $("#chatBox");

    if (message.sender == receiver) {
        let chat = $("<div>");
        chat.addClass("message received");
        chat.text(message.message);
        chat.addClass("p-3 ");
        chatBox.append(chat);
        chatBox.scrollTop(chatBox.prop("scrollHeight"));
    } else {
        console.log("NEW")
        $('a[data-id="' + message.sender + '"]').find('small').attr("hidden", false);
    }

}

$(document).ready(function () {
    // setting current user id
    sender = $("#sender").data("id");

    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/user/' + sender + '/queue/messages', function (message) {
            showMessage(JSON.parse(message.body));
        });
    });

    // sets the current chat id and loads the chat
    $(".list-group-item").click(function () {
        if (receiver != $(this).data("id")) {
            receiver = $(this).data("id");
            $("#chatBox").empty();
            loadChat();
            $('a[data-id="' + receiver + '"]').find("small").attr("hidden", true);
        }
        
    });

    $("#chatForm").submit(function (event) {
        event.preventDefault();
        let inputField = $("#messageInput");
        let messageText = inputField.val().trim();

        if (messageText != "" && receiver) {
            let chatBox = $("#chatBox");
            let messageDiv = $("<div>").addClass("message sent").text(messageText);

            $.post("/api/v1/messages/new",
                {
                    message: messageText,
                    sender: sender,
                    receiver: receiver
                },
                function (data, status) {
                    console.log(status);
                });

            console.log(JSON.stringify({
                message: messageText,
                sender: sender,
                receiver: receiver
            }));

            stompClient.send("/app/send", {}, JSON.stringify({
                message: messageText,
                sender: sender,
                receiver: receiver
            }));

            chatBox.append(messageDiv);
            chatBox.scrollTop(chatBox.prop("scrollHeight"));

            inputField.val("");
            console.log(inputField.value)
        }
    });
});
