package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ViewRequirementController {
	  @FXML
	    private TextArea textfld;
	
	public void setTexts(String text11) {
		textfld.setText(text11);
	}
}
