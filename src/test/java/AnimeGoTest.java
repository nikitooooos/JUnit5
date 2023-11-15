import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AnimeGoTest extends TestBase{

    @BeforeEach
    void openSite(){
        open("https://animego.org/");
    }

    @CsvFileSource(resources = "animeSearchAnimeGO.csv")
    @ParameterizedTest(name = "Для введенного аниме {0} отображается аниме {1}")
    @Tag("WEB")
    void searchAnime(String animeSearch, String animeName){
        $("#navbar-search").click();
        $("[name=q]").setValue(animeSearch).pressEnter();
        $(".row").shouldHave(text(animeName));
    }
}
