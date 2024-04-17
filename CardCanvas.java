import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.imageio.ImageIO;

public class CardCanvas extends JPanel {
    private ArrayList<Card> cards;
    private BufferedImage[] cardImages;

    public CardCanvas() {
        cards = new ArrayList<>();
        setPreferredSize(new Dimension(800, 600)); // Tamaño del lienzo

        //Cargamos las imagenes
        cardImages = new BufferedImage[4];
        try {
            cardImages[0] = ImageIO.read(new File("suits/oros.png"));
            cardImages[1] = ImageIO.read(new File("suits/copas.png"));
            cardImages[2] = ImageIO.read(new File("suits/espadas.png"));
            cardImages[3] = ImageIO.read(new File("suits/bastos.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCard(Card card) {
        cards.add(card);
        repaint(); // Vuelve a dibujar el lienzo con la nueva carta
    }

    private int getSuitIndex(String suit) {
        switch (suit) {
            case "oros":
                return 0;
            case "copas":
                return 1;
            case "espadas":
                return 2;
            case "bastos":
                return 3;
            default:
                return -1;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int y = 50;
        Color backgroundColor = new Color(250, 239, 193);

        // Dibujar las cartas en el lienzo
        for (Card card : cards) {
            if (card.isFaceUp()) {
                int suitIndex = getSuitIndex(card.getSuit());

                if (suitIndex != -1 && suitIndex < cardImages.length && cardImages[suitIndex] != null) {
                    g.setColor(backgroundColor);
                    g.fillRect(x, y, 100, 150);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, 100, 150);
                    g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 30));
                    g.drawString(Integer.toString(card.getRank()), x + 30, y + 85);
                    g.drawImage(cardImages[suitIndex], x + 5, y + 5, null);
                } else {
                    // Si no se encuentra la imagen del palo, dibujar un rectángulo
                    g.setColor(Color.WHITE);
                    g.fillRect(x, y, 100, 150);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, 100, 150);
                    g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 80));
                    g.drawString(card.toString(), x + 10, y + 20);
                }
            } else {
                g.setColor(Color.BLUE);
                g.fillRect(x, y, 100, 150);
                g.setColor(Color.WHITE);
                g.drawRect(x, y, 100, 150);
            }
            x += 120; // Espaciado horizontal entre cartas
        }
    }

    // Método main para probar la clase
    public static void main(String[] args) {
        JFrame frame = new JFrame("Card Canvas");
        CardCanvas canvas = new CardCanvas();
        frame.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Agregar algunas cartas al lienzo
        canvas.addCard(new Card("oros", 7, true));
        canvas.addCard(new Card("copas", 10, true));
        canvas.addCard(new Card("espadas", 1, false));
        canvas.addCard(new Card("bastos", 11, true));
    }
}
