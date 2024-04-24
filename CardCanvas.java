import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.imageio.ImageIO;

//Esta clase lo que hace es comprobar el funcionamiento del canvas y la creacion de las cartas
public class CardCanvas extends JPanel
{
    private ArrayList<Card> cards;
    private BufferedImage[] cardImages;
    private HashMap<String, Integer> suitIndexMap;
    private BufferedImage pokerTableImage;

    public CardCanvas() {
        cards = new ArrayList<>();
        setPreferredSize(new Dimension(1000, 600));

        suitIndexMap = new HashMap<>();
        suitIndexMap.put("oros", 0);
        suitIndexMap.put("copas", 1);
        suitIndexMap.put("espadas", 2);
        suitIndexMap.put("bastos", 3);

        //Cargamos las imagenes
        cardImages = new BufferedImage[4];
        try {
            cardImages[0] = ImageIO.read(new File("suits/bastos.png"));
            cardImages[1] = ImageIO.read(new File("suits/copas.png"));
            cardImages[2] = ImageIO.read(new File("suits/espadas.png"));
            cardImages[3] = ImageIO.read(new File("suits/oros.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        //aqui se carga la imagen del poker
        try
        {
            pokerTableImage = ImageIO.read(new File("suits/istockphoto-1218355494-612x612.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCard(Card card) {
        cards.add(card);
        repaint();
    }

    public void printCardsArray(ArrayList<Card> cards) {
        System.out.println("Cartas: ");
        this.cards = cards;
        for (Card card : cards) {
            System.out.println(card);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int y = 50;

        if (pokerTableImage != null) {
            g.drawImage(pokerTableImage, 0, 0, getWidth(), getHeight(), this);
        }

        Color backgroundColor = new Color(250, 239, 193);

        for (Card card : cards) {
            if (card.isFaceUp()) {
                String suit = card.getSuit();
                int suitIndex = suitIndexMap.getOrDefault(suit, -1);

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
            }
            else
            {
                g.setColor(Color.BLUE);
                g.fillRect(x, y, 100, 150);
                g.setColor(Color.WHITE);
                g.drawRect(x, y, 100, 150);
            }
            x += 120;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Card Canvas");
        CardCanvas canvas = new CardCanvas();
        frame.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //probamos cartas
        canvas.addCard(new Card("oros", 7, true));
        canvas.addCard(new Card("copas", 10, true));
        canvas.addCard(new Card("espadas", 1, true));
        canvas.addCard(new Card("bastos", 11, false));
    }

   //public boolean
}