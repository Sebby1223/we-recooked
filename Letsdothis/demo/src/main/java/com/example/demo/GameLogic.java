package com.example.demo;

import java.util.Random;

public class GameLogic {
    private int playerHP;
    private String playerName;
    private String playerWeapon;
    private int monsterHP;
    private int silverRing;

    public void initGame() {
        playerHP = 10;
        monsterHP = 15;
        playerWeapon = "Knife";
        silverRing = 0;
    }

    public String getGameState() {
        return "Your HP: " + playerHP + "\nYour Weapon: " + playerWeapon;
    }

    public String playerSetUp(String name) {
        playerName = name;
        return "Hello " + playerName + ", let's start the game!";
    }

    public String townGate(int choice) {
        if (choice == 1) {
            if (silverRing == 1) {
                return ending();
            } else {
                return "Guard: Hello there, stranger. So your name is " + playerName
                        + "? \nSorry but we cannot let stranger enter our town.";
            }
        } else if (choice == 2) {
            playerHP--;
            return "Guard: Hey don't be stupid.\n\nThe guard hit you so hard and you gave up.\n(You receive 1 damage)\n\nYour HP: " + playerHP;
        } else if (choice == 3) {
            return crossRoad();
        } else {
            return "Invalid choice!";
        }
    }

    public String crossRoad() {
        return "You are at a crossroad. If you go south, you will go back to the town.\n\n"
                + "1: Go north\n"
                + "2: Go east\n"
                + "3: Go south\n"
                + "4: Go west";
    }

    public String north() {
        playerHP++;
        return "There is a river. You drink the water and rest at the riverside.\n"
                + "Your HP is recovered.\n"
                + "Your HP: " + playerHP + "\n\n"
                + "1: Go back to the crossroad";
    }

    public String east() {
        playerWeapon = "Long Sword";
        return "You walked into a forest and found a Long Sword!\n"
                + "Your Weapon: " + playerWeapon + "\n\n"
                + "1: Go back to the crossroad";
    }

    public String west() {
        return "You encounter a goblin!\n\n"
                + "1: Fight\n"
                + "2: Run";
    }

    public String fight() {
        return "Your HP: " + playerHP + "\n"
                + "Monster HP: " + monsterHP + "\n\n"
                + "1: Attack\n"
                + "2: Run";
    }

    public String attack() {
        int playerDamage = 0;
        if (playerWeapon.equals("Knife")) {
            playerDamage = new Random().nextInt(5);
        } else if (playerWeapon.equals("Long Sword")) {
            playerDamage = new Random().nextInt(8);
        }

        monsterHP -= playerDamage;
        String output = "You attacked the monster and gave " + playerDamage + " damage!\n"
                + "Monster HP: " + monsterHP + "\n";

        if (monsterHP < 1) {
            output += win();
        } else {
            int monsterDamage = new Random().nextInt(4);
            playerHP -= monsterDamage;
            output += "The monster attacked you and gave " + monsterDamage + " damage!\n"
                    + "Player HP: " + playerHP + "\n";

            if (playerHP < 1) {
                output += dead();
            } else {
                output += fight();
            }
        }

        return output;
    }

    public String dead() {
        return "You are dead!!!\n\nGAME OVER";
    }

    public String win() {
        silverRing = 1;
        return "You killed the monster!\n"
                + "The monster dropped a ring!\n"
                + "You obtained a silver ring!\n\n"
                + "1: Go east";
    }

    public String ending() {
        return "Guard: Oh you killed that goblin!?? Great!\n"
                + "Guard: It seems you are a trustworthy guy. Welcome to our town!\n\n"
                + "           THE END                    ";
    }
}
