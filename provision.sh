#!/bin/bash

# Jenkins pre-setup
apt-get update --assume-no
apt-get install -y jenkins  openjdk-11-jre-headless vim
which java

# LTS Installation: https://www.jenkins.io/doc/book/installing/linux/#debianubuntu
curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io.key | tee \
  /usr/share/keyrings/jenkins-keyring.asc > /dev/null
echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
  https://pkg.jenkins.io/debian-stable binary/ | tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null
apt-get install -y jenkins

# Enable docker builds
apt install docker.io
chmod 777 /var/run/docker.sock
