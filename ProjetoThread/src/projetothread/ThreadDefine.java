package projetothread;

public class ThreadDefine extends Thread {

    private RelatorioCategoria relatorio;

    public ThreadDefine(RelatorioCategoria relatorio) {
        this.relatorio = relatorio;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i <= 250; i++) {
                relatorio.defineSite();
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}