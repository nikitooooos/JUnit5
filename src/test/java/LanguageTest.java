import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class LanguageTest {

    static Stream<Arguments> escapeFromTarkovButtonsTest() {
        return Stream.of(
                Arguments.of(Language.Ru, List.of("НОВОСТИ ОБ ИГРЕ", "МЕДИА", "МЕРЧ", "РЕЙТИНГ", "СТРИМЕР ЧЕЛЛЕНДЖ", "ФОРУМ", "ПОДДЕРЖКА", "ВИКИ")),
                Arguments.of(Language.Eng, List.of("NEWS", "ABOUT", "MEDIA", "MERCH", "RATINGS", "STREAMER CHALLENGE", "FORUM", "SUPPORT", "WIKI")),
                Arguments.of(Language.De, List.of("NEWS", "ÜBER", "MEDIEN", "MERCH", "RATINGS", "STREAMERCHALLENGE", "FORUM", "SUPPORT", "WIKI")),
                Arguments.of(Language.Esp, List.of("NOTICIAS", "JUEGO", "MEDIA", "MERCH", "CLASIFICACIONES", "DESAFÍO STREAMER", "FORO", "SOPORTE", "WIKI")),
                Arguments.of(Language.Por, List.of("NOTÍCIAS", "JOGO", "MIDIA", "MERCH", "AVALIAÇÕES", "STREAMER CHALLENGE", "FORUM", "SUPORTE", "WIKI")),
                Arguments.of(Language.Fra, List.of("ACTUALITÉS", "À PROPOS", "MÉDIAS", "MERCH", "CLASSEMENTS", "STREAMER CHALLENGE", "FORUM", "ASSISTANCE", "WIKI")),
                Arguments.of(Language.Ch, List.of("新闻动态", "关于游戏", "媒体资讯", "纪念品", "排行榜", "主播挑战赛", "论坛", "支持", "百科")),
                Arguments.of(Language.Ita, List.of("NOVITÀ", "ABOUT", "MEDIA", "MERCH", "VALUTAZIONE", "SFIDA DEGLI STREAMER", "FORUM", "SUPPORTO", "WIKI")),
                Arguments.of(Language.Jp, List.of("NEWS", "ABOUT", "MEDIA", "MERCH", "RATINGS", "ストリーマーチャレンジ", "FORUM", "SUPPORT", "WIKI"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка отображения кнопок")
    void escapeFromTarkovButtonsTest(Language language, List<String> expectedButtons) {
        open("https://www.escapefromtarkov.com//");
        $$(".current").find(text(language.name())).click();
        $$(".menu inline it").filter(visible)
                .shouldHave(CollectionCondition.texts(expectedButtons));
    }
}
