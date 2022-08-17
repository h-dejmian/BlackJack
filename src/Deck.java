import java.util.ArrayList;

public class Deck {
    private int deckSize = 52;
    private final ArrayList<Card> cards = new ArrayList<>();

    public Deck() {
        //Adding cards 2-10 to the deck
        int colorValue = 0;
        Color color = null;
        do {
            color = Color.values()[colorValue];
            for (int i = 2; i < 11; i++) {
                cards.add(new Card(i, color, Figure.NUMBER));
            }
            colorValue++;
        } while(colorValue < Color.values().length);

        //Adding figures to the deck
        colorValue = 0;
        do {
            color = Color.values()[colorValue];
            cards.add(new Card(10, color, Figure.JACK));
            cards.add(new Card(10, color, Figure.QUEEN));
            cards.add(new Card(10, color, Figure.KING));
            cards.add(new Card(11, color, Figure.ACE));
            colorValue++;
        } while(colorValue < Color.values().length);
    }

    public Card pickCard(Player player) {
        int randomNumber = (int) (Math.random() * deckSize);

        Card card = cards.get(randomNumber);
        player.getCards().add(card);

        cards.remove(randomNumber);
        deckSize--;

        return card;
    }
}
