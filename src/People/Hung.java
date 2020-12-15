/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package People;

import controller.CodeGenerator;
import java.io.FileWriter;
import java.io.IOException;
import model.TableInfo;
import static controller.CodeGenerator.*;
import model.ColumnProperty;

/**
 *
 * @author hungy
 */


public class Hung {
    public static String resSearch(String tenTruong, String moTa, String kieuDL, String kieuNhap) {
        String res = "";
        if (kieuDL.equals("Long") || kieuDL.equals("Double")) {
            if (kieuNhap.equals("Combobox")) {
                res = "\t\t\t\t\t\t\t\t\t<label class=\"col-lg-1 control-label\">" + moTa + "</label>\n" +
                        "\t\t\t\t\t\t\t\t\t<div class=\"col-lg-2 selectContainer\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t<div id=\"cb" + tenTruong + "Search\">\n" +
                        "\t\t\t\t\t\t\t\t\t</div>\n";
            } else {
                res = "\t\t\t\t\t\t\t\t\t<label class=\"col-lg-1 control-label\">" + moTa + "</label>\n" +
                        "\t\t\t\t\t\t\t\t\t<div class=\"col-lg-1 selectContainer\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t<input type=\"number\" class=\"form-control\" placeholder=\"Từ\" id=\"" + tenTruong + "SearchFrom\">\n" +
                        "\t\t\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t\t\t<div class=\"col-lg-1\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t<input type=\"number\" class=\"form-control\" placeholder=\"Đến\" id=\"" + tenTruong + "SearchTo\">\n" +
                        "\t\t\t\t\t\t\t\t\t</div>\n";
            }
        } else if (kieuDL.equals("Date")) {
            res = "\t\t\t\t\t\t\t\t\t<label class=\"col-lg-1 control-label\">" + moTa + "</label>\n" +
                    "\t\t\t\t\t\t\t\t\t<div class=\"col-lg-1 selectContainer\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t<input type=\"text\" class=\"dateCalendar\" placeholder=\"Từ\" id=\"" + tenTruong + "SearchFrom\">\n" +
                    "\t\t\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t\t\t\t<div class=\"col-lg-1\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t<input type=\"text\" class=\"dateCalendar\" placeholder=\"Đến\" id=\"" + tenTruong + "SearchTo\"> \n" +
                    "\t\t\t\t\t\t\t\t\t</div>\n";
        }
        return res;
    }


    public static void genformSearch(TableInfo tableInfo, String folder) throws IOException {
        FileWriter fileWriter = new FileWriter(folder+"\\formSearch.jsp");
        fileWriter.write("<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>\n" +
                "<%@ taglib prefix=\"fmt\" uri=\"http://java.sun.com/jsp/jstl/fmt\" %>\n" +
                "<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\" %>\n" +
                "\n" +
                "<section id=\"widget-grid\" class=\"\" style=\"\n" +
                "         border-left-width: 0px;\n" +
                "         border-top-width: 0px;\n" +
                "         border-bottom-width: 0px;\n" +
                "         border-right-width: 0px;\">\n" +
                "    <div class=\"row\">\n" +
                "        <div class=\"col-lg-12\">\n" +
                "            <div class=\"jarviswidget\" id=\"wid-id-0\" data-widget-colorbutton=\"false\" data-widget-editbutton=\"false\" data-widget-deletebutton=\"false\" data-widget-sortable=\"false\" role=\"widget\">\n" +
                "                <!--<button type=\"button\" class=\"btn btn-sm btn-success\" id=\"show_hide_go\">Tìm kiếm - In danh sách</button><br>-->\n" +
                "                <div class=\"formsearchBody\" style=\"margin-left:4px !important;\">\n" +
                "                    <form id=\"productForm\" class=\"form-horizontal bv-form\" novalidate=\"novalidate\" style=\"width: 100%;\">\n" +
                "                        <button type=\"submit\" class=\"bv-hidden-submit\" style=\"display: none; width: 0px; height: 0px;\"></button>\n" +
                "                        <fieldset>\n");


        int k = tableInfo.countSearch;
        int r = k / 4;
        int q = k%4;
        int i = 0;
        int countSearch = 0;
        if (r == 0) {
            fileWriter.append("\t\t\t\t\t\t\t\t<div class=\"form-group has-feedback\">\n");
            fileWriter.append("\t\t\t\t\t\t\t\t\t<label class=\"col-lg-1 control-label\" style=\"padding:5px 4px\">Từ khóa</label>\n" +
                    "                                \t<div class=\"col-lg-2 selectContainer\">\n" +
                    "                                    \t<input type=\"text\" class=\"form-control\" placeholder=\"Nhập từ khóa\" id=\"keySearch\">\n" +
                    "                                \t</div>\n");
            for (; i < tableInfo.columns.size(); i++) {
                if (tableInfo.columns.get(i).isSearch() == true) {
                    String res = resSearch(tableInfo.columns.get(i).getColName(),
                            tableInfo.columns.get(i).getColDescription(),
                            tableInfo.columns.get(i).getColType(),
                            tableInfo.columns.get(i).getInputType()
                    );
                    fileWriter.append(res);
                }
            }
            fileWriter.append("\t\t\t\t\t\t\t\t</div>\n\n");
        }else {
            fileWriter.append("\t\t\t\t\t\t\t\t<div class=\"form-group has-feedback\">\n");
            fileWriter.append("\t\t\t\t\t\t\t\t\t<label class=\"col-lg-1 control-label\" style=\"padding:5px 4px\">Từ khóa</label>\n" +
                    "                                \t<div class=\"col-lg-2 selectContainer\">\n" +
                    "                                    \t<input type=\"text\" class=\"form-control\" placeholder=\"Nhập từ khóa\" id=\"keySearch\">\n" +
                    "                                \t</div>\n");
            for (; i < tableInfo.columns.size(); i++) {
                if (tableInfo.columns.get(i).isSearch() == true) {
                    countSearch++;
                    String res = resSearch(tableInfo.columns.get(i).getColName(),
                            tableInfo.columns.get(i).getColDescription(),
                            tableInfo.columns.get(i).getColType(),
                            tableInfo.columns.get(i).getInputType()
                    );
                    fileWriter.append(res);
                    if (countSearch == 3) {
                        fileWriter.append("\t\t\t\t\t\t\t\t</div>\n");
                        break;
                    }
                }
            }
            k -= 3;
            countSearch -= 3;
            r = k / 4;
            q = k % 4;
            i++;
            if (r == 0) {
                fileWriter.append("\t\t\t\t\t\t\t\t<div class=\"form-group has-feedback\">\n");
                for (; i < tableInfo.columns.size(); i++) {
                    if (tableInfo.columns.get(i).isSearch() == true) {
                        String res = resSearch(tableInfo.columns.get(i).getColName(),
                                tableInfo.columns.get(i).getColDescription(),
                                tableInfo.columns.get(i).getColType(),
                                tableInfo.columns.get(i).getInputType()
                        );
                        fileWriter.append(res);
                    }
                }
                fileWriter.append("\t\t\t\t\t\t\t\t</div>\n");
            } else {
                for (int dem = 0; dem < r; dem++) {
                    fileWriter.append("\t\t\t\t\t\t\t\t<div class=\"form-group has-feedback\">\n");
                    for (; i < tableInfo.columns.size(); i++) {
                        if (tableInfo.columns.get(i).isSearch() == true) {
                            countSearch++;
                            String res = resSearch(tableInfo.columns.get(i).getColName(),
                                    tableInfo.columns.get(i).getColDescription(),
                                    tableInfo.columns.get(i).getColType(),
                                    tableInfo.columns.get(i).getInputType()
                            );
                            fileWriter.append(res);
                            if (countSearch % 4 == 3 && countSearch <= k) {
                                fileWriter.append("\t\t\t\t\t\t\t\t/div>\n");
                            } else {
                                break;
                            }
                        }
                    }
                }
                if (q != 0) {
                    fileWriter.append("\t\t\t\t\t\t\t\t<div class=\"form-group has-feedback\">\n");
                    for (; i < tableInfo.columns.size(); i++) {
                        if (tableInfo.columns.get(i).isSearch() == true) {
                            String res = resSearch(tableInfo.columns.get(i).getColName(),
                                    tableInfo.columns.get(i).getColDescription(),
                                    tableInfo.columns.get(i).getColType(),
                                    tableInfo.columns.get(i).getInputType()
                            );
                            fileWriter.append(res);
                        }
                    }
                    fileWriter.append("\t\t\t\t\t\t\t\t</div>\n");
                }
            }
        }
        fileWriter.append("                        </fieldset>\n" +
                "                        \n" +
                "                    </form>\n" +
                "                </div>\n" +
                "                <br>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "</section>\n" +
                "\n" +
                "<style>\n" +
                "    .ui-select-match-text{\n" +
                "        width: 100%;\n" +
                "        overflow: hidden;\n" +
                "        text-overflow: ellipsis;\n" +
                "        padding-right: 40px;\n" +
                "    }\n" +
                "    .ui-select-toggle > .btn.btn-link {\n" +
                "        margin-right: 10px;\n" +
                "        top: 6px;\n" +
                "        position: absolute;\n" +
                "        right: 10px;\n" +
                "    }\n" +
                "    .multiselect-container .input-group{\n" +
                "        margin: 0px !important;\n" +
                "    }\n" +
                "    .checkbox-status .checkbox-inline:nth-child(2n+1){\n" +
                "        margin-left: 0px !important;\n" +
                "    }\n" +
                "    .formsearchBody{\n" +
                "        display: block\n" +
                "    }\n" +
                "</style>");
        fileWriter.close();
    }

    public static void genJs(TableInfo tableInfo, String folder) throws  IOException{
        FileWriter fileWriter = new FileWriter(folder+"\\" + uncapitalize(tableInfo.tableName) + ".js");
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
                fileWriter.append("\t{text: \""+columnProperty.getColDescription()+"\", datafield: '"+columnProperty.getColName()+"', res: \"data-class='phone'\"},\n");

            }
        }
        fileWriter.append("\t{text: \"Chức năng\", datafield: 'gid', edit: 1, sortable: false, clstitle: 'tlb_class_center'}\n");
        fileWriter.append("];\n\n");

        /*********************************************************************************************
         *                                do search funtion 1
         *********************************************************************************************/
        fileWriter.append("doSearch = function () {\n" +
"    \n" +
"    var keySearch = $('#keySearch').val();\n" +
"    \n");
//        int count_cb = 0;
//        int count_db = 0;
//        int count_long = 0;
//        int count_date = 0;
//        for (int i = 0; i < tableInfo.columns.size(); i++) {
//            ColumnProperty colProp = tableInfo.columns.get(i);
//            if (colProp.isSearch()) {                
//                if (colProp.getColType().equals("Long")) {
//                    if (colProp.getInputType().equals("Combobox")) {
//                        count_cb++;
//                    } else {
//                        count_long++;
//                    }
//                }
//                if (colProp.getColType().equals("Double")) {
//                    count_db++;
//                }
//                if (colProp.getColType().equals("Date")) {
//                    count_date++;
//                }
//            }
//        }
        
        //Combobox
        int t_cb = 0;
        for (int i = 0;i<tableInfo.columns.size();i++){
            if (tableInfo.columns.get(i).getInputType().equals("Combobox") && tableInfo.columns.get(i).getColType().equals("Long") && tableInfo.columns.get(i).isSearch()){
                t_cb++;
                fileWriter.append("    var listLong"+(t_cb)+" = $('#cb"+tableInfo.columns.get(i).getColName()+"SearchCombobox').val();\n");
            }
        }
        //date
        int t_date = 0;
        for (int i = 0;i<tableInfo.columns.size();i++){
            if (tableInfo.columns.get(i).getColType().equals("Date") && tableInfo.columns.get(i).isSearch()){
                t_date++;
                fileWriter.append("    var string"+(t_date)+" = $('#"+tableInfo.columns.get(i).getColName()+"SearchFrom').val();\n");
                t_date++;
                fileWriter.append("    var string"+(t_date)+" = $('#"+tableInfo.columns.get(i).getColName()+"SearchTo').val();\n");
            }
        }
        
        //long not cb
        int t_long = 0;
        for (int i = 0;i<tableInfo.columns.size();i++){
            if (tableInfo.columns.get(i).getColType().equals("Long") && !tableInfo.columns.get(i).getInputType().equals("Combobox") && tableInfo.columns.get(i).isSearch()){
                t_long++;
                fileWriter.append("    var long"+(t_long)+" = $('#"+tableInfo.columns.get(i).getColName()+"SearchCombobox').val();\n");
            }
        }
        
        //double
        int t_db = 0;
        for (int i = 0;i<tableInfo.columns.size();i++){
            if (tableInfo.columns.get(i).getColType().equals("Double") && tableInfo.columns.get(i).isSearch()){
                t_db++;
                fileWriter.append("    var double"+(t_db)+" = $('#"+tableInfo.columns.get(i).getColName()+"SearchCombobox').val();\n");
            }
        }
        
        
        fileWriter.append(
"    var url = \"getall"+tableInfo.tableName.toLowerCase()+".json\";\n" +
"    url += \"?key=\" + keySearch;\n");
        //Combobox
        int t_cb1 = 0;
        for (int i = 0;i<tableInfo.columns.size();i++){
            if (tableInfo.columns.get(i).getInputType().equals("Combobox") && tableInfo.columns.get(i).getColType().equals("Long") && tableInfo.columns.get(i).isSearch()){
                t_cb1++;
                fileWriter.append("url += \"&listLong"+t_cb1+"=\"+listLong"+t_cb1+";\n");
            }
        }
        //date
        int t_date1 = 0;
        for (int i = 0;i<tableInfo.columns.size();i++){
            if (tableInfo.columns.get(i).getColType().equals("Date") && tableInfo.columns.get(i).isSearch()){
                t_date1++;
                fileWriter.append("    url += \"&string"+t_date1+"=\"+string"+t_date1+";\n");
                t_date1++;
                fileWriter.append("    url += \"&string"+t_date1+"=\"+string"+t_date1+";\n");
            }
        }
        
        //long not cb
        int t_long1 = 0;
        for (int i = 0;i<tableInfo.columns.size();i++){
            if (tableInfo.columns.get(i).getColType().equals("Long") && !tableInfo.columns.get(i).getInputType().equals("Combobox") && tableInfo.columns.get(i).isSearch()){
                t_long1++;
                fileWriter.append("    url += \"&long"+t_long1+"=\"+long"+t_long1+";\n");
            }
        }
        
        //double
        int t_db1 = 0;
        for (int i = 0;i<tableInfo.columns.size();i++){
            if (tableInfo.columns.get(i).getColType().equals("Double") && tableInfo.columns.get(i).isSearch()){
                t_db1++;
                fileWriter.append("    url += \"&double"+t_db1+"=\"+double"+t_db1+";\n");
            }
        }
        
        
                fileWriter.append("\n"+
"    vt_datagrid.loadPageAgainRes(\"#dataTblDocumentType\", url);\n" +
"    vt_sys.showBody();\n" +
"    vt_loading.hideIconLoading();\n" +
"    return false;\n" +
"};");
        
        
        
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
        /*********************************************************************************************
         *                                 do search
         *********************************************************************************************/
//        fileWriter.append(
//                "doSearch = function () {\n" +
//                        "    vt_datagrid.loadPageAgainRes(\"#dataTblDocumentType\", \"getall" + tableInfo.tableName.toLowerCase() + ".json\");\n" +
//                        "    vt_sys.showBody();\n" +
//                        "    vt_loading.hideIconLoading();\n" +
//                        "    return false;\n" +
//                        "};\n\n"
//        );
        
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
                        "\t\n" );
        for(int i = 1; i < tableInfo.columns.size(); i++){
            ColumnProperty colProp = tableInfo.columns.get(i);
            if(colProp.getInputType().equals("Combobox")){
                fileWriter.append("\t\tvt_combobox.buildMultipleCombobox(\"cb" + colProp.getColName() + "Search\", \"" + colProp.getComboboxBuildPath() + "\", 0, \"" + colProp.getComboboxName() + "\", \"" + colProp.getComboboxValue() + "\", \"- Chọn " + colProp.getColDescription() + " -\", 0);\n");
            }
        }
        
        
        fileWriter.append("\t$(\"#btnSearch\").click(function () {\n" +
"        doSearch();\n" +
"    });\n");

        /**********************************************************************************************
         *                            edit
         **********************************************************************************************/
//        for(int i = 1; i < tableInfo.columns.size(); i++){
//            ColumnProperty colProp = tableInfo.columns.get(i);
//            if(colProp.getInputType().equals("Combobox") && colProp.isSearch() ){
//                fileWriter.append("\t\tvt_combobox.buildCombobox(\"cb" + colProp.getColName() + "Search\", \"" + colProp.getComboboxBuildPath() + "\", 0, \"" + colProp.getComboboxName() + "\", \"" + colProp.getComboboxValue() + "\", \"- Chọn " + colProp.getColDescription() + " -\", 0);\n");
//            }
//        }

        fileWriter.append("\tonClickBtAdd = function () {\n" +
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
                        "    },\n"
        );

        /*********************************************************************************************
         *                                 Viewjsp
         *********************************************************************************************/
        fileWriter.append("\n\tview: function(id) {\n" +
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
    
    static void genView(TableInfo tableInfo, String folder) throws IOException {
        FileWriter fileWriter = new FileWriter(folder + "\\View.jsp");
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
                            "\t\t\t\t\t<input class=\"form-control\" placeholder=\"\" name=\"\" id=\""+tableInfo.columns.get(4*i+j+1).getColName()+"\" type=\"text\" readonly=\"true\"/>\n"+
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
                            "\t\t\t\t\t<input class=\"form-control\" placeholder=\"\" name=\"\" id=\""+tableInfo.columns.get(i).getColName()+"\" type=\"text\" readonly=\"true\"/>\n"+
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
}
