var receiver;
var sender;

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
            }
        });
}

$(document).ready(function () {
    // setting current user id
    sender = $("#sender").data("id");

    // sets the current chat id and loads the chat
    $(".list-group-item").click(function () {
        if (receiver != $(this).data("id")) {
            receiver = $(this).data("id");
            loadChat();
        }
    });

    $("#chatForm").submit(function (event) {
        event.preventDefault();
        let inputField = $("#messageInput");
        let messageText = inputField.val().trim();

        if (messageText != "") {
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

            chatBox.append(messageDiv);
            chatBox.scrollTop(chatBox.prop("scrollHeight"));

            inputField.val("");
            console.log(inputField.value)
        }
    });
});
