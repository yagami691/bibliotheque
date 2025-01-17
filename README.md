# Gestion d'une bibliothèque

## Objectif
Ce projet a pour but de créer une application Java permettant de gérer une bibliothèque, de suivre les livres, les membres et les emprunts.

---

## Prérequis

### Extensions et Outils
- **Java Platform Extension** sur **VS Code** (ou tout autre IDE compatible avec Java).
- **Système de gestion de base de données relationnelle** : PostgreSQL (version 17) avec pgAdmin (interface graphique).

### Librairies et Versions
- **Driver PostgreSQL JDBC** : `postgresql-42.7.3.jar`.
- **JDK (Java Development Kit)** : 
  - `java version "21.0.5" 2024-10-15 LTS`.

---

## Instructions pour exécuter le projet

### Compilation
Utilisez la commande suivante pour compiler les fichiers Java :
```
javac -d out -cp "lib/postgresql-42.7.3.jar" src/*.java
```

Utilisez la commande suivante pour exécuter l'application :
```
java -cp ".;out;lib/postgresql-42.7.3.jar" Main
```
