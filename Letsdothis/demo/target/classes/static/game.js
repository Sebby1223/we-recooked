// Fetch game data from the server
fetch('/') // Endpoint for starting the game
  .then(response => response.text())
  .then(data => {
    // Update the HTML with the game data
    document.getElementById('gameOutput').innerText = data;
  })
  .catch(error => console.error('Error:', error));

// Send user input to the server
const userInput = document.getElementById('userInput');
const sendButton = document.getElementById('sendButton');

sendButton.addEventListener('click', () => {
  const choice = userInput.value;
  fetch('/choice', {
    method: 'POST',
    headers: {
      'Content-Type': 'text/plain'
    },
    body: choice
  })
    .then(response => response.text())
    .then(data => {
      // Update the HTML with the server's response
      document.getElementById('gameOutput').innerText += '\n' + data;
      userInput.value = ''; // Clear the input field
    })
    .catch(error => console.error('Error:', error));
});