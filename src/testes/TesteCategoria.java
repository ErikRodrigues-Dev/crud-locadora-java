package testes;

import entidades.Categoria;
import persistencia.CategoriaDAO;

public class TesteCategoria {
    public static void main(String[] args) {
       Categoria categoria = new Categoria();
       categoria.setNome("Comedia");
       categoria.setTipo('F'); //F - Filme | J - Jogo
    
     //CategoriaDAO.inserir(categoria); 
     //categoria.setId(3);
      
        
    }
}
