#!/bin/bash

sudo apt update
#
# List é utilizado para listar todos os software que serão atualizados no sistema.
sudo apt list --upgradable
#
# Upgrade é utilizado para instalar atualizações disponíveis de todos os pacotes atualmente 
# instalados no sistema a partir das fontes configuradas via sources.list
sudo apt upgrade
#
# Dist-Upgrade além de executar a função de atualização, também lida de forma inteligente 
# com as novas dependências das novas versões de pacotes
sudo apt dist-upgrade
#
# Full-Upgrade executa a função de atualização, mas removerá os pacotes atualmente 
# instalados se isso for necessário para atualizar o sistema como um todo
sudo apt full-upgrade
#
# Autoremove é utilizado para remover pacotes que foram instalados automaticamente para 
# satisfazer dependências de outros pacotes e agora não são mais necessários, pois as 
# dependências foram alteradas ou os pacotes que precisavam deles foram removidos nesse 
# meio tempo.
sudo apt autoremove
#
# Autoclean como Clean, o autoclean limpa o repositório local de arquivos de pacotes 
# recuperados. A diferença é que ele remove apenas arquivos de pacotes que não podem 
# mais ser baixados e são inúteis.
sudo apt autoclean
#
# Clean limpa o repositório local de arquivos de pacotes recuperados
sudo apt clean
#
# Analisando o conteúdo da arquivo OS-Release
sudo cat /etc/os-release
#
# Analisando o conteúdo do arquivo lsb-release
sudo cat /etc/lsb-release