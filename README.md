# Walisonu app

This project was originally deployed to AWS

## Authors

* **Vladislav Ogorodnik**
* **Egle Palk**
* **Kauri Riivak**

## Technologies

- AWS EC2
- Gitlab-Runner

## AWS Installation guide

**Need to have AWS EC2 instance (We use: t2.micro (free tier eligible))**
Open AWS EC2 instance in terminal and follow the steps

**Setup Gitlab-Runner**
1. Download Gitlab-Runner binaries for your system:
>sudo curl -L --output /usr/local/bin/gitlab-runner https://gitlab-runner-downloads.s3.amazonaws.com/latest/binaries/gitlab-runner-linux-amd64

2. Give it permissions to execute:
> sudo chmod +x /usr/local/bin/gitlab-runner

3. Create a GitLab CI user:
>sudo useradd --comment 'GitLab Runner' --create-home gitlab-runner --shell /bin/bash

4. Install and run as service:
>sudo gitlab-runner install --user=gitlab-runner --working-directory=/home/gitlab-runner
sudo gitlab-runner start

**Registering Runners**

1. Run the following command:
>sudo gitlab-runner register

2. Enter your GitLab instance URL:
>https://gitlab.cs.ttu.ee/

3. Enter the token you obtained to register the Runner:
>P3urYm2XY76i8uaeBSpo

4.Enter a description for the Runner, you can change this later in GitLab’s UI:
>walisonu

5.Enter the tags associated with the Runner, you can change this later in GitLab’s UI:
>words

6.Enter the Runner executor:
>shell

7. Install Java
>sudo apt-get install openjdk-11-jre openjdk-11-jdk
 sudo apt-get update


**Configure gitlab**

- 1. Create .gitlab-ci.yml in project root
- 2. Push it to gitlab

**Configure Backend as a Service**

cd /etc/systemd/system
sudo touch walisonu.service

[Unit]
Description=walisonu backend service
After=network.target

[Service]
Type=simple
User=root
WorkingDirectory=/home/gitlab-runner/api-deployment
ExecStart=/usr/bin/java -jar java-walisonu.jar
Restart=on-abort

[Install]
WantedBy=multi-user.target

**Restart backend service after build**

Allow gitlab-runner to use sudo when restarting heroes service
>sudo visudo
gitlab-runner ALL = NOPASSWD: /usr/sbin/service walisonu *

**Enable service**

sudo systemctl daemon-reload
sudo systemctl enable walisonu
sudo service walisonu restart
 

### **Congrats you're done!**




