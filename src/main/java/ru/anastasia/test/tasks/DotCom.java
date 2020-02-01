package ru.anastasia.test.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;

public class DotCom {
    private ArrayList<String> locationCells;
    private String name;

    public void setLocationCells(ArrayList<String> loc) {
        locationCells = loc;
    }
    public void setName(String n) {
        name = n;
    }

    public String checkYourself(String userInput) {
        String result = "Пфффф, мимо";
        int index = locationCells.indexOf(userInput);
            if (index >= 0) {
                locationCells.remove(index);

                if (locationCells.isEmpty()) {
                    result = "Ладно, ладно, потопил";
                    System.out.println("Ну и как ты мог потопить " + name + " ? Такой сайт был хороший! ");
                } else {
                    result = "Задел чуток";
            } //end if
        } //end if
        return result;
    }
}

class DotComBust {

    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;

    private void setUpGame() {
        //создаем несколько "сайтов" и присваиваем им адреса
        DotCom one = new DotCom();
        one.setName("Sashka-durashka.com");
        DotCom two = new DotCom();
        two.setName("Varriss_Kriss_KisKisKis.com");
        DotCom three = new DotCom();
        three.setName("QueenAnastasia.com");
        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(three);

        System.out.println("Твоя цель - потопить три сайта.");
        System.out.println("Sashka-durashka.com, Varriss_Kriss_KisKisKis.com, QueenAnastasia.com");
        System.out.println("Помни: поменьше ходов и побольше рома. Дерзай!");

        for (DotCom dotComToSet : dotComsList) {

            ArrayList<String> newLocation = helper.placeDotCom(3);

            dotComToSet.setLocationCells(newLocation);
        }
    }

    private void startPlaing() {
        while (!dotComsList.isEmpty()) {
            String userGuess = helper.getUserInput("Делай ход, пупсик");
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "Пфффф, мимо";
        for (DotCom dotComToTest : dotComsList) {
            result = dotComToTest.checkYourself(userGuess);
            if (result.equals("Задел чуток")) {
                break;
            }
            if (result.equals("Ладно, ладно, потопил")) {
                dotComsList.remove(dotComToTest);
                break;
            }
        }
        System.out.println(result);
    }

    private void finishGame() {
        System.out.println("Ты фсё испоооооортил! Ты фсех убииииил!");
        if (numOfGuesses <= 18) {
            System.out.println("Это заняло у тебя всего " + numOfGuesses + " попыток! Ты монстр!");
            System.out.println("Теперь я знаю, что ты настоящий пират...");
        } else {
            System.out.println("Ты так долго это делал... " + numOfGuesses + " попыток...");
            System.out.println("Твой корабль успел пойти на дно, из-за тебя утонули невинные котята...");
        }
    }

    public static void main(String[] args) {
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaing();
    }
}
//import java.io.*;

class GameHelper {

    private static final String alphabet = "abcdefg";
    private int gridLength = 7;
    private int gridSize = 49;
    private int [] grid = new int[gridSize];
    private int comCount = 0;


    public String getUserInput(String prompt) {
        String inputLine = null;
        System.out.print(prompt + "  ");
        try {
            BufferedReader is = new BufferedReader(
                    new InputStreamReader(System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0 )  return null;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return inputLine.toLowerCase();
    }



    public ArrayList<String> placeDotCom(int comSize) {                 // line 19
        ArrayList<String> alphaCells = new ArrayList<String>();
        String [] alphacoords = new String [comSize];      // holds 'f6' type coords
        String temp = null;                                // temporary String for concat
        int [] coords = new int[comSize];                  // current candidate coords
        int attempts = 0;                                  // current attempts counter
        boolean success = false;                           // flag = found a good location ?
        int location = 0;                                  // current starting location

        comCount++;                                        // nth dot com to place
        int incr = 1;                                      // set horizontal increment
        if ((comCount % 2) == 1) {                         // if odd dot com  (place vertically)
            incr = gridLength;                               // set vertical increment
        }

        while ( !success & attempts++ < 200 ) {             // main search loop  (32)
            location = (int) (Math.random() * gridSize);      // get random starting point
            //System.out.print(" try " + location);
            int x = 0;                                        // nth position in dotcom to place
            success = true;                                 // assume success
            while (success && x < comSize) {                // look for adjacent unused spots
                if (grid[location] == 0) {                    // if not already used
                    coords[x++] = location;                    // save location
                    location += incr;                          // try 'next' adjacent
                    if (location >= gridSize){                 // out of bounds - 'bottom'
                        success = false;                         // failure
                    }
                    if (x>0 & (location % gridLength == 0)) {  // out of bounds - right edge
                        success = false;                         // failure
                    }
                } else {                                      // found already used location
                    // System.out.print(" used " + location);
                    success = false;                          // failure
                }
            }
        }                                                   // end while

        int x = 0;                                          // turn good location into alpha coords
        int row = 0;
        int column = 0;
        // System.out.println("\n");
        while (x < comSize) {
            grid[coords[x]] = 1;                              // mark master grid pts. as 'used'
            row = (int) (coords[x] / gridLength);             // get row value
            column = coords[x] % gridLength;                  // get numeric column value
            temp = String.valueOf(alphabet.charAt(column));   // convert to alpha

            alphaCells.add(temp.concat(Integer.toString(row)));
            x++;

            // System.out.print("  coord "+x+" = " + alphaCells.get(x-1));

        }
        // System.out.println("\n");

        return alphaCells;
    }
}