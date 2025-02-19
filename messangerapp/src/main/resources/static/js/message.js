document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("chatForm").addEventListener("submit", function (event) {
        event.preventDefault(); // Prevent form from reloading the page

        let inputField = document.getElementById("messageInput");
        let messageText = inputField.value.trim();

        if (messageText !== "") {
            let chatBox = document.getElementById("chatBox");

            // Create message bubble
            let messageDiv = document.createElement("div");
            messageDiv.className = "message sent";
            messageDiv.textContent = messageText;

            // Append message & scroll down
            chatBox.appendChild(messageDiv);
            chatBox.scrollTop = chatBox.scrollHeight;

            // Clear input field
            inputField.value = "";
        }
    });
});
