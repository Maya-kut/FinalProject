package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.AuthorizationPage;
import testData.AuthorizationTestData;

@Owner("Maiya_Lys")
@Tag("web")
@DisplayName("Тесты на авторизацию в Хабр")
public class AuthorizationTests extends TestBase {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    AuthorizationTestData authorizationTestData = new AuthorizationTestData();

    @Test
    @DisplayName("Успешная авторизация")
    void succesfullAuthTest() {
        authorizationPage.openAuthorizationPage()
                .setEmail(authorizationTestData.userEmail)
                .setPassword(authorizationTestData.userPassword)
                .passCaptcha()
                .pressSubmitButton();
        authorizationPage.authorizationSuccessCheck();
    }

    @Test
    @DisplayName("Проверка получения сообщения об ошибке при непроставлении чекбокса 'Я не робот'")
    void iAmRobotTest() {
        authorizationPage.openAuthorizationPage()
                .setEmail(authorizationTestData.userEmail)
                .setPassword(authorizationTestData.userPassword)
                .pressSubmitButton();

        authorizationPage.captchaErrorCheck();
    }

    @Test
    @DisplayName("Проверка получения сообщения об ошибке при попытке авторизации без логина")
    void authorizationWithOutEmailTest() {
        authorizationPage.openAuthorizationPage()
                .setPassword(authorizationTestData.userPassword)
                .passCaptcha()
                .pressSubmitButton();
        authorizationPage.emailErrorCheck();
    }

    @Test
    @DisplayName("Проверка получения сообщения об ошибке при попытке авторизации без пароля")
    void authorizationWithOutPasswordTest() {
        authorizationPage.openAuthorizationPage()
                .setEmail(authorizationTestData.userEmail)
                .passCaptcha()
                .pressSubmitButton();

        authorizationPage.passwordErrorCheck();

    }
}
