package MODEL;


import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import ENTIDADE.Usuario;

public class UsuarioComboBoxModel implements ComboBoxModel{
	//classe  respónsavel por exibir os dados de um combobox
	
			//Lista de itens que serão exibidos
			private ArrayList<Usuario> dados;
			//Objeto curso que será selecionado
			private Usuario cliente;

			//Construtor que recebe um lista de curso como parâmetro
			public UsuarioComboBoxModel(ArrayList<Usuario>c){
				dados = c;

			}

			@Override
			public Object getElementAt(int indice) {

				return dados.get(indice);
			}

			@Override
			public int getSize() {

				return dados.size();
			}

			@Override
			public Object getSelectedItem() {

				return cliente;
			}

			@Override
			public void setSelectedItem(Object item) {
				cliente = (Usuario)item;

			}
			@Override
			public void addListDataListener(ListDataListener arg0) {
				// TODO Auto-generated method stub

			}
			@Override
			public void removeListDataListener(ListDataListener arg0) {
				// TODO Auto-generated method stub
			}
	
}
