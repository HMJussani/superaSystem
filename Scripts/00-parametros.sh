#!/bin/bash
#=============================================================================================
#                    VARIÁVEIS GLOBAIS UTILIZADAS EM TODOS OS SCRIPTS                        #
#=============================================================================================
#
# Declarando as variáveis utilizadas na verificação e validação da versão do Ubuntu Server 
#
# Variável da Hora Inicial do Script, utilizada para calcular o tempo de execução do script
# opção do comando date: +%T (Time)
HORAINICIAL=$(date +%T)
#
# Variáveis para validar o ambiente, verificando se o usuário é "Root" e versão do "Ubuntu"
# opções do comando id: -u (user)
# opções do comando: lsb_release: -r (release), -s (short), 
USUARIO=$(id -u)
UBUNTU=$(lsb_release -rs)
#
# Variável do Caminho e Nome do arquivo de Log utilizado em todos os script
# opção da variável de ambiente $0: Nome do comando/script executado
# opção do redirecionador | (piper): Conecta a saída padrão com a entrada padrão de outro comando
# opções do comando cut: -d (delimiter), -f (fields)
LOGSCRIPT="/var/log/$(echo $0 | cut -d'/' -f2)"
#
# Exportando o recurso de Noninteractive do Debconf para não solicitar telas de configuração e
# nenhuma interação durante a instalação ou atualização do sistema via Apt ou Apt-Get. Ele 
# aceita a resposta padrão para todas as perguntas.
export DEBIAN_FRONTEND="noninteractive"
#
#=============================================================================================
#              VARIÁVEIS DE REDE DO SERVIDOR UTILIZADAS EM TODOS OS SCRIPTS                  #
#=============================================================================================
#
# Declarando as variáveis utilizadas nas configurações de Rede do Servidor Ubuntu 
#
# Variável do Usuário padrão utilizado no Servidor Ubuntu 
USUARIODEFAULT="hmjussani"
#
# Variável da Senha padrão utilizado no Servidor Ubuntu 
# OBSERVAÇÃO IMPORTANTE: essa variável será utilizada em outras variáveis 
SENHADEFAULT="hmjussani"
#
# Variável do Nome (Hostname) do Servidor Ubuntu 
NOMESERVER="$(uname -a | cut -d' ' -f2)"
#
# Variável do Nome de Domínio do Servidor Ubuntu 
# OBSERVAÇÃO IMPORTANTE: essa variável será utilizada em outras variáveis 
DOMINIOSERVER="server.intra"
#
# Variável do Nome de Domínio NetBIOS do Servidor Ubuntu 
# OBSERVAÇÃO IMPORTANTE: essa variável será utilizada em outras variáveis 
# opção do redirecionador | (piper): Conecta a saída padrão com a entrada padrão de outro comando
# opções do comando cut: -d (delimiter), -f (fields)
DOMINIONETBIOS="$(echo $DOMINIOSERVER | cut -d'.' -f1)"
#
# Variável do Nome (Hostname) FQDN (Fully Qualified Domain Name) do Servidor Ubuntu 
# OBSERVAÇÃO IMPORTANTE: essa variável será utilizada em outras variáveis 
FQDNSERVER="$NOMESERVER.$DOMINIOSERVER"
#
# Variável do Endereço IPv4 principal (padrão) do Servidor Ubuntu 
IPV4SERVER="192.168.100.126" #Rede Corporativa
#IPV4SERVER="192.168.10.119" #Rede RMA
#
# Variável do Nome da Interface Lógica do Servidor Ubuntu Server 
# CUIDADO!!! o nome da interface de rede pode mudar dependendo da instalação do Ubuntu Server,
# verificar o nome da interface com o comando: ip address show e mudar a variável INTERFACE com
# o nome correspondente.
INTERFACE="enp0s3"
#INTERFACE="enp3s0"
#
NETPLAN="/etc/netplan/00-installer-config.yaml"
#
#=============================================================================================
#                        VARIÁVEIS UTILIZADAS NO SCRIPT: 01-openssh.sh                       #
#=============================================================================================
#
# Arquivos de configuração (conf) do Serviço de Rede OpenSSH utilizados nesse script
# 01. /etc/netplan/00-installer-config.yaml = arquivo de configuração da placa de rede
# 02. /etc/hostname = arquivo de configuração do Nome FQDN do Servidor
# 03. /etc/hosts = arquivo de configuração da pesquisa estática para nomes de host local
# 04. /etc/nsswitch.conf = arquivo de configuração do switch de serviço de nomes de serviço
# 05. /etc/ssh/sshd_config = arquivo de configuração do Servidor OpenSSH
# 06. /etc/hosts.allow = arquivo de configuração de liberação de hosts por serviço de rede
# 07. /etc/hosts.deny = arquivo de configuração de negação de hosts por serviço de rede
# 08. /etc/issue.net = arquivo de configuração do Banner utilizado pelo OpenSSH no login
# 09. /etc/default/shellinabox = arquivo de configuração da aplicação Shell-In-a-Box
# 10. /etc/neofetch/config.conf = arquivo de configuração da aplicação Neofetch
# 11. /etc/cron.d/neofetch-cron = arquivo de atualização do Banner Motd o Neofetch
# 12. /etc/motd = arquivo de mensagem do dia gerado pelo Neofetch utilizando o CRON
# 13. /etc/rsyslog.d/50-default.conf = arquivo de configuração do Syslog/Rsyslog
#
# Arquivos de monitoramento (log) do Serviço de Rede OpenSSH Server utilizados nesse script
# 01. sudo systemctl status ssh = status do serviço do OpenSSH
# 02. sudo journalctl -t sshd = todas as mensagens referente ao serviço do OpenSSH
# 03. tail -f /var/log/syslog | grep sshd = filtrando as mensagens do serviço do OpenSSH
# 04. tail -f /var/log/auth.log | grep ssh = filtrando as mensagens de autenticação do OpenSSH
# 05. tail -f /var/log/tcpwrappers-allow-ssh.log = filtrando as conexões permitidas do OpenSSH
# 06. tail -f /var/log/tcpwrappers-deny.log = filtrando as conexões negadas do OpenSSH
# 07. tail -f /var/log/cron.log = filtrando as mensagens do serviço do CRON
#
# Variável das dependências do laço de loop do OpenSSH Server
SSHDEP="openssh-server openssh-client"
#
# Variável de instalação dos softwares extras do OpenSSH Server
SSHINSTALL="net-tools traceroute ipcalc nmap tree pwgen neofetch shellinabox"
#
# Variável da porta de conexão padrão do OpenSSH Server
PORTSSH="22"
#
# Variável da porta de conexão padrão do Shell-In-a-Box
PORTSHELLINABOX="4200"

#=============================================================================================
#                       VARIÁVEIS UTILIZADAS NO SCRIPT: 05-ntp.sh                            #
#=============================================================================================
# Variável de sincronização do NTP Server com o Site ntp.br
NTPSERVER="a.st1.ntp.br"
#
# Variável do Zona de Horário do Ubuntu Server
TIMEZONE="America/Sao_Paulo"
#
# Variável de Configuração do Locale do Ubuntu Server
LOCALE="pt_BR.UTF-8"
#
# Variável das dependências do laço de loop do NTP Server
NTPDEP="isc-dhcp-server"
#
# Variável de instalação do serviço de rede NTP Server e Client
NTPINSTALL="ntp ntpdate"
#
# Variável da porta de conexão padrão do NTP Server
PORTNTP="123"

#=============================================================================================
#                        VARIÁVEIS UTILIZADAS NO SCRIPT: 01-mysql.sh                          #
#=============================================================================================
# 01. /etc/mysql/mysql.conf.d/mysqld.cnf = arquivo de configuração do Servidor MySQL
# 02. sudo systemctl status mysql = status do serviço do Oracle MySQL
# 03. tail -f /var/log/mysql/* = vários arquivos de Log's do serviço do MySQL
# 04. tail -f /var/log/tcpwrappers-allow-mysql.log = filtrando as conexões permitidas do MySQL
# 05. tail -f /var/log/tcpwrappers-deny.log = filtrando as conexões negadas do MySQL
# Variável do usuário padrão do MySQL (Root do Mysql, diferente do Root do GNU/Linux)
USERMYSQL="root"
#
# Variáveis da senha e confirmação da senha do usuário Root do Mysql 
SENHAMYSQL="Mysql@2023"
AGAINMYSQL=$SENHAMYSQL
#
# Variáveis de configuração e liberação da conexão remota para o usuário Root do MySQL
# opções do comando CREATE: create (criar), user (criação de usuário), user@'%' (usuário @ localhost), 
# identified by (identificado por - senha do usuário)
# opções do comando GRANT: grant (permissão), all (todos privilégios), on (em ou na | banco ou 
# tabela), *.* (todos os bancos/tabelas) to (para), user@'%' (usuário @ localhost), 
# opções do comando ALTER: alter (alterar, user (alteração de usuário), root@localhost (usuário @localhost),
# identified by (identificado por - senha do usuário), with (com suporte)
# opção do comando FLUSH: privileges (recarregar as permissões de privilégios)
CREATEUSER="CREATE USER '$USERMYSQL'@'%' IDENTIFIED BY '$SENHAMYSQL';"
ALTERUSER="ALTER USER '$USERMYSQL'@'localhost' IDENTIFIED WITH mysql_native_password BY '$SENHAMYSQL';"
GRANTALL="GRANT ALL ON *.* TO '$USERMYSQL'@'localhost';"
FLUSH="FLUSH PRIVILEGES;"
SELECTUSER="SELECT user,host FROM user;"
#
# Variável do Endereço IPv4 acesso remoto Mysql
IPV4DEV="192.168.100.101" #IP maquina virtual Supera
# Variáveis de configuração e liberação da conexão remota para o usuário Root do MySQL
CREATEUSER="CREATE USER '$USERMYSQL'@'%' IDENTIFIED BY '$SENHAMYSQL';"
ALTERUSER="ALTER USER '$USERMYSQL'@'$IPV4DEV' IDENTIFIED WITH mysql_native_password BY '$SENHAMYSQL';"
GRANTALL="GRANT ALL ON *.* TO '$USERMYSQL'@'$IPV4DEV';"
FLUSH="FLUSH PRIVILEGES;"
SELECTUSER="SELECT user,host FROM user;"
#
# Variável de configuração do usuário padrão de administração do PhpMyAdmin (Root do MySQL)
ADMINUSER=$USERMYSQL
#
# Variáveis da senha do usuário Root do MySQL e senha de administração o PhpMyAdmin
ADMIN_PASS=$SENHAMYSQL
APP_PASSWORD=$SENHAMYSQL
APP_PASS=$SENHAMYSQL
# Variável da porta de conexão padrão do MySQL Server
PORTMYSQL="3306"


#=============================================================================================
#                         VARIÁVEIS UTILIZADAS NO SCRIPT:        02-Supera                   #
#=============================================================================================

CREATE_DATABASE_SUPERA="CREATE DATABASE dbSupera CHARACTER SET utf8mb4 collate utf8mb4_bin;"
CREATE_USER_DATABASE_SUPERA="CREATE USER 'desenvolvimento'@'localhost' IDENTIFIED BY 'Desenvolvimento@1';"
GRANT_DATABASE_SUPERA="GRANT USAGE ON *.* TO 'desenvolvimento'@'localhost';"
GRANT_ALL_DATABASE_SUPERA="GRANT ALL PRIVILEGES ON dbSupera.* TO 'desenvolvimento'@'localhost';"
CREATE_USER_DATABASE_SUPERA="CREATE USER 'desenvolvimento'@'$IPV4DEV' IDENTIFIED BY 'Desenvolvimento@1';"
GRANT_DATABASE_SUPERA="GRANT USAGE ON *.* TO 'desenvolvimento'@'$IPV4DEV';"
GRANT_ALL_DATABASE_SUPERA="GRANT ALL PRIVILEGES ON dbSupera.* TO 'desenvolvimento'@'$IPV4DEV';"
SET_GLOBAL_SUPERA="SET GLOBAL log_bin_trust_function_creators = 1;"
FLUSH_SUPERA="FLUSH PRIVILEGES;"

#=============================================================================================
#                                                                                            #
#                     VARIÁVEIS UTILIZADAS NO SCRIPT: 01-openssl-mysql.sh                    #
#                                                                                            #
#=============================================================================================
#
# Arquivos de configuração (conf) da Unidade Certificado Raiz Confiável do OpenSSL
# 01. /etc/ssl/index.txt = arquivo de configuração da base de dados do OpenSSL
# 02. /etc/ssl/index.txt.attr = arquivo de configuração dos atributos da base de dados do OpenSSL
# 03. /etc/ssl/serial = arquivo de configuração da geração serial dos certificados
# 04. /etc/ssl/ca.conf = arquivo de configuração de Unidade Certificadora Raiz Confiável da CA
#
# Arquivos de configuração (conf) da Geração do Certificado do Apache2 Server
# 01. /etc/ssl/apache2.conf = arquivo de configuração do certificado do Apache2
# 02. /etc/apache2/sites-available/default-ssl.conf = arquivo de configuração do HTTPS do Apache2
#
# Arquivos de configuração (conf) da Geração do Certificado do VSFTPd Server
# 01. /etc/ssl/vsftpd.conf = arquivo de configuração do certificado do VSFTPd
# 02. /etc/vsftpd.conf = arquivo de configuração do VSFTPd Server
#
# Arquivos de configuração (conf) da Geração do Certificado do Tomcat9 Server
# 01. /etc/ssl/tomcat9.conf = arquivo de configuração do certificado do Tomcat9
# 02. /etc/tomcat9/server.xml = arquivo de configuração do Tomcat9 Server
#
# Arquivos de configuração (conf) da Geração do Certificado do MySQL Server
# 01. /etc/ssl/mysql.conf = arquivo de configuração do certificado do MySQL
# 02. /etc/mysql/mysql.conf.d/mysqld.cnf = arquivo de configuração do MySQL Server
# 03. /etc/mysql/mysql.conf.d/mysql.cnf = arquivo de configuração do MySQL Client
#
# Arquivos de configuração (conf) da Geração do Certificado do Grafana Server
# 01. /etc/ssl/grafana.conf = arquivo de configuração do certificado do Grafana
# 02. /etc/grafana/grafana-server = arquivo de configuração do Grafana Server
#
# Arquivos de monitoramento (log) do Serviço de Certificado OpenSSL utilizados nesse script
# 01. cat /etc/ssl/index.txt = arquivo de configuração da base de dados do OpenSSL
# 02. cat /etc/ssl/index.txt.attr = arquivo de configuração dos atributos da base de dados do OpenSSL
# 03. cat /etc/ssl/serial = arquivo de configuração da geração serial dos certificados
# 04. ls -lh /etc/ssl/ = vários arquivos de configuração dos certificados do OpenSSL
# 05. ls -lh /etc/ssl/certs/pti-ca.pem = unidade certificada raiz confiável do OpenSSL
#
# Variáveis utilizadas na geração das chaves privadas/públicas dos certificados do OpenSSL
#
# Variável da senha utilizada na geração das chaves privadas/públicas da CA e dos certificados
PASSPHRASE=$USUARIODEFAULT
#
# Variável do tipo de criptografia da chave privada com as opções de: -aes128, -aes192, -aes256, 
# -camellia128, -camellia192, -camellia256, -des, -des3 ou -idea, padrão utilizado: -aes256
CRIPTOKEY="aes256" 
#
# Variável do tamanho da chave privada utilizada em todas as configurações dos certificados,
# opções de: 1024, 2048, 3072 ou 4096, padrão utilizado: 2048
BITS="2048" 
#
# Variável da assinatura da chave de criptografia privada com as opções de: md5, -sha1, sha224, 
# sha256, sha384 ou sha512, padrão utilizado: sha256
CRIPTOCERT="sha256" 
#
# Variável do diretório de download da CA para instalação nos Desktops Windows e GNU/Linux
DOWNLOADCERT="/home/$USUARIODEFAULT/"
#
# Variável das dependências do laço de loop do OpenSSL
SSLDEPCA="openssl bind9 apache2"


#=============================================================================================
#                         VARIÁVEIS UTILIZADAS NO SCRIPT: 23-zabbix.sh                       #
#=============================================================================================
#
# Arquivos de configuração (conf) do sistema Zabbix Server utilizados nesse script
# 01. /etc/zabbix/zabbix_server.conf = arquivo de configuração do serviço do Zabbix Server
# 02. /etc/zabbix/apache.conf = arquivo de configuração do Virtual host e PHP do Zabbix Server
# 03. /etc/zabbix/zabbix_agentd.conf = arquivo de configuração do serviço do Zabbix Agent
#
# Arquivos de monitoramento (log) do Serviço do Zabbix Server utilizados nesse script
# 01. sudo systemctl status zabbix-server = status do serviço do Zabbix Server
# 02. sudo systemctl status zabbix-agent = status do serviço do Zabbix Agent
# 03. tail -f /var/log/zabbix/zabbix_server.log = arquivo de Log do serviço do Zabbix Server
# 04. tail -f /var/log/zabbix/zabbix_agentd.log = arquivo de Log do serviço do Zabbix Agent
# 05. tail -f /var/log/syslog | grep -i zabbix = filtrando as mensagens do serviço do Zabbix
#
# Declarando as variáveis utilizadas nas configurações do sistema de monitoramento Zabbix Server
#
# Variável de download do Repositório do Zabbix Server (Link atualizado no dia 25/01/2023)
ZABBIXIREP="wget https://repo.zabbix.com/zabbix/6.4/ubuntu/pool/main/z/zabbix-release/zabbix-release_6.4-1+ubuntu20.04_all.deb"
#
# Variável de instalação do Zabbix Server e suas Dependências.
ZABBIXINSTALL="install zabbix-server-mysql zabbix-frontend-php zabbix-apache-conf zabbix-agent \
zabbix-sql-scripts traceroute nmap snmp snmpd snmp-mibs-downloader"
#
# Declarando as variáveis para criação da Base de Dados do Zabbix Server
# opção do comando create: create (criação), database (base de dados), base (banco de dados), 
# character set (conjunto de caracteres), collate (comparar)
# opção do comando create: create (criação), user (usuário), identified by (identificado por - 
# senha do usuário), password (senha)
# opção do comando grant: grant (permissão), usage (uso em | uso na), *.* (todos os bancos/tabelas),
# to (para), user (usuário), identified by (identificado por - senha do usuário), password (senha)
# opões do comando GRANT: grant (permissão), all (todos privilégios), on (em ou na | banco ou tabela), 
# *.* (todos os bancos/tabelas) to (para), user@'%' (usuário @ localhost), identified by (identificado 
# por - senha do usuário), password (senha)
# opção do comando FLUSH: flush (atualizar), privileges (recarregar as permissões)
#
# OBSERVAÇÃO: NO SCRIPT: 20-ZABBIX.SH É UTILIZADO AS VARIÁVEIS DO MYSQL DE USUÁRIO E SENHA
# DO ROOT DO MYSQL CONFIGURADAS NO BLOCO DAS LINHAS: 366 até 371, VARIÁVEIS UTILIZADAS NO SCRIPT: 
# 07-lamp.sh LINHAS: 261 até 262
CREATE_DATABASE_ZABBIX="CREATE DATABASE zabbix CHARACTER SET utf8mb4 collate utf8mb4_bin;"
CREATE_USER_DATABASE_ZABBIX="CREATE USER 'zabbix'@'localhost' IDENTIFIED BY 'zabbix';"
GRANT_DATABASE_ZABBIX="GRANT USAGE ON *.* TO 'zabbix'@'localhost';"
GRANT_ALL_DATABASE_ZABBIX="GRANT ALL PRIVILEGES ON zabbix.* TO 'zabbix'@'localhost';"
SET_GLOBAL_ZABBIX="SET GLOBAL log_bin_trust_function_creators = 1;"
FLUSH_ZABBIX="FLUSH PRIVILEGES;"
CREATE_TABLE_ZABBIX="/usr/share/zabbix-sql-scripts/mysql/server.sql.gz"
#
# Variável das dependências do laço de loop do Zabbix Server
ZABBIXDEP="mysql-server mysql-common apache2 php bind9 apt-transport-https software-properties-common"
#
# Variáveis das portas de conexão padrão do Zabbix Server e Agent
PORTZABBIX1="10050"
PORTZABBIX2="10051"