
# Instalando o SGBD (Sistema de Gerenciamento de Banco de Dados) MySQL ou MariaDB
sudo apt update && sudo apt install mysql-server mysql-client mysql-common
sudo apt update && sudo apt install mariadb-server mariadb-client mariadb-common
#
# Gerenciadores Gráficos do SGBD MySQL ou MariaDB
sudo apt update && sudo apt install mysql-workbench
sudo apt update && sudo apt install emma
sudo apt update && sudo apt install phpmyadmin (precisa do Apache2 e PHP)
#
# Aplicando as políticas de segurança no SGDB MySQL ou MariaDB
#
# Políticas de Segurança do MySQL
sudo mysql_secure_installation
1. Connecting to MySQL using a blank password (Press y|Y for Yes, any other key for No:) <Enter>
2. New password root: vaamonde <Enter>
3. Re-enter new password root: vaamonde <Enter>
4. Remove anonymous users? (Press y|Y for Yes, any other key for No:) y <Enter>
5. Disallow root login remotely (Press y|Y for Yes, any other key for No:) <Enter>
6. Remove test database and access to it? (Press y|Y for Yes, any other key for No:) <Enter>
7. Reload privilege tables now? (Press y|Y for Yes, any other key for No:) y <Enter>
#
# Políticas de Segurança do MariaDB
# (Obs: na instalação é associado a senha do seu usuário para o root do MariaDB)
sudo mysql_secure_installation
1. Enter current password for root (enter for none): pti@2019 <Enter>
2. Change the root password? [Y/n]: y <Enter>
3. New password: vaamonde <Enter>
4. Re-enter new password: vaamonde <Enter>
5. Remove anonymous users? [Y/n]: y <Enter>
6. Disallow root login remotely? [Y/n]: n <Enter>
7. Remove test database and access to it? [Y/n]: n <Enter>
8. Reload privilege tables now? [Y/n]: y <Enter>
#
# Localização dos arquivos de configuração do SGBD do MySQL ou MariaDB
/etc/mysql <-- Diretório de configuração do SGBD MySQL ou MariaDB
/etc/mysql/mysql.conf.d/ <-- Configurações do Servidor SGBD do MySQL
/etc/mysql/mysql.conf.d/mysqld.cnf <-- Arquivo de configuração do Servidor SGBD do MySQL
/etc/mysql/mariadb.conf.d/ <-- Configurações do Servidor SGBD do MariaDB
/etc/mysql/mariadb.conf.d/50-server.cnf <-- Arquivo de configuração do Servidor SGBD do MariaDB
#
# Atualizando o arquivo de configuração do SGBD do MySQL ou MariaDB
sudo cp -v /etc/mysql/mysql.conf.d/mysqld.cnf /etc/mysql/mysql.conf.d/mysqld.cnf.old
sudo vim /etc/mysql/mysql.conf.d/mysqld.cnf
sudo cp -v /etc/mysql/mariadb.conf.d/50-server.cnf /etc/mysql/mariadb.conf.d/50-server.cnf.old
sudo vim /etc/mysql/mariadb.conf.d/50-server.cnf
#
# Verificando o Serviço do SGBD do MySQL ou MariaDB
sudo systemctl status mysql
sudo systemctl restart mysql
sudo systemctl stop mysql
sudo systemctl start mysql
sudo systemctl status mariadb
sudo systemctl restart mariadb
sudo systemctl stop mariadb
sudo systemctl start mariadb
#
# Verificando o Porta de Conexão do SGDB do MySQL ou MariaDB
# (opções do comando netstat: -a all | -n numeric)
sudo netstat -an | grep 3306
#
# Acessando o SGBD do MySQL ou MariaDB com o usuário root do MySQL/MariaDB
# (opções do comando mysql: -u user | -p password)
sudo mysql -u root -p
#
# Verificando os Bancos de Dados Existentes no SGBD do MySQL ou MariaDB
SHOW DATABASES;
#
# Criando o nosso Banco de Dados Vaamonde no SGBD do MySQL ou MariaDB
# Verificando o nosso Banco de Dados criado no SGBD do MySQL ou MariaDB
CREATE DATABASE vaamonde;
SHOW DATABASES;
#
# Permitindo que o usuário Root administre o servidor Remotamente do MySQL ou MariaDB
# (opções do comando GRANT: grant (permissão), all (todos privilégios), on (em ou na | banco ou tabela), *.* (todos os bancos/tabelas))
# (opções do comando GRANT: to (para), user@'%' (usuário @ localhost), identified by (identificado por - senha do usuário))
# Obs: no MySQL versão >= 8.0.x o comando de permissão para o usuário root mudou:
# Primeiro criar o usuário: CREATE USER 'vaamonde'@'localhost' IDENTIFIED WITH mysql_native_password BY 'vaamonde';
# Segundo aplicar as permissões: GRANT ALL PRIVILEGES ON *.* TO 'vaamonde'@'localhost';
# Terceiro aplicar todas as mudanças: FLUSH PRIVILEGES;
GRANT ALL ON *.* TO root@'%' IDENTIFIED BY 'vaamonde'
#
# Criando usuários no SGBD do MySQL ou MariaDB
# (opções do comando CREATE: create (criação), user (usuário), identified by (identificado por - senha do usuário))
CREATE USER 'vaamonde' IDENTIFIED BY 'vaamonde';
#
# Aplicando as permissões de acesso ao Banco de Dados Vaamonde no SGBD do MySQL ou MariaDB
# (opções do comando GRANT: grant (permissão), usage (uso em banco ou tabela), on (em ou na | banco ou tabela), *.* (todos os bancos/tabelas))
# (opções do comando GRANT: to (para), 'vaamonde' (usuário), identified by (identificado por - senha do usuário))
# (opções do comando GRANT: all (todos privilégios), privileges (privilégios), on (em ou na | banco ou tabela), vaamonde.* (banco/tabelas), to (para) 'vaamonde' (usuário))
GRANT USAGE ON *.* TO 'vaamonde' IDENTIFIED BY 'vaamonde';
GRANT ALL PRIVILEGES ON vaamonde.* TO 'vaamonde';
FLUSH PRIVILEGES;
EXIT




echo -e "Atualizando o arquivo de configuração do Certificado do MySQL, aguarde..."
	# opção do comando: &>> (redirecionar a saída padrão adicionando)
	# opção do comando cp: -v (verbose)
	# opção do bloco e agrupamentos {}: (Agrupa comandos em um bloco)
	cp -v conf/ssl/mysql.conf /etc/ssl/ &>> $LOG
echo -e "Arquivo atualizado com sucesso!!!, continuando com o script...\n"
sleep 5
#
echo -e "Criando o Chave Privada de: $BITS do MySQL, senha padrão: $PASSPHRASE, aguarde..." 
	# opção do comando: &>> (redirecionar a saída padrão)
	# opções do comando openssl: 
	# genrsa (command generates an RSA private key),
	# -criptokey (Encrypt the private key with the AES, CAMELLIA, DES, triple DES or the IDEA ciphers)
	# -out (The output file to write to, or standard output if not specified), 
	# -passout (The output file password source), 
	# pass: (The actual password is password), 
	# bits (The size of the private key to generate in bits)
	#
	openssl genrsa -$CRIPTOKEY -out /etc/ssl/private/mysql.key.old -passout pass:$PASSPHRASE $BITS &>> $LOG
echo -e "Chave Privada do MySQL criada com sucesso!!!, continuando com o script...\n"
sleep 5
#
echo -e "Removendo a senha da Chave Privada do MySQL, senha padrão: $PASSPHRASE, aguarde..."
	# opção do comando: &>> (redirecionar a saída padrão)
	# opções do comando openssl: 
	# rsa (command processes RSA keys),
	# -in (The input file to read from, or standard input if not specified),
	# -out (The output file to write to, or standard output if not specified),
	# -passin (The key password source),
	# pass: (The actual password is password)
	# opção do comando rm: -v (verbose)
	#
	openssl rsa -in /etc/ssl/private/mysql.key.old -out /etc/ssl/private/mysql.key \
	-passin pass:$PASSPHRASE &>> $LOG
	rm -v /etc/ssl/private/mysql.key.old &>> $LOG
echo -e "Senha da Chave Privada do MySQL removida com sucesso!!!, continuando com o script...\n"
sleep 5
#
echo -e "Verificando o arquivo de Chave Privada do MySQL, aguarde..."
	# opção do comando: &>> (redirecionar a saída padrão)
	# opções do comando openssl: 
	# rsa (command processes RSA keys), 
	# -noout (Do not output the encoded version of the key), 
	# -modulus (Print the value of the modulus of the key), 
	# -in (The input file to read from, or standard input if not specified), 
	# md5 (The message digest to use MD5 checksums)
	#
	openssl rsa -noout -modulus -in /etc/ssl/private/mysql.key | openssl md5 &>> $LOG
echo -e "Arquivo de Chave Privada do MySQL verificada com sucesso!!!, continuando com o script...\n"
sleep 5
#
echo -e "Editando o arquivo de configuração do Certificado do MySQL mysql.conf, pressione <Enter> para continuar."
	# opção do comando read: -s (Do not echo keystrokes)
	read -s
	nano /etc/ssl/mysql.conf
echo -e "Arquivo editado com sucesso!!!, continuando com o script...\n"
sleep 5
#
echo -e "Criando o arquivo CSR (Certificate Signing Request), confirme as mensagens do arquivo: mysql.conf, aguarde...\n"
	# opções do comando openssl: 
	# req (command primarily creates and processes certificate requests in PKCS#10 format), 
	# -new (Generate a new certificate request),
	# -criptocert (The message digest to sign the request with)
	# -nodes (Do not encrypt the private key),
	# -key (The file to read the private key from), 
	# -out (The output file to write to, or standard output if not specified),
	# -extensions (Specify alternative sections to include certificate extensions), 
	# -config (Specify an alternative configuration file)
	#
	# Criando o arquivo CSR, mensagens que serão solicitadas na criação do CSR
	# 	Country Name (2 letter code): BR <-- pressione <Enter>
	# 	State or Province Name (full name): Brasil <-- pressione <Enter>
	# 	Locality Name (eg, city): Sao Paulo <-- pressione <Enter>
	# 	Organization Name (eg, company): Bora para Pratica <-- pressione <Enter>
	# 	Organization Unit Name (eg, section): Procedimentos em TI <-- pressione <Enter>
	# 	Common Name (eg, server FQDN or YOUR name): pti.intra <-- pressione <Enter>
	# 	Email Address: pti@pti.intra <-- pressione <Enter>
	#
	openssl req -new -$CRIPTOCERT -nodes -key /etc/ssl/private/mysql.key -out \
	/etc/ssl/requests/mysql.csr -extensions v3_req -config /etc/ssl/mysql.conf
	echo
echo -e "Criação do arquivo CSR feito com sucesso!!!, continuando com o script...\n"
sleep 5
#
echo -e "Verificando o arquivo CSR (Certificate Signing Request) do MySQL, aguarde..."
	# opção do comando: &>> (redirecionar a saída padrão)
	# opções do comando openssl: 
	# req (command primarily creates and processes certificate requests in PKCS#10 format), 
	# -noout (Do not output the encoded version of the request), 
	# -text (Print the certificate request in plain text), 
	# -in (The input file to read a request from, or standard input if not specified)
	#
	openssl req -noout -text -in /etc/ssl/requests/mysql.csr &>> $LOG
echo -e "Arquivo CSR verificado com sucesso!!!, continuando com o script...\n"
sleep 5
#
echo -e "Criando o certificado assinado CRT (Certificate Request Trust), do MySQL, aguarde...\n"
	# opção do comando: &>> (redirecionar a saída padrão
	# opções do comando openssl: 
	# x509 (command is a multi-purpose certificate utility),
	# ca (command is a minimal certificate authority (CA) application)
	# -req (Expect a certificate request on input instead of a certificate),
	# -days (The number of days to make a certificate valid for),
	# -criptocert (The message digest to sign the request with),							
	# -in (The input file to read from, or standard input if not specified),
	# -CA (The CA certificate to be used for signing),
	# -CAkey (Set the CA private key to sign a certificate with),
	# -CAcreateserial (Create the CA serial number file if it does not exist instead of generating an error),
	# -out (The output file to write to, or standard output if none is specified)
	# -config (Specify an alternative configuration file)
	# -extensions (The section to add certificate extensions from),
	# -extfile (File containing certificate extensions to use).
	#
	# Sign the certificate? [y/n]: y <Enter>
	# 1 out of 1 certificate request certified, commit? [y/n]: y <Enter>
	#
	# OPÇÃO DE ASSINATURA DO ARQUIVO CRT SEM UTILIZAR O WIZARD DO CA, CÓDIGO APENAS DE DEMONSTRAÇÃO
	# openssl x509 -req -days 3650 -$CRIPTOCERT -in /etc/ssl/requests/mysql.csr -CA \
	# /etc/ssl/newcerts/ca.crt -CAkey /etc/ssl/private/ca.key -CAcreateserial \
	# -out /etc/ssl/newcerts/mysql.crt -extensions v3_req -extfile /etc/ssl/mysql.conf &>> $LOG
	#
	openssl ca -in /etc/ssl/requests/mysql.csr -out /etc/ssl/newcerts/mysql.crt \
	-config /etc/ssl/ca.conf -extensions v3_req -extfile /etc/ssl/mysql.conf
	echo
echo -e "Criação do certificado assinado CRT do MySQL feito com sucesso!!!, continuando com o script...\n"
sleep 5
#
echo -e "Verificando o arquivo CRT (Certificate Request Trust) do MySQL, aguarde..."
	# opção do comando: &>> (redirecionar a saída padrão)
	# opções do comando openssl: 
	# x509 (command is a multi-purpose certificate utility), 
	# -noout (Do not output the encoded version of the request),
	# -text (Print the full certificate in text form), 
	# -modulus (Print the value of the modulus of the public key contained in the certificate), 
	# -in (he input file to read from, or standard input if not specified), 
	# md5 (The message digest to use MD5 checksums)
	#
	openssl x509 -noout -modulus -in /etc/ssl/newcerts/mysql.crt | openssl md5 &>> $LOG
	openssl x509 -noout -text -in /etc/ssl/newcerts/mysql.crt &>> $LOG
	cat /etc/ssl/index.txt &>> $LOG
	cat /etc/ssl/index.txt.attr &>> $LOG
	cat /etc/ssl/serial &>> $LOG
echo -e "Arquivo CRT do MySQL verificado com sucesso!!!, continuando com o script...\n"
sleep 5
#
echo -e "Verificando as configurações do TLS/SSL do MySQL, aguarde..."
	# opção do comando: &>> (redirecionar a saída padrão)
	# opção do comando mysql: -u (user), -p (password) -e (execute), mysql (database) 
	mysql -u $USERMYSQL -p$SENHAMYSQL -e "SHOW VARIABLES LIKE '%ssl_%';" mysql &>> $LOG
echo -e "Verificação do TLS/SSL do MySQL feita com sucesso!!!, continuando com o script...\n"
sleep 5
#
echo -e "Editando o arquivo de configuração mysqld.cnf, pressione <Enter> para continuar."
	# opção do comando read: -s (Do not echo keystrokes)
	read -s
	nano /etc/mysql/mysql.conf.d/mysqld.cnf
echo -e "Arquivo editado com sucesso!!!, continuando com o script...\n"
sleep 5
#
echo -e "Editando o arquivo de configuração mysql.cnf, pressione <Enter> para continuar."
	# opção do comando read: -s (Do not echo keystrokes)
	read -s
	nano /etc/mysql/mysql.conf.d/mysql.cnf
echo -e "Arquivo editado com sucesso!!!, continuando com o script...\n"
sleep 5
#
#
echo -e "Verificando as novas configurações do TLS/SSL do MySQL, aguarde..."
	# opção do comando: &>> (redirecionar a saída padrão)
	# opção do comando mysql: -u (user), -p (password) -e (execute), mysql (database) 
	mysql -u $USERMYSQL -p$SENHAMYSQL -e "SHOW VARIABLES LIKE '%ssl_%';" mysql &>> $LOG
echo -e "Verificação do TLS/SSL do MySQL feita com sucesso!!!, continuando com o script...\n"
sleep 5
#
echo -e "Testando o Certificado TLS/SSL do MySQL, aguarde..."
	# opção do comando: &>> (redirecionar a saída padrão)
	# opção do comando echo: | (piper, faz a função de Enter no comando)
	# opções do comando openssl: 
	# s_client (command implements a generic SSL/TLS client which connects to a remote host using SSL/TLS)
	# -connect (The host and port to connect to)
	# -servername (Include the TLS Server Name Indication (SNI) extension in the ClientHello message)
	# -showcerts (Display the whole server certificate chain: normally only the server certificate itself is displayed)
	#
	echo | openssl s_client -connect $IPV4SERVER:3306 -servername mysql.$DOMINIOSERVER -showcerts &>> $LOG
echo -e "Certificado do Apache2 testado sucesso!!!, continuando com o script...\n"
sleep 5
#
