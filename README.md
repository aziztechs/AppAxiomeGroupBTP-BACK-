# AppAxiomeGroupBTP - Backend

## Description du Projet

AppAxiomeGroupBTP est une application de gestion de chantiers de construction développée pour Axiome Group BTP. Cette application permet de gérer l'ensemble des processus liés aux chantiers de construction, depuis la planification jusqu'à la livraison.

## Fonctionnalités Principales

- **Gestion des Chantiers** : Création, suivi et gestion des chantiers de construction
- **Gestion des Budgets** : Suivi des budgets prévisionnels, engagés et réalisés
- **Gestion des Documents** : Stockage et gestion des documents liés aux chantiers
- **Gestion des Incidents** : Suivi et résolution des incidents sur les chantiers
- **Gestion des Jalons** : Planification et suivi des étapes clés des chantiers
- **Gestion des Factures** : Création, validation et suivi des factures
- **Gestion des Fournisseurs** : Gestion des fournisseurs et de leurs prestations
- **Gestion des Utilisateurs** : Administration des utilisateurs et de leurs droits
- **Notifications** : Système de notifications pour informer les utilisateurs des événements importants

## Architecture Technique

- **Framework** : Spring Boot
- **Base de données** : MySQL
- **Sécurité** : Spring Security avec JWT
- **Documentation API** : Swagger/OpenAPI
- **Architecture** : Architecture en couches (Controller, Service, Repository)

## Structure du Projet

```
src/main/java/aziztechs/sn/appaxiomegroupbtpback/
├── config/           # Configuration de l'application
├── controllers/      # Contrôleurs REST
│   ├── impl/         # Implémentations des contrôleurs
│   └── interfaces/   # Interfaces des contrôleurs
├── dto/              # Objets de transfert de données
│   ├── requiest/     # DTOs pour les requêtes
│   └── response/     # DTOs pour les réponses
├── entities/         # Entités JPA
├── exceptions/       # Gestion des exceptions
├── repositories/     # Repositories JPA
├── services/         # Services métier
│   ├── impl/         # Implémentations des services
│   └── interfaces/   # Interfaces des services
└── utils/            # Classes utilitaires
```

## Installation et Démarrage

### Prérequis

- Java 17 ou supérieur
- Maven 3.8 ou supérieur
- MySQL 8.0 ou supérieur

### Installation

1. Cloner le dépôt :
   ```bash
   git clone https://github.com/aziztechs/AppAxiomeGroupBTP-BACK-.git
   cd AppAxiomeGroupBTP-BACK-
   ```

2. Configurer la base de données dans `src/main/resources/application.properties`

3. Compiler et exécuter l'application :
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. L'application sera accessible à l'adresse : `http://localhost:8080`
   
5. La documentation Swagger sera disponible à : `http://localhost:8080/swagger-ui.html`

## API REST

L'application expose une API REST complète pour interagir avec toutes les fonctionnalités :

- `/api/auth` - Authentification et gestion des utilisateurs
- `/api/chantiers` - Gestion des chantiers
- `/api/budgets` - Gestion des budgets
- `/api/documents` - Gestion des documents
- `/api/incidents` - Gestion des incidents
- `/api/jalons` - Gestion des jalons
- `/api/factures` - Gestion des factures
- `/api/fournisseurs` - Gestion des fournisseurs
- `/api/notifications` - Gestion des notifications
- `/api/roles` - Gestion des rôles et permissions

## Sécurité

L'application utilise Spring Security avec JWT pour sécuriser les API. Chaque requête (sauf l'authentification) nécessite un token JWT valide dans l'en-tête `Authorization`.

## Contribution

Pour contribuer au projet :

1. Forker le dépôt
2. Créer une branche pour votre fonctionnalité (`git checkout -b feature/nouvelle-fonctionnalite`)
3. Committer vos changements (`git commit -m 'Ajout d'une nouvelle fonctionnalité'`)
4. Pousser vers la branche (`git push origin feature/nouvelle-fonctionnalite`)
5. Ouvrir une Pull Request

## Licence

Ce projet est la propriété d'Axiome Group BTP et est destiné à un usage interne uniquement.

## Contact

Pour toute question ou suggestion, veuillez contacter l'équipe de développement à l'adresse : contact@axiomegroup.sn

