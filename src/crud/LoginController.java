
package crud;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.User;


public class LoginController implements Initializable {

    @FXML
    private TextField user;

    @FXML
    private TextField password;
    
    @FXML
    private Label error;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}    
    
    public void entrar() throws IOException {
        User usuario = new User();
        
        usuario.validateUser(user.getText(), password.getText());
        
        if(usuario.getIsValid()) {
            CRUD.setRoot("Product");
        } else {
            error.setText("[ERRO] Login inválido!");
        }
    }
    
    public void registerUser() throws IOException {
        CRUD.setRoot("UserRegistration");
    }
}
