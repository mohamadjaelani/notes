kubectl get ns 14031-crl -> get namespace
kubectl get all -n 14031-crl -> all pods


--edit
kubectl edit deployment crl-ellise-gateway-service-sit-aws
kubectl edit deployment crl-ellise-gateway-service-prod-aws



-- verify pod is running or not
kubectl get pod shell-demo

-- get shell into running container
kubectl exec --stdin --tty shell-demo -- /bin/bash

-- download file from pod
Kubectl cp pod's_name:<src_dir> <target_dir>

-- show pod log
kubectl logs pod-name
-- describe pod
kubectl describe pod [pod-name]

-- get pvc properties
kubectl get pvc 14031-crl-pvc-dev -ojson

-- get deployment
Kubectl get deployment

-- stop pod
kubectl scale --replicas=0 deployment/<deployement_name>
kubectl scale --replicas=1 deployment/

-- copy file from pod
kubectl cp -n azid-eks-crl-dev crl-letter-service-9f78c79df-pdfk5:/var/www/uploadnb/2025-04-28/A2025042812190000002/A2025042812190000002_MEMBERWITHAOPHOTO_2025-04-28.jpg ./AcceptanceLetter_BWS991122439_231021140621.pdf

kubectl exec --stdin --tty crl-letter-service-prod-79b587fb5f-tmcd7 -- /bin/bash

-- enter to pod
kubectl exec --stdin --tty <pod's name> -- /bin/bash
kubectl exec --stdin --tty crl-ellise-letter-service-prod-aws-6f8bf5ff57-ksk68 -- /bin/bash

--Cari dan hapus/tested
find . -name "*.pdf" -delete
find . -name "*.jpg" -delete
find . -name "*.png" -delete
find . -name "*.csv" -delete
find . -name "*.*" -delete
 -- delete all file in the directory/ sub directory
rm -r <directory>
--eg
rm -r SPKTAPBSI

SPKTAPBSI


Du -hs <dirname> ->get size of a directory in linus

Df -ah

Get directory list:
ls -F | grep "/$"
Or
ls -d */

Show pvc space:

kubectl exec -it <pod-name> -- df -h

kubectl exec -it crl-letter-service-8567c46f89-p7bv6 -- rm -r /var/www/letter/assert/pdf



<img width="2174" height="823" alt="image" src="https://github.com/user-attachments/assets/b0e14363-186e-428f-a7fc-59d2fe4c95a4" />
