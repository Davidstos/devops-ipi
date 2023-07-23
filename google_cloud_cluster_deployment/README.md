# Déploiement d’un cluster Kubernetes privé sur Google Cloud

Pour la mise en place d'un cluster privé sur google cloud, j'ai créé un cluster sur un sous réseau privé d'un VPC. J'ai également mis une VM bastion avec un service account qui permet d'intéragir avec le cluster.
J'ai mis en place une règle d'accès en 22 vers la VM bastion, et une règle d'accès en 443 80 vers le cluster.
Je n'ai pas été au bout de l'installation car je suis partis sur un cluster non manager RKE.

## Schéma de la configuration à mettre en place

![alt text](network_cluster_configuration.png "Cluster network")

Voici quelques commandes que j'ai utiliser pour vous montrer le travail réalisé.

⚠️ ces commandes sont en désordres complet mais ce sont des notes.

```bash
gcloud config set project  melodic-eye-388413


gcloud compute project-info add-metadata \
    --metadata google-compute-default-region=europe-west1
    
gcloud container clusters create-auto private-cluster-0 \
    --create-subnetwork name=my-subnet-0 \
    --enable-master-authorized-networks \
    --enable-private-nodes \
    --enable-private-endpoint \
    --region=europe-west1


gcloud container clusters update private-cluster-0 \
    --enable-master-authorized-networks \
    --master-authorized-networks 192.168.0.7/32 \
    --region=europe-west1




gcloud compute networks create my-net-2 \
    --subnet-mode custom


gcloud compute networks subnets create my-subnet-2 \
    --network my-net-2 \
    --range 192.168.0.0/20 \
    --secondary-range my-pods=10.4.0.0/14,my-services=10.0.32.0/20 \
    --enable-private-ip-google-access

gcloud container clusters create-auto private-cluster-2 \
    --region europe-west1 \
    --enable-master-authorized-networks \
    --network my-net-2 \
    --subnetwork my-subnet-2 \
    --cluster-secondary-range-name my-pods \
    --enable-private-endpoint \
    --services-secondary-range-name my-services \
    --enable-private-nodes
   
 gcloud compute instances create node2 \
  --zone=europe-west1-b \
  --machine-type=n2d-highmem-8 \
  --subnet=my-subnet-2 \
  --scopes=cloud-platform \
  --metadata ssh-keys="fr_david_dias:ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQCQ+QzPcFJ30qmB1PIpJz8qzE0KcyjZW5TknPddnG71YVLqDWqBjm/T3dEWZw+Fn9B6EBLFh5YKc92pVy48AIgzW94qbBdj7Y3cF/xVt6m39vN7ngxeo1dgy7eRfRrOTAaLwkvYQ2+WA+RL0px50hUwjF60zFMHE+Wom8peQlfaYERzcFbXpG4P0LOdonshwCiAgMMt63S2MSKT+Ml/X6WxzxaDp7xcBaCABh8o4oXD4E/ExtrjvNfGcTQLiEA5NpZdDLFsd1neATx6rn+r3GWplcpDYqbEEH3FxFwNX097N6nBzqNrZFll562X8VtVUVgtUNPFdMbry2DeTLa9DVnT93/P+VXh1ZEwJ4JHa7MitWwDfz67aSwxXf14hKHj2A6wxzQ3/efUzIs90Ouy5MUisAdA+m/d/EtJ07PGDXtKdja4upGincWSf69yLE/3Vr1cke1NQVjgmvfNAEXTbLzAmk3PLHTEYoU/eoQ/9r05F3fyVKwNK+1ZPLck+BoEDxk= david@Zenbook" \
  --service-account=get-cluster-cred@melodic-eye-388413.iam.gserviceaccount.com \
  --create-disk=auto-delete=yes,boot=yes,device-name=instance-1,image=projects/debian-cloud/global/images/debian-11-bullseye-v20230509,mode=rw,size=30,type=projects/melodic-eye-388413/zones/europe-west1-b/diskTypes/pd-balanced 
  
 gcloud compute instances create private \
  --zone=europe-west1-b \
  --machine-type=n1-standard-2 \
  --subnet=my-subnet-2 \
  --no-address

  
gcloud container clusters update private-cluster-2 \
    --enable-master-authorized-networks \
    --master-authorized-networks 192.168.0.7/32 \
    --region=europe-west1
  
  
  gcloud compute firewall-rules create allow-ssh \
  --direction=INGRESS \
  --priority=1000 \
  --network=my-net-2 \
  --action=ALLOW \
  --rules=tcp:22 \
  --source-ranges=0.0.0.0/0 \
  --target-tags=bastion \
  --enable-logging

gcloud compute --project=myproject firewall-rules create mynet-allow-ssh --direction=INGRESS --priority=1000 --network=mynet --action=ALLOW --rules=tcp:22 --source-ranges=0.0.0.0/0

  
 gcloud compute instances add-iam-policy-binding bastion --member=serviceAccount:110099034245800556588  --role=roles/iam.serviceAccountKeyAdmin
 
 
gcloud container clusters get-credentials private-cluster-2 --region=europe-west1

sudo apt-get install kubectl
sudo apt-get install google-cloud-sdk-gke-gcloud-auth-plugin

gcloud compute instances set-iam-policy bastion --region europe-west1 --project melodic-eye-388413 --member fr_david_dias --role roles/container.clusterAdmin

 gcloud iam service-accounts create get-cluster-cred \
    --description "get cluster credential" \
    --project melodic-eye-388413
    
gcloud projects add-iam-policy-binding melodic-eye-388413 \
    --member="serviceAccount:get-cluster-cred@melodic-eye-388413.iam.gserviceaccount.com" \
    --role="roles/container.clusterViewer"
    
gcloud compute instances set-service-account bastion \
    --service-account=get-cluster-cred@melodic-eye-388413.iam.gserviceaccount.com \
    --project melodic-eye-388413

gcloud projects add-iam-policy-binding melodic-eye-388413 \
    --member="serviceAccount:get-cluster-cred@melodic-eye-388413.iam.gserviceaccount.com" \
    --role="roles/container.clusterAdmin"
    
gcloud projects add-iam-policy-binding melodic-eye-388413 \
    --member="serviceAccount:get-cluster-cred@melodic-eye-388413.iam.gserviceaccount.com" \
    --role="roles/iam.serviceAccountTokenCreator"
  
```
