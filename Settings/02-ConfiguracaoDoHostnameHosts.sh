#!/bin/bash
# Hosts.allow e Hosts.Deny: formato de arquivos de controle de acesso ao host
#
# Alterando o nome do servidor
sudo vim /etc/hostname
#
# Adicionando as informações de pesquisa de IP e nome no servidor
sudo vim /etc/hosts
#
# Verificando a forma de consulta de nomes no servidor
sudo vim /etc/nsswitch.conf
#
# Configurando a segurança de acesso remoto do servidor
sudo vim /etc/hosts.allow
sudo vim /etc/hosts.deny
#
# Reinicializando o servidor
sudo reboot
#
# Checando as informações do servidor
sudo hostname
sudo hostname -A
sudo hostname -d
#
# Verificando os usuário logados no servidor
sudo w
#
# Verificando o arquivo de log do servidor
sudo cat /var/log/hosts.allow.log
