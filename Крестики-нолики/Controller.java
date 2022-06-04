package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private AnchorPane scene;

    @FXML
    private Text winnerText;

    ArrayList<Button> buttons;
    private int playerTurn = 0;


    @FXML
    void restartGame(ActionEvent event) {
        buttons.forEach(button ->{
            button.setDisable(false);
            button.setText("");
        });
        winnerText.setText("Хрестики-нулики");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));
        buttons.forEach(button ->{
            setupButton(button);
        });
    }
    private void setupButton(Button button){
        button.setOnMouseClicked(MouseEvent ->{
            setPlayerSymbol(button);
            button.setDisable(true);
            checkIfGameOver();
        });
    }
    private void setPlayerSymbol(Button button){
        if(playerTurn % 2 == 0){
            button.setText("X");
            playerTurn = 1;
        }else{
            button.setText("O");
            playerTurn = 0;
        }
    }
    public void checkIfGameOver(){

        for(int i = 0; i < 8; i++){
            String[] lines = new String[8];
            switch (i){
                case 0: lines[0] = button1.getText() + button2.getText() + button3.getText();
                case 1: lines[1] = button4.getText() + button5.getText() + button6.getText();
                case 2: lines[2] = button7.getText() + button8.getText() + button9.getText();
                case 3: lines[3] = button1.getText() + button4.getText() + button7.getText();
                case 4: lines[4] = button2.getText() + button5.getText() + button8.getText();
                case 5: lines[5] = button3.getText() + button6.getText() + button9.getText();
                case 6: lines[6] = button1.getText() + button5.getText() + button9.getText();
                case 7: lines[7] = button3.getText() + button5.getText() + button7.getText();
            };
            if(lines[i].equals("XXX")){
                winnerText.setText("X виграв!");
                buttonsDisabled();
            }
            if(lines[i].equals("OOO")){
                winnerText.setText("O виграв!");
                buttonsDisabled();
            }
        }
    }
    public void buttonsDisabled(){
        buttons.forEach(button ->{
            button.setDisable(true);
        });
    }
}
