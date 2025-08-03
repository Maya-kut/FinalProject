package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class UITest extends TestBase {
    @Test
    void mainPage() {
        step("Open form", () -> {
            open();
//            executeJavaScript("$('#fixedban').remove()");
//            executeJavaScript("$('footer').remove()");
        });
    }
}