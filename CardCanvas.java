import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

public class CardCanvas extends JPanel {
    private ArrayList<Card> cards;

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int y = 50;

        // Dibujar las cartas en el lienzo
        for (Card card : cards) {
            if (card.isFaceUp()) {
                g.setColor(Color.WHITE);
                g.fillRect(x, y, 100, 150);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, 100, 150);
                g.drawString(card.toString(), x + 10, y + 20);
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
