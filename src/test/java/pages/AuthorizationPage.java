package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import testData.AuthorizationTestData;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationPage {
    private final SelenideElement
            emailInput = $("[id='ident-form'] [type='email']"),
            passwordInput = $("[id='ident-form'] [name='password']"),
            captchaCheckbox = $("[data-testid=\"checkbox-iframe\"]"),
            authorizationSubmitButton = $("[class='button button_wide button_primary']"),
            captchaError = $(".s-error"),
            emailError = $("#ident-alert"),
            passwordError = $("#ident-alert"),
            successAuthorizationText = $(".CheckboxCaptcha-Button");


    @Step("Перейти на страницу авторизации пользователя")
    public AuthorizationPage openAuthorizationPage() {
        open("https://account.habr.com/ru/");
        return this;
    }

    @Step("Заполнить логин пользователя")
    public AuthorizationPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    @Step("Заполнить пароль пользователя")
    public AuthorizationPage setPassword(String value) {
        passwordInput.setValue(value);
        return this;
    }

    @Step("Доказать, что не робот")
    public AuthorizationPage passCaptcha() {
        captchaCheckbox.click();
        return this;
    }

    @Step("Нажать на кнопку 'Войти'")
    public AuthorizationPage pressSubmitButton() {
        authorizationSubmitButton.click();
        return this;
    }

    @Step("Проверить переход на страницу приветствия при успешной авторизации")
    public AuthorizationPage authorizationSuccessCheck() {
        String userEmail = "vip.persik@internet.ru"; //это плохо, как указать, что это переменная из AuthorizationTestData?
        successAuthorizationText
                .shouldHave(text("Приветствуем вас, @" + userEmail));
        return this;
    }

    @Step("Не отмечать чекбокс 'Я не робот'")
    public AuthorizationPage captchaErrorCheck() {
        captchaError.shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text("Необходимо пройти капчу"));
        return this;
    }

    @Step("Ошибка, если введен некорректный логин")
    public AuthorizationPage emailErrorCheck() {
        emailError.shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text("Неизвестное сочетание email и пароля"));
        return this;
    }

    @Step("Ошибка, если введен некорректный пароль")
    public AuthorizationPage passwordErrorCheck() {
        passwordError.shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text("Неизвестное сочетание email и пароля"));
        return this;
    }



}
