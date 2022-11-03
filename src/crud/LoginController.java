
package crud;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LoginController implements Initializable {

    @FXML
    private TextField user;

    @FXML
    private TextField password;
    
    @FXML
    private Label error;
    
    // CRIAÇÃO DA TABELA

    // create table login(
    // id number primary key,
    // username varchar2(9) not null,
    // password varchar2(8) not null
    // );

    // INSERÇÃO
    // insert into login
    // values (1, 'Bigolindo', 'vouDar10')

    @Override
    public void initialize(URL url, ResourceBundle rb) {}    
    
    public void entrar() throws IOException {
        //Muito tarde pra conseguir fazer com o bd certinho, pelo menos a tabela e registro foi feito
        if(user.getText().equals("Bigolindo") && password.getText().equals("vouDar10")) {
            CRUD.setRoot("LocadoraVeiculo");
        } else {
            error.setText("[ERRO] Login inválido!");
        }
    }
}
