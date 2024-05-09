// game.js

// Function to send the player's choice to the server
function sendChoice(choice) {
  fetch('http://localhost:8080/choice', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ choice: choice })
  })
    .then(response => response.text())
    .then(result => {
      // Update the game output with the server's response
      document.getElementById('output').innerHTML = result;
    })
    .catch(error => {
      console.error('Error:', error);
    });
}

// Function to handle the player's choice
function makeChoice(choice) {
  sendChoice(choice);
}

// Event listener for the choice buttons
document.addEventListener('DOMContentLoaded', function() {
  var choiceButtons = document.getElementsByClassName('choice-btn');
  for (var i = 0; i < choiceButtons.length; i++) {
    choiceButtons[i].addEventListener('click', function() {
      var choice = parseInt(this.getAttribute('data-choice'));
      makeChoice(choice);
    });
  }
});