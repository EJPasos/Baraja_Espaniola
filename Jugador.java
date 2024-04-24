import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private ArrayList<Card> mano;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarCarta(Card carta) {
        mano.add(carta);
    }

    public int calcularPuntuacion() {
        int puntuacion = 0;
        for (Card carta : mano) {
            puntuacion += carta.getRank();
        }
        return puntuacion;
    }

    public ArrayList<Card> getMano() {
        return mano;
    }
}