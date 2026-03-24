package Partie_A.coincoin.arena;

public class CanardClassique extends CanardDeCombat {
    public CanardClassique(String nom, int pvMax, int atk) {
        super(nom, pvMax, atk);
    }
    public CanardClassique(String nom, int pvMax) {
        this(nom, pvMax, 30);
    }

    @Override
    public String getType() { return "Normal"; }

    @Override
    public void attaquer(CanardDeCombat cible) {
        double mult = cible.etreAttaqueePar(this);
        effectuerAttaque(cible, mult);
    }
}
