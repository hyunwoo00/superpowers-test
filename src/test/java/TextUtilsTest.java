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
}
