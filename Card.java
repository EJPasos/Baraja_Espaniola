public class Card {
    // Atributos
    private String suit;   // Palo (oros, copas, espadas, bastos)
    private int rank;      // Valor (1-12)
    private boolean faceUp; // Indica si la carta está boca arriba o boca abajo

    // Constructor
    public Card(String suit, int rank, boolean faceUp) {
        this.suit = suit;
        this.rank = rank;
        this.faceUp = faceUp;
    }

    // Métodos getter y setter
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

    // Método para obtener una representación textual de la carta
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
                rankString = "Caballo3";
                break;
            case 12:
                rankString = "Rey";
                break;
            default:
                rankString = Integer.toString(rank);
        }
        return (faceUp ? rankString + " de " + suit : "Carta boca abajo");
    }

    // Ejemplo de uso
    public static void main(String[] args) {
        Card card = new Card("oros", 7, true);
        System.out.println(card); // Imprime: 7 de oros
        card.setFaceUp(false);
        System.out.println(card); // Imprime: Carta boca abajo
    }
}