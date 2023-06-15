/*
 * Atualizado em 25/03/2019 - Desabilita botoes se não conectar a serial
 */
package view;

import infraestrutura.ThreadTelegram;
import infraestrutura.ProtocoloBomba;
import infraestrutura.Relogio;
import infraestrutura.Telegram;
import infraestrutura.auxliarGetSet;
import javax.swing.JOptionPane;
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 * @author Rick
 */
public class TelaMedicao extends javax.swing.JInternalFrame {

    //*********Váriáveis*****************  
    ProtocoloBomba bombadados = new ProtocoloBomba();
    private boolean bomba = false;
    private boolean valvula = false;
    Telegram telegram = new Telegram();
    Relogio clock = new Relogio();
    SerialPort arduinoPort = null;
    private int exe = 16;

//**************Funçoes*************** 
    public void icones(boolean valve, boolean bomba) {
        if (bomba) {
            btnBomba1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/motorYellow.png"))); // ligado 
        } else if (!bomba) {
            btnBomba1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/motorBlack.png"))); // NOI18N
        }
        if (valve) {
            btnValve1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/valveGreen.png")));
        } else if (!valve) {
            btnValve1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/valveBlack.png"))); // NOI18N   
        }
    }

    public void ligaBomba(boolean estado) {
        if (auxliarGetSet.isConectado()) {
            if (estado) {
                enviaComandos("01#", auxliarGetSet.getPortaSerial());
//                btnBomba1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/motorYellow.png"))); // ligado
                if (auxliarGetSet.isConectado()) {
                    telegram.mensagem("Bomba de vácuo LIGADA!");
                }
                auxliarGetSet.setBomba(true);

            } else if (!estado) {
                enviaComandos("00#", auxliarGetSet.getPortaSerial());
//                btnBomba1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/motorBlack.png"))); // NOI18N
                if (auxliarGetSet.isConectado()) {
                    telegram.mensagem("Bomba de vácuo DESLIGADA!");
                }
                auxliarGetSet.setBomba(false);
            }
        }
    }

    public void abreValve(boolean valve) {
        if (auxliarGetSet.isConectado()) {
            if (valve) {
                enviaComandos("11#", auxliarGetSet.getPortaSerial());
//                btnValve1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/valveGreen.png"))); // NOI18N  

                if (auxliarGetSet.isConectado()) {
                    telegram.mensagem("Válvula de segurança ABERTA!");
                }
                auxliarGetSet.setValvula(true);
            } else if (!valve) {
                enviaComandos("10#", auxliarGetSet.getPortaSerial());
//                btnValve1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/valveBlack.png"))); // NOI18N 
                if (auxliarGetSet.isConectado()) {
                    telegram.mensagem("Válvula de segurança FECHADA!");
                }
                auxliarGetSet.setValvula(false);

            }

        }
    }

    private boolean enviaComandos(String dado, SerialPort porta) {
        boolean sucesso = false;
        if (!porta.equals(null)) {
            if (porta.isOpened()) {
                try {
                    porta.writeString(dado);
                    sucesso = true;
                } catch (SerialPortException ex) {
                    JOptionPane.showMessageDialog(null, "A porta não está aberta!");
                }
            }
            if (!porta.isOpened()) {
                JOptionPane.showMessageDialog(null, "A porta não está aberta!");
            }
        }
        return sucesso;
    }

    public boolean conectar(SerialPort serialPort) {
        boolean success = false;
        //manometro.setLedBlinking(true);
        ProtocoloBomba bomba = new ProtocoloBomba();
        if (serialPort.isOpened()) {
            try {
                serialPort.writeString("on");
                String st = null;
                try {
                    st = serialPort.readString(40); //12/14
                  
                    boolean terminou = bomba.trataRecebido(st);
                    if (terminou) {
                     //   manometro.setValue(auxliarGetSet.getPressao());
                     //   dspTemp.setValue(auxliarGetSet.getTemp());
                     //   dspUmi.setValue(auxliarGetSet.getUmid());
                        String hora = auxliarGetSet.getHora();
                        String data = auxliarGetSet.getData();
                        st = TelaPrincipal.txtMedidos.getText() + " " + auxliarGetSet.getPressao()+ "  " + auxliarGetSet.getTemp() + "ºC  "  + auxliarGetSet.getUmid() +"% "+ hora+"\n";
                        TelaPrincipal.txtMedidos.setText(st);
                        if (auxliarGetSet.isConectado()) {
                            telegram.mensagem(hora + " Falha de comunicação serial. Verifique!");
                            auxliarGetSet.SetConectado(false);
                        }

                        if (auxliarGetSet.isNetConect()) {
                            telegram.mensagem(st + " conectado.");
                        }

                    }
                } catch (SerialPortException ex) {
                    System.out.println("Erro porta serial :" + ex);

                } catch (NumberFormatException e) {
                    System.out.println("Formato inválido :" + e);
                    try {
                        serialPort.writeString("on");
                    } catch (SerialPortException ex) {
                    }
                }
            } catch (SerialPortException ex) {
                System.out.println("Erro serial :" + ex);

            } catch (RuntimeException ex) {
                auxliarGetSet.setNetConect(false);
                System.out.println("Falha de internet: " + ex);
            }
            icones(auxliarGetSet.isValvula(), auxliarGetSet.isBomba());
            arduinoPort = serialPort;
            success = true;
            TelaPrincipal.ledSerialCon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/led2.png")));

        } else {
            JOptionPane.showMessageDialog(null, "A porta não está aberta!");
        }
        return success;
    }

    public void disconectar() {
        if (arduinoPort != null) {
            try {               
                if (arduinoPort.isOpened()) {
                  arduinoPort.closePort();
                }
            } catch (SerialPortException ex) {
                System.out.println("Erro ao desconectar :" + ex);
            }
        }
      //  manometro.setValue(0);
      //  dspTemp.setValue(0);
     //   dspUmi.setValue(0);
        TelaPrincipal.ledSerialCon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/led0.png")));
     //   manometro.setLedBlinking(true);
    }

    public TelaMedicao() {
        initComponents();
        ThreadTelegram conversa = new ThreadTelegram();
        if (auxliarGetSet.isConectado()) {
            conversa.iniciarTelegram();
        }
        btnBomba.setEnabled(auxliarGetSet.isConectado());
        btnBomba1.setEnabled(auxliarGetSet.isConectado());
        btnValve.setEnabled(auxliarGetSet.isConectado());
        btnValve1.setEnabled(auxliarGetSet.isConectado());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnBomba = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnValve = new javax.swing.JButton();
        btnBomba1 = new javax.swing.JButton();
        btnValve1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnConectar = new javax.swing.JButton();
        txtTeste = new javax.swing.JTextField();
        btnDesconectar = new javax.swing.JButton();

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(779, 460));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(null));
        jPanel3.setPreferredSize(new java.awt.Dimension(996, 196));

        jLabel1.setText("Bomba de Vácuo:");

        btnBomba.setText("Liga");
        btnBomba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBombaActionPerformed(evt);
            }
        });

        jLabel2.setText("Vávula de entrada:");

        btnValve.setText("Abre");
        btnValve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValveActionPerformed(evt);
            }
        });

        btnBomba1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/motorBlack.png"))); // NOI18N
        btnBomba1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBomba1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBomba1ActionPerformed(evt);
            }
        });

        btnValve1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/valveBlack.png"))); // NOI18N
        btnValve1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnValve1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValve1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Temperatura Ambiente ");

        jLabel4.setText("Umidade relativa do ar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btnBomba, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(btnBomba1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnValve1)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(24, 24, 24)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnValve, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(34, 34, 34))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnBomba1)
                                .addGap(18, 18, 18)
                                .addComponent(btnBomba))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(7, 7, 7))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(btnValve1)
                        .addGap(18, 18, 18)
                        .addComponent(btnValve)))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(null));

        btnConectar.setText("Conectar");
        btnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarActionPerformed(evt);
            }
        });

        btnDesconectar.setText("Desconectar");
        btnDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesconectarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTeste, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnConectar)
                        .addGap(29, 29, 29)
                        .addComponent(btnDesconectar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(txtTeste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConectar)
                    .addComponent(btnDesconectar))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setBounds(0, 0, 779, 460);
    }// </editor-fold>//GEN-END:initComponents

    private void btnConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarActionPerformed
        conectar(auxliarGetSet.getPortaSerial());
        auxliarGetSet.SetConectado(true);
        if (auxliarGetSet.isConectado()) {
            telegram.mensagem("Sistema de aquisão de dados conectado.");
        }
        clock.stop = true;
        Thread relogio = new Thread(clock);
        relogio.start();

    }//GEN-LAST:event_btnConectarActionPerformed

    private void btnDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesconectarActionPerformed

        disconectar();
        if (auxliarGetSet.isConectado()) {
            telegram.mensagem("Sistema de aquisão de dados desconectado. Ensaio finalizado.");
        }
        auxliarGetSet.SetConectado(false);
    }//GEN-LAST:event_btnDesconectarActionPerformed

    private void btnValve1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValve1ActionPerformed
        if (!valvula) {
            abreValve(true);
            valvula = true;
            btnValve.setText("Fecha");

        } else {
            abreValve(false);
            valvula = false;
            btnValve.setText("Abre");
        }
        icones(auxliarGetSet.isValvula(), auxliarGetSet.isBomba());
    }//GEN-LAST:event_btnValve1ActionPerformed

    private void btnBomba1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBomba1ActionPerformed
        if (!bomba) {
            ligaBomba(true);
            bomba = true;
            btnBomba.setText("Desliga");

        } else {
            ligaBomba(false);
            bomba = false;
            btnBomba.setText("Liga");
        }
        icones(auxliarGetSet.isValvula(), auxliarGetSet.isBomba());
    }//GEN-LAST:event_btnBomba1ActionPerformed

    private void btnValveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValveActionPerformed
        valvula = auxliarGetSet.isValvula();
        if (!valvula) {
            abreValve(true);
            valvula = true;
            btnValve.setText("Fecha");

        } else {
            abreValve(false);
            valvula = false;
            btnValve.setText("Abre");
        }
        icones(auxliarGetSet.isValvula(), auxliarGetSet.isBomba());
    }//GEN-LAST:event_btnValveActionPerformed

    private void btnBombaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBombaActionPerformed
        bomba = auxliarGetSet.isBomba();
        if (!bomba) {
            ligaBomba(true);
            bomba = true;
            btnBomba.setText("Desliga");

        } else {
            ligaBomba(false);
            bomba = false;
            btnBomba.setText("Liga");
        }
        icones(auxliarGetSet.isValvula(), auxliarGetSet.isBomba());
    }//GEN-LAST:event_btnBombaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBomba;
    private javax.swing.JButton btnBomba1;
    private javax.swing.JButton btnConectar;
    private javax.swing.JButton btnDesconectar;
    private javax.swing.JButton btnValve;
    private javax.swing.JButton btnValve1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtTeste;
    // End of variables declaration//GEN-END:variables
}
