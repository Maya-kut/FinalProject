package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ArticleSearchPage {
    private final SelenideElement
            emailInput = $("input[name='email']"),
            passwordInput = $("input[name='password']"),
            captchaCheckbox = $(".CheckboxCaptcha-Button"),
            authorizationSubmitButton = $("[class='button button_wide button_primary']"),
            captchaError = $(".s-error"),
            emailError = $("#ident-alert"),
            passwordError = $("#ident-alert");


    @Step("Перейти на страницу авторизации пользователя")
    public ArticleSearchPage openAuthorizationPage() {
        open("https://account.habr.com/ru/ident/");
        return this;
    }

    @Step("Заполнить логин пользователя")
    public ArticleSearchPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    @Step("Заполнить пароль пользователя")
    public ArticleSearchPage setPassword(String value) {
        passwordInput.setValue(value);
        return this;
    }

    @Step("Доказать, что не робот")
    public ArticleSearchPage passCaptcha() {
        captchaCheckbox.click();
        return this;
    }

    @Step("Нажать на кнопку 'Войти'")
    public ArticleSearchPage pressSubmitButton() {
        authorizationSubmitButton.click();
        return this;
    }

    @Step("Не отмечать чекбокс 'Я не робот'")
    public ArticleSearchPage captchaErrorCheck() {
        captchaError.shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text("Необходимо пройти капчу"));
        return this;
    }

    @Step("Ошибка, если введен некорректный логин")
    public ArticleSearchPage emailErrorCheck() {
        emailError.shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text("Неизвестное сочетание email и пароля"));
        return this;
    }

    @Step("Ошибка, если введен некорректный пароль")
    public ArticleSearchPage passwordErrorCheck() {
        passwordError.shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text("Неизвестное сочетание email и пароля"));
        return this;
    }



}
