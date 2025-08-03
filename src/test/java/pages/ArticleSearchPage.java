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
            articleName = $("[class='tm-search__input tm-input-text-decorated__input']"),
            emptyArticleSearch = $("[data-test-id='empty-placeholder-text']"),
            keyWordArticleSearch = $("[class='tm-search__input tm-input-text-decorated__input']");


    private final ElementsCollection
            articleSearchSuccessCheck = $$("[data-test-id='articles-list']");//как исправить тип так, чтобы коллекци валидно отображалась?

    @Step("Перейти на страницу авторизации пользователя")
    public ArticleSearchPage openArticleSearchPage() {
        open("/search/");
        return this;
    }

    @Step("Поиск значения")
    public ArticleSearchPage setArticleName(String value) {
        articleName.setValue(value).pressEnter();
        return this;
    }

    @Step("Пустой результат поиска")
    public ArticleSearchPage emptyArticleSearch() {
        emptyArticleSearch.shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text("К сожалению, здесь пока нет ни одной публикации"));
        return this;
    }

    @Step("Результат поиска не пустой")
    public ArticleSearchPage articleSearchSuccessCheck() {
        articleSearchSuccessCheck.shouldBe(sizeGreaterThan(0));
        return this;
    }

    @Step("Поиск статьи по ключевому слою")
    public ArticleSearchPage keyWordArticleSearchSuccessCheck() {
        keyWordArticleSearch.
                articleSearchSuccessCheck.findBy(text(article)).shouldHave(text(article));
        return this;
    }
}
