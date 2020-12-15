<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="dialog-formAddNew">
	<form:form id="evaluatePlan1Form" modelAttribute="evaluatePlan1Form" class="form-horizontal">	
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="hidden" id="gid" name="gid" value=""/>
		<input type="hidden" id="isedit" name="isedit" value="0"/>
		<fieldset>
			<legend class="fs-legend-head">
				<span class="iconFS"></span>
				<span class="titleFS" style="color: #047fcd !important;"><b>Thông tin chung</b></span>
			</legend>
			<div class="form-group-add row">
				<label class="col-lg-1 control-label  lb_input">Cơ quan thẩm định</label>
				<div class="col-lg-2">
					<div id="cbexpertise_organ"></div> 
					<input name="expertise_organ" id="expertise_organ" class="text_hidden"  />
					<span id="expertise_organ_error" class="note note-error"></span>
				</div>
				<label class="col-lg-1 control-label  lb_input">Số kế hoạch</label>
				<div class="col-lg-2">
					<input name="plan_number" id="plan_number" type="text" class="form-control"/>
					<span id="plan_number_error" class="note note-error"></span>
				</div>
				<label class="col-lg-1 control-label  lb_input">Địa điểm lập kế hoạch 1</label>
				<div class="col-lg-2">
					<div id="cbplace1"></div> 
					<input name="place1" id="place1" class="text_hidden"  />
					<span id="place1_error" class="note note-error"></span>
				</div>
				<label class="col-lg-1 control-label  lb_input">Ngày lập kế hoạch</label>
				<div class="col-md-2" input-group>
				<input class="dateCalendar" placeholder="Bắt đầu" name="expertise_date" id="expertise_date" type="text"/>
					<span id="expertise_date_error" class="note note-error"></span>
				</div>
			</div>

			<div class="form-group-add row">
				<label class="col-lg-1 control-label  lb_input">Tên tổ chức, đơn vị được thẩm định</label>
				<div class="col-lg-2">
					<div id="cbdept_id"></div> 
					<input name="dept_id" id="dept_id" class="text_hidden"  />
					<span id="dept_id_error" class="note note-error"></span>
				</div>
				<label class="col-lg-1 control-label  lb_input">Đơn vị khác</label>
				<div class="col-lg-2">
					<input name="dept_other" id="dept_other" type="text" class="form-control"/>
					<span id="dept_other_error" class="note note-error"></span>
				</div>
				<label class="col-lg-1 control-label  lb_input">Các căn cứ</label>
				<div class="col-lg-2">
					<input name="the_bases" id="the_bases" type="text" class="form-control"/>
					<span id="the_bases_error" class="note note-error"></span>
				</div>
				<label class="col-lg-1 control-label  lb_input">Mục đích, yêu cầu</label>
				<div class="col-lg-2">
					<input name="the_purpose" id="the_purpose" type="text" class="form-control"/>
					<span id="the_purpose_error" class="note note-error"></span>
				</div>
			</div>

			<div class="form-group-add row">
				<label class="col-lg-1 control-label  lb_input">Thời gian, địa điểm</label>
				<div class="col-lg-2">
					<input name="place_and_time" id="place_and_time" type="text" class="form-control"/>
					<span id="place_and_time_error" class="note note-error"></span>
				</div>
				<label class="col-lg-1 control-label  lb_input">Nội dung kiểm tra</label>
				<div class="col-lg-2">
					<input name="test_content" id="test_content" type="text" class="form-control"/>
					<span id="test_content_error" class="note note-error"></span>
				</div>
				<label class="col-lg-1 control-label  lb_input">Thành phần tham gia</label>
				<div class="col-lg-2">
					<input name="the_participants" id="the_participants" type="text" class="form-control"/>
					<span id="the_participants_error" class="note note-error"></span>
				</div>
				<label class="col-lg-1 control-label  lb_input">Tổ chức thực hiện</label>
				<div class="col-lg-2">
					<input name="organization_perform" id="organization_perform" type="text" class="form-control"/>
					<span id="organization_perform_error" class="note note-error"></span>
				</div>
			</div>

			<div class="form-group-add row">
				<label class="col-lg-1 control-label  lb_input">Thủ trưởng</label>
				<div class="col-lg-2">
					<input name="master_id" id="master_id" type="text" class="form-control"/>
					<span id="master_id_error" class="note note-error"></span>
				</div>
				<label class="col-lg-1 control-label  lb_input">Kinh độ trái</label>
				<div class="col-lg-2">
					<input name="left_long" id="left_long" type="number" class="form-control"/>
					<span id="left_long_error" class="note note-error"></span>                           
				</div>
				<label class="col-lg-1 control-label  lb_input">Kinh độ phải</label>
				<div class="col-lg-2">
					<input name="right_long" id="right_long" type="number" class="form-control"/>
					<span id="right_long_error" class="note note-error"></span>                           
				</div>
			</div>

		</fieldset>
	</form:form>
</div>
<script type="text/javascript">
	$("#dialog-formAddNew").dialog({
		width: isMobile.any() ? $(window).width() : ($(window).width() / 5 * 4),
		height: $(window).height() / 5 * 4,
		autoOpen: false,
		modal: true,
		position: [($(window).width() / 10 * 1), 50],
		open: function () {
			$('.areaTable').addClass('custom-overlay-popup-add-edit');
			$('.dialogAddEdit').css('z-index', 1001);

		},
		close: function () {
			$('.areaTable').removeClass('custom-overlay-popup-add-edit');

		},
		buttons: [{
			html: "<fmt:message key='button.close' />",
			"class": "btn btn-default",
			click: function () {
			$(this).dialog('close');
			}
 			}, {
				html: "<fmt:message key='button.update' />",
				"class": "btn btn-primary",
				"id": "btnAddTblInfoNotifyYes",
				click: function () {
					var item = $('#isedit').val();
					if (item === '0') {
						addTblDocumentTypeMethod();
					} else {
						editTblDocumentTypeMethod();
					}
				}
			}
		]
	});
</script>