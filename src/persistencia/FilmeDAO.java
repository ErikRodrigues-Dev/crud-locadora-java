package persistencia;

import entidades.Categoria;
import entidades.Filme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class FilmeDAO {
public static List<Filme> listar() throws SQLException {
        List<Filme> lista = new ArrayList<Filme>();
        try {
             String sql = "select filme.*," +   
                          " categoria.nome as categoria, " +
                          " categoria.tipo " +
                          "from filme " +
                          "inner join categoria on filme.categoria_id = categoria.id";
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("categoria_id"));
                categoria.setNome(rs.getString("categoria"));
                categoria.setTipo(rs.getString("tipo").charAt(0));
                
                Filme filme = new Filme(categoria);
                filme.setId(rs.getInt("id"));
                filme.setDescricao(rs.getString("descricao"));
                filme.setDiretor(rs.getString("diretor"));
                filme.setDuracao(rs.getInt("duracao"));
                filme.setNumroDias(rs.getInt("numeroDias"));
                filme.setPreco(rs.getDouble("preco"));
                filme.setTitulo(rs.getString("titulo"));
                
                lista.add(filme);
            }
                rs.close();
                st.close();              
            
        } catch (Exception e) {
            System.out.println("FilmeDAO.listar");
            System.out.println(e.getMessage());    
        }
        return lista;
         
       }

public static boolean inserir(Filme filme) {
    try {
        String sql = "INSERT INTO filme (titulo, descricao, preco, numeroDias "
                +   "diretor, duracao, categoria_id) VALUES (?,?,?,?,?,?,?)";
        Connection con = Conexao.getConexao();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, filme.getTitulo());
        ps.setString(2, filme.getDescricao());
        ps.setDouble(3, filme.getPreco());
        ps.setInt(4, filme.getNumroDias());
        ps.setString(5, filme.getDiretor());
        ps.setInt(6, filme.getDuracao());
        ps.setInt(7, filme.getCategoria().getId());
        
        int inseridos = ps.executeUpdate();
        
        ps.close();
        
        return inseridos > 0;
    } catch (Exception e) {
         System.out.println("FilmeDAO.inserir: " +e.getMessage());
        
        }
        return false;
    }

public static boolean alterar(Filme filme) {
    
    try {
        String sql = "UPDATE filme SET titulo=?, descricao=?, preco=?, "
                +    "numeroDias=?, diretor=?, duracao=?, categoria_id=?"
                +    "(?,?,?,?,?,?,?)";
        Connection con = Conexao.getConexao();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, filme.getTitulo());
        ps.setString(2, filme.getDescricao());
        ps.setDouble(3, filme.getPreco());
        ps.setInt(4, filme.getNumroDias());
        ps.setString(5, filme.getDiretor());
        ps.setInt(6, filme.getDuracao());
        ps.setInt(7, filme.getCategoria().getId());
        
        int alterados = ps.executeUpdate();
        
        ps.close();
        
        return alterados > 0;
    } catch (Exception e) {
         System.out.println("FilmeDAO.alterar: " +e.getMessage());
        
        }
        return false;
    }

public static boolean excluir(int id) {
    
    try {
        String sql = "DELETE FROM WHERE id = ?";
        Connection con = Conexao.getConexao();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        
        int excluidos = ps.executeUpdate();
        ps.close();
        
        return excluidos > 0;
    } catch (Exception e) {
         System.out.println("FilmeDAO.alterar: " +e.getMessage());
        
        }
        return false;
    }


}


