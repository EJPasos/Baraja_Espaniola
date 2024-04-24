import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class GamePlay {
    private ArrayList<Card> deck;
    private CardCanvas canvas;
    private ArrayList<Jugador> jugadores;
    private int jugadorActualIndex;

    public GamePlay(int numJugadores) {
        canvas = new CardCanvas();
        jugadores = new ArrayList<>();
        jugadorActualIndex = 0;

        //creamos las cartas
        deck = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            for (String suit : new String[]{"oros", "copas", "espadas", "bastos"})
            {
                deck.add(new Card(suit,i,true));
            }
        }
        Collections.shuffle(deck);

        for (int i = 0; i < numJugadores; i++) {
            jugadores.add(new Jugador("Jugador " + (i + 1)));
        }
    }

    public void repartirCartas(int numCartas)
    {
        for (int i = 0; i < numCartas; i++)
        {
            for (Jugador jugador : jugadores)
            {
                if (!deck.isEmpty())
                {
                    jugador.agregarCarta(deck.remove(0));
                }
                else
                {
                    System.out.println("No hay más cartas en la baraja.");
                }
            }
        }
        canvas.printCardsArray(jugadores.get(0).getMano());
    }

    public void pullRandomCard()
    {
        Jugador jugadorActual = jugadores.get(jugadorActualIndex);
        if (!deck.isEmpty()) {
            int randomIndex = (int) (Math.random() * deck.size());
            Card randomCard = deck.remove(randomIndex);
            jugadorActual.agregarCarta(randomCard);
            canvas.printCardsArray(jugadorActual.getMano());
            canvas.repaint();
            System.out.println("Turno de " + jugadorActual.getNombre());
        } else {
            System.out.println("no hay más cartas en la baraja.");
        }
    }

    public void switchPlayer()
    {
        jugadorActualIndex = (jugadorActualIndex + 1) % jugadores.size();
        Jugador jugadorActual = jugadores.get(jugadorActualIndex);
        canvas.printCardsArray(jugadorActual.getMano());
        canvas.repaint();
        System.out.println("turno de " + jugadorActual.getNombre());
    }

    public void determineWinner()
    {
        Jugador ganador = null;
        int maxPuntuacion = -1;

        for (Jugador jugador : jugadores)
        {
            int puntuacion = jugador.calcularPuntuacion();
            if (puntuacion > 18)
            {
                continue;
            }
            if (puntuacion > maxPuntuacion)
            {
                ganador = jugador;
                maxPuntuacion = puntuacion;
            }
        }

        if (ganador != null)
        {
            String mensaje = "Ha ganado el " + ganador.getNombre() + " con " + maxPuntuacion + " puntos";
            System.out.println(mensaje);
            JOptionPane.showMessageDialog(null, mensaje, "GANADOR",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            System.out.println("ningún jugador gana todos se pasaron de 18");
        }
    }

    public static void main(String[] args)
    {
        int numJugadores = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de jugadores:"));
        GamePlay game = new GamePlay(numJugadores);

        JFrame frame = new JFrame("POKER GAME BY ZAIKO");
        frame.add(game.canvas, BorderLayout.CENTER);

        JButton pullCardButton = new JButton("Pedir Carta");
        pullCardButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                game.pullRandomCard();
            }
        });

        JButton switchPlayerButton = new JButton("siguiente jugador");
        switchPlayerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                game.switchPlayer();
            }
        });

        JButton endGameButton = new JButton("Terminar Juego");
        endGameButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                game.determineWinner();
                System. exit(0);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(pullCardButton);
        buttonPanel.add(switchPlayerButton);
        buttonPanel.add(endGameButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        game.repartirCartas(2);
        System.out.println("turno de " + game.jugadores.get(0).getNombre());
    }
}