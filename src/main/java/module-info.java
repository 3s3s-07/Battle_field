module com.example.battle_graphics {

    // الوحدات المطلوبة
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    // 1. تصدير الحزمة التي تحتوي على فئات الـ UI الرئيسية (App, GameController, InputHandler)
    // هذا هو الحل لخطأ IllegalAccessException
    exports com.example.battle_graphics.fx;

    // 2. تصدير الحزمة التي تحتوي على فئات الـ OOP الأساسية (Fighter, Weapon, Projectile)
    // هذا ضروري إذا كنت تستخدم هذه الفئات في حزم أخرى
    exports com.example.battle_graphics.base;

    // 3. فتح الحزمة للـ Reflection، ضروري لـ FXML
    // إذا كان لديك ملفات FXML في مسار الحزمة الرئيسية
    opens com.example.battle_graphics to javafx.fxml;
}