# ProjetAlgo2

Pour pouvoir lancer le script My_Graph.java, il est indispensable d'utiliser la bibliothèque algs4.jar.
Dans cette bibliothèque nous avons besoin de Graph.java, In.java et Stack.java.
Il faut aussi la classe Vector dans java.util.

Pour tester le script, il faut ajouter en paramètre le fichier permettant de construire le graphe.
Ce fichier est composé de cette manière: première ligne, le nombre de sommets du graphe, deuxième ligne
le nombre d'arètes du graphe, et pour les lignes suivantes ce sera les liens entre les différents sommets
sous la forme 1 2, donc le sommet 1 à une arête le reliant au sommet 2.

Sur Ubuntu 20.04 :
    Pour compiler : javac -cp ./src/algs4.jar: My_Graph.java 
    Pour exécuter : java -cp ./src/algs4.jar: My_Graph ./src/graphEnonce.txt

Lors de l'exécution, le script imprimera la dégénérescence, ensuite la coloration, et pour finir la
profondeur de chaque sommet du graphe. 

Les graphes utilisés dans le cadre de la question 4 sont disponible dans le dossier src.