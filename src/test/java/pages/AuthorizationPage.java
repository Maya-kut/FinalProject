package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import testData.AuthorizationTestData;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationPage {
    private final SelenideElement
            emailInput = $("input[type='email'][name='email'][form='ident-form']"),
            passwordInput = $("input[name='password']"),
            captchaCheckbox = $(".CheckboxCaptcha-Button"),
            authorizationSubmitButton = $("[class='button button_wide button_primary']"),
            captchaError = $(".s-error"),
            emailError = $("#ident-alert"),
            passwordError = $("#ident-alert"),
            successAuthorizationText = $(".CheckboxCaptcha-Button");


    @Step("Перейти на страницу авторизации пользователя")
    public AuthorizationPage openAuthorizationPage() {
        open("https://account.habr.com/ru/ident/bYplturosbj41YuH2AFXb01KRKQ-vdD7bQzEnxStLr7ST5On-xaZBY8XOqngQEN1b9fY8ljpC0DiGovGmmGlwvXGluEV7CJbupz3J93FcfCVYChMjYE1TAP-js9b1Tq_asnqEnpEhWzTz-27FY4LhwoRUPTQ5ZW89gJ43BpnOAZ5EJseLM5um8l8TBpraAq2q2NnIRjA7Ut-IdwQ");
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
