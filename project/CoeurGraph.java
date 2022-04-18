/*

Du coup l'idée c'est de reprendre le même algorithme que l'exercice 1...
Donc on parcourt le graphe (sous forme de vecteur) et au moment de supprimer un sommet
on retient sa profondeur, qui est égale à k.
Comme au début quand k=1 on supprime les sommets qui sont en mauve sur la figure 3, ce sont les 1-coeurs.

Maintenant je sais pas si on doit faire un programme qui renvoie la profondeur de tous les sommets du graphe
ou bien si on doit faire une fonction c(v) qu'on appelle sur chaque sommet v dont on veut la profondeur individuellement.

Si c'est pour chaque sommet individuellement alors on parcourt le graphe en supprimant les sommets
et on s'arrête (sans mauvais jeu de mot) au moment de supprimer le sommet qu'on étudie et on renvoie k.
Faut juste passer le sommet dont on veut la profondeur en paramètre à la fonction je pense.

Sinon si c'est pour donner la profondeur de tous les sommets en une fois je sais pas
sous quelle forme on doit renvoyer le résultat, soit on print chaque fois le sommet qu'on supprime et k,
soit on trouve un système de dictionnaire qui associe un sommet et une valeur de k...


                         ,.
                        (_|,.
                       ,' /, )_________   _
                    __j o``-'          `.'-)'
                   (")                   \'
                    `-j                  |
                      `-._(             /
                         |_\  |----^.  /
                        /_]'|_|   /_)_/
                           /_]'    /_]'

                           
*/