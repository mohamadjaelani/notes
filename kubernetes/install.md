# the steps installing kubernetes in ubuntu
## for master and worker nodes (all command running in su and using termius)
  1. get the lattest update ```apt-get update```
  2. install ```apt-transport-https```, but 1stly check the latest version using this command ```apt-cache policy apt-transport-https``` then install using
     ```
     apt install -y apt-transport-https=2.0.11
     ```
  3. install ```ca-certificates```, but 1stly check the latest version or installed version using this command ```apt-cache policy ca-certificates``` mostly the certificate is installed but if the value of Installed and Candidate is different, the installed one is not the latest, then install using command
     ```
     apt install -y ca-certificates=20240203~20.04.1
     ```
  4. disalbed ```swap memory``` but before do that, please make sure the swap memory is active using command ```cat /proc/swaps``` if swap active then disabled it using
     ```
     swapoff -a
     ```
  5. remove swap memory to avoiding it reactivated when system reboot using command ```cat /proc/swaps``` then remark line
     ```
     /swap.img       none    swap    sw      0       0
     ```
     by adding ```#``` in front of the line by edit the file using ```vi /etc/fstab``` to be
     ```
     #/swap.img      none    swap    sw      0       0
     ```
  6. install containerd by creating file ```containerd.conf``` using command
     ```
     tee /etc/modules-load.d/containerd.conf << EOF
     overlay
     br_netfilter
     EOF
     ```
     the file would be loaded automatically when system reboot. to verify the file is created using command
     ```
     cat /etc/modules-load.d/containerd.conf
     ```
     to load the file manualy using command
     ```
     modprobe overlay
     modprobe br_netfilter
     ```
      so no need to restart the system. then download containerd using command
     ```
     wget https://github.com/containerd/containerd/releases/download/v1.7.5/containerd-1.7.5-linux-amd64.tar.gz
     ```
      then extract ```tar.gz``` file using command
     ```
     tar Cxzvf /usr/local containerd-1.7.5-linux-amd64.tar.gz
     ```
     create new directory for containerd
     ```
     mkdir -p /etc/containerd
     ```
     generate configuration file in the containerd directory
     ```
     containerd config default | tee /etc/containerd/config.toml
     
     ```
     ```
     sed -i 's/SystemdCgroup = false/SystemdCgroup = true/g' /etc/containerd/config.toml
     ```
     finally install ```containerd.service``` by downloading the file using
     ```
     wget https://raw.githubusercontent.com/containerd/containerd/main/containerd.service
     ```
     then move the file to ```/etc/systemd/system/``` using this command
     ```
     mv containerd.service /etc/systemd/system/
     ```
     reload the daemon
     ```
     systemctl daemon-reload
     ```
     restart the containerd service
     ```
     systemctl restart containerd
     ```
     enabled the service so it will be running if the system reboot
     ```
     systemctl enable containerd
     ```
  7. set IP Forwarding by creating configuration file using this command
     ```
     tee /etc/sysctl.d/99-kubernetes-cri.conf << EOF
     net.bridge.bridge-nf-call-ip6tables=1
     net.bridge.bridge-nf-call-iptables=1
     net.ipv4.ip_forward=1
     EOF
     ```
     verify the file by this command
     ```
     cat /etc/sysctl.d/99-kubernetes-cri.conf
     ```
     then apply the ```sysctl``` file using command
     ```
     sysctl -p /etc/sysctl.d/99-kubernetes-cri.conf
     ```
  8. install ```runc``` by download the file using command
      ```
      wget https://github.com/opencontainers/runc/releases/download/v1.1.9/runc.amd64
      ```
      install using command
     ```
     install -m 755 runc.amd64 /usr/local/sbin/runc
     ```
  9. install ```crictl``` by download the file using command
      ```
      wget https://github.com/kubernetes-sigs/cri-tools/releases/download/v1.28.0/crictl-v1.28.0-linux-amd64.tar.gz
      ```
      extract the compressed file
      ```
      tar -zxvf crictl-v1.28.0-linux-amd64.tar.gz
      ```
      then install it
      ```
      install -m 755 crictl /usr/local/bin/crictl
      ```
      repointing crctl endpoint to containerd
      ```
      crictl config runtime-endpoint unix:///var/run/containerd/containerd.sock
      ```
      check the version
      ```
      crictl --runtime-endpoint=unix:///run/containerd/containerd.sock version
      ```
  10. Add kubernetes repository
      download signing public key for kubernetes respository and instal gpg keyrings
      create directory
      ```
      sudo mkdir -p -m 755 /etc/apt/keyrings
      ```
      ```
      curl -fsSL https://pkgs.k8s.io/core:/stable:/v1.28/deb/Release.key | sudo gpg --dearmor -o /etc/apt/keyrings/kubernetes-apt-keyring.gpg
      ```
      add kubernetes repo to ubuntu repo
      ```
      echo 'deb [signed-by=/etc/apt/keyrings/kubernetes-apt-keyring.gpg] https://pkgs.k8s.io/core:/stable:/v1.28/deb/ /' | sudo tee /etc/apt/sources.list.d/kubernetes.list
      ```
      do update ```apt-get update``` just to make sure repo has kubernetes repos working fine.
  12. Install kubelet, kubadm and kubectl
      check version
      ```
      apt-cache policy kubeadm
      ```
      then install kubeadm
      ```
      apt install -y kubeadm=1.28.15-1.1
      ```
      then hold update for kubelet, kubeadm and kubectl
      ```
      apt-mark hold kubelet kubeadm kubectl
      ```
      activate kubelet, to automatically running after system boot
      ```
      systemctl enable --now kubelet
      ```
      check version
      ```
      kubectl version --client --output=yaml && kubeadm version --output=yaml
      ```

## Only Master
  install kubernetes for Master
  ```
  kubeadm init
  ```
  or using below config
  ```
  vi kubeadm-config.yaml

  apiVersion: kubeadm.k8s.io/v1beta3
  kind: InitConfiguration
  localAPIEndpoint:
    advertiseAddress: "192.168.1.20"
    bindPort: 6443
  nodeRegistration:
    criSocket: unix:///run/containerd/containerd.sock
    taint:
      - efffect: PreferNoSchedule
        key: node-role.kubernetes.io/master
  ---
  apiVersion: kubeadm.k8s.io/v1beta3
  kind: ClusterConfiguration
  kubernetesVersion: 1.28.1
  networking:
    podSubnet: "192.168.0.0/16"
    serviceSubnet: "192.168.0.0/12"
  apiServer:
    extraArgs:
      authorization-mode: "Node,RBAC"
    timeoutForControlPlane: 4m0s
  ---
  apiVersion: kubelet.config.k8s.io/v1beta1
  kind: KubeletConfiguration
  cgroupDriver: systemd
  failSwapOn: false
  ```
  
  if you found error use this command
  ```
  kubeadm reset cleanup-node
  ```
  then run ```kubeadm init``` again. you'll get join token as below
  ```
  kubeadm join 192.168.1.23:6443 --token g2jump.kww06e695f6mbb1c --discovery-token-ca-cert-hash sha256:567216c5e582c27cfc515ed27e0e1887ca45ccf6b6a74dac829369b1a1471f5e 
  ```
  if you forget to copy it, then you can get join token by running below command
  ```
  kubeadm token create --print-join-command
  ```
  then execute join token in worker node
## that's it
