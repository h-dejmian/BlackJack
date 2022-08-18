import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BlackJack {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BlackJack bj = new BlackJack();

        while(true) {
            bj.createGame();

            System.out.println("Would you like to play again? Y/N");

            try {
                String answer = br.readLine();
                if (answer.equalsIgnoreCase("Y")) {
                    System.out.println();
                    bj.createGame();
                }
                else if (answer.equalsIgnoreCase("N")) break;
                else System.out.println("Incorrect data. Type Y or N");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createGame() {
        System.out.println("Welcome to BlackJack!");
        System.out.println("*********************");

        Deck deck = new Deck();

        Player player = new Player("Player");
        Player croupier = new Player("Croupier");

        deck.pickCard(croupier);

        deck.pickCard(player);
        deck.pickCard(player);

        while(true) {
            if (!croupier.isPass()) {
                deck.pickCard(croupier);
                hideLastCard(croupier);
            }

            if (croupierPass(croupier.getPoints())) {
                croupier.setPass(true);
            }

            croupier.presentCards();
            player.presentCards();

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Would you like to get next? Y/N");

                String answer;

                while(true) {
                    answer = br.readLine();
                    answer = answer.toUpperCase();
                    if(answer.equals("Y") || answer.equals("N")) break;
                    System.out.println("Wrong data. Type Y or N");
                }

                switch(answer) {
                    case "Y" :  {
                        deck.pickCard(player);

                        if(player.getPoints() > 21) {
                            while (!croupierPass(croupier.getPoints())) {
                                deck.pickCard(croupier);
                                hideLastCard(croupier);
                            }
                            croupier.setPass(true);
                            break;
                        }
                        else continue;
                    }
                    case "N":   {
                        if (!croupier.isPass()) {
                            while (!croupierPass(croupier.getPoints())) {
                                deck.pickCard(croupier);
                                hideLastCard(croupier);
                            }
                            croupier.setPass(true);
                            break;
                        }
                    }
                }

                System.out.println();

                checkGameStatus(player,croupier);

                System.out.println("*********************");
                croupier.presentCardsWithPoints();
                player.presentCardsWithPoints();
                System.out.println("*********************");
                System.out.println();

                break;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean croupierPass(int points) {
        return points >= 17;
    }

    public void hideLastCard(Player player) {
        List<Card> list = player.getCards();
        list.get(list.size()-2).setHidden(false);
        Card card =  list.get(list.size()-1);
        card.setHidden(true);
    }

    public void reverseHideCard(Player player) {
        List<Card> list = player.getCards();
        Card card =  list.get(list.size()-1);
        card.setHidden(false);
    }

    public boolean hasPlayerWon(int p1Points, int p2Points) {
        if(p2Points > 21) return true;
        if(p1Points > 21) return false;
        if(Math.abs(21 - p1Points) < Math.abs(21 - p2Points)) return true;
        return false;
    }
    public boolean isGameDraw(int p1Points, int p2Points) {
        return p1Points == p2Points;
    }

    public void checkGameStatus(Player player, Player croupier) {
        if(hasPlayerWon(player.getPoints(), croupier.getPoints())) {
            System.out.println("You won!");
            System.out.println();
            reverseHideCard(croupier);
        }
        else if(isGameDraw(player.getPoints(), croupier.getPoints()))  {
            System.out.println("Game is draw!");
            System.out.println();
            reverseHideCard(croupier);
        }
        else {
            System.out.println("You loose!");
            System.out.println();
            reverseHideCard(croupier);
        }
    }
}
