import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {

        try {
                Scanner in = new Scanner(System.in);

            try (Client client = new Client()) {

                while (true) {
                    //Gets PlayerID
                    int playerID = client.getPlayerID();
                    System.out.println("Your ID: " + playerID);
                    //Gets AllPlayerInformation including ID and Score
                    var playerInfo = client.getAllPlayerInfo();
                    playerInfo.stream().forEach((p)-> System.out.println(p));
                    while (true) {
                        //Guess Game
                        int numberGuess;
                        do {
                            System.out.println("Guess a number between 1 to 10:");
                            String numberGuessString = in.nextLine();
                            if(numberGuessString.isEmpty())
                                System.out.println("Input cannot be empty");
                            else if(!numberGuessString.matches("\\d+"))
                                System.out.println("Please only input numbers");
                            else {
                                   numberGuess = Integer.parseInt(numberGuessString);
                                   if(numberGuess >=1 && numberGuess <=10)
                                       break;
                                   else
                                       System.out.println("Invalid Number Entered. Please try again");
                            }
                        }
                        while (true);

                        int positionGuess;
                        do {
                            System.out.println("Guess the position between 1 to 3:");
                            String positionGuessString = in.nextLine();
                            if(positionGuessString.isEmpty())
                                System.out.println("Input cannot be empty");
                            else if(!positionGuessString.matches("\\d+"))
                                System.out.println("Please only input numbers");
                            else {
                                positionGuess = Integer.parseInt(positionGuessString);
                                if(positionGuess>=1 && positionGuess<=3)
                                    break;
                                else
                                    System.out.println("Invalid Number Entered, Please try again");
                            }
                        }
                        while (true);

                        var reply = client.guessTheNumber(numberGuess,positionGuess);
                        for (String s : reply) {
                            System.out.println(s);
                        }
                        String checkCountReply = client.checkCount();
                        if (checkCountReply.equals("correct")) {
                            String checkScoreReply = client.checkScore();
                            if(checkScoreReply.equals("win")) {
                                System.out.println("You won the game! Press Enter to play again");
                                in.nextLine();
                                break;
                            }
                            else
                                System.out.println("You guessed correctly!");
                                break;
                        }
                        else if (checkCountReply.equals("lose")) {
                            System.out.println("You lose!");
                            break;
                        }
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}