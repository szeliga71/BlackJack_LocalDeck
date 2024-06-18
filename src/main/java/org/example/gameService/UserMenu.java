package org.example.gameService;

import org.example.gamePlay.GamePlay;
import org.example.gamePlay.Validators;
import org.example.model.House;
import org.example.model.Player;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserMenu {


    private final Scanner scanner = new Scanner(System.in);
    Validators validators=new Validators(scanner);

    public void lauchMenu() {

        boolean menu = true;
        while (menu) {

            System.out.println("\n");
            System.out.println("        MENU BLACK JACK !!!\n");
            System.out.println(" Please choose the correct number :\n");
            System.out.println(" 1. Start New Game ");
            System.out.println(" 2. Display game history files ");
            System.out.println(" 3. Display Scores from specified date");
            System.out.println(" 4. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    String nick = validators.enterNickName(scanner);
                    Player player = new Player(nick);
                    House house = new House();
                    GamePlay gameplay = new GamePlay(house, player);
                    gameplay.start();

                }
                case "2" -> {

                    List<File> files = LoadGameResults.loadHistoryFile();
                    System.out.println("List of files containing game histories :");
                    LoadGameResults.showAllGameFiles(files);
                    System.out.println("\n");

                }
                case "3" -> {
                    System.out.println("Please provide the date for which you want to view the game history ");
                    LocalDate date = TimeDateProvider.inputDate(scanner);
                    System.out.println("\n");
                    String gameResult = LoadGameResults.showGameResultFromSpecifiedDate(date);
                    System.out.println("Games from the day " + date + " " + "\n");
                    System.out.println(gameResult + "\n");
                }
                case "4" -> {
                    System.out.println(" END ");
                    menu = false;
                }
                default -> System.out.println("Incorrect choice, please try again ");

            }


        }
        scanner.close();
    }



}
