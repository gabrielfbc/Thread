package projetothread;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RelatorioRanking {

    private ArrayList<String> sites = new ArrayList<String>();
    private BufferedReader in;
    private String[] temp;
    private ReentrantLock rl;
    private Condition c;
    private int contador;
    private String top;

    public RelatorioRanking(ReentrantLock rl, Condition c, String local) throws FileNotFoundException {
        this.in = new BufferedReader(new FileReader(local));
        this.rl = rl;
        this.c = c;
        this.contador = 0;
    }

    public void incluiSite() throws IOException {
        //Bloqueia acesso ao método
        rl.lock();
        try {
            if (in.ready()) {
                String str = in.readLine();
                temp = str.split(";");
                sites.add(temp[0]);
                //Acorda Threads que estão dormindo
                c.signalAll();
            }
        } finally {
            //Garante a liberação de acesso ao método
            rl.unlock();
        }
    }

    public void elegeSite() throws InterruptedException {
        rl.lock();
        //Bloqueia acesso ao método, região crítica
        try {
            if (sites.isEmpty()) {
                //Se o array estiver vazio, aguarda
                System.out.println("Aguardando array...");
                c.await();
            }
            int aux = 1;
            String s = sites.get(0);
            for (int i = 1; i < sites.size(); i++) {
                if (sites.get(i).equalsIgnoreCase(s)) {
                    aux++;
                    sites.remove(i);
                    i--;
                }
            }
            if (aux > contador) {
                top = s;
                contador = aux;
            } else {
                if (aux == contador) {
                    top += "\n" + s;
                }
            }
            sites.remove(0);
        } finally {
            //Garante a liberação de acesso ao método
            rl.unlock();
        }
    }

    public String imprime() {
        return ("Site(s) mais acessado(s):\n"+top);
    }
}
