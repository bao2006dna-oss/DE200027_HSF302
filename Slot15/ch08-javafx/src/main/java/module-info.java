module com.fu.ch08 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;      // T5
    requires javafx.media;    // T5

    // 1. Mở package gốc chứa HelloApplication cho javafx.graphics để khởi tạo ứng dụng
    opens com.fu.ch08 to javafx.graphics;
    exports com.fu.ch08;

    // 2. FXMLLoader cần reflection vào controller trong gói b4
    opens com.fu.ch08.b4 to javafx.fxml;

    // 3. Export các package giao diện (b1–b3 viết bằng code, không FXML)
    exports com.fu.ch08.b1;
    exports com.fu.ch08.b2;
    exports com.fu.ch08.b3;
    exports com.fu.ch08.b4;
}