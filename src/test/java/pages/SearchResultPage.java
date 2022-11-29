package pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.*;

public class SearchResultPage {

    public SearchResultPage verifySearchResult(String result) {
        $x("//a[contains(text(),'" + result + "')]").should(Condition.visible).shouldHave(Condition.text(result));
        return this;
    }

    public SearchResultPage verifyProductPrice (String product, String price) {
        $x("//a[contains(text(),'" + product + "')]").should(Condition.visible).shouldHave(Condition.text(product));
        $(".itemDescriptionPrice").shouldHave(Condition.text(price));
        return this;

    }
}