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
- nginx default dir ```/etc/nginx/sites-available/```

### how add localhost to nginx reverse proxy
```sudo vim /etc/nginx/sites-available/default```
- modify this file or create one if not exist
  ```
  server {
          listen 80; # Or any other port Nginx should listen on
          server_name your_domain_or_localhost_alias; # e.g., example.localhost or localhost
  
          location /your_app_path/ { # e.g., /api/ or /
              proxy_pass http://localhost:PORT_OF_YOUR_SERVICE/; # e.g., http://localhost:8080/
              proxy_set_header Host $host;
              proxy_set_header X-Real-IP $remote_addr;
              proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
          }
  
          # Other location blocks or configurations can go here
      }
  ```
- verify the syntax
  ```sudo nginx -t```
- if ok then reaload/restart nginx service
  ```sudo systemctl reload nginx```
