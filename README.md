# devops-ipi

Travail réalisé:

- L'appication utilisée est une application Java nommée social network.
- Mise en place d'une image java rootless avec un user non root nomée docker-ipi par l'équipe de dev.
- L'équipe de dev à mis en place les manifests suivant:
  - manifest/app/1-app_db.yaml
  - manifest/app/1-app_java.yaml
  - manifest/app/1-secret.yaml

- L'équipe d'infra les manifests suivant:
  - manifest/app/1-netpol.yaml (network policies)
  - manifest/RBAC/role-admin-role-dev.yaml
  - manifest/RBAC/role-manage-pods-dev.yaml
  - manifest/RBAC/role-binding-sylvie-dev.yaml
  - manifest/RBAC/role-binding-bob-dev.yaml

- Mise en place d'un Github Workflow qui déploie automatiquement les nouvelles versions de l'application sur le Harbor du cluster. (tésté et validé il faut remonter les workflows car le cluster n'est plus actif)
- Mise en place d'un cluster K8S on premise et un cluster RKE on premise.
- Mise en place d'une VM bastion avec deux interface une connectée au réseaux publique et l'autre dans le réseau des nodes du cluster, pour pouvoir communiquer à l'API du controle plane.

    Bien évidement pour la VM bastion elle n'est accèssible qu'en SSH, avec authentification via certificat (l'authentification ayant été désactivé).
- Dans cette VM bastion deux utilisateur Sylvie et Bob tout deux ayant leur propre kubeconfig voir le dossier RBAC pour voir leurs droits.
Bob étant un dev et Sylvie une admin du namespace utilisé. Ces deux user se connecte à la VM bastion avec leur clé publique rsa. Puis au cluster via le kubeconfig avec certificat signé par la ca du cluster(voir RBAC).
