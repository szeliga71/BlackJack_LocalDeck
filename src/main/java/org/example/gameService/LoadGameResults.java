package org.example.gameService;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

public class LoadGameResults {

    private static final String mainPath = "src/main/GameHistory";
    private  Scanner scanner;

   /* public LoadGameResults(Scanner scanner) {
        this.scanner = scanner;
    }*/

   /* public static String getAllGameFiles(List<File> files) {
        StringBuilder sb = new StringBuilder();
        if (files == null) {
            throw new IllegalArgumentException("Please provide a valid argument");
        }
        if (files.isEmpty()) {
            return "The game history does not contain any saved sessions in the files";
        } else {
            int i = 1;
            for (File file : files) {
                sb.append(i).append(". ").append(file.getName());
                ++i;
            }
            return sb.toString();
        }
    }*/

    public static List<File> loadHistoryFile() {
        File directory = new File(mainPath);
        List<File> filesInDirectory;
        if (directory.isDirectory()) {
            filesInDirectory = Arrays.asList(Objects.requireNonNull(directory.listFiles()));
            filesInDirectory.sort(Comparator.comparing(File::getName));
        } else {
            throw new IllegalArgumentException("The path to directory or the directory is invalid");
        }
        return filesInDirectory;
    }

    private static String createPathToFile(LocalDate date) {
        if (date != null) {
            String dateInText = date.toString();
            return mainPath + "/game history " + dateInText + ".csv";
        } else {
            throw new IllegalArgumentException("Please provide a valid argument");
        }
    }



   /*  public static File createFile(LocalDate date) {
        return new File(createPathToFile(date));
    }

    public static String gameResultFromSpecifiedDate(File searchedFile) throws FileNotFoundException {
        if (searchedFile != null && searchedFile.exists()) {
            return readFromFile(searchedFile);
        } else {
            throw new FileNotFoundException("Provided file does not exist or used wrong path name to file");
        }
    }

    private static String readFromFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;
            while ((line = bufferedReader.readLine()) != null) {
                if (!firstLine) {
                    stringBuilder.append("\n");
                }
                stringBuilder.append(line);
                firstLine = false;
            }
        } catch (IOException e) {
            System.err.println(" An error occured while reading the file");
        }
        return stringBuilder.toString();
    }*/

    //===============================

   /* public static LocalDate inputDate() {

        Scanner scanner = new Scanner(System.in);
        LocalDate date = null;
        boolean correct = false;

        while (!correct) {

            try {
                System.out.println("Please enter a day (1-31): ");
                int day = Integer.parseInt(scanner.nextLine());

                System.out.println("Please enter a month (1-12): ");
                int month = Integer.parseInt(scanner.nextLine());

                System.out.println("Please enter a year(e.g,2023): ");
                int year = Integer.parseInt(scanner.nextLine());

                date = LocalDate.of(year, month, day);
                correct = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid number");
            } catch (IllegalArgumentException | DateTimeException e) {
                System.out.println("Invalid date. Please enter a valid date.");
            }catch(Exception e){
                System.out.println("An unexpected error occurred."+ e.getMessage());
            }
        }
        return date;
    }*/

    public static String showGameResultFromSpecifiedDate(LocalDate date) {

        File searchedFile = new File("src/main/GameHistory/game history " + date.toString() + ".csv");

        StringBuilder stringBuilder = new StringBuilder();

        if (searchedFile.exists()) {

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(searchedFile))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
            } catch (IOException e) {
                System.err.println(" An error occured while reading the file");
            }
        }
        return stringBuilder.toString();
    }



  /*  public void showAllGameFilesName(List<String>nameFiles) {
        if (nameFiles != null) {
            int i = 1;
            for (String nameFile : nameFiles) {
                System.out.println(i + "." + nameFile);
                ++i;
            }
        } else {
            throw new IllegalArgumentException("Prosze dostarczyc odpowiedni argument");
        }
    }*/
    public static void showAllGameFiles(List<File> files){
        if(files!=null){
            int i=1;
            for(File file : files){
                System.out.println(i+". "+file.getName());
                ++i;
            }

        }else{
            throw new IllegalArgumentException("Prosze dostarczyc odpowiedni argument");
        }
    }
}
