package Partie_A.coincoin.arena;

public abstract class CanardDeCombat implements Soignable, Combattant {

    private final String nom;
    private String surnom;
    private final int pvMax;
    private int pvActuels;
    private int atk;

    private static int nbCanardsCrees = 0;
    public static final int ATK_MIN = 1;

    public CanardDeCombat(String nom, int pvMax, int atk) {
        if (pvMax < 1) {
            throw new IllegalArgumentException("pvMax doit être >= 1.");
        }
        if (atk < ATK_MIN) {
            throw new IllegalArgumentException("atk doit être >= " + ATK_MIN + ".");
        }
        this.nom = nom;
        this.surnom = nom;
        this.pvMax = pvMax;
        this.pvActuels = pvMax;
        this.atk = atk;
        nbCanardsCrees++;
    }

    public String getNom()       { return nom; }
    public String getSurnom()    { return surnom; }
    public int getPvMax()        { return pvMax; }
    public int getPvActuels()    { return pvActuels; }
    public int getAtk()          { return atk; }

    public static int getNbCanardsCrees() { return nbCanardsCrees; }

    public void setSurnom(String surnom) { this.surnom = surnom; }

    public boolean estKO() {
        return pvActuels <= 0;
    }

    public void subirDegats(int degats) {
        pvActuels = Math.max(0, pvActuels - degats);
        System.out.println(surnom + " subit " + degats + " degats (PV: " + pvActuels + "/" + pvMax + ")");
    }

    public void soigner() {
        pvActuels = pvMax;
    }

    public void soignerPartiel(int soin) {
        pvActuels = Math.min(pvMax, pvActuels + soin);
    }

    @Override
    public String toString() {
        return "[" + getType() + "] " + nom + " \"" + surnom + "\" (PV: " + pvActuels + "/" + pvMax + " | ATK: " + atk + ")";
    }

    public abstract String getType();

    public double etreAttaqueePar(CanardFeu attaquant)      { return 1.0; }
    public double etreAttaqueePar(CanardEau attaquant)      { return 1.0; }
    public double etreAttaqueePar(CanardPlante attaquant)   { return 1.0; }
    public double etreAttaqueePar(CanardClassique attaquant){ return 1.0; }

    protected void effectuerAttaque(CanardDeCombat cible, double mult) {
        int degats = (int)(getAtk() * mult);
        System.out.println(getSurnom() + " attaque " + cible.getSurnom()
                + " (" + getType() + " -> " + cible.getType()
                + " : x" + mult + ") -> " + degats + " degats");
        cible.subirDegats(degats);
    }

    public abstract void attaquer(CanardDeCombat cible);
}
