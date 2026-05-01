# the steps installing kubernetes in ubuntu
## for master and 2 worker nodes (all command running in su)
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
    containerd config default > /etc/containerd/config.toml
    ```
    make sure that container is using ```systemd``` by checked ```systemd_cgroup``` value using command
    ```
    cat /etc/containerd/config.toml | grep system
    ```
    if its ```false``` that indicates the containerd does not using ```systemd```. change the value to ```true``` by edit the configuration file using command
    ```
    vi /etc/containerd/config.toml
    ```
    find text ```system``` by ```/system``` then change ```systemd_cgroup=true``` save it using ```:wq```

    
  8. 
