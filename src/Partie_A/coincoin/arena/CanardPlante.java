package Partie_A.coincoin.arena;

public class CanardPlante extends CanardDeCombat {

    public CanardPlante(String nom, int pvMax, int atk) {
        super(nom, pvMax, atk);
    }

    public CanardPlante(String nom, int pvMax) {
        this(nom, pvMax, 30);
    }

    @Override
    public String getType() { return "Plante"; }

    public void regenerer() {
        int soin = (int)(getPvMax() * 0.1);
        soignerPartiel(soin);
        System.out.println(getSurnom() + " regenere " + soin + " PV (PV: " + getPvActuels() + "/" + getPvMax() + ")");
    }

    @Override
    public void attaquer(CanardDeCombat cible) {
        double mult = cible.etreAttaqueePar(this);
        effectuerAttaque(cible, mult);
    }

    @Override
    public double etreAttaqueePar(CanardFeu attaquant)      { return 2.0; }

    @Override
    public double etreAttaqueePar(CanardEau attaquant)      { return 0.5; }

    @Override
    public double etreAttaqueePar(CanardPlante attaquant)   { return 0.5; }

    @Override
    public double etreAttaqueePar(CanardClassique attaquant){ return 1.0; }
}
