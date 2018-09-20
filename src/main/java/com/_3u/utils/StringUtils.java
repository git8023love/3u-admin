package com._3u.utils;

import org.apache.commons.codec.binary.Base64;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * Jonny
 */
public class StringUtils {
    public static final String EMPTY = "";
    
    /**
	 * 判断string is "" or null
	 * 
	 * @param str
	 *            待查字符串
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}
	
	public static boolean isEmpty(String str,String defaultValue){
		return str == null || str.trim().length() == 0 || str.trim().equals(defaultValue);
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 判断string is "" or null or whitespace
	 * 
	 * @param str
	 *            待查字符串
	 * @return boolean
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (null == str || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}


	/**
	 * 获取字符串左边指定长度的字符
	 * 
	 * @param str
	 *            待处理字符串
	 * @param len
	 *            长度
	 * @return String
	 */
	public static String left(String str, int len) {
		if (null == str) {
			return null;
		}
		if (len < 0) {
			return EMPTY;
		}
		if (str.length() <= len) {
			return str;
		}
		return str.substring(0, len);
	}

	/**
	 * 获取右边指定长度字符串
	 * 
	 * @param str
	 *            待处理字符串
	 * @param len
	 *            长度
	 * @return String
	 */
	public static String right(String str, int len) {
		if (null == str) {
			return null;
		}
		if (len < 0) {
			return EMPTY;
		}
		if (str.length() <= len) {
			return str;
		}
		return str.substring(str.length() - len);
	}

	public static String join(Object[] array, char separator) {
		if (array == null) {
			return null;
		}
		return join(array, separator, 0, array.length);
	}

	public static String join(Object[] array, char separator, int startIndex,
			int endIndex) {
		if (array == null) {
			return null;
		}
		int bufSize = endIndex - startIndex;
		if (bufSize <= 0) {
			return EMPTY;
		}
		bufSize *= ((array[startIndex] == null ? 16 : array[startIndex]
				.toString().length()) + 1);
		StringBuilder buf = new StringBuilder(bufSize);
		for (int i = startIndex; i < endIndex; i++) {
			if (i > startIndex) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}


	
	/**
     * 复制字符
     *
     * @param c   字符
     * @param num 数量
     * @return 新字符串
     */
    public static String dup(char c, int num) {
        if (c == 0 || num < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder(num);
        for (int i = 0; i < num; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * @param cs 字符串
     * @return 是不是为空字符串
     */
    public static boolean isEmpty(CharSequence cs) {
        return null == cs || cs.length() == 0;
    }

    /**
     * @param cs 字符串
     * @return 是不是为空白字符串
     */
    public static boolean isBlank(CharSequence cs) {
        if (null == cs) {
            return true;
        }
        int length = cs.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param object Object
     * @return object.toString(), 对object进行toString()操作, 如果为null返回""
     */
    public static String sNull(Object object) {
        return sNull(object, "");
    }

    /**
     * @param object Object
     * @param def    默认值
     * @return 对object进行toString()操作, 如果为null返回def中定义的值
     */
    public static String sNull(Object object, String def) {
        return null != object ? object.toString() : def;
    }



    /**
     * @param str       源字符串
     * @param separator 分隔符
     * @return 截取分隔符之前的字符串
     */
    public static String substringBefore(String str, String separator) {
        if (isEmpty(str) || separator == null) {
            return str;
        }
        if (separator.length() == 0) {
            return "";
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    /**
     * @param str       源字符串
     * @param separator 分隔符
     * @return 截取分隔符之后的字符串
     */
    public static String substringAfter(String str, String separator) {
        if (isEmpty(str)) {
            return str;
        }
        if (separator == null) {
            return "";
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return "";
        } else {
            return str.substring(pos + separator.length());
        }
    }

    /**
     * 比较两个字符串忽略大小写
     *
     * @param str1 字符串A
     * @param str2 字符串B
     * @return true 如果两个字符串相等,且两个字符串均不为null
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

    /**
     * 检查两个字符串是否相等.
     *
     * @param s1 字符串A
     * @param s2 字符串B
     * @return true 如果两个字符串相等,且两个字符串均不为null
     */
    public static boolean equals(String s1, String s2) {
        return s1 == null ? s2 == null : s1.equals(s2);
    }

    /**
     * @param str 字符串
     * @return 是否有长度
     */
    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    /**
     * @param str 字符串
     * @return 是否包含文本内容
     */
    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }


    /**
     * 在字符串左侧填充一定数量的特殊字符
     *
     * @param cs    字符串
     * @param width 字符数量
     * @param c     字符
     * @return 新字符串
     */
    public static String alignRight(CharSequence cs, int width, char c) {
        if (null == cs) {
            return null;
        }
        int len = cs.length();
        if (len >= width) {
            return cs.toString();
        }
        return new StringBuilder().append(dup(c, width - len)).append(cs).toString();
    }

    /**
     * 在字符串右侧填充一定数量的特殊字符
     *
     * @param cs    字符串
     * @param width 字符数量
     * @param c     字符
     * @return 新字符串
     */
    public static String alignLeft(CharSequence cs, int width, char c) {
        if (null == cs) {
            return null;
        }
        int length = cs.length();
        if (length >= width) {
            return cs.toString();
        }
        return new StringBuilder().append(cs).append(dup(c, width - length)).toString();
    }


    /**
     * @param array     对象数组
     * @param separator 分隔符
     * @return 使用分隔符连接数组返回字符串
     */
    public static String join(Object[] array, String separator) {
        if (null == array) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    /**
     * @param collection 集合
     * @param separator  分隔符
     * @return 使用分隔符连接对象返回字符串
     */
    @SuppressWarnings("unchecked")
	public static String join(Collection collection, String separator) {
        if (collection == null) {
            return null;
        }
        return join(collection.iterator(), separator);
    }

    /**
     * @param iterator  迭代对象
     * @param separator 分隔符
     * @return 使用分隔符连接对象返回字符串
     */
    @SuppressWarnings("unchecked")
	public static String join(Iterator iterator, String separator) {
        if (null == iterator) {
            return null;
        }
        if (!iterator.hasNext()) {
            return "";
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return null == first ? "" : first.toString();
        }

        StringBuilder buf = new StringBuilder(256);
        if (null != first) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (null != separator) {
                buf.append(separator);
            }
            Object obj = iterator.next();
            if (null != obj) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    /**
     * @param array      对象数组
     * @param separator  分隔符
     * @param startIndex 开始位置
     * @param endIndex   结束位置
     * @return 使用分隔符连接数组返回字符串
     */
    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (null == array) {
            return null;
        }
        if (null == separator) {
            separator = "";
        }

        int bufSize = (endIndex - startIndex);
        if (bufSize <= 0) {
            return "";
        }

        bufSize *= ((null == array[startIndex] ? 16 : array[startIndex].toString().length()) + separator.length());

        StringBuilder buf = new StringBuilder(bufSize);

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (null != array[i]) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }


    /**
     * 根据一个正则式,将字符串拆分成数组,空元素将被忽略
     *
     * @param s     字符串
     * @param regex 正则式
     * @return 字符串数组
     */
    public static String[] splitIgnoreBlank(String s, String regex) {
        if (null == s) {
            return new String[0];
        }
        String[] ss = s.split(regex);
        List<String> list = new LinkedList<String>();
        for (String st : ss) {
            if (isBlank(st)) {
                continue;
            }
            list.add(trim(st));
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * 去掉字符串前后空白
     *
     * @param cs 字符串
     * @return 新字符串
     */
    public static String trim(CharSequence cs) {
        if (null == cs) {
            return null;
        }
        if (cs instanceof String) {
            return ((String) cs).trim();
        }
        int length = cs.length();
        if (length == 0) {
            return cs.toString();
        }
        int l = 0;
        int last = length - 1;
        int r = last;
        for (; l < length; l++) {
            if (!Character.isWhitespace(cs.charAt(l))) {
                break;
            }
        }
        for (; r > l; r--) {
            if (!Character.isWhitespace(cs.charAt(r))) {
                break;
            }
        }
        if (l > r) {
            return "";
        } else if (l == 0 && r == last) {
            return cs.toString();
        }
        return cs.subSequence(l, r + 1).toString();
    }
    
    /**
     * 判断搜索引擎中的值是否是大于0
     * 
     * @param value 值
     * @return
     */
    public static boolean isExistsContent(String s){
    	if (s == null || s.equals("") || s.equals("0")) {
			return false;
		}
		return true;
    }
    
    public static String getMiddleStr(String msg){
    	if(msg==null || msg.equals(""))
    		return "";
    	return msg.substring(1,msg.length()-1);
    }
    
    public static StringBuilder replaceAll(StringBuilder sb, String oldStr, String newStr) {
        int i = sb.indexOf(oldStr);
        int oldLen = oldStr.length();
        int newLen = newStr.length();
        while (i > -1) {
            sb.delete(i, i + oldLen);
            sb.insert(i, newStr);
            i = sb.indexOf(oldStr, i + newLen);
        }
        return sb;
    }
    
    public static String escapeJs(String content) {
    	if (content == null) {
    		return content;
    	}
    	return content.replace("'", "\\\'");
    }

	public static String cutHtmlString(String source, int length) {
		if (!isEmpty(source)) {
			source = HTMLUtils.html2Text(source);
			source = source.replace("&nbsp;", "");
			source = source.replace("\n", "");
			length = length * 2;
			StringBuilder sb = new StringBuilder();
			int counter = 0;
			for (int i = 0; i < source.length(); i++) {
				char c = source.charAt(i);
				if (c < 255) {
					counter++;
				} else {
					counter = counter + 2;
				}
				if (counter > length) {
					break;
				}
				sb.append(c);
			}
			if (sb.toString().equals(source)) {
				return source;
			}
			return sb.toString() + "...";
		}
		return "";
	}
	
	/**
	 * 获取字符串左边指定长度的字符
	 * 
	 * @param str
	 *            待处理字符串
	 * @param len
	 *            长度
	 * @return String
	 */
	public static String cutString(String str, int len) {
		str = HTMLUtils.html2Text(str);
		if (null == str) {
			return null;
		}
		if (len < 0) {
			return EMPTY;
		}
		if (str.length() <= len) {
			return str;
		}
		while (!str.substring(len, len + 1).matches("\\s")) {
			len--;
			if (len < 1) {
				return EMPTY;
			}
		}
		return str.substring(0, len) + "...";
	}
	
	public static String starRepalceString(String start, int count, String end){
		for(int i = 0; i < count; i++ ){
			start += "*";
		}
		start += end;
		return start;
	}

    /**
     * @param str String
     * @return String
     */
    public static String isoToGBK(String str) {
        if (str == null) {
            return "";
        }
        try {
            byte[] bytes = str.getBytes("iso-8859-1");
            String destStr = new String(bytes, "GBK");
            return destStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 转换指定字符串的编码
     *
     * @param str
     * @param fromEncoding
     * @param toEncoding
     * @return
     */
    public static String convert(String str, String fromEncoding,
                                 String toEncoding) {
        if (str == null) {
            return "";
        }
        try {
            byte[] bytes = str.getBytes(fromEncoding);
            String destStr = new String(bytes, toEncoding);
            return destStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String toUnicode(java.lang.String text) {
        if (text == null)
            return "";
        char chars[] = text.toCharArray();
        java.lang.StringBuffer sb = new StringBuffer();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            int s = chars[i];
            sb.append("&#");
            sb.append(s);
            sb.append(";");
        }

        return sb.toString();
    }

    /**
     * 检测字符串里是否有中文字符
     *
     * @param str
     * @return
     */
    public static boolean chinese(String str) {
        if (str == null) {
            return false;
        }
        String regex = "[\u0391-\uFFE5]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        boolean validate = m.matches();
        return validate;
    }

    /**
     * 检测输入的邮政编码是否合法
     *
     * @param code
     * @return
     */
    public static boolean isPostCode(String code) {
        if (code == null) {
            return false;
        }
        String regex = "[1-9]\\d{5}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(code);
        boolean validate = m.matches();
        return validate;
    }

    /**
     * 字符串是否是"nul"字符串
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        if (str == null || str.equals("null")) {
            return true;
        }
        return false;
    }

    /**
     * 将"null"字符串或者null值转换成""
     *
     * @param str
     * @return
     */
    public static String nullStringToEmptyString(String str) {
        if (str == null) {
            str = "";
        }
        if (str.equals("null")) {
            str = "";
        }
        return str;
    }

    /**
     * 将"null"字符串或者null值转换成""
     *
     * @param str
     * @return
     */
    public static String nullStringToSetString(String str) {
        if (StringUtils.isEmpty(str)) {
            str = "设置";
        }
        if (str == null) {
            str = "设置";
        }
        if (str.equals("null")) {
            str = "设置";
        }
        return str;
    }

    /**
     * 将"null"字符串或者null值转换成""
     *
     * @param str
     * @return
     */
    public static String nullStringToUnknowString(String str) {
        if (str == null) {
            str = "未知";
        }
        if (str.equals("null")) {
            str = "未知";
        }
        return str;
    }

    /**
     * 屏掉WML不支持的代码
     *
     * @param str
     * @return
     */
    public static String wmlEncode(String str) {
        if (str == null)
            return "";
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("'", "&apos;");
        str = str.replaceAll("\"", "&quot;");
        str = str.replaceAll("\n", "<br/>");
        str = str.replaceAll("<br>", "<br/>");
        return str;
    }

    /**
     * 将字节转换成16进制
     *
     * @param b byte[]
     * @return String
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * 是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        str = StringUtils.nullStringToEmptyString(str);
        String regex = "\\d+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        boolean validate = m.matches();
        return validate;
    }

    /**
     * 检查书的ISBN号是否合法
     *
     * @param isbn
     * @return
     */
    public static boolean isISBN(String isbn) {
        if (StringUtils.isEmpty(isbn)) {
            return false;
        }
        int len = isbn.length();
        if (len != 13) {
            return false;
        }
        String[] splits = isbn.split("-");
        len = splits.length;
        if (len != 4) {
            return false;
        }
        len = splits[0].length();
        if (len < 1 || len > 5) {
            return false;
        }
        len = splits[1].length();
        if (len < 2 || len > 5) {
            return false;
        }
        len = splits[2].length();
        if (len < 1 || len > 6) {
            return false;
        }
        len = splits[3].length();
        if (len != 1) {
            return false;
        }
        String realISBN = isbn.replaceAll("-", "");
        char[] numbers = realISBN.toCharArray();
        int sum = 0;
        for (int i = 10; i > 1; i--) {
            int index = 10 - i;
            int number = Integer.parseInt(String.valueOf(numbers[index]));
            sum = sum + number * i;
        }
        int code = 11 - (sum % 11);
        String codeStr = String.valueOf(code);
        if (code == 10) {
            codeStr = "X";
        }
        if (!splits[3].equals(codeStr)) {
            return false;
        }
        return true;
    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }

    public static String substring(String str, int start, int length) {
        int len = str.length();
        if (len > 15) {
            str = str.substring(start, length);
        }
        str = str + "......";
        return str;
    }

    /**
     * Encode a string using algorithm specified in web.xml and return the
     * resulting encrypted password. If exception, the plain credentials string is
     * returned
     *
     * @param password  Password or other credentials to use in authenticating this
     *                  username
     * @param algorithm Algorithm used to do the digest
     * @return encypted password based on the algorithm.
     */
    public static String encodePassword(String password, String algorithm) {
        byte[] unencodedPassword = password.getBytes();

        MessageDigest md = null;

        try {
            // first create an instance, given the provider
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            return password;
        }

        md.reset();

        // call the update method one or more times
        // (useful when you don't know the size of your data, eg. stream)
        md.update(unencodedPassword);

        // now calculate the hash
        byte[] encodedPassword = md.digest();

        StringBuffer buf = new StringBuffer();

        for (byte anEncodedPassword : encodedPassword) {
            if ((anEncodedPassword & 0xff) < 0x10) {
                buf.append("0");
            }

            buf.append(Long.toString(anEncodedPassword & 0xff, 16));
        }

        return buf.toString();
    }

    /**
     * Encode a string using Base64 encoding. Used when storing passwords as
     * cookies.
     * <p/>
     * This is weak encoding in that anyone can use the decodeString routine to
     * reverse the encoding.
     *
     * @param str
     * @return String
     */
    public static String encodeString(String str) {
        Base64 encoder = new Base64();
        return String.valueOf(encoder.encode(str.getBytes())).trim();
    }

    /**
     * Decode a string using Base64 encoding.
     *
     * @param str
     * @return String
     */
    public static String decodeString(String str) {
        Base64 dec = new Base64();
        return String.valueOf(dec.decode(str));
    }

    /**
     * 字符串替换
     * @param text
     * @param start
     * @param end
     * @param replacement
     * @return
     */
    public static String replace(String text, int start, int end, String replacement) {
        int len = text.length();
        if (start < len && end <= len && start > 0 && end > 0) {
            String part1 = text.substring(0, start);
            String part2 = text.substring(end);
            return part1 + replacement + part2;
        } else {
            return text;    
        }
    }

    /**
     * 字符串替换
     * @param text
     * @param start
     * @param end
     * @param replacement
     * @return
     */
    public static String replace(String text, int start, int end, char replacement) {
        char[] chars = text.toCharArray();
        int len = text.length();
        String tempText = "";
        if (start < len && end <= len && start > 0 && end > 0) {
            for (int i = start; i <= end; i++) {
                int theIndex = Integer.valueOf(i);
                if (theIndex > 0 && theIndex < text.length()) {
                    chars[theIndex] = replacement;
                }
            }
        }

        for (char c : chars) {
            tempText += c;
        }
        return tempText;
    }

    /**
     * 字符串替换
     * @param text
     * @param index
     * @param replacement
     * @return
     */
    public static String replace(String text, String index, char replacement) {
        char[] chars = text.toCharArray();
        String[] tempIndex = index.split(",");
        String tempText = "";
        for (String i : tempIndex) {
            int theIndex = Integer.valueOf(i);
            if (theIndex > 0 && theIndex < text.length()) {
                chars[theIndex] = replacement;
            }
        }

        for (char c : chars) {
            tempText += c;
        }
        return tempText;
    }

    /**
     * 把键值对的字符串写入HashMap（如：{ip=211.136.20.44, softid=, cid=ad.ucweb, date=2009-04-14:11:59:56, mid=, userAgent=-, page=/soft/system/sort})
     * @param str
     * @return
     */
    public static Map<String,String> loadStrToMap(String str) {
        if (!StringUtils.isEmpty(str)) {
            Map<String, String> valueMap = new WeakHashMap<String, String>();
            if (str.startsWith("{") && str.endsWith("}")) {
                str = str.substring(1, str.length() - 1);
            }
            String[] splits = str.split(",");
            for (String s : splits) {
                String[] tempSplits = s.split("=");
                if (tempSplits != null && tempSplits.length == 2) {
                    String key = tempSplits[0].trim();
                    String value = tempSplits[1];
                    valueMap.put(key, value);
                }
            }
            return valueMap;
        }
       return null; 
    }

    /**
     * 字符串替换
     * @param text
     * @param replacement
     * @return
     */
    public static String replace(String text, String[] replacement) {
        return text;
    }
    
    /**
     * 字符串替换
     * @param text
     * @param oldChar
     * @param newChar
     * @return
     */
    public static String replace(String text, String oldChar, String newChar) {
        return text.replace(oldChar, newChar);
    }

    public static void main(String... args) {
    	System.out.println(cutString("123 4", 4));
	}
}