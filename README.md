# Projet 6 - Site web d'une association d'escalade

## Contenu

Ce projet est un site web communautaire permettant aux adaptes de l'escalade de pouvoir se renseigner sur des sites d'escalade, de partager leurs expériences sur différents sites, en laissant des commentaires, d'ajouter d'autres spots et d'ajouter plus d'informations sur des spots déjà présents.

Il est possible aussi, pour des utilisateurs connectés, d'ajouter des topos, de faire une demande de prêt d'un topo au propriétaire et d'accepter ou de refuser le prêt.

------------------------

## Prérequis

* Java version 1.8
* Maven 3.6

------------------------

## Base de données

Les scripts SQL présents dans src/main/resources/Scripts permettent de créer une base de données avec un jeu de données.

* Création des tables : 
    
    * db_escalade.sql

* Alimentation des données : 
    
    * jeu_de_donnees.sql

Le SGBD (Système de Gestion de Base de Données) configuré dans ce projet est postgreSQL, si vous utilisez un autre SGBD il faudra alors changer la configuration dans le fichier "application.properties" qui se trouve dans le chemin src/resources application.properties .

------------------------

## Installation et déploiement

### Déploiement avec ou sans conteneur web préinstallé

L'application peut être déployée de deux façons:

1. sous forme d'une application "standalone" intégrant un conteneur web (grâce à SpringBoot)

2. sous forme d'une webapp traditionnelle (war) à déployer dans le répertoire webapps d'un conteneur web (comme Tomcat, par exemple)

### Déploiement sans conteneur web

* Clonez le projet Github

* Exécutez la ligne de commande :

    * mvn clean package spring-boot:run

* Ouvrez un navigateur web avec l'adresse :

    * http://localhost:8080

------------------------

## Déploiement dans un conteneur Web

Générez un package au format WAR dans le répertoire target avec la commande :

* mvn clean package

* Placez le package (war) dans le répertoire webapps d'un conteneur web (type Tomcat)

* Avec un Tomcat déployé en local, utilisez l'URL suivant dans un navigateur web :

    * http://localhost:8080/escalade-0.0.1-SNAPSHOT.war