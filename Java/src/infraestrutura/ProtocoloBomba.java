/*
 * Protocolo de comunicação:
 * #xxxx,ddmmyy - valor e data
 * # - inicio da palavra
 * x - valor medido
 * dmy - dia mes e ano
 * $hhmmss,bvxx - hora e status
 * $ - inicio da palavra
 * hms - hora, min, seg
 * b - bomba lig ou desl
 * v - valvula aberta ou fechada
 * x - futuro
 */
package infraestrutura;

public class ProtocoloBomba {

    private String[] recebido = new String[12];
    private String data = "";
    private String umi = "";
    private String temp = "";
    private String hora = "";
    private String saidas = "";
    
    public boolean trataRecebido(String rec) {
        boolean sucesso = false;
        int tamanho = rec.length();
        String aux = "";
        String separador = rec.substring(0, 1);
        if (tamanho == 40) {
            if (separador.equals("#")) {
                recebido = rec.split(";");
                aux = recebido[0];
                auxliarGetSet.setPressao(Float.valueOf(aux.substring(1, aux.length())));
                umi = recebido[1];
                auxliarGetSet.setUmid(Float.valueOf(umi));
                temp = recebido[2];
                auxliarGetSet.setTemp(Float.valueOf(temp));
                hora = recebido[3];
                auxliarGetSet.setHora(hora);
                data = recebido[4];
                auxliarGetSet.setData(data);
                saidas = recebido[5];
                switch (saidas) {
                    case "00": {
                        auxliarGetSet.setBomba(false);
                        auxliarGetSet.setValvula(false);
                        break;
                    }
                    case "01": {
                        auxliarGetSet.setBomba(true);
                        auxliarGetSet.setValvula(false);
                        break;
                    }
                    case "10": {
                        auxliarGetSet.setBomba(false);
                        auxliarGetSet.setValvula(true);
                        break;
                    }
                    case "11": {
                        auxliarGetSet.setBomba(true);
                        auxliarGetSet.setValvula(true);
                         break;
                    }

                }
                sucesso = true;
            }
        }
        return sucesso;
    }
      
}
