# Ingress controller nginx

```bash
# Créer un espace de noms pour le contrôleur Ingress NGINX
kubectl create namespace ingress-nginx

# Utiliser Helm pour installer le contrôleur Ingress NGINX
# Ajouter le repo
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update

# Installer le contrôleur Ingress NGINX
helm install ingress-nginx ingress-nginx/ingress-nginx --namespace ingress-nginx

# Vérifier que le contrôleur d'Ingress NGINX est en cours d'exécution
kubectl get pods -n ingress-nginx
```
