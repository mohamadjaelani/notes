###To check the status from the command line:
```
sudo systemctl status gdm
```

To stop:
```
sudo systemctl stop gdm
```

To start:
```
sudo systemctl start gdm
```

To disable (prevent loading at system startup):
```
sudo systemctl disable gdm
```

To enable (loading at system startup):
```
sudo systemctl enable gdm
```

### Listing all active and loaded services:
```
systemctl list-units --type=service
```

###Listing all services (including inactive and failed):
```
systemctl list-units --type=service --all
```
