package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import BD.Conexao;
import ENTIDADE.Perfil;
import ENTIDADE.Situacao;
import ENTIDADE.Usuario;

public class UsuarioDAO {
	public boolean insert(Usuario usuario) {
		try {
			Connection con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO USUARIO (NOME,EMAIL,SENHA,EMPRESA,COD_PERFIL,COD_SITUACAO) VALUES (?,?,?,?,?,?)");

			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getSenha());
			ps.setString(4, usuario.getEmpresa());
			ps.setInt(5, usuario.getPerfil().getCod_perfil());
			ps.setInt(6, usuario.getSituacao().getCod_situacao());
			ps.execute();

			return true;
		} catch (SQLException sql) {
			sql.printStackTrace();

			return false;
		}
	}

	public boolean update(Usuario usuario) {
		try {
			Connection con = Conexao.getConexao();
			PreparedStatement ps = con
					.prepareStatement("UPDATE USUARIO SET NOME = ? , EMAIL = ? ,SENHA = ? ,EMPRESA = ? "
							+ ",COD_PERFIL = ? , COD_SITUACAO = ? WHERE COD_USUARIO = ?");
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getSenha());
			ps.setString(4, usuario.getEmpresa());
			ps.setInt(5, usuario.getPerfil().getCod_perfil());
			ps.setInt(6, usuario.getSituacao().getCod_situacao());
			ps.setInt(7, usuario.getCod_usuario());

			if (ps.executeUpdate() > 0) {
				return true;

			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(int codigo) {
		try {
			Connection con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement("DELETE FROM USUARIO WHERE COD_USUARIO = ?");
			ps.setInt(1, codigo);
			if (ps.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Usuario> select() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		try {

			Connection con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM USUARIO");
			ResultSet rs = ps.executeQuery();

			// 0
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setNome(rs.getString("NOME"));
				u.setEmail(rs.getString("EMAIL"));
				u.setSenha(rs.getString("SENHA"));
				u.setEmpresa(rs.getString("EMPRESA"));
				u.setCod_usuario(rs.getInt("COD_USUARIO"));

				Perfil p = new Perfil();
				p.setCod_perfil(rs.getInt("COD_PERFIL"));
				

				Situacao s = new Situacao();
				s.setCod_situacao(rs.getInt("COD_SITUACAO"));
			
				u.setPerfil(p);
				u.setSituacao(s);
				lista.add(u);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			return lista;

		}

	}

	public Usuario buscarUsuario(String usuario, String senha) {
		try (Connection conex = Conexao.getConexao();
				PreparedStatement sqlSelect = conex
						.prepareStatement("SELECT * FROM USUARIO " + "WHERE EMAIL = ? AND SENHA = ?")) {
			sqlSelect.setString(1, usuario);
			sqlSelect.setString(2, senha);
			try (ResultSet sqlResult = sqlSelect.executeQuery()) {
				while (sqlResult.next()) {
					Usuario usu = new Usuario();
					usu.setEmail(sqlResult.getString("EMAIL"));
					usu.setSenha(sqlResult.getString("SENHA"));
					return usu;
				}
			}
			return null;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;

		}
	}
	
	public Usuario verrificaAtivo(String email) {
		try (Connection conex = Conexao.getConexao();
				PreparedStatement sqlSelect = conex
						.prepareStatement("SELECT COD_SITUACAO FROM USUARIO WHERE EMAIL = ?")) {
			sqlSelect.setString(1, email);
			try (ResultSet sqlResult = sqlSelect.executeQuery()) {
				while (sqlResult.next()) {
					Usuario usu = new Usuario();
					usu.setEmail(sqlResult.getString("COD_SITUACAO"));
					return usu;
				}
			}
			return null;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;

		}
	}

	public static void main(String[] args) {
		UsuarioDAO uDAO = new UsuarioDAO();
		Usuario usuario = null;
		usuario = uDAO.buscarUsuario("teste@teste.com", "123");
		if (usuario == null) {
			JOptionPane.showMessageDialog(null, "Usuario ou senha ivalidos!");
		} else {
			JOptionPane.showMessageDialog(null, "Usuario encontrado");
		}
	
//		UsuarioDAO uDAO = new UsuarioDAO();
//		Usuario usuario = null;
//		usuario = uDAO.verrificaAtivo("teste@teste.com");
//	
//		if (usuario.equals(0)){
//			JOptionPane.showMessageDialog(null, "Usuario ou senha ivalidos!");
//		} else {
//			JOptionPane.showMessageDialog(null, "Usuario encontrado");
//		}
	
	}
}
