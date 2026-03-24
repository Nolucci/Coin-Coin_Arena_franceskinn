package Partie_B.coincoin.arena;

public interface Combattant {
    void attaquer(Canard cible);
    boolean estKO();
    String getNom();
}
