# SimpleCashSI – Application bancaire
### EPITA – Projet Java

Ce projet propose une application bancaire simplifiée permettant de gérer des clients, leurs comptes, leurs cartes bancaires et leur suivi par un conseiller dans une agence.  
L’objectif : offrir un système clair, intuitif et cohérent pour représenter le fonctionnement d’une petite institution bancaire.

---

## Fonctionnalités principales

### Gestion des clients
- Création, modification, consultation et suppression d’un client.
- Lorsqu’un client est supprimé :
    - tous ses comptes sont supprimés automatiquement,
    - toutes ses cartes bancaires sont désactivées pour éviter tout usage abusif.

---

### Comptes bancaires

Deux types de comptes sont proposés :

**Compte Courant**
- Autorisation de découvert automatique : **1000€**.

**Compte Épargne**
- Taux de rémunération appliqué automatiquement : **3%**.

L'application permet de :
- créer un compte pour un client,
- consulter les comptes existants,
- créditer ou débiter un compte,
- effectuer des virements internes.

---

### Cartes bancaires
- Possibilité d’ajouter une carte à un compte courant.
- Types disponibles :
    - Visa Electron
    - Visa Premier
- Chaque carte peut être activée ou désactivée.
- En cas de suppression client, les cartes deviennent automatiquement inactives.

---

### Agences et conseillers
- Une agence est identifiée par un code unique de **5 caractères alphanumériques**.
- Chaque conseiller appartient à une agence.
- Règle métier : **un conseiller peut suivre au maximum 10 clients**.
- Il est possible d’attribuer ou changer le conseiller d’un client.

---

### Virements bancaires
- Permet d’effectuer un virement entre deux comptes internes.
- Le système vérifie :
    - le solde du compte débiteur,
    - le respect du découvert autorisé.

---

### Audit interne
- Permet de lister les comptes considérés comme “non conformes”.
- Exemples :
    - solde trop négatif pour un compte courant,
    - solde insuffisant sur un compte épargne.

---

### Simulation de crédit
- Permet d’obtenir une indication simple sur la faisabilité d’un crédit pour un client.
- Répond par **oui/non** selon les critères internes.

---

## Diagramme UML du système

![UML du projet](architecture.puml)

Ce schéma permet de comprendre rapidement l’organisation générale du système.

---

## Suivi du projet et organisation

La gestion complète du projet, des fonctionnalités à développer et des user stories se trouve sur notre espace Jira :

https://projet-gunslay-etienne.atlassian.net/jira/software/projects/KAN/boards/1?selectedIssue=KAN-21

## Liste complète des appels API

GET /api/clients
GET /api/clients/{id}
POST /api/clients
PUT /api/clients/{id}
DELETE /api/clients/{id}

GET /api/comptes
GET /api/comptes/{id}
POST /api/comptes
POST /api/comptes/{numero}/credit?montant={montant}
POST /api/comptes/{numero}/debit?montant={montant}

GET /api/cartes
GET /api/cartes/{id}
POST /api/cartes
PUT /api/cartes/{id}/desactiver

POST /api/virements?debiteur={numero}&receveur={numero}&montant={montant}

GET /api/audit

POST /api/simulation?clientId={id}&montant={montant}&duree={mois}

GET /api/agences
GET /api/agences/{id}
POST /api/agences

GET /api/conseillers
GET /api/conseillers/{id}
POST /api/conseillers
PUT /api/conseillers/{id}/assigner-client?clientId={id}