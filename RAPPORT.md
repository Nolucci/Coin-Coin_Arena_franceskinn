# Rapport – Coin-Coin Arena

## Partie A

**R1 – Vous avez un attribut nom et un attribut surnom. Si deux canards sont tous les deux des "Canard Flamme", ils partagent le même nom, les mêmes PV de base, la même attaque de base. Pourtant, dans votre code, ces valeurs communes sont dupliquées dans chaque instance. Voyez-vous le problème ?**

Oui. Chaque instance stocke ses propres copies de `nom`, `pvMax` et `atk`, meme si deux canards de la meme espece ont des valeurs identiques. C'est un gaspillage memoire. La Partie B regle ca avec `EspeceCanard` : un seul objet partagé contient les stats de base.

---

**R2 – Le compilateur voit que cible est de type CanardDeCombat. Aucune de vos surcharges n'accepte un CanardDeCombat. Meme si a l'execution cible est un CanardEau, la surcharge est resolue a la compilation d'apres le type declare, pas le type reel. C'est la difference fondamentale entre surcharge et redefinition. Cette tentative echoue. Comment s'en sortir ?**

 Java choisit a l'execution selon le type reel de l'objet. On passe `this` dont le type concret est connu a la compilation dans chaque sous-classe, ce qui permet de choisir la bonne surcharge de `etreAttaqueePar`.

---

**R3 – Comptez le nombre de methodes etreAttaqueePar que vous avez du ecrire. Si on ajoute un 5eme type, il faut creer la classe avec ses methodes, ajouter une methode dans CanardDeCombat, la redefnir dans chacune des sous-classes existantes. C'est du polymorphisme pur, mais c'est lourd. Gardez ce cout en tete pour la Partie B.**

Avec 4 types j'ai 4 methodes par classe soit 4x4 = 16 methodes au total. Ajouter un 5eme type oblige a modifier la classe mere et toutes les sous-classes existantes. Le nombre de methodes croit en N².

---

**R4 – Les methodes de Soignable et Combattant existent deja dans CanardDeCombat. Alors pourquoi s'embeter a creer ces interfaces ? Imaginez qu'un jour votre arene accepte aussi des combattants qui ne sont pas des canards. Comment les interfaces rendraient cela possible sans toucher au code de Arene ?**

Sans interface, `Arene` est couplée directement a `CanardDeCombat`. Si on veut faire combattre un canard ou un dresseur, il faudrait modifier `Arene`. Avec `Combattant`, n'importe quelle classe qui implemente l'interface peut etre utilisee dans l'arene sans y toucher.

---

**R5 – Le CanardConfus est un CanardEau avec un comportement modifie. Mais imaginez un CanardFeu confus, ou un CanardPlante confus. Faudrait-il creer CanardFeuConfus, CanardPlanteConfus, etc. ? Combien de classes cela ferait-il si on avait 4 types x 3 comportements speciaux ? C'est le probleme de l'explosion combinatoire.**

Oui, 4 types x 3 comportements = 12 sous-classes. Et si un canard peut etre confus ET enrage en meme temps, ca multiplie encore.

---

**R6 – Pour la regeneration des canards Plante, vous devez tester if (canard instanceof CanardPlante). C'est un instanceof utile ou un signe de mauvaise conception ? Pourrait-on s'en passer avec une methode finDeTour() redefinie dans chaque sous-classe ?**

C'est un signe de mauvaise conception. La logique propre a `CanardPlante` devrait etre dans `CanardPlante`, pas dans `Arene`. Avec une methode `finDeTour()` vide par defaut dans `CanardDeCombat` et redefinie dans `CanardPlante`, `Arene` appellerait juste `canard.finDeTour()` sans savoir de quel type il s'agit. Ajouter un `CanardGlace` qui perd des PV ne necessiterait alors aucune modification de `Arene`.

---

## Partie B

**R7 – Dans la Partie A, le type d'un canard est determine par sa classe. Dans la Partie B, ce sera determine par un attribut. Quelles consequences cela a-t-il sur : (a) l'ajout d'un nouveau type, (b) la possibilite de changer de type a l'execution, (c) la necessite de faire des instanceof ?**

(a) Ajouter un type = ajouter une valeur dans l'Enum et completer la table, aucune nouvelle classe.
(b) Changer de type a l'execution devient possible puisque c'est un attribut, pas une classe.
(c) Plus besoin d'`instanceof` pour les types, `getType() == TypeCanard.X` suffit.

---

**R8 – Comparez : dans la Partie A, la table des multiplicateurs est eclatee en N² methodes reparties dans toutes les sous-classes. Ici, toute la table est centralisee dans l'Enum. Si on ajoute un type Electrique : dans la Partie A, il faut ajouter une methode dans la classe mere + la redefnir dans potentiellement toutes les sous-classes. Dans la Partie B, il suffit d'ajouter une ligne dans l'Enum. Quel design est le plus maintenable ?**

La Partie B est plus maintenable. Toute la logique des multiplicateurs est au meme endroit. En Partie A, pour comprendre ce que fait Feu contre Eau il faut chercher dans plusieurs classes. En Partie B une seule lecture du tableau suffit.

---

**R9 – Creez mentalement 50 "Canard Flamme" dans chaque version : combien d'objets EspeceCanard existent en memoire ? Combien de copies des stats de base y a-t-il dans la Partie A ? C'est le principe du pattern Flyweight.**

En Partie A : 50 copies de `nom`, `pvMax`, `atk`. En Partie B : 1 seul objet `EspeceCanard.CANARD_FLAMME`, partage par les 50 instances. 

---

**R10 – Lequel de ces deux designs respecte le mieux le principe "ouvert a l'extension, ferme a la modification" ? Autrement dit : dans quel design peut-on ajouter un nouveau type sans modifier de code existant ?**

La Partie B. Ajouter un type en Partie A force a modifier `CanardDeCombat` et toutes les sous-classes. En Partie B on etend l'Enum sans toucher au code existant.

---

**R11 – Les deux "font la meme chose". Mais : (a) lequel survivrait si on supprimait la classe CanardPlante ? (b) lequel est plus facile a lire ? (c) si le comportement de fin de tour variait selon le type, ou placeriez-vous cette logique dans chaque version ?**

(a) `getType() == TypeCanard.PLANTE` survit, `instanceof CanardPlante` ne compile plus si la classe disparait.
(b) Le test sur l'Enum est plus lisible, on teste une valeur et non une relation de type.
(c) En Partie A : methode `finDeTour()` redefinie dans chaque sous-classe. En Partie B : methode abstraite `finDeTour(Canard c)` dans l'Enum, chaque valeur la definit. `Arene` appelle `canard.getType().finDeTour(canard)`.

---

**R12 – Le CanardConfus a un comportement special. Dans la Partie B, il n'y a qu'une seule classe Canard. Comment modeliseriez-vous ce comportement sans creer de sous-classe ? Plusieurs pistes : un attribut booleen confus, une interface ComportementAttaque avec differentes implementations, un Enum EtatCanard. Laquelle vous semble la plus extensible ?**

Un attribut booleen est simple mais ne gere pas plusieurs comportements simultanés. Un `Enum EtatCanard` est propre pour des etats exclusifs.