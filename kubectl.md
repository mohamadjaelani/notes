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



kubectl edit deployment/crl-document-service-prod -o yaml --save-config
kubectl edit deployment/crl-filenet-service-prod -o yaml --save-config
kubectl edit deployment/crl-housekeeping-service-prod -o yaml --save-config
kubectl edit deployment/crl-kta-cover-service-prod -o yaml --save-config
kubectl edit deployment/crl-letter-service -o yaml --save-config
kubectl edit deployment/crl-payment-status-service -o yaml --save-config
kubectl edit deployment/crl-micro-insurance-cover-service-prod -o yaml --save-config

oc -n 14031-crl login -u MohamadJaelani api.ocp3.azlife.allianz.co.id:6443 --insecure-skip-tls-verify=true

oc -n 14031-crl login -u MohamadJaelani api.ocp4.azlife.allianz.co.id:6443 --insecure-skip-tls-verify=true

private-docker-registry.nexus02.cdp.prod.aws.allianz.co.id/crl/prod/crl-filenet-service:2.0.0.RELEASE@sha256:209d1fc9f23bda581b83c717d9f517240ee656e64d68db5750ab648b36e68853


kubectl edit deployment/crl-ellise-document-service-prod-aws -o yaml --save-config
kubectl edit deployment/crl-ellise-email-service-prod-aws -o yaml --save-config
kubectl edit deployment/crl-ellise-attunity-service-prod-aws -o yaml --save-config


20220587-25-006
7vcoux_W

-- ocp3 down
kubectl scale deployment/crl-api-gateway-service-prod --replicas=0
kubectl scale deployment/crl-document-service-prod --replicas=0
kubectl scale deployment/crl-filenet-service-prod --replicas=0
kubectl scale deployment/crl-housekeeping-service-prod --replicas=0
kubectl scale deployment/crl-kta-cover-service-prod --replicas=0
kubectl scale deployment/crl-letter-service --replicas=0
kubectl scale deployment/crl-payment-status-service --replicas=0
kubectl scale deployment/crl-micro-insurance-cover-service-prod --replicas=0

-- ocp4 down
kubectl scale deployment/crl-api-gateway-service-prod --replicas=0
kubectl scale deployment/crl-document-service-prod --replicas=0
kubectl scale deployment/crl-filenet-service-prod --replicas=0
kubectl scale deployment/crl-housekeeping-service-prod --replicas=0
kubectl scale deployment/crl-kta-cover-service-prod --replicas=0
kubectl scale deployment/crl-letter-service --replicas=0
kubectl scale deployment/crl-micro-insurance-cover-service-prod --replicas=0

-- ocp3 up
kubectl scale deployment/crl-api-gateway-service-prod --replicas=1
kubectl scale deployment/crl-document-service-prod --replicas=1
kubectl scale deployment/crl-filenet-service-prod --replicas=1
kubectl scale deployment/crl-housekeeping-service-prod --replicas=1
kubectl scale deployment/crl-kta-cover-service-prod --replicas=1
kubectl scale deployment/crl-letter-service --replicas=1
kubectl scale deployment/crl-payment-status-service --replicas=1
kubectl scale deployment/crl-micro-insurance-cover-service-prod --replicas=1

-- ocp4 up
kubectl scale deployment/crl-api-gateway-service-prod --replicas=1
kubectl scale deployment/crl-document-service-prod --replicas=1
kubectl scale deployment/crl-filenet-service-prod --replicas=1
kubectl scale deployment/crl-housekeeping-service-prod --replicas=1
kubectl scale deployment/crl-kta-cover-service-prod --replicas=1
kubectl scale deployment/crl-letter-service --replicas=1
kubectl scale deployment/crl-micro-insurance-cover-service-prod --replicas=1

