package com.metanit;



/**
 * Класс, выпоняющий сопоставление строки с образцом
 */
public class StrPatternDemo {

    /**
     * Сопоставление с образцом (непосредственная реализация)
     * @param str Строка
     * @param pattern Образец
     * @param strIdx Текущая позиция в строке
     * @param ptrnIdx Текущая позиция в шаблоне
     * @param matches Массив для сохранения сопоставлений символов строки символам шаблона
     * @return Было ли успешным сопоставление
     */

    private static boolean matchInner(
        String str, String pattern,
        int strIdx, int ptrnIdx,
        String[] matches
    ) {
        // если шаблон уже "закончился", то истина, если и "строка" закончилась
        // (ложь - в противном случае)
        if (ptrnIdx >= pattern.length())
            return strIdx >= str.length();
        // строка уже "закончилась", а в шаблоне не "*", то ложь
        if (strIdx >= str.length() && pattern.charAt(ptrnIdx) != '*')
            return false;

        if (pattern.charAt(ptrnIdx) == '?') {
            // истина будет, если остаток строки (без текущего символа) распознается остатком шаблона (без текущего символа)
            boolean result = matchInner(str, pattern, strIdx + 1, ptrnIdx + 1, matches);
            if (result) {
                matches[ptrnIdx] = str.substring(strIdx, strIdx + 1);
            }
            return result;
        }
        else if (pattern.charAt(ptrnIdx) == '*') {
            // пытаемся последовательно сопоставить "*" от 0 до всех оставшихся символов строки
            for (int k = 0; k <= str.length() - strIdx; k++) {
                boolean result = matchInner(str, pattern, strIdx + k, ptrnIdx + 1, matches);
                if (result) {
                    matches[ptrnIdx] = str.substring(strIdx, strIdx + k);
                    return true;
                }
            }
            return false;
        }
        else {
            // истина будет, если текущие символ строки и шаблона совпадают и
            // остаток строки (без текущего символа) распознается остатком шаблона (без текущего символа)
            boolean result = str.charAt(strIdx) == pattern.charAt(ptrnIdx) &&
                StrPatternDemo.matchInner(str, pattern, strIdx + 1, ptrnIdx + 1, matches);
            if (result) {
                matches[ptrnIdx] = str.substring(strIdx, strIdx + 1);
            }
            return result;
        }
    }

    /**
     * Сопоставление с образцом
     * @param str Строка
     * @param pattern Образец
     * @return Массив сопоставлений символов строки символам шаблона
     */
    public static String[] match(String str, String pattern) {
        if (str == null || pattern == null)
            return null;

        String[] matches = new String[pattern.length()];
        return StrPatternDemo.matchInner(str, pattern, 0, 0, matches) ? matches : null;
    }

}
