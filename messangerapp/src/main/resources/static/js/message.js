let listItem = $("<a>");
    listItem.addClass("list-group-item list-group-item-action py-3 lh-tight");
    listItem.attr("aria-current","true");
let headerDiv = $("<div>");
    headerDiv.addClass("d-flex w-100 align-items-center justify-content-between");
let innerHeaderSt = $("<strong>");
    innerHeaderSt.addClass("mb-1");
let innerHeaderSm = $("<small>");
let subHead = $("<div>");
    subHead.addClass("col-10 mb-1 small");

    

function loadUsers() {
    let listItem = $("<a>");
    listItem.addClass("list-group-item list-group-item-action py-3 lh-tight");
    listItem.attr("aria-current","true");

}

$(document).ready(function () {
    $.get("/api/v1/messages/all", function (data, status) {
        console.log("Data: " + data + "\nStatus: " + status);
    });

    console.log("Message from Thymeleaf:", messageData);

    $("#chatForm").submit(function (event) {
        event.preventDefault();
        let inputField = $("#messageInput");
        let messageText = inputField.val().trim();

        if (messageText != "") {
            let chatBox = $("#chatBox");
            let messageDiv = $("<div>").addClass("message sent").text(messageText);

            $.post("/api/v1/messages/new",
                {
                    message: "Donald Duck",
                },
                function (data, status) {
                    alert("Data: " + data + "\nStatus: " + status);
                });

            chatBox.append(messageDiv);
            chatBox.scrollTop(chatBox.prop("scrollHeight"));

            inputField.val("");
            console.log(inputField.value)
        }
    });
});
