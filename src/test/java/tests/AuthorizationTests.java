package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.AuthorizationPage;
import testData.AuthorizationTestData;

@Tag("web")
@DisplayName("Тесты на авторизацию в Хабр")
public class AuthorizationTests extends TestBase {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    AuthorizationTestData authorizationTestData = new AuthorizationTestData();

    @Test
    @DisplayName("Успешная авторизация")
    void succesfullAuth() {
        authorizationPage.openAuthorizationPage()
                .setEmail(authorizationTestData.userEmail)
                .setPassword(authorizationTestData.userPassword)
                .passCaptcha()
                .pressSubmitButton();
        authorizationPage.authorizationSuccessCheck();
    }

    @Test
    @DisplayName("Проверка получения сообщения об ошибке при непроставлении чекбокса 'Я не робот'")
    void iAmRobot() {
        authorizationPage.openAuthorizationPage()
                .setEmail(authorizationTestData.userEmail)
                .setPassword(authorizationTestData.userPassword)
                .pressSubmitButton();

        authorizationPage.captchaErrorCheck();
    }

    @Test
    @DisplayName("Проверка получения сообщения об ошибке при попытке авторизации без логина")
    void authorizationWithOutEmail() {
        authorizationPage.openAuthorizationPage()
                .setPassword(authorizationTestData.userPassword)
                .passCaptcha()
                .pressSubmitButton();
        authorizationPage.emailErrorCheck();
    }

    @Test
    @DisplayName("Проверка получения сообщения об ошибке при попытке авторизации без пароля")
    void authorizationWithOutPassword() {
        authorizationPage.openAuthorizationPage()
                .setEmail(authorizationTestData.userEmail)
                .passCaptcha()
                .pressSubmitButton();

        authorizationPage.passwordErrorCheck();

    }
}
