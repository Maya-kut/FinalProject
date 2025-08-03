package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.ArticleSearchPage;
import testData.ArticleSearchTestData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

@DisplayName("Тесты на поиск статей в Хабр")//добавить шаги
public class ArticleSearchTests extends TestBase {

    ArticleSearchPage articleSearchPage = new ArticleSearchPage();
    ArticleSearchTestData articleSearchTestData = new ArticleSearchTestData();


    @DisplayName("Проверить, что при поиске по существующему значению, результат поиска не будет равен 0")
    @ValueSource(strings = {
            "Ситидрайв", "ИИ в тестировании ПО", "освоение космоса"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдаваться не пустой список статей")
    void searchResultsNotEmpty(String articleName) {
        step("Ввод значения для поиска", () -> {
            articleSearchPage.openArticleSearchPage()
                    .setArticleName(articleSearchTestData.getRandomValue(articleName));//как взять рандомное значение из списка?

        });
        step("Проверка, что результат поиска не пустой", () -> {
            articleSearchPage.articleSearchSuccessCheck();
        });
    }

    @DisplayName("Проверить, что при поиске по ключевому слову, будут найдены определенные статьи")
    @CsvSource(value = {
            "Ситидрайв, Как успешно пройти собеседование в сфере IT?",
            "ИИ в тестировании ПО,  «ИИ-тестировщик»: от идеи к реализации",
            "освоение космоса,   «Роскосмос» и ИМБП РАН заключили контракт на отработку технологий освоения дальнего космоса"
    })
    @CsvFileSource(resources = "/searchResultsShouldContainValue.csv")
    @ParameterizedTest(name = "Для ключевого слова {0} должна быть статья {1}")
    void keyWordArticleSearchSuccessCheck(String keyWord, String article) {
        $("input[class='search-form__input search-form__input--search']").setValue(keyWord).pressEnter();
        $$("[class='app-catalog__content']").findBy(text(article)).shouldHave(text(article));
    }
}