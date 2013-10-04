package projetothread;

public class ThreadInclui extends Thread {

    private RelatorioRanking relatorio;

    public ThreadInclui(RelatorioRanking relatorio) {
        this.relatorio = relatorio;

    }

    @Override
    public void run() {
        try {
            for (int i = 0; i <= 400; i++) {
                relatorio.incluiSite();
                System.out.println("Site adicionado.");
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}
