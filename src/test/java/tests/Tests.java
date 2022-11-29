package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainPage;
import pages.SearchResultPage;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;

public class Tests extends TestBase {
    MainPage mainPage = new MainPage();
    SearchResultPage searchResultPage = new SearchResultPage();


    @BeforeEach
    public void openMainPage() {
        open("https://sturmuniform.ru/");
    }


    static Stream<Arguments> checkSubcategoryTest() {
        return Stream.of(Arguments.of("Обувь",
                        List.of("5.11 Tactical (США)",
                                "Dragon Tooth",
                                "Garsing",
                                "LOWA",
                                "Moretan",
                                "Sibearian",
                                "STURMER",
                                "X-Bionic",
                                "Бутекс",
                                "Оригинал",
                                "Россия",
                                "Фарадей (РФ)",
                                "Все производители")),
                Arguments.of("Медицина",
                        List.of("LEAF Project (РФ)",
                                "Апполо",
                                "Оригинал",
                                "Россия",
                                "Все производители")));
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка наличия подкатегорий {1} в категории {0}")
    @Tag("Smoke")
    void checkSubcategoryTest(String category, List<String> subcategory) {
        mainPage.findCategory(category)
                .checkSubcategory(subcategory);

    }


    @ValueSource(strings = {"Helikon Urban Tactical Hoodie", "Фонарь Olight Baton 3 Pro"})
    @DisplayName("Проверка результатов поиска")
    @ParameterizedTest
    void checkSearchResult(String productName) {
        mainPage.searchProduct(productName);
        searchResultPage.verifySearchResult(productName);
    }

    @CsvSource({
            "Helikon Urban Tactical Hoodie, 5.560 руб.",
            "Фонарь Olight Baton 3 Pro,  6.000 руб."
    })
    @DisplayName("Проверка соотвествия что товар {0} соотвествует заявленной цене {1}")
    @ParameterizedTest
    void checkProductPrice(String productName, String price){
        mainPage.searchProduct(productName);
        searchResultPage.verifyProductPrice(productName,price);
    }
}