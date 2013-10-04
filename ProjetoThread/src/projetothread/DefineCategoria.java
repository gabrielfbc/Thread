package projetothread;

public class DefineCategoria implements Runnable {

    private RelatorioCategoria relat;
    private String categoria;

    public DefineCategoria(RelatorioCategoria relat, String categoria) {
        this.relat = relat;
        this.categoria = categoria;
    }

    private void defineCategoria() {
        try {
            //Definição do site
            System.out.println("Definindo site...");
            Thread.sleep((int) (Math.random() * 1000));
            
            if (categoria.equalsIgnoreCase("Jogos")) {
                relat.setJogos(relat.getJogos() + 1);
            } else {
                if (categoria.equalsIgnoreCase("Notícias")) {
                    relat.setNoticias(relat.getNoticias() + 1);
                } else {
                    if (categoria.equalsIgnoreCase("Trabalho")) {
                        relat.setTrabalho(relat.getTrabalho() + 1);
                    } else {
                        if (categoria.equalsIgnoreCase("Musica")) {
                            relat.setMusica(relat.getMusica() + 1);
                        } else {
                            if (categoria.equalsIgnoreCase("Download")) {
                                relat.setDownload(relat.getDownload() + 1);
                            }
                        }
                    }
                }
            }
            System.out.println("Site definido!");
        } catch (InterruptedException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            //Libera recurso do semáforo
            relat.getSemafaro().release();

        }
    }

    @Override
    public void run() {
        defineCategoria();
    }
}
