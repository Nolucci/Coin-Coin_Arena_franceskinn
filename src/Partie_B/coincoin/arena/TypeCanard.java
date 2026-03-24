package Partie_B.coincoin.arena;

public enum TypeCanard {
    FEU("Feu"),
    EAU("Eau"),
    PLANTE("Plante"),
    NORMAL("Normal");

    private final String libelle;

    TypeCanard(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() { return libelle; }

    // Table des multiplicateurs indexée par ordinal() de l'attaquant et de la cible
    // Ordre : FEU=0, EAU=1, PLANTE=2, NORMAL=3
    //                        Feu   Eau   Plante Normal
    private static final double[][] TABLE = {
        /* FEU    */ { 0.5,  0.5,  2.0,  1.0 },
        /* EAU    */ { 2.0,  0.5,  0.5,  1.0 },
        /* PLANTE */ { 0.5,  2.0,  0.5,  1.0 },
        /* NORMAL */ { 1.0,  1.0,  1.0,  1.0 },
    };

    public double getMultiplicateur(TypeCanard cible) {
        return TABLE[this.ordinal()][cible.ordinal()];
    }
}
