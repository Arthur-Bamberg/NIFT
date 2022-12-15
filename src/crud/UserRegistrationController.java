/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package crud;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.User;

/**
 * FXML Controller class
 *
 * @author Arthur
 */
public class UserRegistrationController implements Initializable {
    
    @FXML
    private TextField name;
    
    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void register() throws IOException {
        User usuario = new User(name.getText(), email.getText(), password.getText());
        usuario.save();
        CRUD.setRoot("Products");
    }
    
}
