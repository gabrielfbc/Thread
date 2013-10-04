package projetothread;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class RelatorioCategoria {

    private int jogos;
    private int noticias;
    private int trabalho;
    private int musica;
    private int download;
    private ArrayList<Site> base = new ArrayList<Site>();
    private ArrayList<String> sites = new ArrayList<String>();
    private BufferedReader in;
    private String[] temp;
    private Semaphore semafaro = new Semaphore(10, true);

    public RelatorioCategoria(ArrayList<Site> base, String local) throws FileNotFoundException {
        this.base = base;
        this.in = new BufferedReader(new FileReader(local));
    }
    
    //area crítica: evitar que duas threads utilizem o mesmo recurso do array
    public synchronized void defineSite() throws InterruptedException {
        if (sites.isEmpty()) {
            //se o array estiver vazio, aguarda até que ThreadAdiciona libere com notifyAll()
            System.out.println("Aguardando array...");
            wait();
        }
        for (Site b : base) {
            if (sites.get(0).equalsIgnoreCase(b.getUrl())) {
                try {
                    //Define que apenas 10 sites sejam categoriados por vez
                    semafaro.acquire();
                    //Para cada site é iniciado uma nova Thread para definir a categoria (definição paralelizada)
                    Thread define = new Thread(new DefineCategoria(this, b.getCategoria().getNome()));
                    define.start();
                } catch (Exception e) {
                    //Garante que se houver algum erro, o recurso será liberado
                    semafaro.release();
                }
                break;
            }
        }
        sites.remove(0);
    }

    public synchronized void addSite() throws InterruptedException, InterruptedException, IOException {
        if (in.ready()) {
            String str = in.readLine();
            temp = str.split(";");
            sites.add(temp[0]);
            //Quando um novo site for adicionado ThreadDefine que estiver em wait() acorda
            notifyAll();
        }
    }

    public String imprime() {
        return ("Sites por categoria:\n"
                + "Jogos: " + jogos + "\n"
                + "Notícias: " + noticias + "\n"
                + "Trabalho: " + trabalho + "\n"
                + "Musica: " + musica + "\n"
                + "Download: " + download);
    }

    public int getJogos() {
        return jogos;
    }

    public void setJogos(int jogos) {
        this.jogos = jogos;
    }

    public int getNoticias() {
        return noticias;
    }

    public void setNoticias(int noticias) {
        this.noticias = noticias;
    }

    public int getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(int trabalho) {
        this.trabalho = trabalho;
    }

    public Semaphore getSemafaro() {
        return semafaro;
    }

    public int getMusica() {
        return musica;
    }

    public void setMusica(int musica) {
        this.musica = musica;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }
}
