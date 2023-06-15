/*
 * Atualização 25/03/2019
 */
package infraestrutura;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.List;
import usuarioDAO.usuariosDao;
import view.TelaMedicao;
import view.TelaPrincipal;

public class Telegram {
    
    boolean serialConectou = auxliarGetSet.isConectado();
    boolean bomba = auxliarGetSet.isBomba();
    boolean valve = auxliarGetSet.isValvula();

    //Criação do objeto bot com as informações de acesso
    TelegramBot bot = TelegramBotAdapter.build("694263867:AAGDjDwj8Hh7S15zljbhz-Y4cpsJXgTyijg");

    //objeto responsável por receber as mensagens
    GetUpdatesResponse updatesResponse;
    //objeto responsável por gerenciar o envio de respostas
    SendResponse sendResponse;
    //objeto responsável por gerenciar o envio de ações do chat
    BaseResponse baseResponse;

    //controle de off-set, isto é, a partir deste ID será lido as mensagens pendentes na fila
    int m = 0;
    
    ThreadTelegram conversa = new ThreadTelegram();
    
    public boolean conexaoExt(boolean habilita) {
        boolean result = false;
        if(habilita){
        try {
            URL url = new URL("http://www.google.com.br");
            URLConnection connection = url.openConnection();
            connection.connect();
            result =  true; 
        } catch (SocketTimeoutException e) {
            result = false;
        } catch (IOException e) {
            result = false;
        }
    }
        return result;
    }   
    public String[] decifrar() {
        String[] msg = new String[4];
        try {
            updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));
            List<Update> updates = updatesResponse.updates();

            //análise de cada ação da mensagem
            for (Update update : updates) {
                m = update.updateId() + 1;
                msg[0] = update.message().from().id().toString();
                msg[1] = update.message().from().firstName();
                msg[2] = update.message().from().lastName();
                
            }
        } catch (NullPointerException ex) {
            conversa.pararTelegram();
            
        } catch (Exception ex) {
            System.out.println("decifra: " + ex);
            
            conversa.pararTelegram();
        }
        
        return msg;
        
    }
    
    public void mensagem(String mensagem) {
        usuariosDao user = new usuariosDao();
        String usuario = null, id = null, nome = null;
        String[] separa = null;
        try {
            usuario = user.retornaUser();
        } catch (SQLException ex) {
            System.out.println("mensagem erro SQL: " + ex);
        }
        separa = usuario.split(",");
        int n = separa.length;
        for (int i = 1; i < n; i++) {
            if (!separa[i].equals("null")) {
                if (i % 2 == 1) {
                    id = separa[i];
                    try {
                        sendResponse = bot.execute(new SendMessage(id, mensagem));
                        
                    } catch (RuntimeException ex) {
                        System.out.println("mensagem: " + ex);
                        conversa.pararTelegram();
                        
                    } catch (Exception ex) {
                        System.out.println("mensagem: " + ex);
                        conversa.pararTelegram();
                    }
                }
            }
        }
    }
    
    public void mensagemInicial(String mensagem, String user) {
        try {
            sendResponse = bot.execute(new SendMessage(user, mensagem));
            
        } catch (RuntimeException ex) {
            System.out.println("mensagem inicial: " + ex);
            auxliarGetSet.setNetConect(false);
            conversa.pararTelegram();
            
        } catch (Exception ex) {
            System.out.println("mensagem inicial: " + ex);
            conversa.pararTelegram();
        }
    }
    
    public void conversa(boolean conectado) {
        if (conectado) {
            usuariosDao user = new usuariosDao();
            try {
                String resposta = null;
                //executa comando no Telegram para obter as mensagens pendentes a partir de um off-set (limite inicial)
                updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));
//        System.out.println(updatesResponse);
                //lista de mensagens
                List<Update> updates = updatesResponse.updates();

                //análise de cada ação da mensagem
                for (Update update : updates) {

                    //atualização do off-set
                    m = update.updateId() + 1;
                    resposta = update.message().text();
                    String iduser = update.message().from().id().toString();
                    
                    if (user.checaUser(iduser)) {
                        String Nomeuser = update.message().from().firstName();
                        String comando = ("Comando: " + resposta + " de " + Nomeuser);
                        if (!resposta.equals(null)) {
                            TelaPrincipal.txtMedidos.setText("\n" + comando + "\n");
                            switch (resposta) {
                                case "/start": {
                                    //envio de "Escrevendo" antes de enviar a resposta
                                    baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
                                    String retorno = "Olá mestre " + Nomeuser + " oque posso fazer por você?\n";
                                    retorno += " Vou sugerir alguns comandos disponíveis:\n";
                                    retorno += "/status\n /ligabomba\n /desligabomba\n /abrevalvula\n /fechavalvula\n";
                                    sendResponse = bot.execute(new SendMessage(update.message().chat().id(), retorno));
                                    break;
                                }
                                case "/status": {
                                    
                                    String stBomba = null;
                                    String stConectou = null;
                                    String stValve = null;
                                    String stpressao = "Pressão atual: " + auxliarGetSet.getPressao() + "mBar\n";
                                    String stTemp = "Temperatura atual: " + auxliarGetSet.getTemp() + "ºC\n";
                                    String stUmi = "Umidade relativa atual: " + auxliarGetSet.getUmid() + "%\n";
                                    String status = "Resumo atual do sistema: \n";
                                    if (serialConectou) {
                                        stConectou = "Módulo conectado\n ";
                                    }
                                    if (!serialConectou) {
                                        stConectou = "Módulo desconectado\n ";
                                    }
                                    if (bomba) {
                                        stBomba = "Bomba: ligada\n ";
                                    }
                                    if (!bomba) {
                                        stBomba = "Bomba: desligada\n ";
                                    }
                                    if (valve) {
                                        stValve = "Válvula: aberta\n ";
                                    }
                                    if (!valve) {
                                        stValve = "Válvula: fechada\n ";
                                    }
                                    //envio de "Escrevendo" antes de enviar a resposta
                                    baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
                                    sendResponse = bot.execute(new SendMessage(update.message().chat().id(), status + stpressao + stTemp + stUmi + stBomba + stValve + stConectou));
                                    
                                    break;
                                }
                                case "/ligabomba": {
                                    if (serialConectou) {
                                        //envio de "Escrevendo" antes de enviar a resposta
                                        baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
                                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Ligando a bomba de vácuo!"));
                                        auxliarGetSet.setBomba(true);
                                     //   TelaMedicao.btnBomba1.doClick();
                                    } else {
                                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Porta Serial desconectada. Verifique!"));
                                    }
                                    break;
                                }
                                case "/desligabomba": {
                                    if (serialConectou) {
                                        //envio de "Escrevendo" antes de enviar a resposta
                                        baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
                                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Desligando a bomba de vácuo!"));
                                        auxliarGetSet.setBomba(false);
                                       // TelaMedicao.btnBomba1.doClick();
                                    } else {
                                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Porta Serial desconectada. Verifique!"));
                                    }
                                    break;
                                }
                                case "/abrevalvula": {
                                    if (serialConectou) {
                                        //envio de "Escrevendo" antes de enviar a resposta
                                        baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
                                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Abrindo a válvula de segurança!"));
                                        auxliarGetSet.setValvula(true);
                                       // TelaMedicao.btnValve1.doClick();
                                    } else {
                                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Porta Serial desconectada. Verifique!"));
                                    }
                                    break;
                                }
                                case "/fechavalvula": {
                                    if (serialConectou) {
                                        //envio de "Escrevendo" antes de enviar a resposta
                                        baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
                                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Fechando a válvula de segurança!"));
                                        auxliarGetSet.setValvula(false);
                                       // TelaMedicao.btnValve1.doClick();
                                    } else {
                                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Porta Serial desconectada. Verifique!"));
                                    }
                                    break;
                                }
                                
                                default: {
                                    sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Não entendi seu comando..."));
                                    break;
                                }
                            }
                            //envio de "Escrevendo" antes de enviar a resposta
                            //baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
                            //verificação de ação de chat foi enviada com sucesso
                            //System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());
                            //verificação de mensagem enviada com sucesso
//                    System.out.println("Mensagem Enviada?" + sendResponse.isOk());
                           
                        }
                    } else {
                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Desculpe mas acho que chamou o sistema errado...\n Ou você não está habilitado a utilizar este sistema."));
                    }
                }
            } catch (RuntimeException ex) {
                System.out.println(" Conversa  - Falha de internet: " + ex);
                conversa.pararTelegram();
                auxliarGetSet.setNetConect(false);
            } catch (SQLException ex) {
                System.out.println("conversa: sql erro " + ex);
                
            } catch (Exception ex) {
                System.out.println("conversa: " + ex);
                conversa.pararTelegram();
                auxliarGetSet.setNetConect(false);
            }
            
        }
    }
    
}
