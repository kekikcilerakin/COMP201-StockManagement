module com.akn.projectcomp202 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.akn.projectcomp202 to javafx.fxml;
    exports com.akn.projectcomp202;
}