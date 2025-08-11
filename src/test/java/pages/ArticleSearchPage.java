package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ArticleSearchPage {
    private final SelenideElement
            articleNameInput = $("[class='tm-search__input tm-input-text-decorated__input']"),
            emptyArticleSearch = $("[data-test-id='empty-placeholder-text']");


    private final ElementsCollection
            articleSearchSuccessCheck = $$("[data-test-id='articles-list']");

    @Step("Перейти на страницу поиска статей")
    public ArticleSearchPage openArticleSearchPage() {
        open("https://habr.com/ru/search/");
        return this;
    }

    @Step("Ввод значения и запуск поиска")
    public void setArticleNameInput(String value) {
        articleNameInput.setValue(value).pressEnter();
    }

    @Step("Пустой результат поиска")
    public ArticleSearchPage emptyArticleSearch() {
        emptyArticleSearch.shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text("К сожалению, здесь пока нет ни одной публикации"));
        return this;
    }

    @Step("Результат поиска не пустой")
    public void articleSearchCheck() {
        articleSearchSuccessCheck.shouldBe(sizeGreaterThan(0));
    }

    @Step("Результат поиска по ключевому слову")
    public void keyWordArticleSearchCheck(String article) {
        articleSearchSuccessCheck.findBy(text(article)).shouldHave(text(article));//как тут правильно передать переменную, чтобы было сравнение название и результат поиска?
    }


}
