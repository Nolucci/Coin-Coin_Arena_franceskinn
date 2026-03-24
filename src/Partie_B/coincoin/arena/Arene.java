package Partie_B.coincoin.arena;

public class Arene {

    public void combattre(Equipe e1, Equipe e2) {
        System.out.println("\nCombat : " + e1.getNomDresseur() + " vs " + e2.getNomDresseur() + "\n");

        Canard c1 = e1.getPremierValide();
        Canard c2 = e2.getPremierValide();

        int tour = 0;

        while (c1 != null && c2 != null) {
            tour++;
            System.out.println("Tour " + tour + " :");

            c1.attaquer(c2);
            if (c2.estKO()) {
                System.out.println(c2.getSurnom() + " est KO.");
                c2 = e2.getPremierValide();
                if (c2 != null) {
                    System.out.println(e2.getNomDresseur() + " envoie " + c2.getSurnom() + ".");
                }
            }

            if (c2 != null && !c1.estKO()) {
                c2.attaquer(c1);
                if (c1.estKO()) {
                    System.out.println(c1.getSurnom() + " est KO");
                    c1 = e1.getPremierValide();
                    if (c1 != null) {
                        System.out.println(e1.getNomDresseur() + " envoie " + c1.getSurnom());
                    }
                }
            }

            if (c1 != null && !c1.estKO() && c1.getType() == TypeCanard.PLANTE) {
                c1.regenerer();
            }
            if (c2 != null && !c2.estKO() && c2.getType() == TypeCanard.PLANTE) {
                c2.regenerer();
            }

            System.out.println();
        }

        System.out.println("Fin du combat apres " + tour + " tour(s).");
        Equipe vainqueur = e1.touteKO() ? e2 : e1;
        System.out.println("Vainqueur : " + vainqueur.getNomDresseur());
        System.out.println("Survivants :");
        vainqueur.afficher();
    }
}
