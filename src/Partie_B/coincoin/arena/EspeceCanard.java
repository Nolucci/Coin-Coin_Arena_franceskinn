package Partie_B.coincoin.arena;

public class EspeceCanard {

    private final String nom;
    private final TypeCanard type;
    private final int pvBase;
    private final int atkBase;
    private final String description;

    public EspeceCanard(String nom, TypeCanard type, int pvBase, int atkBase, String description) {
        this.nom = nom;
        this.type = type;
        this.pvBase = pvBase;
        this.atkBase = atkBase;
        this.description = description;
    }

    public String getNom()          { return nom; }
    public TypeCanard getType()     { return type; }
    public int getPvBase()          { return pvBase; }
    public int getAtkBase()         { return atkBase; }
    public String getDescription()  { return description; }

    // --- Espèces prédéfinies ---

    public static final EspeceCanard CANARD_FLAMME =
        new EspeceCanard("Canard Flamme", TypeCanard.FEU, 39, 52,
            "Un canard dont la flamme ne s'éteint jamais.");

    public static final EspeceCanard CANARD_MARIN =
        new EspeceCanard("Canard Marin", TypeCanard.EAU, 44, 48,
            "Un canard qui maîtrise les courants.");

    public static final EspeceCanard CANARD_MOUSSE =
        new EspeceCanard("Canard Mousse", TypeCanard.PLANTE, 45, 49,
            "Un canard recouvert de mousse régénérante.");

    public static final EspeceCanard CANARD_CLASSIQUE =
        new EspeceCanard("Canard Classique", TypeCanard.NORMAL, 50, 45,
            "Le bon vieux canard jaune. Indémodable.");
}
