#!/bin/bash
#
# mysql -u root -p --ssl-mode=required
#	SHOW VARIABLES LIKE '%ssl%';
#	\s 
#	exit
#
# Arquivo de configuração dos parâmetros utilizados nesse script
source 00-parametros.sh

echo -e "Criando o Banco de Dados e Populando as Tabelas do SuperaSystem, aguarde esse processo demora um pouco..."
	# opção do comando: &>> (redirecionar de saída padrão)
	# opção do comando: | piper (conecta a saída padrão com a entrada padrão de outro comando)
	# opção do comando mysql: -u (user), -p (password), -e (execute)
	# opção do comando zcat: -v (verbose)
	mysql -u $USERMYSQL -p$SENHAMYSQL -e "$CREATE_DATABASE_SUPERA" mysql &>> $LOG
	mysql -u $USERMYSQL -p$SENHAMYSQL -e "$CREATE_USER_DATABASE_SUPERA" mysql &>> $LOG
	mysql -u $USERMYSQL -p$SENHAMYSQL -e "$GRANT_DATABASE_SUPERA" mysql &>> $LOG
	mysql -u $USERMYSQL -p$SENHAMYSQL -e "$GRANT_ALL_DATABASE_SUPERA" mysql &>> $LOG
	mysql -u $USERMYSQL -p$SENHAMYSQL -e "$SET_GLOBAL_SUPERA" mysql &>> $LOG
	mysql -u $USERMYSQL -p$SENHAMYSQL -e "$FLUSH_SUPERA" mysql &>> $LOG	
echo -e "Banco de Dados criado com sucesso!!!, continuando com o script...\n"
sleep 5

# script para calcular o tempo gasto (SCRIPT MELHORADO, CORRIGIDO FALHA DE HORA:MINUTO:SEGUNDOS)
# opção do comando date: +%T (Time)
HORAFINAL=$(date +%T)
# opção do comando date: -u (utc), -d (date), +%s (second since 1970)
HORAINICIAL01=$(date -u -d "$HORAINICIAL" +"%s")
HORAFINAL01=$(date -u -d "$HORAFINAL" +"%s")
# opção do comando date: -u (utc), -d (date), 0 (string command), sec (force second), +%H (hour), %M (minute), %S (second), 
TEMPO=$(date -u -d "0 $HORAFINAL01 sec - $HORAINICIAL01 sec" +"%H:%M:%S")
# $0 (variável de ambiente do nome do comando)
echo -e "Tempo gasto para execução do script $0: $TEMPO"
echo -e "Pressione <Enter> para concluir o processo."
# opção do comando date: + (format), %d (day), %m (month), %Y (year 1970), %H (hour 24), %M (minute 60)
echo -e "Fim do script $0 em: $(date +%d/%m/%Y-"("%H:%M")")\n" &>> $LOG
read
exit 1
