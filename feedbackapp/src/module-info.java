module feedbackapp {
	requires javafx.controls;
	requires java.sql;
	requires java.desktop;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires itextpdf;
	requires jakarta.persistence;

	opens controller to javafx.graphics, javafx.fxml;
	opens model to javafx.base,javafx.graphics, javafx.fxml;
}
