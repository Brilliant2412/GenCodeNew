package controller;

import People.Hung;
import People.Tung;
import model.ColumnProperty;
import model.TableInfo;
import static controller.CodeGenerator.capitalize;
import static controller.CodeGenerator.uncapitalize;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Web {
    private static String checkDang(String tenTruong,String moTa,String kieuDL,String kieuNhap){
        String res = "";
        if (kieuDL.equals("String")){
            res = "\t\t\t\t<label class=\"col-lg-1 control-label  lb_input\">"+moTa+"</label>\n" +
                    "\t\t\t\t<div class=\"col-lg-2\">\n"+
                    "\t\t\t\t\t<input name=\""+tenTruong+"\" id=\""+tenTruong+"\" type=\"text\" class=\"form-control\"/>\n" +
                    "\t\t\t\t\t<span id=\""+tenTruong+"_error\" class=\"note note-error\"></span>\n" +
                    "\t\t\t\t</div>\n";
        }else if (kieuDL.equals("Long") || kieuDL.equals("Double")){
            if (kieuNhap.equals("Combobox")){
                res = "\t\t\t\t<label class=\"col-lg-1 control-label  lb_input\">"+moTa+"</label>\n" +
                        "\t\t\t\t<div class=\"col-lg-2\">\n" +
                        "\t\t\t\t\t<div id=\"cb"+tenTruong+"\"></div> \n" +
                        "\t\t\t\t\t<input name=\""+tenTruong+"\" id=\""+tenTruong+"\" class=\"text_hidden\"  />\n" +
                        "\t\t\t\t\t<span id=\""+tenTruong+"_error\" class=\"note note-error\"></span>\n" +
                        "\t\t\t\t</div>\n";
            }else{
                res = "\t\t\t\t<label class=\"col-lg-1 control-label  lb_input\">"+moTa+"</label>\n" +
                        "\t\t\t\t<div class=\"col-lg-2\">\n" +
                        "\t\t\t\t\t<input name=\""+tenTruong+"\" id=\""+tenTruong+"\" type=\"number\" class=\"form-control\"/>\n" +
                        "\t\t\t\t\t<span id=\""+tenTruong+"_error\" class=\"note note-error\"></span>                           \n" +
                        "\t\t\t\t</div>\n";
            }
        }else if (kieuDL.equals("Date")){
            res = "\t\t\t\t<label class=\"col-lg-1 control-label  lb_input\">"+moTa+"</label>\n" +
                    "\t\t\t\t<div class=\"col-md-2\" input-group>\n"+
                    "\t\t\t\t<input class=\"dateCalendar\" placeholder=\"Bắt đầu\" name=\""+tenTruong+"\" id=\""+tenTruong+"\" type=\"text\"/>\n" +
                    "\t\t\t\t\t<span id=\""+tenTruong+"_error\" class=\"note note-error\"></span>\n" +
                    "\t\t\t\t</div>\n";
        }
        return res;
    }

    static void genController(TableInfo tableInfo, String folder) throws IOException {
        FileWriter fileWriter = new FileWriter(folder + "\\" + tableInfo.tableName + "Controller.java");
        fileWriter.write("package com.tav.web.controller;\n" +
                "\n" +
                "import com.google.common.base.Strings;\n" +
                "import com.tav.web.common.DateUtil;" +
                "import com.google.gson.Gson;\n" +
                "import com.google.gson.JsonObject;\n" +
                "import com.tav.common.web.form.JsonDataGrid;\n" +
                "import com.tav.web.bo.ServiceResult;\n" +
                "import com.tav.web.bo.UserSession;\n" +
                "import com.tav.web.bo.ValidationResult;\n" +
                "import com.tav.web.common.CommonConstant;\n" +
                "import com.tav.web.common.CommonFunction;\n" +
                "import com.tav.web.common.ConvertData;\n" +
                "import com.tav.web.common.ErpConstants;\n" +
                "import com.tav.web.common.StringUtil;\n" +
                "import com.tav.web.data."+ tableInfo.tableName+"Data;\n" +
                "import com.tav.web.dto."+ tableInfo.tableName+"DTO;\n" +
                "import com.tav.web.dto.ImportErrorMessage;\n" +
                "import java.util.Date;\n" +
                "import com.tav.web.dto.SearchCommonFinalDTO;\n" +
                "import com.tav.web.dto.ObjectCommonSearchDTO;\n" +
                "import java.io.BufferedOutputStream;\n" +
                "import java.io.File;\n" +
                "import java.io.FileInputStream;\n" +
                "import java.io.FileNotFoundException;\n" +
                "import java.io.FileOutputStream;\n" +
                "import java.io.IOException;\n" +
                "import java.nio.file.Files;\n" +
                "import java.nio.file.Path;\n" +
                "import java.nio.file.Paths;\n" +
                "import java.text.ParseException;\n" +
                "import java.text.SimpleDateFormat;\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.Iterator;\n" +
                "import java.util.List;\n" +
                "import java.util.regex.Pattern;\n" +
                "import javax.servlet.http.HttpServletRequest;\n" +
                "import javax.servlet.http.HttpServletResponse;\n" +
                "import javax.servlet.http.HttpSession;\n" +
                "import javax.ws.rs.core.MediaType;\n" +
                "import org.apache.poi.hssf.usermodel.HSSFWorkbook;\n" +
                "import org.apache.poi.ss.usermodel.Cell;\n" +
                "import org.apache.poi.ss.usermodel.DataFormatter;\n" +
                "import org.apache.poi.ss.usermodel.Row;\n" +
                "import org.apache.poi.ss.usermodel.Sheet;\n" +
                "import org.apache.poi.ss.usermodel.Workbook;\n" +
                "import org.apache.poi.xssf.usermodel.XSSFWorkbook;\n" +
                "import org.json.JSONObject;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Controller;\n" +
                "import org.springframework.ui.Model;\n" +
                "import org.springframework.web.bind.annotation.ModelAttribute;\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                "import org.springframework.web.bind.annotation.RequestMethod;\n" +
                "import org.springframework.web.bind.annotation.ResponseBody;\n" +
                "import org.springframework.web.multipart.MultipartFile;\n" +
                "import org.springframework.web.multipart.MultipartHttpServletRequest;\n" +
                "import org.springframework.web.servlet.ModelAndView;\n");

        fileWriter.append("\n" +
                "@Controller\n" +
                "public class "+ tableInfo.tableName+"Controller extends SubBaseController {\n" +
                "\n" +
                "    @Autowired\n" +
                "    private "+ tableInfo.tableName+"Data "+uncapitalize(tableInfo.tableName)+"Data;\n" +
                "\n" +
                "    @RequestMapping(\"/\" + ErpConstants.RequestMapping."+ tableInfo.title.toUpperCase()+")\n" +
                "    public ModelAndView agent(Model model, HttpServletRequest request) {\n" +
                "        return new ModelAndView(\""+uncapitalize(tableInfo.tableName)+"\");\n" +
                "    }\n" +
                "\n" +
                "    @RequestMapping(value = {\"/\" + ErpConstants.RequestMapping.GET_ALL_"+ tableInfo.title.toUpperCase()+"}, method = RequestMethod.GET)\n" +
                "    @ResponseBody\n" +
                "    public JsonDataGrid getAll(HttpServletRequest request) {\n" +
                "        try {\n" +
                "            // get info paging\n" +
                "            Integer currentPage = getCurrentPage();\n" +
                "            Integer limit = getTotalRecordPerPage();\n" +
                "            Integer offset = --currentPage * limit;\n" +
                "            JsonDataGrid dataGrid = new JsonDataGrid();\n" +
                "            SearchCommonFinalDTO searchDTO = new SearchCommonFinalDTO();\n");
                



                int count_cb =0;
                int count_db =0;
                int count_long =0;
                int count_date =0;

                for (int i = 0; i < tableInfo.columns.size(); i++) {
                    ColumnProperty colProp = tableInfo.columns.get(i);
                    if (colProp.isSearch())
                    {
                        if (colProp.getColType().equals("Long") )
                        {
                            if (colProp.getInputType().equals("Combobox"))
                            {
                                count_cb++;
                            }
                            else
                            {
                                count_long++;
                            }
                        }
                        if (colProp.getColType().equals("Double"))
                        {
                            count_db++;
                        }
                        if (colProp.getColType().equals("Date"))
                        {
                            count_date++;
                        }

                    }

                }
                fileWriter.append("            searchDTO.setStringKeyWord(request.getParameter(\"key\"));\n");

                for (int i = 0; i < count_cb; i++) {

                    fileWriter.append("\tif (request.getParameter(\"listLong"+(i+1)+"\") != null) {\n" +
                            "                searchDTO.setListLong"+(i+1)+"(ConvertData.convertStringToListLong(request.getParameter(\"listLong"+(i+1)+"\")));\n" +
                            "            }\n");

                }
                for (int i = 0; i < 2*count_db; i+=2) {
                    fileWriter.append("\ttry{\n" +
                            "                searchDTO.setDouble"+(i+1)+"(Double.parseDouble(request.getParameter(\"double"+(i+1)+"\")));\n" +
                            "                searchDTO.setDouble"+(i+2)+"(Double.parseDouble(request.getParameter(\"double"+(i+2)+"\")));\n" +
                            "            }catch(Exception ex){}\n");

                }

                for (int i = 0; i < 2*count_long; i+=2) {
                    fileWriter.append("\ttry{\n" +
                            "                searchDTO.setLong"+(i+1)+"(Long.parseLong(request.getParameter(\"long"+(i+1)+"\")));\n" +
                            "                searchDTO.setLong"+(i+2)+"(Long.parseLong(request.getParameter(\"long"+(i+2)+"\")));\n" +
                            "            }catch(Exception ex){}\n");

                }

                for (int i = 0; i < 2*count_date; i+=2) {
                    fileWriter.append("            searchDTO.setString"+(i+1)+"(request.getParameter(\"string"+(i+1)+"\"));\n" +
                            "            searchDTO.setString"+(i+2)+"(request.getParameter(\"string"+(i+2)+"\"));");

                }




        
                        
            fileWriter.append("            List<"+ tableInfo.tableName+"DTO> lst = new ArrayList<>();\n" +
                "            Integer totalRecords = 0;\n" +
                "            totalRecords = "+uncapitalize(tableInfo.tableName)+"Data.getCount(searchDTO);\n" +
                "            if (totalRecords > 0) {\n" +
                "                lst = "+uncapitalize(tableInfo.tableName)+"Data.getAll(searchDTO, offset, limit);\n" +
                "            }\n" +
                "            dataGrid.setCurPage(getCurrentPage());\n" +
                "            dataGrid.setTotalRecords(totalRecords);\n" +
                "            dataGrid.setData(lst);\n" +
                "            return dataGrid;\n" +
                "        } catch (Exception e) {\n" +
                "            logger.error(e.getMessage(), e);\n" +
                "            return null;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    @RequestMapping(value = \"/\" + ErpConstants.RequestMapping.GET_"+ tableInfo.title.toUpperCase()+"_BY_ID, method = RequestMethod.GET)\n" +
                "    public @ResponseBody\n" +
                "    "+ tableInfo.tableName+"DTO getOneById(HttpServletRequest request) {\n" +
                "        Long id = Long.parseLong(request.getParameter(\"gid\"));\n" +
                "        return "+uncapitalize(tableInfo.tableName)+"Data.getOneById(id);\n" +
                "    }\n" +
                "\n" +
                "    //add\n" +
                "    @RequestMapping(value = {\"/\" + ErpConstants.RequestMapping.ADD_"+ tableInfo.title.toUpperCase()+"}, method = RequestMethod.POST, produces = ErpConstants.LANGUAGE)\n" +
                "    @ResponseBody\n" +
                "    public String addOBJ(@ModelAttribute(\"" + uncapitalize(tableInfo.tableName) + "Form\") "+ tableInfo.tableName+"DTO "+uncapitalize(tableInfo.tableName)+"DTO, MultipartHttpServletRequest multipartRequest,\n" +
                "            HttpServletRequest request) throws ParseException {\n" +
                "\n" +
                "        JSONObject result;\n" +
                "        String error = validateForm("+uncapitalize(tableInfo.tableName)+"DTO);\n" +
                "        ServiceResult serviceResult;\n" +
                "        if (error != null) {\n" +
                "            return error;\n" +
                "        } else {\n");
        for(int i = 0; i < tableInfo.columns.size(); i++){
            ColumnProperty colProp = tableInfo.columns.get(i);
            if(colProp.getColType().equals("Date")){
                fileWriter.append(
                        "            if (!StringUtil.isEmpty(" + uncapitalize(tableInfo.tableName) + "DTO.get" + capitalize(colProp.getColName()) + "())) {\n" +
                                "                        " + uncapitalize(tableInfo.tableName) + "DTO.set" + capitalize(colProp.getColName()) + "(DateUtil.formatDate(" + uncapitalize(tableInfo.tableName) + "DTO.get" + capitalize(colProp.getColName()) + "()));\n" +
                                "            }\n"
                );
            }
        }
        fileWriter.append(
                "            serviceResult = "+uncapitalize(tableInfo.tableName)+"Data.addObj("+uncapitalize(tableInfo.tableName)+"DTO);\n" +
                        "            processServiceResult(serviceResult);\n" +
                        "            result = new JSONObject(serviceResult);\n" +
                        "        }\n" +
                        "        return result.toString();\n" +
                        "    }\n" +
                        "\n" +
                        "    //update\n" +
                        "    @RequestMapping(value = {\"/\" + ErpConstants.RequestMapping.UPDATE_"+ tableInfo.title.toUpperCase()+"}, method = RequestMethod.POST, produces = ErpConstants.LANGUAGE)\n" +
                        "    @ResponseBody\n" +
                        "    public String updateOBJ(@ModelAttribute(\"" + uncapitalize(tableInfo.tableName) + "Form\") "+ tableInfo.tableName+"DTO "+uncapitalize(tableInfo.tableName)+"DTO, MultipartHttpServletRequest multipartRequest,\n" +
                        "            HttpServletRequest request) throws ParseException {\n" +
                        "\n" +
                        "        JSONObject result;\n" +
                        "        String error = validateForm("+uncapitalize(tableInfo.tableName)+"DTO);\n" +
                        "        ServiceResult serviceResult;\n" +
                        "        if (error != null) {\n" +
                        "            return error;\n" +
                        "        } else {\n");
        for(int i = 0; i < tableInfo.columns.size(); i++){
            ColumnProperty colProp = tableInfo.columns.get(i);
            if(colProp.getColType().equals("Date")){
                fileWriter.append(
                        "            if (!StringUtil.isEmpty(" + uncapitalize(tableInfo.tableName) + "DTO.get" + capitalize(colProp.getColName()) + "())) {\n" +
                                "                        " + uncapitalize(tableInfo.tableName) + "DTO.set" + capitalize(colProp.getColName()) + "(DateUtil.formatDate(" + uncapitalize(tableInfo.tableName) + "DTO.get" + capitalize(colProp.getColName()) + "()));\n" +
                                "            }\n"
                );
            }
        }
        fileWriter.append(
                "            serviceResult = "+uncapitalize(tableInfo.tableName)+"Data.updateBO("+uncapitalize(tableInfo.tableName)+"DTO);\n" +
                        "            processServiceResult(serviceResult);\n" +
                        "            result = new JSONObject(serviceResult);\n" +
                        "        }\n" +
                        "        return result.toString();\n" +
                        "    }\n" +
                        "\n" +
                        "    //validate\n" +
                        "    private String validateForm("+ tableInfo.tableName+"DTO cbChaDTO) {\n" +
                        "        List<ValidationResult> lsError = new ArrayList<>();\n" +
                        "        if (lsError.size() > 0) {\n" +
                        "            Gson gson = new Gson();\n" +
                        "            return gson.toJson(lsError);\n" +
                        "        }\n" +
                        "        return null;\n" +
                        "    }\n" +
                        "\n" +
                        "    @RequestMapping(value = {\"/\" + ErpConstants.RequestMapping.DELETE_"+ tableInfo.title.toUpperCase()+"}, method = RequestMethod.POST,\n" +
                        "            produces = \"text/html;charset=utf-8\")\n" +
                        "    public @ResponseBody\n" +
                        "    String deleteObj(@ModelAttribute(\"objectCommonSearchDTO\") ObjectCommonSearchDTO objectCommonSearchDTO,\n" +
                        "            HttpServletRequest request) {\n" +
                        "        HttpSession session = request.getSession();\n" +
                        "        ServiceResult serviceResult = "+uncapitalize(tableInfo.tableName)+"Data.deleteObj(objectCommonSearchDTO);\n" +
                        "        processServiceResult(serviceResult);\n" +
                        "        JSONObject result = new JSONObject(serviceResult);\n" +
                        "        return result.toString();\n" +
                        "    }\n" +
                        "\n" +
                        "}\n");

        fileWriter.close();
    }

    static void genControllerParameters(TableInfo tableInfo, String folder) throws IOException {
        FileWriter fileWriter = new FileWriter(folder + "\\" + tableInfo.tableName.toLowerCase() +".html");
        fileWriter.write("public static final String "+ tableInfo.title.toUpperCase()+" = \""+ tableInfo.tableName.toLowerCase() +".html\";\n" +
                "public static final String GET_"+ tableInfo.title.toUpperCase()+"_BY_ID = \"getone" + tableInfo.tableName.toLowerCase() +"bygid.json\";\n" +
                "public static final String GET_ALL_"+ tableInfo.title.toUpperCase()+"= \"getall"+ tableInfo.tableName.toLowerCase() +".json\";\n" +
                "public static final String ADD_"+ tableInfo.title.toUpperCase()+" = \"add"+ tableInfo.tableName.toLowerCase() +".html\";\n" +
                "public static final String UPDATE_"+ tableInfo.title.toUpperCase()+" = \"update"+ tableInfo.tableName.toLowerCase() +".html\";\n" +
                "public static final String DELETE_"+ tableInfo.title.toUpperCase()+" = \"delete"+ tableInfo.tableName.toLowerCase() +".html\";");
        fileWriter.close();
    }

    static void genData(TableInfo tableInfo, String folder) throws IOException {
        FileWriter fileWriter = new FileWriter(folder + "\\" + tableInfo.tableName + "Data.java");
        fileWriter.write("package com.tav.web.data;\n" +
                "\n" +
                "import com.tav.web.bo.ServiceResult;\n" +
                "import com.tav.web.common.Config;\n" +
                "import com.tav.web.common.RestRequest;\n" +
                "import com.tav.web.dto."+ tableInfo.tableName +"DTO;\n" +
                "import com.tav.web.dto.SearchCommonFinalDTO;\n" +
                "import com.tav.web.dto.ObjectCommonSearchDTO;\n" +
                "import java.util.List;\n" +
                "import java.util.Date;\n" +
                "import org.apache.log4j.Logger;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Component;\n\n");
        fileWriter.append("@Component\n" +
                "public class "+ tableInfo.tableName +"Data {\n" +
                "\tprotected static final Logger logger = Logger.getLogger("+ tableInfo.tableName +"Data.class);\n" +
                "\tprivate static final String subUrl = \"/"+ uncapitalize(tableInfo.tableName) +"RsServiceRest\";\n");
        fileWriter.append("\n\t@Autowired\n" +
                "\tprivate Config config;\n");
        fileWriter.append("\n\t// get all\n" +
                "\tpublic List<"+ tableInfo.tableName +"DTO> getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {\n" +
                "\t\tString url = config.getRestURL() + subUrl + \"/getAll/\" + offset + \"/\" + limit;\n" +
                "\t\ttry {\n" +
                "\t\t\tList<"+ tableInfo.tableName +"DTO> jsResult = RestRequest.postAndReturnObjectArray(url, searchDTO, "+ tableInfo.tableName +"DTO.class);\n" +
                "\t\t\tif (jsResult == null) {\n" +
                "\t\t\t\treturn null;\n" +
                "\t\t\t} else {\n" +
                "\t\t\t\treturn jsResult;\n" +
                "\t\t\t}\n" +
                "\t\t} catch (Exception e) {\n" +
                "\t\t\tlogger.error(e.getMessage(), e);\n" +
                "\t\t\treturn null;\n" +
                "\t\t}\n" +
                "\t}\n");
        fileWriter.append("\n\t//get count\n" +
                "\tpublic Integer getCount(SearchCommonFinalDTO searchDTO) {\n" +
                "\t\tString url = config.getRestURL() + subUrl + \"/getCount\";\n" +
                "\t\treturn (Integer) RestRequest.postAndReturnObject(url, searchDTO, Integer.class);\n" +
                "\t}\n");
        fileWriter.append("\n\tpublic ServiceResult addObj("+ tableInfo.tableName +"DTO cbChaDTO) {\n" +
                "\t\tString url = config.getRestURL() + subUrl + \"/addDTO/\";\n" +
                "\t\tServiceResult result = (ServiceResult) RestRequest.postAndReturnObject(url, cbChaDTO, ServiceResult.class);\n" +
                "\t\treturn result;\n" +
                "\t}");
        fileWriter.append("\n\tpublic ServiceResult updateBO("+ tableInfo.tableName +"DTO cbChaDTO) {\n" +
                "\t\tString url = config.getRestURL() + subUrl + \"/updateBO/\";\n" +
                "\t\tServiceResult result = (ServiceResult) RestRequest.postAndReturnObject(url, cbChaDTO, ServiceResult.class);\n" +
                "\t\treturn result;\n" +
                "\t}\n");
        fileWriter.append("\n\tpublic ServiceResult deleteObj(ObjectCommonSearchDTO objectCommonSearchDTO) {\n" +
                "\t\tString url = config.getRestURL() + subUrl + \"/deleteList/\";\n" +
                "\t\tServiceResult result = (ServiceResult) RestRequest.postAndReturnObject(url, objectCommonSearchDTO, ServiceResult.class);\n" +
                "\t\treturn result;\n" +
                "\t}\n");
        fileWriter.append("\n\tpublic "+ tableInfo.tableName +"DTO getOneById(Long id) {\n" +
                "\t\tString url = config.getRestURL() + subUrl + \"/getOneById/\" + id;\n" +
                "\t\t"+ tableInfo.tableName +"DTO item = ("+ tableInfo.tableName +"DTO) RestRequest.getObject(url, "+ tableInfo.tableName +"DTO.class);\n" +
                "\t\treturn item;\n" +
                "\t}\n");
        fileWriter.append("\n\n}");
        fileWriter.close();
    }

    static void genTitle(TableInfo tableInfo, String folder) throws IOException {
        FileWriter fileWriter = new FileWriter(folder + "\\" + "title.txt");
        fileWriter.write("\t<definition name=\""+uncapitalize(tableInfo.tableName)+"\" extends=\"bodyContentAdmin.definition\">  \n" +
                "        \t<put-attribute name=\"title\" value=\"title.role\" />  \n" +
                "        \t<put-attribute name=\"body\" value=\"/WEB-INF/pages/"+ uncapitalize(tableInfo.tableName)+"/list.jsp\" />  \n" +
                "    \t</definition>");
        fileWriter.close();
    }

    static void genListjsp(TableInfo tableInfo, String folder) throws IOException {
        FileWriter fileWriter = new FileWriter(folder + "\\" + "list.jsp");
        fileWriter.write("<%@ page contentType=\"text/html;charset=UTF-8\" %>\n" +
                "<%@ taglib uri=\"http://www.springframework.org/tags/form\" prefix=\"form\"%>  \n" +
                "<%@ taglib uri=\"http://java.sun.com/jsp/jstl/fmt\" prefix=\"fmt\"%>\n" +
                "\n" +
                "<link href=\"${pageContext.request.contextPath}/share/bootstrap-multiselect/css/bootstrap-multiselect.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
                "<script src=\"${pageContext.request.contextPath}/share/bootstrap-multiselect/js/bootstrap-multiselect.js\" type=\"text/javascript\"></script>\n");
        fileWriter.append("<script src=\"${pageContext.request.contextPath}/share/core/js/"+uncapitalize(tableInfo.tableName)+".js\"/>\n");

        fileWriter.append("\n" +
                "\n" +
                "<link href=\"share/core/css/guideproperty.css\" rel=\"stylesheet\">\n" +
                "<link href=\"share/core/css/common.css\" rel=\"stylesheet\">\n" +
                "<jsp:include page=\"../common/header.jsp\"></jsp:include>\n" +
                "    <section id=\"widget-grid\" class=\"\" style=\"height:100%;\">\n" +
                "        <div class=\"row\">\n" +
                "            <article class=\"col-sm-12 col-md-12 col-lg-12\">\n" +
                "                <div style=\"height:100% !important;padding: 3px;\">\n" +
                "                    <article class=\"widgetTop1 col-sm-12 col-md-12 col-lg-12\">\n" +
                "                    <jsp:include page=\"formSearch.jsp\" />\n" +
                "                    <div class=\"jarviswidget jarviswidget-color-blueDark\" id=\"wid-id-1\" data-widget-fullscreenbutton=\"false\"\n" +
                "                         data-widget-togglebutton=\"false\" data-widget-deletebutton=\"false\" data-widget-colorbutton=\"false\" data-widget-editbutton=\"false\"\n" +
                "                         style=\"height:100% !important;\">\n" +
                "                        <div style=\"height:100% !important;\">\n" +
                "                            <div class=\"widget-body no-padding\">\n" +
                "                                <div class=\"table-responsive\" id=\"table-responsive\">                           \n" +
                "                                    <table id=\"dataTblDocumentType\" style=\"width: 100%;\"></table>\n" +
                "                                    <jsp:include page=\"../common/tablePaging.jsp\"></jsp:include>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>      \n" +
                "                        </div>\n" +
                "                    </article>\n" +
                "                </div>\n" +
                "            </article>\n" +
                "        <jsp:include page=\"dialogAdd.jsp\" /> \n" +
                "        <jsp:include page=\"view.jsp\" />\n"+
                "    </section>\n" +
                "    <input type=\"hidden\" id=\"screenId\" value=\"${requestScope['javax.servlet.forward.request_uri']}\"/>\n" +
                "<script src=\"${pageContext.request.contextPath}/share/core/js/common.js\"/>");
        fileWriter.close();
    }

    static void genJs(TableInfo tableInfo, String folder) throws  IOException{
        FileWriter fileWriter = new FileWriter(folder + "\\" + uncapitalize(tableInfo.tableName) + ".js");
        fileWriter.write("//$(\"#TBL_DOCUMENT_TYPE\").addClass(\"active\");\n" +
                "//$(\"#naviParent\").replaceWith($(\"#ROOT_LAND_POINT  span\").html());\n" +
                "//$(\"#naviChild\").replaceWith($(\"#cbma  span\").html());\n" +
                "\n\n");
        /*********************************************************************************************
         *                                 var editCellRendererVT
         *********************************************************************************************/
        fileWriter.append(
                "var editCellRendererVT = function (gid) {\n" +
                        "    return '<div style=\"text-align: center\">'\n" +
                        "            + '    <a class=\"tooltipCus iconEdit\" href=\"javascript:objTblDocumentType.editTblDocumentType(\\'' + gid + '\\')\">'\n" +
                        "            + '        <span class=\"tooltipCustext\">' + $(\"#tooltipEdit\").val() + '</span><img src=\"share/core/images/edit.png\" class=\"grid-icon\"/>'\n" +
                        "            + '    </a><a class=\"tooltipCus iconDelete\" href=\"javascript:objTblDocumentType.deleteTblDocumentType(\\'' + gid + '\\')\">'\n" +
                        "            + '        <span class=\"tooltipCustext\">' + $(\"#tooltipDelete\").val() + '</span><img src=\"share/core/images/delete_1.png\" class=\"grid-icon\"/></a>'\n" +
                        "            + '</div>';" +
                        "};\n\n");

        /*********************************************************************************************
         *                                 var datafields
         *********************************************************************************************/
        fileWriter.append("var datafields = [\n");
        for (int i = 0; i <tableInfo.columns.size(); i++) {
            ColumnProperty columnProperty = tableInfo.columns.get(i);
            if (columnProperty.getColType().equals("String")) {
                fileWriter.append("    {name: '" + columnProperty.getColName() + "', type: 'String'},\n");
            }
            else if (columnProperty.getColType().equals("Date")) {
                fileWriter.append("    {name: '" + columnProperty.getColName() + "', type: 'String'},\n");
                fileWriter.append("    {name: '" + columnProperty.getColName() + "ST', type: 'String'},\n");
            }
            else fileWriter.append("    {name: '" + columnProperty.getColName() + "', type: 'Number'},\n");
        }
        fileWriter.append("];\n\n");

        /*********************************************************************************************
         *                                 var columns
         *********************************************************************************************/
        fileWriter.append("var columns = [\n" +
                "\t{text: \"STT\", sortable: false, datafield: '', styleClass: 'stt', clstitle: 'tlb_class_center', res: \"data-hide='phone'\"},\n" +
                "\t{text: 'gid', datafield: 'gid', hidden: true},\n");
        for (int i = 0; i < tableInfo.columns.size(); i++) {
            ColumnProperty columnProperty = tableInfo.columns.get(i);

            if (columnProperty.isShow())
            {
                fileWriter.append("\t{text: \""+columnProperty.getColDescription()+"\", datafield: '" + columnProperty.getColName());
                if(columnProperty.getColType().equals("Date") || columnProperty.getInputType().equals("Combobox")){
                    fileWriter.append("ST");
                }
                fileWriter.append("', res: \"data-class='phone'\"},\n");

            }
        }
        fileWriter.append("\t{text: \"Chức năng\", datafield: 'gid', edit: 1, sortable: false, clstitle: 'tlb_class_center'}\n");
        fileWriter.append("];\n\n");

        /*********************************************************************************************
         *                                 var gridSetting
         *********************************************************************************************/
        fileWriter.append("var gridSetting = {\n" +
                "    sortable: false,\n" +
                "    virtualmode: true,\n" +
                "    isSetting: false,\n" +
                "    enableSearch: false,\n" +
                "    onClickRow: true\n" +
                "};\n\n");
        fileWriter.write(
                "doSearch = function () {\n" +
                        "    vt_datagrid.loadPageAgainRes(\"#dataTblDocumentType\", \"getall" + tableInfo.tableName.toLowerCase() + ".json\");\n" +
                        "    vt_sys.showBody();\n" +
                        "    vt_loading.hideIconLoading();\n" +
                        "    return false;\n" +
                        "};\n\n"
        );
        
        
        
        for(int i = 0; i < tableInfo.columns.size(); i++){
            ColumnProperty colProp = tableInfo.columns.get(i);
            if(colProp.getColType().equals("Date")){
                fileWriter.append(
                        "$(\"#" + colProp.getColName() + "\").datepicker({\n" +
                                "\tduration: \"fast\",\n" +
                                "\tchangeMonth: true,\n" +
                                "\tchangeYear: true,\n" +
                                "\tdateFormat: 'dd/mm/yy',\n" +
                                "\tconstrainInput: true,\n" +
                                "\tdisabled: false,\n" +
                                "\tyearRange: \"-20:+10\",\n" +
                                "\tonSelect: function (selected) {\n" +
                                "\t}\n" +
                                "});\n\n"
                );
            }
        }
        
        for(int i = 0; i < tableInfo.columns.size(); i++){
            ColumnProperty colProp = tableInfo.columns.get(i);
            if(colProp.getColType().equals("Date")){
                fileWriter.append(
                        "$(\"#" + colProp.getColName() + "SearchFrom\").datepicker({\n" +
                                "\tduration: \"fast\",\n" +
                                "\tchangeMonth: true,\n" +
                                "\tchangeYear: true,\n" +
                                "\tdateFormat: 'dd/mm/yy',\n" +
                                "\tconstrainInput: true,\n" +
                                "\tdisabled: false,\n" +
                                "\tyearRange: \"-20:+10\",\n" +
                                "\tonSelect: function (selected) {\n" +
                                "\t}\n" +
                                "});\n\n"
                );
                fileWriter.append(
                        "$(\"#" + colProp.getColName() + "SearchTo\").datepicker({\n" +
                                "\tduration: \"fast\",\n" +
                                "\tchangeMonth: true,\n" +
                                "\tchangeYear: true,\n" +
                                "\tdateFormat: 'dd/mm/yy',\n" +
                                "\tconstrainInput: true,\n" +
                                "\tdisabled: false,\n" +
                                "\tyearRange: \"-20:+10\",\n" +
                                "\tonSelect: function (selected) {\n" +
                                "\t}\n" +
                                "});\n\n"
                );
            }
        }
        
        fileWriter.append(
                "$(function () {\n" +
                        "\tdoSearch();\n" +
                        "\t\n" +

                        /**********************************************************************************************
                         *                            onClickBtAdd
                         **********************************************************************************************/

                        "\tonClickBtAdd = function () {\n" +
                        "        vt_form.reset($('#" + uncapitalize(tableInfo.tableName) + "Form'));\n" +
                        "        $(\"#gid\").val(\"\"); // reset form\n" +
                        "        vt_form.clearError();\n" +
                        "        $(\"#isedit\").val(\"0\");\n" +
                        "        \n"
        );
        for(int i = 1; i < tableInfo.columns.size(); i++){
            ColumnProperty colProp = tableInfo.columns.get(i);
            if(colProp.getInputType().equals("Combobox")){
                fileWriter.append("\t\tvt_combobox.buildCombobox(\"cb" + colProp.getColName() + "\", \"" + colProp.getComboboxBuildPath() + "\", 0, \"" + colProp.getComboboxName() + "\", \"" + colProp.getComboboxValue() + "\", \"- Chọn " + colProp.getColDescription() + " -\", 0);\n");
            }
        }
        fileWriter.append("\n" +
                "        $('#dialog-formAddNew').dialog({\n" +
                "            title: \"Thêm mới " + tableInfo.description + "\"\n" +
                "        }).dialog('open');\n" +
                "        return false;\n" +
                "    };\n" +
                "\t\n"
        );

        /**********************************************************************************************
         *                            setValueToForm
         **********************************************************************************************/

        fileWriter.append(
                "\tsetValueToForm = function () {\n" +
                        "        var item;\n");
        for(int i = 0; i < tableInfo.columns.size(); i++){
            ColumnProperty colProp = tableInfo.columns.get(i);
            if(colProp.getInputType().equals("Combobox")){
                fileWriter.append(
                        "\t\titem = $('#cb" + colProp.getColName() + "Combobox').val();\n" +
                                "\t\t$('input[name=\""  + colProp.getColName() + "\"]').val(item);\n"
                );
            }
        }
        fileWriter.append("\t}");

        /**********************************************************************************************
         *                            editTblDocumentTypeMethod
         **********************************************************************************************/

        fileWriter.append("\n" +
                "\teditTblDocumentTypeMethod = function () {\n" +
                "        clearError();\n" +
                "        setValueToForm();\n" +
                "        $.ajax({\n" +
                "            traditional: true,\n" +
                "            url: \"token.json\",\n" +
                "            dataType: \"text\",\n" +
                "            type: \"GET\"\n" +
                "        }).success(function (result) {\n" +
                "            if (vt_form.validate1(\"#" + uncapitalize(tableInfo.tableName) + "Form\", null, objTblDocumentType.validateRule))\n" +
                "            {\n" +
                "                var formdataTmp = new FormData();\n" +
                "                var formData = new FormData(document.getElementById(\"" + uncapitalize(tableInfo.tableName) + "Form\"));\n" +
                "                for (var pair of formData.entries()) {\n" +
                "                    formdataTmp.append(pair[0], pair[1]);\n" +
                "                }\n" +
                "                $.ajax({\n" +
                "                    async: false,\n" +
                "                    url: \"update" + tableInfo.tableName.toLowerCase() + ".html\",\n" +
                "                    data: formdataTmp,\n" +
                "                    processData: false,\n" +
                "                    contentType: false,\n" +
                "                    enctype: 'multipart/form-data',\n" +
                "                    type: \"POST\",\n" +
                "                    headers: {\"X-XSRF-TOKEN\": result},\n" +
                "                    dataType: 'json',\n" +
                "                    beforeSend: function (xhr) {\n" +
                "                        vt_loading.showIconLoading();\n" +
                "                    },\n" +
                "                    success: function (result) {\n" +
                "                        if (result.error != null) {\n" +
                "                            vt_loading.hideIconLoading();\n" +
                "                        } else\n" +
                "                        if (result !== null && result.length > 0) {\n" +
                "                            for (var i = 0; i < result.length; i++) {\n" +
                "                                $(\"#\" + result[i].fieldName + \"_error\").text(result[i].error);\n" +
                "                            }\n" +
                "                            setTimeout('$(\"#\"' + result[0].fieldName + ').focus()', 100);\n" +
                "                            vt_loading.hideIconLoading();\n" +
                "                        } else {\n" +
                "                            $(\"#dialog-formAddNew\").dialog(\"close\");\n" +
                "                            doSearch();\n" +
                "                            vt_loading.hideIconLoading();\n" +
                "                            vt_loading.showAlertSuccess(\"Chỉnh sửa thông tin thành công\");\n" +
                "                        }\n" +
                "\n" +
                "                    }, error: function (xhr, ajaxOption, throwErr) {\n" +
                "                        console.log(xhr);\n" +
                "                        console.log(ajaxOption);\n" +
                "                        console.log(throwErr);\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "    };\n"
        );

        /**********************************************************************************************
         *                            addTblDocumentTypeMethod
         **********************************************************************************************/

        fileWriter.append("\n" +
                "\taddTblDocumentTypeMethod = function () {\n" +
                "        vt_form.clearError();\n" +
                "        setValueToForm();\n" +
                "        $.ajax({\n" +
                "            traditional: true,\n" +
                "            url: \"token.json\",\n" +
                "            dataType: \"text\",\n" +
                "            type: \"GET\"\n" +
                "        }).success(function (result) {\n" +
                "            if (vt_form.validate1(\"#" + uncapitalize(tableInfo.tableName) + "Form\", null, objTblDocumentType.validateRule))\n" +
                "            {\n" +
                "                var formdataTmp = new FormData();\n" +
                "                var formData = new FormData(document.getElementById(\"" + uncapitalize(tableInfo.tableName) + "Form\"));\n" +
                "                for (var pair of formData.entries()) {\n" +
                "                    formdataTmp.append(pair[0], pair[1]);\n" +
                "                }\n" +
                "                $.ajax({\n" +
                "                    url: \"add" + tableInfo.tableName.toLowerCase() + ".html\",\n" +
                "                    data: formdataTmp,\n" +
                "                    processData: false,\n" +
                "                    contentType: false,\n" +
                "                    enctype: 'multipart/form-data',\n" +
                "                    type: \"POST\",\n" +
                "                    headers: {\"X-XSRF-TOKEN\": result},\n" +
                "                    dataType: 'json',\n" +
                "                    beforeSend: function (xhr) {\n" +
                "                        vt_loading.showIconLoading();\n" +
                "                    },\n" +
                "                    success: function (result) {\n" +
                "                        if (result.error != null) {\n" +
                "                        } else if (result !== null && result.length > 0) {\n" +
                "                            for (var i = 0; i < result.length; i++) {\n" +
                "                                $(\"#\" + result[i].fieldName + \"_error\").text(result[i].error);\n" +
                "                            }\n" +
                "                            setTimeout('$(\"#' + result[0].fieldName + '\").focus()', 100);\n" +
                "                            vt_loading.hideIconLoading();\n" +
                "                        } else {\n" +
                "                            $(\"#dialog-formAddNew\").dialog(\"close\");\n" +
                "                            doSearch();\n" +
                "                            vt_loading.hideIconLoading();\n" +
                "                            vt_loading.showAlertSuccess(\"Thêm mới thành công\");\n" +
                "                        }\n" +
                "                    }, error: function (xhr, ajaxOption, throwErr) {\n" +
                "                        console.log(xhr);\n" +
                "                        console.log(ajaxOption);\n" +
                "                        console.log(throwErr);\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "\n" +
                "        });\n" +
                "    };\n" +
                "});\n\n"
        );

        /*********************************************************************************************
         *                                 var objTblDocumentType
         *********************************************************************************************/
        fileWriter.append("var objTblDocumentType = {\n" +
                /*********************************************************************************************
                 *                                 validateRule
                 *********************************************************************************************/
                "\tvalidateRule: {\n" +
                "        rules: {\n");
        for (int i = 0; i < tableInfo.columns.size(); i++) {
            ColumnProperty columnProperty = tableInfo.columns.get(i);
            if (columnProperty.isValidate())
            {
                fileWriter.append("\t\t\t"+columnProperty.getColName()+": {\n" +
                        "                required: true\n" +
                        "            }");
                if(i != tableInfo.columns.size() -1){
                    fileWriter.append(",");
                }
                fileWriter.append("\n");
            }

        }
        fileWriter.append("        },\n");

        fileWriter.append("        messages: {\n");

        for (int i = 0; i < tableInfo.columns.size(); i++) {
            ColumnProperty columnProperty = tableInfo.columns.get(i);
            if (columnProperty.isValidate())
            {
                fileWriter.append("\t\t\t"+columnProperty.getColName()+": {\n" +
                        "                required: \""+columnProperty.getValidateMessage()+"\"\n" +
                        "            }");
                if(i != tableInfo.columns.size() -1){
                    fileWriter.append(",");
                }
                fileWriter.append("\n");
            }

        }
        fileWriter.append("        }\n" +
                "    },\n\n");

        /*********************************************************************************************
         *                                 editTblDocumentType
         *********************************************************************************************/
        fileWriter.append("\teditTblDocumentType: function (id) {\n" +
                "        if (id !== null) {\n" +
                "            vt_form.reset($('#"+uncapitalize(tableInfo.tableName)+"Form'));\n" +
                "            vt_form.clearError();\n" +
                "            $.ajax({\n" +
                "                async: false,\n" +
                "                data: {gid: id},\n" +
                "                url: \"getone"+tableInfo.tableName.toLowerCase()+"bygid.json\",\n" +
                "                success: function (data, status, xhr) {\n" +
                "                    $(\"#gid\").val(data.gid);\n");
        for (int i = 1; i < tableInfo.columns.size(); i++) {
            ColumnProperty columnProperty = tableInfo.columns.get(i);
            if (columnProperty.getColType().equals("Date"))
            {
                fileWriter.append("\t\t\t\t\t$(\"#"+columnProperty.getColName()+"\").val(data."+columnProperty.getColName()+"ST);\n");
            }
            else if (columnProperty.getInputType().equals("Combobox"))
            {
                //ok gen lai cho anh file js cai file excel cua a la file nao a
                fileWriter.append("\t\t\t\t\tvt_combobox.buildCombobox(\"cb"+columnProperty.getColName()+"\", \""+columnProperty.getComboboxBuildPath()+"\", data."+columnProperty.getColName()+", \""+columnProperty.getComboboxName()+"\", \""+columnProperty.getComboboxValue()+"\", \"- Chọn "+columnProperty.getColDescription()+" -\", 0);\n");
            }
            else fileWriter.append("\t\t\t\t\t$(\"#"+columnProperty.getColName()+"\").val(data."+columnProperty.getColName()+");\n");

        }
        fileWriter.append("\n\t\t\t\t\t$('#dialog-formAddNew').dialog({\n" +
                "                        title: \"Cập nhật thông tin " + tableInfo.description + "\"\n" +
                "                    }).dialog('open');\n" +
                "                    // set css to form\n" +
                "                    $('#dialog-formAddNew').parent().addClass(\"dialogAddEdit\");\n" +
                "                    objCommon.setTimeout(\"code\");\n" +
                "                    return false;\n" +
                "                }\n" +
                "            });\n" +
                "        }\n" +
                "    },\n" +
                "\tgid: null,\n\n");
        /*********************************************************************************************
         *                                 deleteTblDocumentType
         *********************************************************************************************/
        fileWriter.append(
                "    deleteTblDocumentType: function (gid) {\n" +
                        "        if (gid !== null) {\n" +
                        "            var tmp_mess = '\"' + $(\"#dataTblDocumentType #\" + gid).parent().parent().parent().find(\".expand\").text() + '\"';\n" +
                        "            vt_loading.showConfirmDeleteDialog(\"Bạn có chắc chắn muốn xóa danh mục\" + \" \" + tmp_mess, function (callback) {\n" +
                        "                if (callback) {\n" +
                        "                    objTblDocumentType.gid = gid;\n" +
                        "                    objTblDocumentType.deleteOneTblDocumentType();\n" +
                        "                }\n" +
                        "            });\n" +
                        "        }\n" +
                        "    },\n\n");

        /*********************************************************************************************
         *                                 deleteOneTblDocumentType
         *********************************************************************************************/
        fileWriter.append(
                "\tdeleteOneTblDocumentType: function () {\n" +
                        "        vt_loading.showIconLoading();\n" +
                        "        var ids = objTblDocumentType.gid;\n" +
                        "        var onDone = function (result) {\n" +
                        "            if (result !== null && result.hasError) {\n" +
                        "                $(\"#deleteDialogMessageError\").text(result.error);\n" +
                        "                vt_loading.hideIconLoading();\n" +
                        "            } else {\n" +
                        "                $(\"#dialog-confirmDelete\").dialog(\"close\");\n" +
                        "                if ($(\"#allValue\").val() === ids && pagenum > 1) {\n" +
                        "                    pagenum--;\n" +
                        "                }\n" +
                        "                doSearch();\n" +
                        "                vt_loading.hideIconLoading();\n" +
                        "                vt_loading.showAlertSuccess(\"Xóa thành công\");\n" +
                        "            }\n" +
                        "        };\n" +
                        "        vt_form.ajax(\"POST\", \"delete" + tableInfo.tableName.toLowerCase() + ".html\", {lstFirst: ids}, null, null, onDone);\n" +
                        "    }\n" +
                        "\n" +
                        "};"
        );
        fileWriter.append("        }\n" +
                "    },\n\n");

        /*********************************************************************************************
         *                                 Viewjsp
         *********************************************************************************************/
        fileWriter.append("\n\tview : function(id) {\n" +
                "        if (id !== null) {\n" +
                "            vt_form.reset($('#"+uncapitalize(tableInfo.tableName)+"Form'));\n" +
                "            vt_form.clearError();\n" +
                "            $.ajax({\n" +
                "                async: false,\n" +
                "                data: {gid: id},\n" +
                "                url: \"getone"+tableInfo.tableName.toLowerCase()+"bygid.json\",\n" +
                "                success: function (data, status, xhr) {\n" +
                "                    $(\"#gid\").val(data.gid);\n");
        for (int i = 1; i < tableInfo.columns.size(); i++) {
            ColumnProperty columnProperty = tableInfo.columns.get(i);
            if (columnProperty.getColType().equals("Date"))
            {
                fileWriter.append("\t\t\t\t\t$(\"#"+columnProperty.getColName()+"View"+"\").val(data."+columnProperty.getColName()+"ST);\n");
            }
            else if (columnProperty.getInputType().equals("Combobox"))
            {
                fileWriter.append("\t\t\t\t\t$(\"#"+columnProperty.getColName()+"View"+"\").val(data."+columnProperty.getColName()+"ST"+");\n");
            }
            else fileWriter.append("\t\t\t\t\t$(\"#"+columnProperty.getColName()+"View"+"\").val(data."+columnProperty.getColName()+");\n");

        }
        fileWriter.append("\n\t\t\t\t\t$('#dialog-formView').dialog({\n" +
                "                        title: \"Xem " + tableInfo.description + "\"\n" +
                "                    }).dialog('open');\n" +
                "                    // set css to form\n" +
                "                    $('#dialog-formView').parent().addClass(\"dialogAddEdit\");\n" +
                "                    objCommon.setTimeout(\"code\");\n" +
                "                    return false;\n" +
                "                }\n" +
                "            });\n" +
                "        }\n" +
                "    }\n" +
                "}");
        fileWriter.close();
    }

    static void genDialogAdd(TableInfo tableInfo, String folder) throws IOException {
        FileWriter fileWriter = new FileWriter(folder + "\\dialogAdd.jsp");
        fileWriter.write("<%@ page contentType=\"text/html;charset=UTF-8\" %>\n" +
                "<%@ taglib prefix=\"spring\" uri=\"http://www.springframework.org/tags\" %>\n" +
                "<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>\n" +
                "<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\" %>\n" +
                "<%@ taglib uri=\"http://www.springframework.org/tags/form\" prefix=\"form\"%>  \n" +
                "<%@ taglib prefix=\"fmt\" uri=\"http://java.sun.com/jsp/jstl/fmt\" %>\n"+
                "<div id=\"dialog-formAddNew\">\n" +
                "\t<form:form id=\""+uncapitalize(tableInfo.tableName)+"Form\" modelAttribute=\""+uncapitalize(tableInfo.tableName)+"Form\" class=\"form-horizontal\">\t\n" +
                "\t\t<input type=\"hidden\" name=\"${_csrf.parameterName}\" value=\"${_csrf.token}\" />\n" +
                "\t\t<input type=\"hidden\" id=\"gid\" name=\"gid\" value=\"\"/>\n" +
                "\t\t<input type=\"hidden\" id=\"isedit\" name=\"isedit\" value=\"0\"/>\n" +
                "\t\t<fieldset>\n");
        fileWriter.append("\t\t\t<legend class=\"fs-legend-head\">\n" +
                "\t\t\t\t<span class=\"iconFS\"></span>\n" +
                "\t\t\t\t<span class=\"titleFS\" style=\"color: #047fcd !important;\"><b>Thông tin chung</b></span>\n" +
                "\t\t\t</legend>\n");
        int k =tableInfo.columns.size()-1;
        int r = k/4;
        int q = k%4;
        for (int i = 0;i<r;i++){
            fileWriter.append("\t\t\t<div class=\"form-group-add row\">\n");
            for (int j =0;j<=3;j++){
                String res = checkDang(tableInfo.columns.get(4*i+j+1).getColName(),
                        tableInfo.columns.get(4*i+j+1).getColDescription(),
                        tableInfo.columns.get(4*i+j+1).getColType(),
                        tableInfo.columns.get(4*i+j+1).getInputType()
                );
                fileWriter.append(res);
            }
            fileWriter.append("\t\t\t</div>\n\n");
        }
        if (q != 0){
            fileWriter.append("\t\t\t<div class=\"form-group-add row\">\n");
            for (int i = r*4+1;i<=k;i++){
                String res = checkDang(tableInfo.columns.get(i).getColName(),
                        tableInfo.columns.get(i).getColDescription(),
                        tableInfo.columns.get(i).getColType(),
                        tableInfo.columns.get(i).getInputType()
                );
                fileWriter.append(res);
            }
            fileWriter.append("\t\t\t</div>\n\n");
        }
        fileWriter.append("\t\t</fieldset>\n" +
                "\t</form:form>\n" +
                "</div>\n" +
                "<script type=\"text/javascript\">\n" +
                "\t$(\"#dialog-formAddNew\").dialog({\n" +
                "\t\twidth: isMobile.any() ? $(window).width() : ($(window).width() / 5 * 4),\n" +
                "\t\theight: $(window).height() / 5 * 4,\n" +
                "\t\tautoOpen: false,\n" +
                "\t\tmodal: true,\n" +
                "\t\tposition: [($(window).width() / 10 * 1), 50],\n" +
                "\t\topen: function () {\n" +
                "\t\t\t$('.areaTable').addClass('custom-overlay-popup-add-edit');\n" +
                "\t\t\t$('.dialogAddEdit').css('z-index', 1001);\n" +
                "\n" +
                "\t\t},\n" +
                "\t\tclose: function () {\n" +
                "\t\t\t$('.areaTable').removeClass('custom-overlay-popup-add-edit');\n" +
                "\n" +
                "\t\t},\n" +
                "\t\tbuttons: [{\n" +
                "\t\t\thtml: \"<fmt:message key='button.close' />\",\n" +
                "\t\t\t\"class\": \"btn btn-default\",\n" +
                "\t\t\tclick: function () {\n" +
                "\t\t\t$(this).dialog('close');\n" +
                "\t\t\t}\n" +
                " \t\t\t}, {\n" +
                "\t\t\t\thtml: \"<fmt:message key='button.update' />\",\n" +
                "\t\t\t\t\"class\": \"btn btn-primary\",\n" +
                "\t\t\t\t\"id\": \"btnAddTblInfoNotifyYes\",\n" +
                "\t\t\t\tclick: function () {\n" +
                "\t\t\t\t\tvar item = $('#isedit').val();\n" +
                "\t\t\t\t\tif (item === '0') {\n" +
                "\t\t\t\t\t\taddTblDocumentTypeMethod();\n" +
                "\t\t\t\t\t} else {\n" +
                "\t\t\t\t\t\teditTblDocumentTypeMethod();\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t});\n" +
                "</script>");
        fileWriter.close();
    }

    static void genDTO_Web(TableInfo tableInfo, String folder) throws IOException {
        FileWriter fileWriter = new FileWriter(folder + "\\" + tableInfo.tableName + "DTO.java");
        fileWriter.write(
                "package com.tav.web.dto;\n\n" +

                        "public class " + tableInfo.tableName + "DTO{\n"
        );
        for(int i = 0; i < tableInfo.columns.size(); i++){
            ColumnProperty colProp = tableInfo.columns.get(i);
            //fileWriter.append("\tprivate " + colProp.getColType() + " " + colProp.getColName() + ";\t\t//" + colProp.getColDescription() + "\n");
            fileWriter.append("\tprivate ");
            if(colProp.getColType().equals("Date")){
                fileWriter.append("String " + colProp.getColName() + ";\t\t//" + colProp.getColDescription() + "\n");
            }
            else{
                fileWriter.append(colProp.getColType() + " " + colProp.getColName() + ";\t\t//" + colProp.getColDescription() + "\n");
            }
            if(!colProp.getFKTable().equals("") || colProp.getColType().equals("Date")){
                fileWriter.append("\tprivate String " + colProp.getColName() + "ST;\n");
            }
        }
        fileWriter.append("\n");
        for(int i = 0; i < tableInfo.columns.size(); i++){
            ColumnProperty colProp = tableInfo.columns.get(i);
            fileWriter.append(
                    "\tpublic ");
            //colProp.getColType() +
            if(colProp.getColType().equals("Date")){
                fileWriter.append("String ");
            }
            else{
                fileWriter.append(colProp.getColType() + " ");
            }
            fileWriter.append("get"+capitalize(colProp.getColName()) + "(){\n" +
                    "\t\treturn " + colProp.getColName() + ";\n" +
                    "\t}\n\n");

            fileWriter.append(
                    "\tpublic void set" + capitalize(colProp.getColName()) + "(");
            //colProp.getColType()
            if(colProp.getColType().equals("Date")){
                fileWriter.append("String ");
            }
            else{
                fileWriter.append(colProp.getColType() + " ");
            }
            fileWriter.append(" " + colProp.getColName() + "){\n" +
                    "\t\tthis." + colProp.getColName() + " = " + colProp.getColName() + ";\n" +
                    "\t}\n\n"

            );
            if(!colProp.getFKTable().equals("") || colProp.getColType().equals("Date")){
                fileWriter.append(
                        "\tpublic String get" + capitalize(colProp.getColName()) + "ST(){\n" +
                                "\t\treturn " + colProp.getColName() + "ST;\n" +
                                "\t}\n\n" +

                                "\tpublic void set" + capitalize(colProp.getColName()) + "ST(String " + colProp.getColName() + "ST){\n" +
                                "\t\tthis." + colProp.getColName() + "ST = " + colProp.getColName() + "ST;\n" +
                                "\t}\n\n"
                );
            }
        }
        fileWriter.append("}");
        fileWriter.close();
    }

    static void genView(TableInfo tableInfo, String folder) throws IOException {
        FileWriter fileWriter = new FileWriter(folder + "\\view.jsp");
        fileWriter.write("<%@ page contentType=\"text/html;charset=UTF-8\" %>\n" +
                "<%@ taglib prefix=\"spring\" uri=\"http://www.springframework.org/tags\" %>\n" +
                "<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>\n" +
                "<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\" %>\n" +
                "<%@ taglib uri=\"http://www.springframework.org/tags/form\" prefix=\"form\"%>  \n" +
                "<%@ taglib prefix=\"fmt\" uri=\"http://java.sun.com/jsp/jstl/fmt\" %>\n" +
                "<div id=\"dialog-formView\">\n" +
                "\t<form:form id=\"" + uncapitalize(tableInfo.tableName) + "Form\" modelAttribute=\"" + uncapitalize(tableInfo.tableName) + "Form\" class=\"form-horizontal\">\t\n" +
                "\t\t<input type=\"hidden\" name=\"${_csrf.parameterName}\" value=\"${_csrf.token}\" />\n" +
                "\t\t<input type=\"hidden\" id=\"gid\" name=\"gid\" value=\"\"/>\n" +
                "\t\t<input type=\"hidden\" id=\"isedit\" name=\"isedit\" value=\"0\"/>\n" +
                "\t\t<fieldset>\n");
        fileWriter.append("\t\t\t<legend class=\"fs-legend-head\">\n" +
                "\t\t\t\t<span class=\"iconFS\"></span>\n" +
                "\t\t\t\t<span class=\"titleFS\" style=\"color: #047fcd !important;\"><b>Thông tin chung</b></span>\n" +
                "\t\t\t</legend>\n");
        int k = tableInfo.columns.size() - 1;
        int r = k / 4;
        int q = k % 4;
        for (int i = 0; i < r; i++) {
            fileWriter.append("\t\t\t<div class=\"form-group-add row\">\n");
            for (int j = 0; j <= 3; j++) {
                String res = "\t\t\t\t<label class=\"col-md-1 control-label \">" + tableInfo.columns.get(4*i+j+1).getColDescription() + "</label>\n" +
                        "\t\t\t\t<div class=\"col-md-2\">\n" +
                        "\t\t\t\t\t<input class=\"form-control\" placeholder=\"\" name=\"\" id=\""+tableInfo.columns.get(4*i+j+1).getColName()+"View"+"\" type=\"text\" readonly=\"true\"/>\n"+
                        "\t\t\t\t</div>\n";
                fileWriter.append(res);
            }
            fileWriter.append("\t\t\t</div>\n\n");
        }
        if (q != 0) {
            fileWriter.append("\t\t\t<div class=\"form-group-add row\">\n");
            for (int i = r * 4 + 1; i <= k; i++) {
                String res = "\t\t\t\t<label class=\"col-md-1 control-label \">" + tableInfo.columns.get(i).getColDescription() + "</label>\n" +
                        "\t\t\t\t<div class=\"col-md-2\">\n" +
                        "\t\t\t\t\t<input class=\"form-control\" placeholder=\"\" name=\"\" id=\""+tableInfo.columns.get(i).getColName()+"View"+"\" type=\"text\" readonly=\"true\"/>\n"+
                        "\t\t\t\t</div>\n";
                fileWriter.append(res);
            }
            fileWriter.append("\t\t\t</div>\n\n");
        }
        fileWriter.append("\t\t</fieldset>\n" +
                "\t</form:form>\n" +
                "</div>\n" +
                "<script type=\"text/javascript\">\n" +
                "\t$(\"#dialog-formView\").dialog({\n" +
                "\t\twidth: isMobile.any() ? $(window).width() : ($(window).width() / 5 * 4),\n" +
                "\t\theight: $(window).height() / 5 * 4,\n" +
                "\t\tautoOpen: false,\n" +
                "\t\tmodal: true,\n" +
                "\t\tposition: [($(window).width() / 10 * 1), 50],\n" +
                "\t\topen: function () {\n" +
                "\t\t\t$('.areaTable').addClass('custom-overlay-popup-add-edit');\n" +
                "\t\t\t$('.dialogAddEdit').css('z-index', 1001);\n" +
                "\n" +
                "\t\t},\n" +
                "\t\tclose: function () {\n" +
                "\t\t\t$('.areaTable').removeClass('custom-overlay-popup-add-edit');\n" +
                "\n" +
                "\t\t},\n" +
                "\t\tbuttons: [{\n" +
                "\t\t\thtml: \"<fmt:message key='button.close' />\",\n" +
                "\t\t\t\"class\": \"btn btn-default\",\n" +
                "\t\t\tclick: function () {\n" +
                "\t\t\t$(this).dialog('close');\n" +
                "\t\t\t}\n" +
                " \t\t\t}, {\n" +
                "\t\t\t\thtml: \"<fmt:message key='button.update' />\",\n" +
                "\t\t\t\t\"class\": \"btn btn-primary\",\n" +
                "\t\t\t\t\"id\": \"btnAddTblInfoNotifyYes\",\n" +
                "\t\t\t\tclick: function () {\n" +
                "\t\t\t\t\tvar item = $('#isedit').val();\n" +
                "\t\t\t\t\tif (item === '0') {\n" +
                "\t\t\t\t\t\taddTblDocumentTypeMethod();\n" +
                "\t\t\t\t\t} else {\n" +
                "\t\t\t\t\t\teditTblDocumentTypeMethod();\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t});\n" +
                "</script>");
        fileWriter.close();
    }

    static void genControllerSearch(TableInfo tableInfo, String folder) throws IOException {
        FileWriter fileWriter = new FileWriter(folder + "\\ControllerSearch.java");
        fileWriter.write("package com.tav.web.controller;\n" +
                "\n" +
                "import com.google.common.base.Strings;\n" +
                "import com.tav.web.common.DateUtil;import com.google.gson.Gson;\n" +
                "import com.google.gson.JsonObject;\n" +
                "import com.tav.common.web.form.JsonDataGrid;\n" +
                "import com.tav.web.bo.ServiceResult;\n" +
                "import com.tav.web.bo.UserSession;\n" +
                "import com.tav.web.bo.ValidationResult;\n" +
                "import com.tav.web.common.CommonConstant;\n" +
                "import com.tav.web.common.CommonFunction;\n" +
                "import com.tav.web.common.ConvertData;\n" +
                "import com.tav.web.common.ErpConstants;\n" +
                "import com.tav.web.common.StringUtil;\n" +
                "import com.tav.web.data.EvaluatePlan1Data;\n" +
                "import com.tav.web.dto.EvaluatePlan1DTO;\n" +
                "import com.tav.web.dto.ImportErrorMessage;\n" +
                "import java.util.Date;\n" +
                "import com.tav.web.dto.ObjectCommonSearchDTO;\n" +
                "import com.tav.web.dto.SearchCommonFinalDTO;\n" +
                "import java.io.BufferedOutputStream;\n" +
                "import java.io.File;\n" +
                "import java.io.FileInputStream;\n" +
                "import java.io.FileNotFoundException;\n" +
                "import java.io.FileOutputStream;\n" +
                "import java.io.IOException;\n" +
                "import java.nio.file.Files;\n" +
                "import java.nio.file.Path;\n" +
                "import java.nio.file.Paths;\n" +
                "import java.text.ParseException;\n" +
                "import java.text.SimpleDateFormat;\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.Iterator;\n" +
                "import java.util.List;\n" +
                "import java.util.regex.Pattern;\n" +
                "import javax.servlet.http.HttpServletRequest;\n" +
                "import javax.servlet.http.HttpServletResponse;\n" +
                "import javax.servlet.http.HttpSession;\n" +
                "import javax.ws.rs.core.MediaType;\n" +
                "import org.apache.poi.hssf.usermodel.HSSFWorkbook;\n" +
                "import org.apache.poi.ss.usermodel.Cell;\n" +
                "import org.apache.poi.ss.usermodel.DataFormatter;\n" +
                "import org.apache.poi.ss.usermodel.Row;\n" +
                "import org.apache.poi.ss.usermodel.Sheet;\n" +
                "import org.apache.poi.ss.usermodel.Workbook;\n" +
                "import org.apache.poi.xssf.usermodel.XSSFWorkbook;\n" +
                "import org.json.JSONObject;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Controller;\n" +
                "import org.springframework.ui.Model;\n" +
                "import org.springframework.web.bind.annotation.ModelAttribute;\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                "import org.springframework.web.bind.annotation.RequestMethod;\n" +
                "import org.springframework.web.bind.annotation.ResponseBody;\n" +
                "import org.springframework.web.multipart.MultipartFile;\n" +
                "import org.springframework.web.multipart.MultipartHttpServletRequest;\n" +
                "import org.springframework.web.servlet.ModelAndView;\n" +
                "\n" +
                "@Controller\n" +
                "public class EvaluatePlan1Controller extends SubBaseController {\n" +
                "\n" +
                "    @Autowired\n" +
                "    private EvaluatePlan1Data evaluatePlan1Data;\n" +
                "\n" +
                "    @RequestMapping(\"/\" + ErpConstants.RequestMapping.EVALUATE_PLAN1)\n" +
                "    public ModelAndView agent(Model model, HttpServletRequest request) {\n" +
                "        return new ModelAndView(\"evaluatePlan1\");\n" +
                "    }\n" +
                "\n" +
                "    @RequestMapping(value = {\"/\" + ErpConstants.RequestMapping.GET_ALL_EVALUATE_PLAN1}, method = RequestMethod.GET)\n" +
                "    @ResponseBody\n" +
                "    public JsonDataGrid getAll(HttpServletRequest request) {\n" +
                "        try {\n" +
                "            // get info paging\n" +
                "            Integer currentPage = getCurrentPage();\n" +
                "            Integer limit = getTotalRecordPerPage();\n" +
                "            Integer offset = --currentPage * limit;\n" +
                "            JsonDataGrid dataGrid = new JsonDataGrid();\n" +
                "            SearchCommonFinalDTO searchDTO = new SearchCommonFinalDTO();\n");

        int count_cb =0;
        int count_db =0;
        int count_long =0;
        int count_date =0;

        for (int i = 0; i < tableInfo.columns.size(); i++) {
            ColumnProperty colProp = tableInfo.columns.get(i);
            if (colProp.isSearch())
            {
                if (colProp.getColType().equals("Long") )
                {
                    if (colProp.getInputType().equals("Combobox"))
                    {
                        count_cb++;
                    }
                    else
                    {
                        count_long++;
                    }
                }
                if (colProp.getColType().equals("Double"))
                {
                    count_db++;
                }
                if (colProp.getColType().equals("Date"))
                {
                    count_date++;
                }

            }

        }
        fileWriter.append("            searchDTO.setStringKeyWord(request.getParameter(\"key\"));\n");

        for (int i = 0; i < count_cb; i++) {

            fileWriter.append("\tif (request.getParameter(\"listLong"+(i+1)+"\") != null) {\n" +
                    "                searchDTO.setListLong"+(i+1)+"(ConvertData.convertStringToListLong(request.getParameter(\"listLong"+(i+1)+"\")));\n" +
                    "            }\n");

        }
        for (int i = 0; i < 2*count_db; i+=2) {
            fileWriter.append("\ttry{\n" +
                    "                searchDTO.setDouble"+(i+1)+"(Double.parseDouble(request.getParameter(\"double"+(i+1)+"\")));\n" +
                    "                searchDTO.setDouble"+(i+2)+"(Double.parseDouble(request.getParameter(\"double"+(i+2)+"\")));\n" +
                    "            }catch(Exception ex){}\n");

        }

        for (int i = 0; i < 2*count_long; i+=2) {
            fileWriter.append("\ttry{\n" +
                    "                searchDTO.setLong"+(i+1)+"(Long.parseLong(request.getParameter(\"long"+(i+1)+"\")));\n" +
                    "                searchDTO.setLong"+(i+2)+"(Long.parseLong(request.getParameter(\"long"+(i+2)+"\")));\n" +
                    "            }catch(Exception ex){}\n");

        }

        for (int i = 0; i < 2*count_date; i+=2) {
            fileWriter.append("            searchDTO.setString"+(i+1)+"(request.getParameter(\"string"+(i+1)+"\"));\n" +
                    "            searchDTO.setString"+(i+2)+"(request.getParameter(\"string"+(i+2)+"\"));");

        }

        fileWriter.append("\n" +
                "            List<EvaluatePlan1DTO> lst = new ArrayList<>();\n" +
                "            Integer totalRecords = 0;\n" +
                "            totalRecords = evaluatePlan1Data.getCount(searchDTO);\n" +
                "            if (totalRecords > 0) {\n" +
                "                lst = evaluatePlan1Data.getAll(searchDTO, offset, limit);\n" +
                "            }\n" +
                "            dataGrid.setCurPage(getCurrentPage());\n" +
                "            dataGrid.setTotalRecords(totalRecords);\n" +
                "            dataGrid.setData(lst);\n" +
                "            return dataGrid;\n" +
                "        } catch (Exception e) {\n" +
                "            logger.error(e.getMessage(), e);\n" +
                "            return null;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    @RequestMapping(value = \"/\" + ErpConstants.RequestMapping.GET_EVALUATE_PLAN1_BY_ID, method = RequestMethod.GET)\n" +
                "    public @ResponseBody\n" +
                "    EvaluatePlan1DTO getOneById(HttpServletRequest request) {\n" +
                "        Long id = Long.parseLong(request.getParameter(\"gid\"));\n" +
                "        return evaluatePlan1Data.getOneById(id);\n" +
                "    }\n" +
                "\n" +
                "    //add\n" +
                "    @RequestMapping(value = {\"/\" + ErpConstants.RequestMapping.ADD_EVALUATE_PLAN1}, method = RequestMethod.POST, produces = ErpConstants.LANGUAGE)\n" +
                "    @ResponseBody\n" +
                "    public String addOBJ(@ModelAttribute(\"evaluatePlan1Form\") EvaluatePlan1DTO evaluatePlan1DTO, MultipartHttpServletRequest multipartRequest,\n" +
                "            HttpServletRequest request) throws ParseException {\n" +
                "\n" +
                "        JSONObject result;\n" +
                "        String error = validateForm(evaluatePlan1DTO);\n" +
                "        ServiceResult serviceResult;\n" +
                "        if (error != null) {\n" +
                "            return error;\n" +
                "        } else {\n" +
                "            if (!StringUtil.isEmpty(evaluatePlan1DTO.getExpertise_date())) {\n" +
                "                        evaluatePlan1DTO.setExpertise_date(DateUtil.formatDate(evaluatePlan1DTO.getExpertise_date()));\n" +
                "            }\n" +
                "            serviceResult = evaluatePlan1Data.addObj(evaluatePlan1DTO);\n" +
                "            processServiceResult(serviceResult);\n" +
                "            result = new JSONObject(serviceResult);\n" +
                "        }\n" +
                "        return result.toString();\n" +
                "    }\n" +
                "\n" +
                "    //update\n" +
                "    @RequestMapping(value = {\"/\" + ErpConstants.RequestMapping.UPDATE_EVALUATE_PLAN1}, method = RequestMethod.POST, produces = ErpConstants.LANGUAGE)\n" +
                "    @ResponseBody\n" +
                "    public String updateOBJ(@ModelAttribute(\"evaluatePlan1Form\") EvaluatePlan1DTO evaluatePlan1DTO, MultipartHttpServletRequest multipartRequest,\n" +
                "            HttpServletRequest request) throws ParseException {\n" +
                "\n" +
                "        JSONObject result;\n" +
                "        String error = validateForm(evaluatePlan1DTO);\n" +
                "        ServiceResult serviceResult;\n" +
                "        if (error != null) {\n" +
                "            return error;\n" +
                "        } else {\n" +
                "            if (!StringUtil.isEmpty(evaluatePlan1DTO.getExpertise_date())) {\n" +
                "                        evaluatePlan1DTO.setExpertise_date(DateUtil.formatDate(evaluatePlan1DTO.getExpertise_date()));\n" +
                "            }\n" +
                "            serviceResult = evaluatePlan1Data.updateBO(evaluatePlan1DTO);\n" +
                "            processServiceResult(serviceResult);\n" +
                "            result = new JSONObject(serviceResult);\n" +
                "        }\n" +
                "        return result.toString();\n" +
                "    }\n" +
                "\n" +
                "    //validate\n" +
                "    private String validateForm(EvaluatePlan1DTO cbChaDTO) {\n" +
                "        List<ValidationResult> lsError = new ArrayList<>();\n" +
                "        if (lsError.size() > 0) {\n" +
                "            Gson gson = new Gson();\n" +
                "            return gson.toJson(lsError);\n" +
                "        }\n" +
                "        return null;\n" +
                "    }\n" +
                "\n" +
                "    @RequestMapping(value = {\"/\" + ErpConstants.RequestMapping.DELETE_EVALUATE_PLAN1}, method = RequestMethod.POST,\n" +
                "            produces = \"text/html;charset=utf-8\")\n" +
                "    public @ResponseBody\n" +
                "    String deleteObj(@ModelAttribute(\"objectCommonSearchDTO\") ObjectCommonSearchDTO objectCommonSearchDTO,\n" +
                "            HttpServletRequest request) {\n" +
                "        HttpSession session = request.getSession();\n" +
                "        ServiceResult serviceResult = evaluatePlan1Data.deleteObj(objectCommonSearchDTO);\n" +
                "        processServiceResult(serviceResult);\n" +
                "        JSONObject result = new JSONObject(serviceResult);\n" +
                "        return result.toString();\n" +
                "    }\n" +
                "\n" +
                "}\n");

        fileWriter.close();
    }

    public static void genWeb(TableInfo tableInfo, String folder) throws IOException {
        File dir = new File(folder);
        dir.mkdirs();
        File dir2 = new File(folder + "\\" + uncapitalize(tableInfo.tableName));
        dir2.mkdirs();
        genControllerParameters(tableInfo, folder);
        genController(tableInfo, folder);
        genData(tableInfo, folder);
        genTitle(tableInfo, folder);
        genListjsp(tableInfo, dir2.getAbsolutePath());
        //genJs(tableInfo, folder);
        Hung.genJs(tableInfo, folder);
        genDialogAdd(tableInfo, dir2.getAbsolutePath());
        genDTO_Web(tableInfo, folder);
        genView(tableInfo,dir2.getAbsolutePath());
        genControllerSearch(tableInfo, folder);
        Tung.genJsSearch(tableInfo,folder);
        Tung.genformSearch(tableInfo,dir2.getAbsolutePath());
    }
}