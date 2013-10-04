package projetothread;

public class ThreadAdiciona extends Thread {

    private RelatorioCategoria relatorio;

    public ThreadAdiciona(RelatorioCategoria relatorio) {
        this.relatorio = relatorio;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i <= 250; i++) {
                relatorio.addSite();
                System.out.println("Site adicionado.");
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}
