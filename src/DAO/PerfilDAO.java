package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import BD.Conexao;
import ENTIDADE.Perfil;
import ENTIDADE.Usuario;

public class PerfilDAO {
	public ArrayList<Perfil> select() {
		ArrayList<Perfil> lista = new ArrayList<Perfil>();

		try {

			Connection con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM PERFIL");
			ResultSet rs = ps.executeQuery();

			// 0
			while (rs.next()) {
				Perfil p = new Perfil();
				p.setCod_perfil(rs.getInt("COD_PERFIL"));
				p.setDescricao_perfil(rs.getString("DESCRICAO_PERFIL"));
				lista.add(p);

			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			return lista;

		}

	}

	public Usuario verificaTipoUsuario(String cod_perfil, String cod_usuario) {
		try (Connection conex = Conexao.getConexao();
				PreparedStatement sqlSelect = conex
						.prepareStatement("SELECT * FROM USUARIO WHERE COD_PERFIL = ? AND COD_USUARIO = ?")) {
			sqlSelect.setString(1, cod_perfil);
			sqlSelect.setString(2, cod_usuario);
			try (ResultSet sqlResult = sqlSelect.executeQuery()) {
				while (sqlResult.next()) {
					Usuario usu = new Usuario();
					usu.setEmail(sqlResult.getString("COD_USUARIO"));
					usu.setSenha(sqlResult.getString("COD_PERFIL"));
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

		PerfilDAO uDAO = new PerfilDAO();
		Usuario usuario = null;
		usuario = uDAO.verificaTipoUsuario("2", "2");
		if (usuario == null) {
			JOptionPane.showMessageDialog(null, "Usuario é administrador!");
		} else {
			JOptionPane.showMessageDialog(null, "Usuario é basico");
		}

	}
}
