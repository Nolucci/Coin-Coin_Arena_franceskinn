package Partie_B.coincoin.arena;

public class Equipe {

    private static final int TAILLE_MAX = 6;

    private Canard[] canards;
    private int taille;
    private final String nomDresseur;

    private static int nbEquipesCrees = 0;

    public Equipe(String nomDresseur) {
        this.nomDresseur = nomDresseur;
        this.canards = new Canard[TAILLE_MAX];
        this.taille = 0;
        nbEquipesCrees++;
    }

    public String getNomDresseur() { return nomDresseur; }
    public static int getNbEquipesCrees() { return nbEquipesCrees; }

    public boolean ajouter(Canard c) {
        if (taille >= TAILLE_MAX) return false;
        canards[taille++] = c;
        return true;
    }

    public boolean retirer(String surnom) {
        for (int i = 0; i < taille; i++) {
            if (canards[i].getSurnom().equals(surnom)) {
                for (int j = i; j < taille - 1; j++) {
                    canards[j] = canards[j + 1];
                }
                canards[--taille] = null;
                return true;
            }
        }
        return false;
    }

    public Canard getPremierValide() {
        for (int i = 0; i < taille; i++) {
            if (!canards[i].estKO()) return canards[i];
        }
        return null;
    }

    public void soignerTous() {
        for (int i = 0; i < taille; i++) {
            canards[i].soigner();
        }
    }

    public void afficher() {
        System.out.println("Equipe de " + nomDresseur + " :");
        for (int i = 0; i < taille; i++) {
            String etat = canards[i].estKO() ? " [KO]" : "";
            System.out.println(canards[i] + etat);
        }
    }

    public boolean touteKO() {
        for (int i = 0; i < taille; i++) {
            if (!canards[i].estKO()) return false;
        }
        return true;
    }
}
