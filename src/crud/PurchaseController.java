package crud;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Category;
import model.Product;

public class ProductController implements Initializable {

    @FXML
    private Label label;
    
    @FXML
    private Label labelCategory;

    @FXML
    private TextField datePurchase;

    @FXML
    private TableColumn<Purchase, ?> dateColumn;

    @FXML
    private TableView<Purchase> purchaseTable;
    
    @FXML
    private TableColumn<Product, ?> productName;
        
    @FXML
    private TableColumn<Product, ?> productUrl;
    
    @FXML
    private TableView<Product> productTable;

    @FXML
    private Button cadastrar;

    @FXML
    private Button editar;

    @FXML
    private Button showProducts;

    @FXML
    private Button deletar;

    @FXML
    private Button concluirEdicao;
    
    @FXML
    private Button voltar;

    private ObservableList<Purchase> purchases;

    private Purchase purchaseEdit = null;

//Logo quando criar ou algo assim poder selecionar algum produto

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        sairEdicao();

        labelCategory.setVisible(false);
        categoryTable.setVisible(false);

        // Atribui o elemento a célula (dica crie um objeto fake para coisas mais
        // complexas)
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("datePurchase"));
        
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productUrl.setCellValueFactory(new PropertyValueFactory<>("url"));
        
        ObservableList lista  = FXCollections.observableArrayList(Purchase.getAll());
        purchaseTable.setItems(lista);
        
        purchases = purchaseTable.getItems();
    }

    public void cadastrar() {
        limparLabel();

        if (inputsEstaoPreenchidos()) {
            Purchase purchaseTemp = new Purchase(datePurchase.getText());
            
            add(purchaseTemp);

            if (purchaseTemp.getSuccessfullyRecorded()) {
                label.setText("Cadastrado com sucesso!");
            } else {
                label.setText("[ERRO] Não foi possível cadastrar a compra no banco de dados!");
            }
            limparInputs();
        } else {
            label.setText("[ERRO] Preencha todos os campos!");
        }
    }

    public void add(Purchase purchase) {
        purchases.add(purchase);
        purchase.save();
    }

    public void editar() {
        // Voce deveria testar se tem algo selecionado ^^
        limparLabel();

        if (purchaseIsSelected()) {
            purchaseEdit = purchaseTable.getSelectionModel().getSelectedItem();

            name.setText(purchaseEdit.getDate());

            entrarEdicao();// Esconde outros botoes e exibe o fim
        } else {
            avisoNaoSelecionado();
        }

    }

    public void concluirEdicao() {
        limparLabel();

        if (purchaseIsSelected()) {
            purchaseEdit.setDate(datePurchase.getText());

            purchaseEdit.update();
            purchaseTable.refresh();
            limparInputs();
            sairEdicao();
        } else {
            avisoNaoSelecionado();
        }
    }

    public void deletar() {
        limparLabel();
        if (purchaseIsSelected()) {
            Purchase purchaseToDelete = purchaseTable.getSelectionModel().getSelectedItem();
            purchases.remove(purchaseToDelete);
            purchaseToDelete.delete();
            purchaseTable.refresh();
        } else {
            avisoNaoSelecionado();
        }
    }

    public void showProducts() {
        limparLabel();
        if (purchaseIsSelected()) {
            Purchase purchaseToGetCategories = purchaseTable.getSelectionModel().getSelectedItem();
            labelCategory.setVisible(false);
            productTable.setVisible(true);
            //ObservableList lista  = FXCollections.observableArrayList(Category.getAll(purchaseToGetCategories.getName()));
            //categoryTable.setItems(lista);
        } else {
            avisoNaoSelecionado();
        }
    }

    public void limparInputs() {
        url.setText("");
        name.setText("");
    }

    public void limparLabel() {
        label.setText("");
    }

    public boolean inputsEstaoPreenchidos() {
        return !datePurchase.getText().isEmpty();
    }

    public boolean purchaseIsSelected() {
        return purchaseTable.getSelectionModel().getSelectedItem() != null;
    }

    public void avisoNaoSelecionado() {
        label.setText("[ERRO] Selecione uma compra para editar!");
    }

    public void sairEdicao() {
        limparInputs();

        concluirEdicao.setVisible(false);
        voltar.setVisible(false);

        cadastrar.setVisible(true);
        editar.setVisible(true);
        deletar.setVisible(true);
    }

    public void entrarEdicao() {
        concluirEdicao.setVisible(true);
        voltar.setVisible(true);

        cadastrar.setVisible(false);
        editar.setVisible(false);
        deletar.setVisible(false);
    }

    public void goToCategory() throws IOException {
        CRUD.setRoot("Category");
    }
    
    public void goToProduct() throws IOException {
        CRUD.setRoot("Product");
    }
}
