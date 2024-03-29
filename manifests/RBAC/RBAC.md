# RBAC

Pour le namespace dev nous avons deux utilisateur 

-   Bob Smith en tant que développeur sur le namespace de dev
-   Sylvie Francon en tant qu'admin du namespace de dev

Dans cette documentation nous verrons comment créer un user.

Vous trouverez dans le dossier /manifests/RBAC tous les manifests avec les roles et roles bindings de chaque user.

## Kubernetes CA
Kubernetes n'a pas de concept d'utilisateurs, il s'appuie sur des certificats et ne fait confiance qu'aux certificats signés par sa propre autorité de certification.

Pour obtenir les certificats CA pour notre cluster, le moyen le plus simple est d'accéder au nœud maître.

Les certificats CA existent par défaut dans le dossier /etc/kubernetes/pki.

Il faut ensuite récupérer ces deux fichiers:
-   /etc/kubernetes/pki/ca.crt
-   /etc/kubernetes/pki/ca.key

## Utilisateur Kubernetes

La première chose que nous devons faire est de créer un certificat signé par notre autorité de certification Kubernetes.

```
# private key
openssl genrsa -out bob.key 2048
```

Maintenant que nous avons une clé, nous avons besoin d'une demande de signature de certificat (CSR).

Nous devons également spécifier les groupes auxquels Bob appartient.

Bob fait partie de l'équipe de dev.

```
openssl req -new -key bob.key -out bob.csr -subj "/CN=Bob Smith/O=Dev"
```

Utilisez l'AC pour générer notre certificat en signant notre CSR.

Nous pouvons également fixer une date d'expiration sur notre certificat.

```
openssl x509 -req -in bob.csr -CA ca.crt -CAkey ca.key -CAcreateserial -out bob.crt -days 365
```

## Kubeconfig

Nous allons essayer d'éviter de gâcher notre configuration kubernetes actuelle.


``` export KUBECONFIG=~/.kube/new-config ```

Créer une entrée de cluster qui pointe vers le cluster et contient les détails du certificat CA :

```
# Pour l'option serveur regarder votre kubeconfig déjà existant

kubectl config set-cluster dev-cluster --server=https://IP:PORT \
--certificate-authority=ca.crt \
--embed-certs=true 
```

```
kubectl config set-credentials bob --client-certificate=bob.crt --client-key=bob.key --embed-certs=true

kubectl config set-context dev --cluster=dev-cluster --namespace=dev --user=bob

kubectl config use-context dev

kubectl get pods
```

L'utilisateur "Bob Smith" ne peut pas lister les ressources "pods" dans le groupe API "" dans l'espace de noms "dev"

Pour donner l'accès à Bob:
```
kubectl -n dev apply -f .\role-manage-pods-dev.yaml
kubectl -n dev apply -f .\role-binding-bob-dev.yaml
```

Pour Sylvie c'est la même chose mais il faut changer le "/CN=Bob Smith/O=Dev" par "/CN=Sylvie Francon/O=Admin"
