# Déploiement d’un cluster Kubernetes privé sur Google Cloud

Pour la mise en place d'un cluster privé sur google cloud, j'ai créé un cluster sur un sous réseau privé d'un VPC. J'ai également mis une VM bastion avec un service account qui permet d'intéragir avec le cluster.
J'ai mis en place une règle d'accès en 22 vers la VM bastion, et une règle d'accès en 443 80 vers le cluster.
Je n'ai pas été au bout de l'installation car je suis partis sur un cluster non manager RKE.

## Schéma de la configuration à mettre en place

![alt text](network_cluster_configuration.png "Cluster network")

Voici quelques commandes que j'ai utiliser pour vous montrer le travail réalisé.
```bash

```
