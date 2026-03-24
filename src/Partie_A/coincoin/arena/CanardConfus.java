package Partie_A.coincoin.arena;

public class CanardConfus extends CanardEau {

    private boolean enrage = false;

    public CanardConfus(String nom, int pvMax, int atk, int pressionJet) {
        super(nom, pvMax, atk, pressionJet);
    }
    public CanardConfus(String nom, int pvMax, int atk) {
        this(nom, pvMax, atk, 5);
    }

    @Override
    public String toString() {
        return super.toString() + " [confus]";
    }

    public void migraine() {
        System.out.println(getSurnom() + " a une migraine. ATK doublee au prochain tour.");
        enrage = true;
    }

    @Override
    public void attaquer(CanardDeCombat cible) {
        if (Math.random() < 0.25) {
            System.out.println(getSurnom() + " est confus et s'attaque lui-meme.");
            double mult = this.etreAttaqueePar(this);
            int degats = (int)(getAtk() * mult);
            System.out.println(getSurnom() + " s'inflige " + degats + " degats.");
            subirDegats(degats);
        } else {
            if (enrage) {
                double mult = cible.etreAttaqueePar(this);
                int degats = (int)(getAtk() * mult * 2);
                System.out.println(getSurnom() + " attaque " + cible.getSurnom()
                        + " (enrage x2) (Eau -> " + cible.getType() + " : x" + mult + ") -> " + degats + " degats");
                cible.subirDegats(degats);
                enrage = false;
            } else {
                super.attaquer(cible);
            }
        }
    }
}
