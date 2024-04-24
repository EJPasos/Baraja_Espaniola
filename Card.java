public class Card {
    private String suit;
    private int rank;
    private boolean faceUp;

    public Card(String suit, int rank, boolean faceUp) {
        this.suit = suit;
        this.rank = rank;
        this.faceUp = faceUp;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isFaceUp() {
        return faceUp;
    }


    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    @Override
    public String toString() {
        String rankString;
        switch (rank) {
            case 1:
                rankString = "As";
                break;
            case 10:
                rankString = "Sota";
                break;
            case 11:
                rankString = "Caballo";
                break;
            case 12:
                rankString = "Rey";
                break;
            default:
                rankString = Integer.toString(rank);
        }
        return (faceUp ? rankString + " de " + suit : "Carta boca abajo");
    }

    public static void main(String[] args) {
        Card card = new Card("oros", 7, true);
        System.out.println(card);
        card.setFaceUp(false);
        System.out.println(card);
    }
}