### NGINX Intall and preparation
- Install NGINX
  ```
  sudo apt update
  sudo apt install nginx -y
  ```
- if using UFW (Uncomplicated Firewall) then Adjust Firewall (UFW)
  ```sudo ufw app list```
- Allow the appropriate profile(s) based on your needs. For basic HTTP, use:
  ```sudo ufw allow 'Nginx HTTP'```
- then, enable UFW if it's not already enabled
  ```sudo ufw enable```
- Verify Nginx Installation
  ```sudo systemctl status nginx```
### Basic Nginx Management Commands:
- Stop Nginx: ```sudo systemctl stop nginx```
- Start Nginx: ```sudo systemctl start nginx```
- Restart Nginx: ```sudo systemctl restart nginx```
- Reload Nginx configurations (without stopping): ```sudo systemctl reload nginx```
- Enable Nginx to start automatically on boot: ```sudo systemctl enable nginx```
- Disable Nginx from starting automatically on boot: ```sudo systemctl disable nginx```
