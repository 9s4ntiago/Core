package de.darkmc.coreutils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ChatColor {
    BLACK('0', "black"),
    DARK_BLUE('1', "dark_blue"),
    DARK_GREEN('2', "dark_green"),
    DARK_AQUA('3', "dark_aqua"),
    DARK_RED('4', "dark_red"),
    DARK_PURPLE('5', "dark_purple"),
    GOLD('6', "gold"),
    GRAY('7', "gray"),
    DARK_GRAY('8', "dark_gray"),
    BLUE('9', "blue"),
    GREEN('a', "green"),
    AQUA('b', "aqua"),
    RED('c', "red"),
    LIGHT_PURPLE('d', "light_purple"),
    YELLOW('e', "yellow"),
    WHITE('f', "white"),
    MAGIC('k', "obfuscated"),
    BOLD('l', "bold"),
    STRIKETHROUGH('m', "strikethrough"),
    UNDERLINE('n', "underline"),
    ITALIC('o', "italic"),
    RESET('r', "reset");

    private final char code;
    private final String name;
    private final String toString;

    public static final Pattern REGEX = Pattern.compile("%([a-zA-Z0-9_]+)%");
    private static final Map<String, ChatColor> BY_NAME = new HashMap<>();

    ChatColor(char code, String name) {
        this.code = code;
        this.name = name;

        toString = new String(new char[]{'§', code});
    }

    public static String translate(String toTranslate) {
        String result = toTranslate;
        Matcher matcher = REGEX.matcher(toTranslate);
        while (matcher.find()) {
            String group = matcher.group();
            String colorName = group.replaceAll("%", "");
            ChatColor color = BY_NAME.get(colorName);
            if (color != null) {
                result = result.replace(group, color.toString());
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return toString;
    }

    static {
        ChatColor[] values = values();
        for (ChatColor color : values) {
            BY_NAME.put(color.name, color);
        }
    }
}
