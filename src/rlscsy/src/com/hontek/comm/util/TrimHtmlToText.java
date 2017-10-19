package com.hontek.comm.util;

import java.util.regex.Pattern;

public class TrimHtmlToText {
	
	
	/** 
     * html转化为text 
     * @param inputString 
     * @return 
     */ 
    public static String htmlToText(String inputString) { 
          String htmlStr = inputString; // 含html标签的字符串 
          String textStr = ""; 
          java.util.regex.Pattern p_script; 
          java.util.regex.Matcher m_script; 
          java.util.regex.Pattern p_style; 
          java.util.regex.Matcher m_style; 
          java.util.regex.Pattern p_html; 
          java.util.regex.Matcher m_html; 
          try { 
           String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script>]*?>[\s\S]*?<\/script> 
           // } 
           String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style>]*?>[\s\S]*?<\/style> 
           // } 
           String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式 

           p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE); 
           m_script = p_script.matcher(htmlStr); 
           htmlStr = m_script.replaceAll(""); // 过滤script标签 

           p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE); 
           m_style = p_style.matcher(htmlStr); 
           htmlStr = m_style.replaceAll(""); // 过滤style标签 

           p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE); 
           m_html = p_html.matcher(htmlStr); 
           htmlStr = m_html.replaceAll(""); // 过滤html标签 

           textStr = htmlStr; 

          } catch (Exception e) { 
           System.err.println("Html2Text: " + e.getMessage()); 
          } 

          return textStr; 
        } 
    
    
    public static String trimHtml2Txt(String html){      
        html = html.replaceAll("\\<head>[\\s\\S]*?</head>(?i)", "");//去掉head  
        html = html.replaceAll("\\<!--[\\s\\S]*?-->", "");//去掉注释  
        html = html.replaceAll("\\<![\\s\\S]*?>", "");  
        html = html.replaceAll("\\<style[^>]*>[\\s\\S]*?</style>(?i)", "");//去掉样式  
        html = html.replaceAll("\\<script[^>]*>[\\s\\S]*?</script>(?i)", "");//去掉js  
        html = html.replaceAll("\\<w:[^>]+>[\\s\\S]*?</w:[^>]+>(?i)", "");//去掉word标签  
        html = html.replaceAll("\\<xml>[\\s\\S]*?</xml>(?i)", "");  
        html = html.replaceAll("\\<html[^>]*>|<body[^>]*>|</html>|</body>(?i)", "");  
        html = html.replaceAll("\\\r\n|\n|\r", " ");//去掉换行  
        html = html.replaceAll("\\<br[^>]*>(?i)", "\n");  
        return html.trim();  
    }  

}
