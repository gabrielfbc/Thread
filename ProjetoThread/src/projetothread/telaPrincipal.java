package projetothread;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JOptionPane;

public class telaPrincipal extends javax.swing.JFrame {

    private ArrayList<Site> sitesBase = new ArrayList<Site>();
    private RelatorioCategoria rcategoria;
    private RelatorioRanking rranking;
    private ReentrantLock rl;
    private Condition c;
    private String local;

    public telaPrincipal() throws FileNotFoundException {
        gerarBase();
        this.local = "C:\\Documents and Settings\\gcampos\\Meus documentos\\Dropbox\\Ciência da Computação\\Sistemas Paralelos e Distribuidos\\unidade 2\\ProjetoThread\\src\\logs\\log";
        this.rcategoria = new RelatorioCategoria(sitesBase, local);
        this.rl = new ReentrantLock();
        this.c = rl.newCondition();
        this.rranking = new RelatorioRanking(rl, c, local);

        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSync = new javax.swing.JButton();
        btnLock = new javax.swing.JButton();
        btnVisualizarSync = new javax.swing.JButton();
        btnVisualizarLock = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSync.setText("Sync");
        btnSync.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSyncActionPerformed(evt);
            }
        });

        btnLock.setText("Lock");
        btnLock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLockActionPerformed(evt);
            }
        });

        btnVisualizarSync.setText("Visualizar");
        btnVisualizarSync.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarSyncActionPerformed(evt);
            }
        });

        btnVisualizarLock.setText("Visualizar");
        btnVisualizarLock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarLockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSync, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVisualizarSync)
                    .addComponent(btnVisualizarLock))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSync)
                    .addComponent(btnVisualizarSync))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLock)
                    .addComponent(btnVisualizarLock))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSyncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSyncActionPerformed
        ThreadDefine t2 = new ThreadDefine(rcategoria);
        ThreadAdiciona t1 = new ThreadAdiciona(rcategoria);
        t2.start();
        t1.start();
    }//GEN-LAST:event_btnSyncActionPerformed

    private void btnVisualizarSyncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarSyncActionPerformed
        JOptionPane.showMessageDialog(null, rcategoria.imprime());
    }//GEN-LAST:event_btnVisualizarSyncActionPerformed

    private void btnVisualizarLockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarLockActionPerformed
        JOptionPane.showMessageDialog(null, rranking.imprime());
    }//GEN-LAST:event_btnVisualizarLockActionPerformed

    private void btnLockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLockActionPerformed
        ThreadElege t1 = new ThreadElege(rranking, "T1");
        ThreadElege t2 = new ThreadElege(rranking, "T2");
        ThreadInclui t3 = new ThreadInclui(rranking);
        t1.start();
        t2.start();
        t3.start();
    }//GEN-LAST:event_btnLockActionPerformed

    private void gerarBase() {
        Categoria cJogos = new Categoria("Jogos");
        Categoria cNoticias = new Categoria("Notícias");
        Categoria cTrabalho = new Categoria("Trabalho");
        Categoria cMusica = new Categoria("Musica");
        Categoria cDownload = new Categoria("Download");
        sitesBase.add(new Site("http://jogomania.com.br", cJogos));
        sitesBase.add(new Site("http://miniclip.com.br", cJogos));
        sitesBase.add(new Site("http://uol.com.br", cNoticias));
        sitesBase.add(new Site("http://terra.com.br", cNoticias));
        sitesBase.add(new Site("http://tecmundo.com.br", cTrabalho));
        sitesBase.add(new Site("http://sap.com.br", cTrabalho));
        sitesBase.add(new Site("http://sound.com.br", cMusica));
        sitesBase.add(new Site("http://musicplay.com.br", cMusica));
        sitesBase.add(new Site("http://mega.com.br", cDownload));
        sitesBase.add(new Site("http://baixaki.com.br", cDownload));
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new telaPrincipal().setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(telaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLock;
    private javax.swing.JButton btnSync;
    private javax.swing.JButton btnVisualizarLock;
    private javax.swing.JButton btnVisualizarSync;
    // End of variables declaration//GEN-END:variables
}
