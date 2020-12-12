package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.calculations.*;

import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.util.ArrayList;

public class Controller { // TODO WSZYSTKO :(

    public Calculation calculation;
    @FXML
    private TextField argumentsTextField;
    @FXML
    private Label latestResultTextLabel;
    @FXML
    private Label currentOperationLabel;

    private ToggleGroup calcSystem;
    @FXML
    private RadioButton hexRadioBtn;
    @FXML
    private RadioButton decRadioBtn;
    @FXML
    private RadioButton binRadioBtn;

    private int mode;

    public Controller(){
        System.out.println("Działa");

    }

    @FXML
    void initialize(){
        mode = 2;
        argumentsTextField.setDisable(true);
        calculation = new Calculation(mode);
        calcSystem = new ToggleGroup();
        hexRadioBtn.setToggleGroup(calcSystem);
        decRadioBtn.setToggleGroup(calcSystem);
        binRadioBtn.setToggleGroup(calcSystem);
        binRadioBtn.setSelected(true);
        setBinButtons(true);
        setDecButtons(false);
        setHexButtons(false);
    }

    @FXML
    public void resetMouseClicked(){
        calculation = new Calculation(mode);
        latestResultTextLabel.setText("");
        currentOperationLabel.setText("");
        argumentsTextField.setText("");
    }
    @FXML
    public void removeMouseClicked()
    {
        if(calculation.getCurrentArgument().equals("")) return;
        calculation.setCurrentArgument(calculation.getCurrentArgument().substring(0, calculation.getCurrentArgument().length()-1));
        argumentsTextField.setText(calculation.getCurrentArgument());
    }

    @FXML
    public void signChangeMouseClicked()
    {
        if(calculation.getCurrentArgument().equals("")){
            calculation.setCurrentArgument("-"+calculation.getCurrentArgument());
            argumentsTextField.setText(calculation.getCurrentArgument());
            return;
        }
        if(calculation.getCurrentArgument().charAt(0) == '!'){
            calculation.setCurrentArgument(calculation.getCurrentArgument().replace('!', '-'));
        }else if(calculation.getCurrentArgument().charAt(0) == '-'){
            calculation.setCurrentArgument(calculation.getCurrentArgument().substring(1));
        }else{
            calculation.setCurrentArgument("-"+calculation.getCurrentArgument());
        }
        argumentsTextField.setText(calculation.getCurrentArgument());
    }

    @FXML
    public void factoryMouseClicked()
    {
        if(calculation.getCurrentArgument().equals("")){
            calculation.setCurrentArgument("!"+calculation.getCurrentArgument());
            argumentsTextField.setText(calculation.getCurrentArgument());
            return;
        }
        if(calculation.getCurrentArgument().charAt(0) == '!'){
            return;
        }else if(calculation.getCurrentArgument().charAt(0) == '-'){
            calculation.setCurrentArgument(calculation.getCurrentArgument().replace('-', '!'));
        }else{
            calculation.setCurrentArgument("!"+calculation.getCurrentArgument());
        }
        argumentsTextField.setText(calculation.getCurrentArgument());
    }

    @FXML
    public void changeOnDecAction()
    {
        int prevMode = mode;
        mode = 10;
        setSystem(prevMode);
        setDecButtons(true);
        setHexButtons(false);
    }

    @FXML
    public void changeOnHexAction()
    {
        int prevMode = mode;
        mode = 16;
        setSystem(prevMode);
        setHexButtons(true);
    }

    @FXML
    public void changeOnBinAction()
    {
        int prevMode = mode;
        mode = 2;
        setSystem(prevMode);
        setBinButtons(true);
        setDecButtons(false);
        setHexButtons(false);
    }

    @FXML
    public void calcMouseClicked()
    {
        if(calculation.getLatestResult() == null) return;
        refreshCalculation();
        refreshTextFields();
        currentOperationLabel.setText("=");
        calculation = new Calculation(mode);
    }

    @FXML
    public void numberMouseClicked(MouseEvent mouseEvent)
    {
        calculation.setCurrentArgument(calculation.getCurrentArgument() + ((Button)mouseEvent.getSource()).getText());
        argumentsTextField.setText(calculation.getCurrentArgument());
    }

    @FXML
    public void addMouseClicked()
    {
        handleAdd();
    }

    @FXML
    public void substractionMouseClicked()
    {
        handleSubstract();
    }

    @FXML
    public void multMouseClicked()
    {
        handleMult();
    }

    @FXML
    public void divMouseClicked()
    {
        handleDiv();
    }
    public void handleAdd()
    {
        refreshCalculation();
        calculation.setOperation(new AddOperation(new Argument(calculation.getLatestResult(), calculation.getMode()), calculation.getMode()));
        refreshTextFields();
    }

    public void handleSubstract()
    {
        refreshCalculation();
        calculation.setOperation(new SubstractOperation(new Argument(calculation.getLatestResult(), calculation.getMode()), calculation.getMode()));
        refreshTextFields();
    }

    public void handleMult()
    {
        refreshCalculation();
        calculation.setOperation(new MultOperation(new Argument(calculation.getLatestResult(), calculation.getMode()), calculation.getMode()));
        refreshTextFields();
    }

    public void handleDiv()
    {
        refreshCalculation();
        calculation.setOperation(new DivOperation(new Argument(calculation.getLatestResult(), calculation.getMode()), calculation.getMode()));
        refreshTextFields();
    }

    public void refreshCalculation(){
        if(calculation.getLatestResult() == null)
        {
            calculation.setLatestResult(new Argument(calculation.getCurrentArgument(), calculation.getMode()).toString());
        }else{
            if(calculation.getCurrentArgument().equals("")) return;
            calculation.getOperation().setArg2(new Argument(calculation.getCurrentArgument(), calculation.getMode()));
            calculation.setLatestResult(calculation.getOperation().execute().toString(calculation.getMode()));

        }
    }

    public void refreshTextFields()
    {
        calculation.setCurrentArgument("");
        argumentsTextField.setText(calculation.getCurrentArgument());
        latestResultTextLabel.setText(calculation.getLatestResult());
        currentOperationLabel.setText(calculation.getOperation().getSign());
    }

    private void setSystem(int prevMode)
    {
        if(calculation.getLatestResult() == null)
        {
            if(!latestResultTextLabel.getText().equals("")){
                latestResultTextLabel.setText(Calculation.convertResult(latestResultTextLabel.getText(), prevMode, mode));
            }
            calculation = new Calculation(mode);
            argumentsTextField.setText(calculation.getCurrentArgument());

        }else{
            int previousMode = calculation.getMode();
            String previousLatestResult = calculation.getLatestResult();
            Operation previousOperation = calculation.getOperation();

            calculation = new Calculation(mode);
            //calculation.setLatestResult((new BigInteger(previousLatestResult, previousMode)).toString());
            calculation.setLatestResult(Calculation.convertResult(previousLatestResult, previousMode, mode));
            calculation.setOperation(previousOperation);

            refreshTextFields();
        }

        System.out.println(calculation);
    }


    @FXML
    private Button oneBtn;
    @FXML
    private Button twoBtn;
    @FXML
    private Button threeBtn;
    @FXML
    private Button fourBtn;
    @FXML
    private Button fiveBtn;
    @FXML
    private Button sixBtn;
    @FXML
    private Button sevenBtn;
    @FXML
    private Button eightBtn;
    @FXML
    private Button nineBtn;
    @FXML
    private Button zeroBtn;
    @FXML
    private Button ABtn;
    @FXML
    private Button BBtn;
    @FXML
    private Button CBtn;
    @FXML
    private Button DBtn;
    @FXML
    private Button EBtn;
    @FXML
    private Button FBtn;
    @FXML
    private Button factoryBtn;


    private void setBinButtons(boolean turned)
    {
        if(turned){
            zeroBtn.setDisable(false);
            zeroBtn.setOpacity(1);
            oneBtn.setDisable(false);
            oneBtn.setOpacity(1);
        }else{
            zeroBtn.setDisable(true);
            zeroBtn.setOpacity(0.5);
            oneBtn.setDisable(true);
            oneBtn.setOpacity(0.5);
        }
    }

    private void setDecButtons(boolean turned)
    {
        if(turned){
            setBinButtons(turned);
            twoBtn.setDisable(false);
            twoBtn.setOpacity(1);
            threeBtn.setDisable(false);
            threeBtn.setOpacity(1);
            fourBtn.setDisable(false);
            fourBtn.setOpacity(1);
            fiveBtn.setDisable(false);
            fiveBtn.setOpacity(1);
            sixBtn.setDisable(false);
            sixBtn.setOpacity(1);
            sevenBtn.setDisable(false);
            sevenBtn.setOpacity(1);
            eightBtn.setDisable(false);
            eightBtn.setOpacity(1);
            nineBtn.setDisable(false);
            nineBtn.setOpacity(1);
            factoryBtn.setDisable(false);
            factoryBtn.setOpacity(1);
        }else{
            twoBtn.setDisable(true);
            twoBtn.setOpacity(0.5);
            threeBtn.setDisable(true);
            threeBtn.setOpacity(0.5);
            fourBtn.setDisable(true);
            fourBtn.setOpacity(0.5);
            fiveBtn.setDisable(true);
            fiveBtn.setOpacity(0.5);
            sixBtn.setDisable(true);
            sixBtn.setOpacity(0.5);
            sevenBtn.setDisable(true);
            sevenBtn.setOpacity(0.5);
            eightBtn.setDisable(true);
            eightBtn.setOpacity(0.5);
            nineBtn.setDisable(true);
            nineBtn.setOpacity(0.5);
            factoryBtn.setDisable(true);
            factoryBtn.setOpacity(0.5);
        }
    }

    public void setHexButtons(boolean turned)
    {
        if(turned)
        {
            setBinButtons(turned);
            setDecButtons(turned);
            ABtn.setOpacity(1);
            ABtn.setDisable(false);
            BBtn.setOpacity(1);
            BBtn.setDisable(false);
            CBtn.setOpacity(1);
            CBtn.setDisable(false);
            DBtn.setOpacity(1);
            DBtn.setDisable(false);
            EBtn.setOpacity(1);
            EBtn.setDisable(false);
            FBtn.setOpacity(1);
            FBtn.setDisable(false);
        }else{
            ABtn.setOpacity(0.5);
            ABtn.setDisable(true);
            BBtn.setOpacity(0.5);
            BBtn.setDisable(true);
            CBtn.setOpacity(0.5);
            CBtn.setDisable(true);
            DBtn.setOpacity(0.5);
            DBtn.setDisable(true);
            EBtn.setOpacity(0.5);
            EBtn.setDisable(true);
            FBtn.setOpacity(0.5);
            FBtn.setDisable(true);
        }
    }

}
