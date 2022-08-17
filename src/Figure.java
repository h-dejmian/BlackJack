public enum Figure {
    NUMBER(""), JACK("J"), QUEEN("Q"), KING("K"), ACE("A");

    public final String label;

    Figure(String label) {
        this.label = label;
    }
}
