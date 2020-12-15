<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link href="${pageContext.request.contextPath}/share/bootstrap-multiselect/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/share/bootstrap-multiselect/js/bootstrap-multiselect.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/share/core/js/evaluatePlan1.js"/>


<link href="share/core/css/guideproperty.css" rel="stylesheet">
<link href="share/core/css/common.css" rel="stylesheet">
<jsp:include page="../common/header.jsp"></jsp:include>
    <section id="widget-grid" class="" style="height:100%;">
        <div class="row">
            <article class="col-sm-12 col-md-12 col-lg-12">
                <div style="height:100% !important;padding: 3px;">
                    <article class="widgetTop1 col-sm-12 col-md-12 col-lg-12">
                    <%--jsp:include page="formSearch.jsp" /--%>
                    <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-1" data-widget-fullscreenbutton="false"
                         data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-colorbutton="false" data-widget-editbutton="false"
                         style="height:100% !important;">
                        <div style="height:100% !important;">
                            <div class="widget-body no-padding">
                                <div class="table-responsive" id="table-responsive">                           
                                    <table id="dataTblDocumentType" style="width: 100%;"></table>
                                    <jsp:include page="../common/tablePaging.jsp"></jsp:include>
                                    </div>
                                </div>
                            </div>      
                        </div>
                    </article>
                </div>
            </article>
        <jsp:include page="dialogAdd.jsp" /> 
    </section>
    <input type="hidden" id="screenId" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<script src="${pageContext.request.contextPath}/share/core/js/common.js"/>