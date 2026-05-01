# the steps installing kubernetes in ubuntu
## for master and 2 worker nodes (all command running in su and using termius)
  1. get the lattest update ```apt-get update```
  2. install ```apt-transport-https```, but 1rstly check the latest version using this command ```apt-cache policy apt-transport-https``` then install using ```apt install -y apt-transport-https=2.0.11```.
  3. install ```ca-certificates```, but 1stly check the latest version or installed version using this command ```apt-cache policy ca-certificates``` mostly the certificate is installed but if the value of Installed and Candidate is different, the installed one is not the latest, then install using command ```apt install -y ca-certificates=20240203~20.04.1```.
  4. disalbed ```swap memory``` but before do that, please make sure the swap memory is active using command ```cat /proc/swaps``` if swap active then disabled it using ```swapoff -a```.
  5. remove swap memory to avoiding it reactivated when system reboot using command ```cat /proc/swaps``` then remark line ```/swap.img       none    swap    sw      0       0``` by adding ```#``` in front of the line by edit the file using ```vi /etc/fstab``` to be ```#/swap.img      none    swap    sw      0       0```.
  6. install containerd by creating file ```containerd.conf``` using command
     ```
     tee /etc/modules-load.d/containerd.conf << EOF
     > overlay
     > br_netfilter
     > EOF
     ```
     the file would be loaded automatically when system reboot. to verify the file is created using command ```cat /etc/modules-load.d/containerd.conf```. to load the file manualy using command ```modprobe overlay``` then ```modprobe br_netfilter``` so no need to restart the system. then download containerd using command
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
      ~~make sure that container is using ```systemd``` by checking ```systemd_cgroup``` value using command~~
      ```
      cat /etc/containerd/config.toml | grep system
      ```
      ~~if its ```false``` that indicates the containerd does not using ```systemd```. change the value to ```true``` by edit the configuration file using command~~
      ```
      vi /etc/containerd/config.toml
      ```
      ~~find text ```system``` by ```/system``` then change ```systemd_cgroup=true``` save it using ```:wq```~~
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
  8. set IP Forwarding by creating configuration file using this command
     ```
     tee /etc/sysctl.d/99-kubernetes-cri.conf << EOF
     > net.bridge.bridge-nf-call-ip6tables=1
     > net.bridge.bridge-nf-call-iptables=1
     > net.ipv4.ip_forward=1
     > EOF
     ```
     verify the file by this command ```cat /etc/sysctl.d/99-kubernetes-cri.conf```
     then apply the ```sysctl``` file using command ```sysctl -p /etc/sysctl.d/99-kubernetes-cri.conf```
  9. install ```runc``` by download the file using command
      ```
      wget https://github.com/opencontainers/runc/releases/download/v1.1.9/runc.amd64
      ```
      install using command
     ```
     install -m 755 runc.amd64 /usr/local/sbin/runc
     ```
  10. install ```crictl``` by download the file using command
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
    ----- continue to page 26----
