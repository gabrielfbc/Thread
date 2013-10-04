package projetothread;

public class ThreadElege extends Thread {

    private RelatorioRanking relatorio;

    public ThreadElege(RelatorioRanking relatorio, String nome) {
        this.relatorio = relatorio;
        this.setName(nome);       
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i <= 400; i++) {
                relatorio.elegeSite();
                System.out.println("Site elegido pela Thread " + this.getName());
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}
