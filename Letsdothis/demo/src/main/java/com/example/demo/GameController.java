package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private Game game;

    public GameController() {
        this.game = new Game();
    }

    @GetMapping("/")
    public ResponseEntity<String> startGame() {
        // Call the playerSetUp() method to initialize the game
        game.playerSetUp();
        return ResponseEntity.ok("Game started! Your HP: " + game.playerHP + ", Your Weapon: " + game.playerWeapon);
    }

    @PostMapping("/choice")
    public ResponseEntity<String> makeChoice(@RequestBody String choice) {
        int choiceNum = Integer.parseInt(choice);
        String gameOutput;
    
        switch (choiceNum) {
            case 1:
                game.townGate();
                gameOutput = "You chose to talk to the guard.";
                break;
            case 2:
                gameOutput = "You attacked the guard! (Implement the attack logic here)";
                break;
            case 3:
                game.crossRoad();
                gameOutput = "You chose to leave the town.";
                break;
            // Add more cases for other choices
            default:
                gameOutput = "Invalid choice!";
                break;
        }
    
        return ResponseEntity.ok(gameOutput);
    }
}