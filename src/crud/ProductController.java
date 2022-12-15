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
    private TextField name;

    @FXML
    private TextField url;

    @FXML
    private TableColumn<Product, ?> nameColumn;

    @FXML
    private TableColumn<Product, ?> urlColumn;

    @FXML
    private TableView<Product> productTable;
    
    @FXML
    private TableColumn<Category, ?> categoryName;
        
    @FXML
    private TableColumn<Category, ?> categoryDescription;
    
    @FXML
    private TableView<Category> categoryTable;

    @FXML
    private Button cadastrar;

    @FXML
    private Button editar;

    @FXML
    private Button showCategories;

    @FXML
    private Button deletar;

    @FXML
    private Button concluirEdicao;
    
    @FXML
    private Button voltar;

    private ObservableList<Product> products;

    private Product productEdit = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        sairEdicao();

        labelCategory.setVisible(false);
        categoryTable.setVisible(false);

        // Atribui o elemento a célula (dica crie um objeto fake para coisas mais
        // complexas)
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
        
        categoryName.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        ObservableList lista  = FXCollections.observableArrayList(Product.getAll());
        productTable.setItems(lista);
        
        products = productTable.getItems();
    }

    public void cadastrar() {
        limparLabel();

        if (inputsEstaoPreenchidos()) {
            Product productTemp = new Product(name.getText(), url.getText());
            
            add(productTemp);

            if (productTemp.getSuccessfullyRecorded()) {
                label.setText("Cadastrado com sucesso!");
            } else {
                label.setText("[ERRO] Não foi possível cadastrar o produto no banco de dados!");
            }
            limparInputs();
        } else {
            label.setText("[ERRO] Preencha todos os campos!");
        }
    }

    public void add(Product product) {
        products.add(product);
        product.save();
    }

    public void editar() {
        // Voce deveria testar se tem algo selecionado ^^
        limparLabel();

        if (productIsSelected()) {
            productEdit = productTable.getSelectionModel().getSelectedItem();

            name.setText(productEdit.getName());
            url.setText(productEdit.getUrl());

            entrarEdicao();// Esconde outros botoes e exibe o fim
        } else {
            avisoNaoSelecionado();
        }

    }

    public void concluirEdicao() {
        limparLabel();

        if (productIsSelected()) {
            productEdit.setName(name.getText());
            productEdit.setUrl(url.getText());

            productEdit.update();
            productTable.refresh();
            limparInputs();
            sairEdicao();
        } else {
            avisoNaoSelecionado();
        }
    }

    public void deletar() {
        limparLabel();
        if (productIsSelected()) {
            Product productToDelete = productTable.getSelectionModel().getSelectedItem();
            products.remove(productToDelete);
            productToDelete.delete();
            productTable.refresh();
        } else {
            avisoNaoSelecionado();
        }
    }

    public void showCategories() {
        limparLabel();
        if (productIsSelected()) {
            Product productToGetCategories = productTable.getSelectionModel().getSelectedItem();
            labelCategory.setVisible(false);
            categoryTable.setVisible(true);
            //ObservableList lista  = FXCollections.observableArrayList(Category.getAll(productToGetCategories.getName()));
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
        return !url.getText().isEmpty() && !name.getText().isEmpty();
    }

    public boolean productIsSelected() {
        return productTable.getSelectionModel().getSelectedItem() != null;
    }

    public void avisoNaoSelecionado() {
        label.setText("[ERRO] Selecione um veículo para editar!");
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
    
    public void goToPurchase() throws IOException {
        CRUD.setRoot("Purchase");
    }
}
