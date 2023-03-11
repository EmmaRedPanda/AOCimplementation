CHAPUIS Emma - M2 ILA

# TP Observer Parallèle

Le but du TP était d'adapter un système de communication entre capteur et afficheurs à un fonctionnement asynchrone à l'aide du patron de conception ActiveObject. On pouvait ensuite appliquer les stratégies de diffusion atomique, séquence et époque.

Le code de mon projet suit le fonctionnement shématisé dans le diagramme UML et le diagramme de séquence ci-dessous :


![diagramme UML](./ressources/AOCumlM3.png "diagramme UML")


![diagramme de séquence](./ressources/AOCseqM3.png "diagramme de séquence")

J'ai pu programmer la mise en place du PC ActiveObject, l'application de la stratégie de diffusion atomique ainsi que les tests pour cette stratégie.

Pour mettre en place la stratégie Atomique, j'ai créé une interface pour les algos de diffusion, puis une classe qui implémente cette interface destinée à l'algo de diffusion atomique. L'interface ainsi que l'implémentation comtiennent 2 méthodes : une "execute(CapteurImpl cap)" et une "verifie(CapteurImpl cap)". Sur le principe, tout va se baser sur un booléen rajouté au capteur, qui sera mis à false par l'algo lors de l'envoie d'une valeur, et ne sera mis de nouveau à true par l'algo que lorsque tous les afficheurs auront récupéré la valeur. Ce booléen va bloquer le changement et l'envoie de valeur tant qu'il est à false, permettant ainsi l'envoie dans l'ordre de toutes les valeurs à chaque afficheur. Comme la méthode "execute" gère l'envoie, une méthode "verifie" a été rajoutée pour ne pas envoyer de valeurs pendant l'attente.

Pour le test de l'algorithme de diffusion atomique, j'initialise tous les éléments et lance le programme sur un nombre random N de tick. Dans un cas, à l'aide d'un "assertEquals()", je compare en sortie les valeurs à la valeur attendue, générée avant le lancement. Dans l'autre cas, à l'aide d'un "assertNotEquals()", je compare en sortie les valeurs à une valeur fausse pour vérifier que le premier test est fiable. L'exécution étant asynchrone, je retarde l'exécution des assertions à l'aide d'un "Thread.sleep()" le temps que le programme ait fini de tourner. Après plusieurs essaies, les tests semblent fonctionner.

Pour lancer le programme, il faut run le fichier "Main" dans /src/code et pour effectuer un test on peut lancer le fichier de tests "TestAlgoAtomique" dans /src/tests.

