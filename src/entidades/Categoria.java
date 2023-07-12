package entidades;

public class Categoria {
    private int id;
    private String nome;
    private char tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
    
    public String getNomeTipo() {
        return tipo == 'F' ? "Filme" : "Jogo";   //Condicional Ternaria
        /*
        if (tipo == 'F') {
        return "Filme";
        } else {
            return "Jogo";
        }
        */
}
 
    public String toString() {
       return nome; 
    
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categoria other = (Categoria) obj;
        return this.id == other.id;
    }

}