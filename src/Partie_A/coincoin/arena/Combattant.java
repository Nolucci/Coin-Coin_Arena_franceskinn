package Partie_A.coincoin.arena;

public interface Combattant {
    void attaquer(CanardDeCombat cible);
    boolean estKO();
    String getNom();
}
