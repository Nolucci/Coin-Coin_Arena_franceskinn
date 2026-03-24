package Partie_B.coincoin.arena;

public class Canard implements Combattant, Soignable {

    private final EspeceCanard espece;
    private final String surnom;
    private final int pvMax;
    private int pvActuels;
    private final int atk;

    private static int nbCanardsCrees = 0;

    public Canard(EspeceCanard espece, String surnom) {
        this.espece = espece;
        this.surnom = surnom;
        this.pvMax = espece.getPvBase();
        this.pvActuels = pvMax;
        this.atk = espece.getAtkBase();
        nbCanardsCrees++;
    }

    @Override public String getNom()    { return espece.getNom(); }
    public String getSurnom()           { return surnom; }
    @Override public int getPvMax()     { return pvMax; }
    @Override public int getPvActuels() { return pvActuels; }
    public int getAtk()                 { return atk; }
    public TypeCanard getType()         { return espece.getType(); }
    public EspeceCanard getEspece()     { return espece; }

    public static int getNbCanardsCrees() { return nbCanardsCrees; }

    @Override
    public void soigner() {
        pvActuels = pvMax;
    }

    public void soignerPartiel(int soin) {
        pvActuels = Math.min(pvMax, pvActuels + soin);
    }

    @Override
    public boolean estKO() {
        return pvActuels <= 0;
    }

    public void subirDegats(int degats) {
        pvActuels = Math.max(0, pvActuels - degats);
        System.out.println(surnom + " subit " + degats + " degats (PV: " + pvActuels + "/" + pvMax + ")");
    }

    @Override
    public void attaquer(Canard cible) {
        double mult = this.getType().getMultiplicateur(cible.getType());
        int degats = (int)(atk * mult);
        System.out.println(surnom + " attaque " + cible.getSurnom()
                + " (" + getType().getLibelle()
                + " -> " + cible.getType().getLibelle()
                + " : x" + mult + ") -> " + degats + " degats");
        cible.subirDegats(degats);
    }

    public void regenerer() {
        int soin = (int)(pvMax * 0.1);
        soignerPartiel(soin);
        System.out.println(surnom + " regenere " + soin + " PV (PV: " + pvActuels + "/" + pvMax + ")");
    }

    @Override
    public String toString() {
        return "[" + getType().getLibelle() + "] " + espece.getNom()
                + " \"" + surnom + "\" (PV: " + pvActuels + "/" + pvMax + " | ATK: " + atk + ")";
    }
}
