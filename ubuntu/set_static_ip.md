## set static ip for ubuntu
1. show ip details ```ip r```
2. edit file ```vi /etc/netplan/file_netplan_xxx.yaml``` and edit the file put value based on your config
   ```
   network:
    version: 2
    renderer: networkd
    ethernets:
      ens33:
        dhcp4: false
        addresses:
          - 192.168.1.20
        routes:
          - to: default
            via: 192.168.1.1
        nameservers:
          addresses: [1.1.1.1, 8.8.8.8]
    ```
