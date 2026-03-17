public class TextUtils {
    public static String truncate(String text, int maxLen) {
        if(text == null || text.isEmpty()) return "";
        if(text.length() <= maxLen) return text;
        if(maxLen <= 3) return "...";
        return text.substring(0, maxLen - 3) + "...";
    }
}
