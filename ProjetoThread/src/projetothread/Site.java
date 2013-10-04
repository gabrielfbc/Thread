package projetothread;

public class Site {
   
    private String url;
    private Categoria categoria;

    public Site(String url, Categoria categoria) {
        this.url = url;
        this.categoria = categoria;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
