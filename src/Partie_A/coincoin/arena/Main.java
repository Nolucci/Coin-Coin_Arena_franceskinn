package Partie_A.coincoin.arena;

public class Main {

    public static void main(String[] args) {
        CanardFeu gerard = new CanardFeu("Canard Flamme", 39, 52, 1.2);
        gerard.setSurnom("Gérard");

        CanardConfus coinCoin = new CanardConfus("Canard Confus", 52, 48);
        coinCoin.setSurnom("Coin-Coin");

        CanardPlante fernand = new CanardPlante("Canard Mousse", 45, 49);
        fernand.setSurnom("Fernand");

        CanardEau hubert = new CanardEau("Canard Marin", 44, 48, 8);
        hubert.setSurnom("Hubert");

        CanardClassique marcel = new CanardClassique("Canard Classique", 50, 45);
        marcel.setSurnom("Marcel");

        CanardFeu josette = new CanardFeu("Canard Braise", 42, 55, 0.9);
        josette.setSurnom("Josette");

        Equipe equipe1 = new Equipe("Sacha");
        equipe1.ajouter(gerard);
        equipe1.ajouter(coinCoin);
        equipe1.ajouter(fernand);

        Equipe equipe2 = new Equipe("Ondine");
        equipe2.ajouter(hubert);
        equipe2.ajouter(marcel);
        equipe2.ajouter(josette);

        equipe1.afficher();
        System.out.println();
        equipe2.afficher();
        System.out.println();

        Arene arene = new Arene();
        arene.combattre(equipe1, equipe2);

        System.out.println("\nNombre total de canards crees : " + CanardDeCombat.getNbCanardsCrees());
    }
}
