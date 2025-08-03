package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

@DisplayName("Тесты на поиск статей в Хабр")
public class ArticleSearchTests extends TestBase {

    @Test
    @DisplayName("Проверка 1")
    void articleSearch() {
        step("Поиск статьи", () -> {
            $("[class='tm-svg-img tm-header-user-menu__icon tm-header-user-menu__icon_search']").click();
            $("[clss='tm-search__input tm-input-text-decorated__input']").setValue("Ситидрайв");
        });
    }


}