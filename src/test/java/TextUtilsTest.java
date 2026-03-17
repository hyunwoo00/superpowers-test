import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TextUtilsTest {

    @Test
    void truncate_shortText_returnsOriginal() {
        assertEquals("hello", TextUtils.truncate("hello", 10));
    }

    @Test
    void truncate_longText_returnsTruncatedwithEllipsis() {
        assertEquals("hel...", TextUtils.truncate("hello world", 6));
    }

    @Test
    void truncate_maxLenThreeOrLess_returnsEllipsis() {
        assertEquals("...", TextUtils.truncate("hello", 3));
        assertEquals("...", TextUtils.truncate("hello", 1));
    }

    @Test
    void truncate_emptyOrNull_returnsEmpty() {
        assertEquals("", TextUtils.truncate("", 5));
        assertEquals("", TextUtils.truncate(null, 5));
    }

    @Test
    void slugify_basic_convertsToLowerKebab() {
        assertEquals("hello-world", TextUtils.slugify("hello-world"));
    }

    @Test
    void slugify_multipleSpaces_collapsedToSingleDash() {
        assertEquals("hello-world", TextUtils.slugify("hello  world"));
    }

    @Test
    void slugify_specialChars_removed() {
        assertEquals("hello-world", TextUtils.slugify("hello! world?"));
    }

    @Test
    void slugify_korean_removed() {
        assertEquals("hello", TextUtils.slugify("hello 한글"));
    }

    @Test
    void slugify_emptyOrNull_returnsEmpty() {
        assertEquals("", TextUtils.slugify(""));
        assertEquals("", TextUtils.slugify(null));
    }

    @Test
    void slugify_leadingTrailingDashes_stripped() {
        assertEquals("hello", TextUtils.slugify("      hello      "));
    }
}
