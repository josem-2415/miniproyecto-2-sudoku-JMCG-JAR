module com.example.sudoku {
    // 1. Módulos requeridos por JavaFX
    requires javafx.controls;
    requires javafx.fxml;

    // 2. Exportar paquetes principales
    // (Permite que el motor de JavaFX inicie tu aplicación y vea tus clases)
    exports com.example.sudoku;
    exports com.example.sudoku.controller;
    exports com.example.sudoku.view;
    exports com.example.sudoku.model;

    // 3. Abrir paquetes al motor FXML
    // (CRÍTICO: Permite que los archivos .fxml se conecten con las variables/métodos @FXML usando Reflexión)
    opens com.example.sudoku to javafx.fxml;
    opens com.example.sudoku.controller to javafx.fxml;
    opens com.example.sudoku.view to javafx.fxml;

    // Nota: El paquete 'model' no necesita un 'opens to javafx.fxml'
    // porque los archivos FXML no interactúan directamente con él,
    // toda la interacción pasa a través del 'controller'.
}