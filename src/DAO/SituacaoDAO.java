package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BD.Conexao;
import ENTIDADE.Situacao;


public class SituacaoDAO {
	public ArrayList<Situacao> select(){
		ArrayList<Situacao> lista = new ArrayList<Situacao>();

		try {

			Connection con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM SITUACAO");
			ResultSet rs = ps.executeQuery();

			//0
			while (rs.next()) {
				Situacao p = new Situacao();
				p.setCod_situacao(rs.getInt("COD_SITUACAO"));
				p.setDescricao_situacao(rs.getString("DESCRICAO_SITUACAO"));
				lista.add(p);
						
			}
			return lista;
		}catch (SQLException e){
			e.printStackTrace();
			return lista;	

		}

	}
}
