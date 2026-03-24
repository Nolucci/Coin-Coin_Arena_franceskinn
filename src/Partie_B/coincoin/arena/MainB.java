package Partie_B.coincoin.arena;

public class MainB {

    public static void main(String[] args) {
        Canard gerard  = new Canard(EspeceCanard.CANARD_FLAMME,   "Gérard");
        Canard hubert  = new Canard(EspeceCanard.CANARD_MARIN,    "Hubert");
        Canard fernand = new Canard(EspeceCanard.CANARD_MOUSSE,   "Fernand");
        Canard marcel  = new Canard(EspeceCanard.CANARD_CLASSIQUE,"Marcel");
        Canard josette = new Canard(EspeceCanard.CANARD_FLAMME,   "Josette");

        System.out.println("Meme espece, surnoms differents :");
        System.out.println("  " + gerard);
        System.out.println("  " + josette);
        System.out.println("  Meme objet EspeceCanard : " + (gerard.getEspece() == josette.getEspece()));
        System.out.println();

        EspeceCanard CANARD_ECLAIR = new EspeceCanard(
            "Canard Eclair", TypeCanard.NORMAL, 38, 60,
            "Un canard tres rapide."
        );
        Canard zap = new Canard(CANARD_ECLAIR, "Zap");
        System.out.println("Nouvelle espece : " + zap);
        System.out.println();

        Equipe equipe1 = new Equipe("Sacha");
        equipe1.ajouter(gerard);
        equipe1.ajouter(fernand);
        equipe1.ajouter(zap);

        Equipe equipe2 = new Equipe("Ondine");
        equipe2.ajouter(hubert);
        equipe2.ajouter(marcel);
        equipe2.ajouter(josette);

        equipe1.afficher();
        System.out.println();
        equipe2.afficher();

        Arene arene = new Arene();
        arene.combattre(equipe1, equipe2);

        System.out.println("\nNombre total de canards crees : " + Canard.getNbCanardsCrees());
    }
}
