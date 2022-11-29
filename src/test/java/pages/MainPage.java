package pages;

import com.codeborne.selenide.CollectionCondition;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import java.util.List;

public class MainPage {
    public MainPage findCategory(String category) {
        $x("//a[contains(text(),'" + category + "')]").click();
        return this;
    }

    public MainPage checkSubcategory(List<String> subcategory) {
        $$(".MANUFACTURER_SORT a").filter(visible)
                .shouldHave(CollectionCondition.texts(subcategory));
        return this;
    }

    public MainPage searchProduct(String productName) {
        $x("//input[@id='quick_find_keyword']").setValue(productName).pressEnter();
        return this;
    }

}