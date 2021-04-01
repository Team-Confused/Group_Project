module Todo.Manager.app.main {


    requires static lombok;
    requires java.logging;


    exports Todo_Manager;
    opens Todo_Manager;
    requires com.google.gson;
    requires com.google.common;


    requires javafx.controls;
    requires javafx.graphics;


}