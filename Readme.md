# MailStone

## Contexte et objectif

Les documents sont généralement à la base des échanges entre entreprises : échanges avec les clients (ventes en B2B), avec les fournisseurs, les partenaires, etc. Mais les diverses application internes dans les entreprises (les « métiers » : services des ressources humaines, des ventes, des affaires juridiques, des achats, etc.) utilisent des données structurées pour réaliser le pilotage de l’activité, la réalisation de bilan, (etc.). Les applications métiers étant conçues séparément, la communication entre la base de données et chaque application se fait par des messages : un fichier contenant les informations utiles à une application est généré à la demande depuis la base de données pour être consommé ensuite par l’application.

Ainsi il est utile de partir des documents pour alimenter la base de données, puis de générer des extractions de ces données, selon les besoins des applications des « métiers », pour que ces données soient utilisées.

L’objectif du projet est ainsi de traiter

1. L’extraction de donnée à partir de texte
2. La conception et l’alimentation d’une base de données
3. La génération de contenus structurés dédiés à des applications métiers
