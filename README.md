                                          Utilisation de Prometheus

creer la base de donnees bdtalend sur postgres et votre mot de passe et nom d'utilisateur par defaut cest postgres
telecharger prometheus executer en ligne de commande en etant dans le repertoire de prometheus (prometeus>prometheus.exe) 
dans le navigateur vous inserer ladresse localhost:9090(cest le port de configuration de prometheus, il est changable) 
vous verrez l'interface de prometheus pour voir les metrics de prometheus par defaut
pour voirs les metrics de l'application vous devrez executer localhost:8083/actuator/metrics sur postman ou sur un navigateur.
