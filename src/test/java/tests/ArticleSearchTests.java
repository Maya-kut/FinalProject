package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.ArticleSearchPage;

import static io.qameta.allure.Allure.step;

@Tag("simple")
@DisplayName("Тесты на поиск статей в Хабр")//добавить шаги
public class ArticleSearchTests extends TestBase {

    ArticleSearchPage articleSearchPage = new ArticleSearchPage();


    @DisplayName("Проверить, что при поиске по существующему значению, результат поиска не будет равен 0")
    @ValueSource(strings = {
            "Ситидрайв", "ИИ в тестировании ПО", "освоение космоса"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдаваться не пустой список статей")
    void searchResultsNotEmpty(String articleName) {
        step("Ввод значения для поиска", () -> {
            articleSearchPage.openArticleSearchPage()
                    .setArticleName(articleName);

        });
        step("Проверка, что результат поиска не пустой", () -> {
            articleSearchPage.articleSearchSuccessCheck();
        });
    }

    @DisplayName("Проверить, что при поиске по ключевому слову, будут найдены определенные статьи")
    @CsvFileSource(resources = "/searchResultsShouldContainValue.csv")
    @ParameterizedTest(name = "Для ключевого слова {0} должна быть статья {1}")
    void keyWordArticleSearchSuccessCheck(String keyWord, String article) {
        {
            step("Ввод названия искомой статьи", () -> {
                articleSearchPage.openArticleSearchPage()
                        .setArticleName(keyWord);

            });
            step("Проверка, что нашлась нужная статья", () -> {
                articleSearchPage.articleSearchSuccessCheck();
            });
        }
//        $("input[class='search-form__input search-form__input--search']").setValue(keyWord).pressEnter();
//        $$("[class='app-catalog__content']").findBy(text(article)).shouldHave(text(article));
    }
}