package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import model.ColumnProperty;
import model.TableInfo;


public class CodeGenerator {
    static String url;
    static String pathString;
    static String pathOne;
    static String pathTwo;

    public CodeGenerator() {
        url="";
        pathString = "";
    }

    public CodeGenerator(String url,String path) {
        setUrl(url);
        setPathString(path);
    }

    public static String getPathString() {
        return pathString;
    }

    public static void setPathString(String pathString) {
        CodeGenerator.pathString = pathString;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        CodeGenerator.url = url;
    }

    public static String capitalize(String s){
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static String uncapitalize(String s){
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }

    public void GEN(int sheet, Vector<Integer> numSubObjs) throws IOException {
        TableInfo tableInfo = new TableInfo(url, sheet, numSubObjs);
        pathOne = pathString+"\\"+tableInfo.tableName+"\\"+"service";
        pathTwo = pathString+"\\"+tableInfo.tableName+"\\"+"web";
        Service.genService(tableInfo,pathOne);
        Web.genWeb(tableInfo, pathTwo);
    }
}