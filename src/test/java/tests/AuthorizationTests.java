package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AuthorizationPage;
import testData.AuthorizationTestData;

import static io.qameta.allure.Allure.step;

@DisplayName("Тесты на авторизацию в Хабр")
public class AuthorizationTests extends TestBase {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    AuthorizationTestData authorizationTestData = new AuthorizationTestData();

    @Test
    @DisplayName("Успешная авторизация")
    void succesfullAuth() {
        step("Ввод корректных данных для авторизации", () -> {
            authorizationPage.openAuthorizationPage()
                    .setEmail(authorizationTestData.userEmail)
                    .setPassword(authorizationTestData.userPassword)
                    .passCaptcha()
                    .pressSubmitButton();
        });
        step("Проверка перехода на страницу приветствия при успешной авторизации", () -> {
            authorizationPage.authorizationSuccessCheck();
        });
    }

    @Test
    @DisplayName("Проверка получения сообщения об ошибке при непроставлении чекбокса 'Я не робот'")
    void iAmRobot() {
        step("Ввод корректных для авторизации без чекбокса 'Я не робот'", () -> {
            authorizationPage.openAuthorizationPage()
                    .setEmail(authorizationTestData.userEmail)
                    .setPassword(authorizationTestData.userPassword)
                    .pressSubmitButton();
        });
        step("Проверка получения сообщения об ошибке", () -> {
            authorizationPage.captchaErrorCheck();
        });
    }

    @Test
    @DisplayName("Проверка получения сообщения об ошибке при попытке авторизации без логина")
    void authorizationWithOutEmail() {
        step("Ввод данных для авторизации без адреса почты", () -> {
            authorizationPage.openAuthorizationPage()
                    .setPassword(authorizationTestData.userPassword)
                    .passCaptcha()
                    .pressSubmitButton();
        });
        step("Проверка получения сообщения об ошибке", () -> {
            authorizationPage.emailErrorCheck();
        });
    }

    @Test
    @DisplayName("Проверка получения сообщения об ошибке при попытке авторизации без пароля")
    void authorizationWithOutPassword() {
        step("Ввод данных для авторизации без пароля", () -> {
            authorizationPage.openAuthorizationPage()
                    .setEmail(authorizationTestData.userEmail)
                    .passCaptcha()
                    .pressSubmitButton();
        });
        step("Проверка получения сообщения об ошибке", () -> {
            authorizationPage.passwordErrorCheck();
        });
    }
}
