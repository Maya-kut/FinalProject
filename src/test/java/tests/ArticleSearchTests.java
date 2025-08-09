package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.ArticleSearchPage;

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
        articleSearchPage.openArticleSearchPage()
                .setArticleNameInput(articleName);
        articleSearchPage.articleSearchCheck();

    }

    @DisplayName("Проверить, что при поиске по ключевому слову, будут найдены определенные статьи")
    @CsvFileSource(resources = "/searchResultsShouldContainValue.csv")
    @ParameterizedTest(name = "Для ключевого слова {0} должна быть статья {1}")
    void keyWordArticleSearchSuccessCheck(String keyWord, String article) {
        {
            articleSearchPage.openArticleSearchPage()
                    .setArticleNameInput(keyWord);
            articleSearchPage.keyWordArticleSearchCheck();
        }
    }


}