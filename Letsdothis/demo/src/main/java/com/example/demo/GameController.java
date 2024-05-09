package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class GameController {
    private final GameLogic gameLogic;

    public GameController(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        gameLogic.initGame();
    }

    @GetMapping("/start")
    public ResponseEntity<String> startGame() {
        gameLogic.initGame();
        return ResponseEntity.ok(gameLogic.getGameState());
    }

    @GetMapping("/intro")
    public ResponseEntity<String> gameIntroduction() {
        return ResponseEntity.ok(gameLogic.gameIntroduction());
    }

    @PostMapping("/setup")
    public ResponseEntity<String> playerSetup(@RequestBody String playerName) {
        return ResponseEntity.ok(gameLogic.playerSetUp(playerName));
    }

    @PostMapping("/choice")
    public ResponseEntity<String> makeChoice(@RequestBody Map<String, Integer> requestBody) {
        Integer choice = requestBody.get("choice");
        if (choice == null) {
            return ResponseEntity.badRequest().body("Missing 'choice' parameter");
        }

        String gameOutput;
        switch (choice) {
            case 1:
                gameOutput = gameLogic.townGate(choice);
                break;
            case 2:
                gameOutput = gameLogic.west();
                break;
            case 3:
                gameOutput = gameLogic.crossRoad();
                break;
            // Add more cases for other choices
            default:
                gameOutput = "Invalid choice!";
                break;
        }
        return ResponseEntity.ok(gameOutput);
    }
}