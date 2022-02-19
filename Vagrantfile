# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/focal64"

  config.vm.define "jenkins_master" do |node|
    node.vm.hostname = "jenkins.master"
    node.vm.network "forwarded_port", guest: 8080, host: 8080
    node.vm.provision "shell" do |shell|
      shell.path = "provision.sh"
    end
  end

  config.vm.provider "virtualbox" do |vb|
    vb.memory = "2048"
  end

end
