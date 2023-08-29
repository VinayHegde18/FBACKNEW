module feedbackapp {
	requires javafx.controls;
	requires java.sql;
	requires java.desktop;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires jasperreports;

	opens controller to javafx.fxml,javafx.graphics,javafx.base,javafx.controls,javafx.media,javafx.swt,javafx.web;
	opens model to javafx.base,javafx.graphics, javafx.fxml ,javafx.swing,javafx.media,javafx.swt,javafx.web;

}
