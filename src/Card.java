
public class Card {
    private int value;
    private Figure figure;
    private Color color;
    private boolean isHidden;
    private final String hiddenCard = "\uD83C\uDCA0";

    public Card(int value, Color color, Figure figure) {
        this.value = value;
        this.color = color;
        this.figure = figure;
    }

    @Override
    public String toString() {
        String col = null;
        switch(color) {
            case CLUB: { col = "\u2663";  break; }
            case DIAMOND: { col = "\u2666"; break; }
            case HEART: { col = "\u2665"; break; }
            case SPADE: { col = "\u2660"; break; }
        }

        String fig = figure.label;
        if(isHidden) return hiddenCard;
        if(figure.equals(Figure.NUMBER)) fig = String.valueOf(value);
        return fig + col;
    }

    public int getValue() {
        return value;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }
}
