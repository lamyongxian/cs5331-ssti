# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|

  config.vm.define "serverssti" do |cfg|
    cfg.vm.box = "ubuntu/focal64"
    cfg.vm.hostname = "server"
    cfg.vm.boot_timeout = 1200
    cfg.vm.network :private_network, ip: "192.168.56.10", gateway: "192.168.56.1", dns: "8.8.8.8"
    cfg.vm.provision "shell", path: "server-config.sh", privileged: true
    cfg.vm.provider "virtualbox" do |vb, override|
      vb.gui = true
      vb.name = "serverssti"
      vb.customize ["modifyvm", :id, "--memory", 4096]
      vb.customize ["modifyvm", :id, "--cpus", 2]
      vb.customize ["modifyvm", :id, "--vram", "32"]
      vb.customize ["modifyvm", :id, "--nicpromisc2", "allow-all"]
      vb.customize ["modifyvm", :id, "--clipboard", "bidirectional"]
      vb.customize ["modifyvm", :id, "--natdnshostresolver1", "on"]
      vb.customize ["setextradata", "global", "GUI/SuppressMessages", "all" ]
    end
  end

  config.vm.define "attackerssti" do |cfg|
    cfg.vm.box = "kalilinux/rolling"
    cfg.vm.hostname = "attacker"
    cfg.vm.boot_timeout = 1200
    cfg.vm.network :private_network, ip: "192.168.56.100", gateway: "192.168.56.1", dns: "8.8.8.8"
    cfg.vm.provision "shell", path: "attacker-config.sh", privileged: true
    cfg.vm.provider "virtualbox" do |vb, override|
      vb.gui = true
      vb.name = "attackerssti"
      vb.customize ["modifyvm", :id, "--memory", 2048]
      vb.customize ["modifyvm", :id, "--cpus", 2]
      vb.customize ["modifyvm", :id, "--vram", "64"]
      vb.customize ["modifyvm", :id, "--nicpromisc2", "allow-all"]
      vb.customize ["modifyvm", :id, "--clipboard", "bidirectional"]
      vb.customize ["modifyvm", :id, "--natdnshostresolver1", "on"]
      vb.customize ["setextradata", "global", "GUI/SuppressMessages", "all" ]
    end
  end
end
