// package crud;

// import java.io.IOException;
// import java.net.URL;
// import java.util.ResourceBundle;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.fxml.FXML;
// import javafx.fxml.Initializable;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.RadioButton;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.scene.control.TextField;
// import javafx.scene.control.cell.PropertyValueFactory;
// import model.Carro;
// import model.ManutTelaLocacao;
// import model.Moto;
// import model.Veiculo;

// public class LocadoraVeiculoController implements Initializable {

//     @FXML
//     private Label label;
    
//         @FXML
//     private Label labelManutencao;

//     @FXML
//     private TextField placaInput;

//     @FXML
//     private TextField modeloInput;

//     @FXML
//     private TextField precoInput;

//     @FXML
//     private TableColumn<Veiculo, ?> modelo;

//     @FXML
//     private TableColumn<Veiculo, ?> placaColuna;

//     @FXML
//     private TableColumn<Veiculo, ?> precoColuna;

//     @FXML
//     private TableColumn<Veiculo, ?> tipoColuna;

//     @FXML
//     private TableView<Veiculo> tabelaVeiculos;
    
//     @FXML
//     private TableColumn<ManutTelaLocacao, ?> nomeMecanico;
        
//     @FXML
//     private TableColumn<ManutTelaLocacao, ?> especialidade;
    
//     @FXML
//     private TableView<ManutTelaLocacao> tabelaManutencoes;

//     @FXML
//     private RadioButton radioMoto;

//     @FXML
//     private RadioButton radioCarro;

//     @FXML
//     private Button cadastrar;

//     @FXML
//     private Button editar;

//     @FXML
//     private Button mostrarManutencoes;

//     @FXML
//     private Button deletar;

//     @FXML
//     private Button concluirEdicao;
    
//     @FXML
//     private Button voltar;

//     private ObservableList<Veiculo> veiculos;

//     private Veiculo veiculoEdicao = null;

//     @Override
//     public void initialize(URL url, ResourceBundle rb) {

//         sairEdicao();

//         labelManutencao.setVisible(false);
//         tabelaManutencoes.setVisible(false);

//         // Atribui o elemento a célula (dica crie um objeto fake para coisas mais
//         // complexas)
//         modelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
//         placaColuna.setCellValueFactory(new PropertyValueFactory<>("placa"));
//         precoColuna.setCellValueFactory(new PropertyValueFactory<>("preco"));
//         tipoColuna.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        
//         nomeMecanico.setCellValueFactory(new PropertyValueFactory<>("nomeMecanico"));
//         especialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));
        
//         ObservableList lista  = FXCollections.observableArrayList(Veiculo.getAll());
//         tabelaVeiculos.setItems(lista);
        
//         veiculos = tabelaVeiculos.getItems();// Vincula a lista da tabelaVeiculos a lista manipulavel
//     }

//     public void cadastrar() {
//         limparLabel();

//         if (inputsEstaoPreenchidos() && placaInput.getText().length() <= 8) {
//             Veiculo veiculoTemp;

//             if (radioMoto.isSelected()) {
//                 veiculoTemp = new Moto(placaInput.getText(), modeloInput.getText(),
//                         Double.parseDouble(precoInput.getText()));
//             } else {
//                 veiculoTemp = new Carro(placaInput.getText(), modeloInput.getText(),
//                         Double.parseDouble(precoInput.getText()));
//             }
            
//             add(veiculoTemp);

//             if (veiculoTemp.getSuccessfullyRecorded()) {
//                 label.setText("Cadastrado com sucesso!");
//             } else {
//                 label.setText("Veículo!");
//             }
//             limparInputs();
//         } else {
//             if (!inputsEstaoPreenchidos()) {
//                 label.setText("[ERRO] Preencha todos os campos!");
//             } else {
//                 label.setText("[ERRO] A placa pode ter no máximo 8 dígitos!");
//             }
//         }
//     }

//     public void add(Veiculo veiculo) {
//         veiculos.add(veiculo);
//         veiculo.save();
//     }

//     public void editar() {
//         // Voce deveria testar se tem algo selecionado ^^
//         limparLabel();

//         if (veiculoEstaSelecionado()) {
//             veiculoEdicao = tabelaVeiculos.getSelectionModel().getSelectedItem();// pega o veiculo selecionado

//             modeloInput.setText(veiculoEdicao.getModelo());
//             placaInput.setText(veiculoEdicao.getPlaca());
//             precoInput.setText(String.valueOf(veiculoEdicao.getPreco()));

//             entrarEdicao();// Esconde outros botoes e exibe o fim
//         } else {
//             avisoNaoSelecionado();
//         }

//     }

//     public void concluirEdicao() {
//         limparLabel();

//         if (veiculoEstaSelecionado()) {
//             veiculoEdicao.setModelo(modeloInput.getText());
//             veiculoEdicao.setPlaca(placaInput.getText());
//             veiculoEdicao.setPreco(Double.parseDouble(precoInput.getText()));

//             veiculoEdicao.update();
//             tabelaVeiculos.refresh();
//             limparInputs();
//             sairEdicao();
//         } else {
//             avisoNaoSelecionado();
//         }
//     }

//     public void deletar() {
//         limparLabel();
//         if (veiculoEstaSelecionado()) {
//             Veiculo v = tabelaVeiculos.getSelectionModel().getSelectedItem();// pega o veiculo selecionado
//             veiculos.remove(v);
//             v.delete();
//             tabelaVeiculos.refresh();
//         } else {
//             avisoNaoSelecionado();
//         }
//     }

//     public void mostrarManutencoes() {
//         limparLabel();
//         if (veiculoEstaSelecionado()) {
//             Veiculo v = tabelaVeiculos.getSelectionModel().getSelectedItem();// pega o veiculo selecionado
//             labelManutencao.setVisible(false);
//             tabelaManutencoes.setVisible(true);
//             ObservableList lista  = FXCollections.observableArrayList(ManutTelaLocacao.getAll(v.getModelo()));
//             tabelaManutencoes.setItems(lista);
//         } else {
//             avisoNaoSelecionado();
//         }
//     }

//     public void limparInputs() {
//         placaInput.setText("");
//         modeloInput.setText("");
//         precoInput.setText("");
//     }

//     public void limparLabel() {
//         label.setText("");
//     }

//     public boolean inputsEstaoPreenchidos() {
//         return !placaInput.getText().isEmpty() && !modeloInput.getText().isEmpty() && !precoInput.getText().isEmpty();
//     }

//     public boolean veiculoEstaSelecionado() {
//         return tabelaVeiculos.getSelectionModel().getSelectedItem() != null;
//     }

//     public void avisoNaoSelecionado() {
//         label.setText("[ERRO] Selecione um veículo para editar!");
//     }

//     public void sairEdicao() {
//         limparInputs();

//         concluirEdicao.setVisible(false);
//         voltar.setVisible(false);

//         radioMoto.setVisible(true);
//         radioCarro.setVisible(true);

//         cadastrar.setVisible(true);
//         editar.setVisible(true);
//         deletar.setVisible(true);
//     }

//     public void entrarEdicao() {
//         concluirEdicao.setVisible(true);
//         voltar.setVisible(true);

//         radioMoto.setVisible(false);
//         radioCarro.setVisible(false);

//         cadastrar.setVisible(false);
//         editar.setVisible(false);
//         deletar.setVisible(false);
//     }

//     public void irTelaFuncionario() throws IOException {
//         CRUD.setRoot("Funcionario");
//     }
    
//     public void irTelaManutencao() throws IOException {
//         CRUD.setRoot("Manutencao");
//     }
    
//     public void irTelaLocacao() throws IOException {
//         CRUD.setRoot("Locacao");
//     }
// }
