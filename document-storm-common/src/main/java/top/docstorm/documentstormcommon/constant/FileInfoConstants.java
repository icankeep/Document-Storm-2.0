package top.docstorm.documentstormcommon.constant;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 定义文件相关的常量
 * @author: passer
 * @version：2019/9/17
 */
public interface FileInfoConstants {

    // 文件转换中
    Byte FILE_CONVERTING = 0;

    // 文件转换成功
    Byte FIlE_SUCCESS = 1;

    // 文件过期
    Byte FILE_EXPIRED = 2;

    /**
     * 文件格式转换的类型，0为word2pdf，1为pdf2word，2为markdown2pdf，3为markdown2html，4为word translate，5为html2pdf
     */
    Byte WORD_TO_PDF = 0;

    Byte PDF_TO_WORD = 1;

    Byte MARKDOWN_TO_PDF = 2;

    Byte MARKDOWN_TO_HTML = 3;

    Byte WORD_TRANSLATE = 4;

    Byte HTML_TO_PDF = 5;

    Map<String, Byte> OPT_INT_MAP = new HashMap<String, Byte>() {
        {
            put("word2pdf", WORD_TO_PDF);
            put("pdf2word", PDF_TO_WORD);
            put("markdown2pdf", MARKDOWN_TO_PDF);
            put("markdown2html", MARKDOWN_TO_HTML);
            put("word_translate", WORD_TRANSLATE);
            put("html2pdf", HTML_TO_PDF);
        }
    };

    /**
     * 文件转换前的后缀名
     */
    Map<String, String> OPT_BEFORE_TRANS_SUFFIX_MAP = new HashMap<String, String>() {
        {
            put("word2pdf", "docx");
            put("pdf2word", "pdf");
            put("markdown2pdf", "md");
            put("markdown2html", "md");
            put("word_translate", "doc");
            put("html2pdf", "html");
        }
    };

    /**
     * 文件转换前的后缀名
     */
    Map<Byte, String> OPT_INT_BEFORE_TRANS_SUFFIX_MAP = new HashMap<Byte, String>() {
        {
            put(WORD_TO_PDF, "docx");
            put(PDF_TO_WORD, "pdf");
            put(MARKDOWN_TO_PDF, "md");
            put(MARKDOWN_TO_HTML, "md");
            put(WORD_TRANSLATE, "doc");
            put(HTML_TO_PDF, "html");
        }
    };

    /**
     * 文件转换后的后缀名
     */
    Map<Byte, String> OPT_INT_AFTER_TRANS_SUFFIX_MAP = new HashMap<Byte, String>() {
        {
            put(WORD_TO_PDF, "pdf");
            put(PDF_TO_WORD, "docx");
            put(MARKDOWN_TO_PDF, "pdf");
            put(MARKDOWN_TO_HTML, "html");
            put(WORD_TRANSLATE, "docx");
            put(HTML_TO_PDF, "pdf");
        }
    };

    /**
     * word类型文档后缀集合
     */
    Set<String> WORD_SUFFIX_SET = new HashSet<String>() {
        {
            add("doc");
            add("docx");
            add("rtf");
        }
    };

    /**
     * html类型文件后缀名
     */
    Set<String> HTML_SUFFIX_SET = new HashSet<String>() {
        {
            add("html");
            add("htm");
        }
    };
}
