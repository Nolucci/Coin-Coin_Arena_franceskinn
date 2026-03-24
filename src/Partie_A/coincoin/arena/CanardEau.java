package Partie_A.coincoin.arena;

public class CanardEau extends CanardDeCombat {

    private int pressionJet;

    public CanardEau(String nom, int pvMax, int atk, int pressionJet) {
        super(nom, pvMax, atk);
        this.pressionJet = pressionJet;
    }

    public CanardEau(String nom, int pvMax, int atk) {
        this(nom, pvMax, atk, 5);
    }

    public int getPressionJet() { return pressionJet; }

    @Override
    public String getType() { return "Eau"; }

    @Override
    public String toString() {
        return super.toString() + " [pression: " + pressionJet + "]";
    }

    @Override
    public void attaquer(CanardDeCombat cible) {
        double mult = cible.etreAttaqueePar(this);
        System.out.println("Jet d'eau (pression: " + pressionJet + ")");
        effectuerAttaque(cible, mult);
    }

    @Override
    public double etreAttaqueePar(CanardFeu attaquant)      { return 0.5; }

    @Override
    public double etreAttaqueePar(CanardEau attaquant)      { return 0.5; }

    @Override
    public double etreAttaqueePar(CanardPlante attaquant)   { return 2.0; }

    @Override
    public double etreAttaqueePar(CanardClassique attaquant){ return 1.0; }
}
