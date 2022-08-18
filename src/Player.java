import java.util.ArrayList;

public class Player {
    private final ArrayList<Card> cards = new ArrayList<>();
    private final String name;
    private int points;
    private boolean pass = false;

    public Player(String name) {
        this.name = name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public String getName() {
        return name;
    }

    public void showCards() {
        for(Card c : this.getCards()) System.out.print(c + " ");
        System.out.println();
    }

    public void presentCards() {
        System.out.println(this.getName() + " cards: ");
        this.showCards();
    }

    public void presentCardsWithPoints() {
        System.out.println(this.getName() + " cards: " + "(" + this.getPoints() + " points)");
        this.showCards();
    }

}
