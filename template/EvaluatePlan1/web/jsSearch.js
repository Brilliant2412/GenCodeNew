//$("#TBL_DOCUMENT_TYPE").addClass("active");
//$("#naviParent").replaceWith($("#ROOT_LAND_POINT  span").html());
//$("#naviChild").replaceWith($("#cbma  span").html());


var editCellRendererVT = function (gid) {
    return '<div style="text-align: center">'
            + '    <a class="tooltipCus iconEdit" href="javascript:objTblDocumentType.editTblDocumentType(\'' + gid + '\')">'
            + '        <span class="tooltipCustext">' + $("#tooltipEdit").val() + '</span><img src="share/core/images/edit.png" class="grid-icon"/>'
            + '    </a><a class="tooltipCus iconDelete" href="javascript:objTblDocumentType.deleteTblDocumentType(\'' + gid + '\')">'
            + '        <span class="tooltipCustext">' + $("#tooltipDelete").val() + '</span><img src="share/core/images/delete_1.png" class="grid-icon"/></a>'
            + '</div>';};

var datafields = [
    {name: 'gid', type: 'Number'},
    {name: 'expertise_organ', type: 'Number'},
    {name: 'plan_number', type: 'String'},
    {name: 'place1', type: 'Number'},
    {name: 'expertise_date', type: 'String'},
    {name: 'expertise_dateST', type: 'String'},
    {name: 'dept_id', type: 'Number'},
    {name: 'dept_other', type: 'String'},
    {name: 'the_bases', type: 'String'},
    {name: 'the_purpose', type: 'String'},
    {name: 'place_and_time', type: 'String'},
    {name: 'test_content', type: 'String'},
    {name: 'the_participants', type: 'String'},
    {name: 'organization_perform', type: 'String'},
    {name: 'master_id', type: 'String'},
    {name: 'left_long', type: 'Number'},
    {name: 'right_long', type: 'Number'},
];

var columns = [
	{text: "STT", sortable: false, datafield: '', styleClass: 'stt', clstitle: 'tlb_class_center', res: "data-hide='phone'"},
	{text: 'gid', datafield: 'gid', hidden: true},
	{text: "Cơ quan thẩm định", datafield: 'expertise_organ', res: "data-class='phone'"},
	{text: "Số kế hoạch", datafield: 'plan_number', res: "data-class='phone'"},
	{text: "Thành phần tham gia", datafield: 'the_participants', res: "data-class='phone'"},
	{text: "Tổ chức thực hiện", datafield: 'organization_perform', res: "data-class='phone'"},
	{text: "Thủ trưởng", datafield: 'master_id', res: "data-class='phone'"},
	{text: "Chức năng", datafield: 'gid', edit: 1, sortable: false, clstitle: 'tlb_class_center'}
];

var gridSetting = {
    sortable: false,
    virtualmode: true,
    isSetting: false,
    enableSearch: false,
    onClickRow: true
};

doSearch = function () {
    vt_datagrid.loadPageAgainRes("#dataTblDocumentType", "getallevaluateplan1.json");
    vt_sys.showBody();
    vt_loading.hideIconLoading();
    return false;
};

$("#expertise_dateSearchFrom").datepicker({
	duration: "fast",
	changeMonth: true,
	changeYear: true,
	dateFormat: 'dd/mm/yy',
	constrainInput: true,
	disabled: false,
	yearRange: "-20:+10",
	onSelect: function (selected) {
	}
});

$("#expertise_dateSearchTo").datepicker({
	duration: "fast",
	changeMonth: true,
	changeYear: true,
	dateFormat: 'dd/mm/yy',
	constrainInput: true,
	disabled: false,
	yearRange: "-20:+10",
	onSelect: function (selected) {
	}
});

$(function () {
	doSearch();
	
	onClickBtAdd = function () {
        vt_form.reset($('#evaluatePlan1Form'));
        $("#gid").val(""); // reset form
        vt_form.clearError();
        $("#isedit").val("0");
        
		vt_combobox.buildCombobox("cbexpertise_organ", "gettypeuser.json?cd=701", 0, "dvsName", "null", "- Chọn Cơ quan thẩm định -", 0);
		vt_combobox.buildCombobox("cbplace1", "gettypeuser.json?cd=705", 0, "dvsName", "null", "- Chọn Địa điểm lập kế hoạch 1 -", 0);
		vt_combobox.buildCombobox("cbdept_id", "getdepartmentComBoBox.json", 0, "deptName", "null", "- Chọn Tên tổ chức, đơn vị được thẩm định -", 0);

        $('#dialog-formAddNew').dialog({
            title: "Thêm mới Kế hoạch Thẩm định đánh giá năng lực"
        }).dialog('open');
        return false;
    };
	
	setValueToForm = function () {
        var item;
		item = $('#cbexpertise_organCombobox').val();
		$('input[name="expertise_organ"]').val(item);
		item = $('#cbplace1Combobox').val();
		$('input[name="place1"]').val(item);
		item = $('#cbdept_idCombobox').val();
		$('input[name="dept_id"]').val(item);
	}
	editTblDocumentTypeMethod = function () {
        clearError();
        setValueToForm();
        $.ajax({
            traditional: true,
            url: "token.json",
            dataType: "text",
            type: "GET"
        }).success(function (result) {
            if (vt_form.validate1("#evaluatePlan1Form", null, objTblDocumentType.validateRule))
            {
                var formdataTmp = new FormData();
                var formData = new FormData(document.getElementById("evaluatePlan1Form"));
                for (var pair of formData.entries()) {
                    formdataTmp.append(pair[0], pair[1]);
                }
                $.ajax({
                    async: false,
                    url: "updateevaluateplan1.html",
                    data: formdataTmp,
                    processData: false,
                    contentType: false,
                    enctype: 'multipart/form-data',
                    type: "POST",
                    headers: {"X-XSRF-TOKEN": result},
                    dataType: 'json',
                    beforeSend: function (xhr) {
                        vt_loading.showIconLoading();
                    },
                    success: function (result) {
                        if (result.error != null) {
                            vt_loading.hideIconLoading();
                        } else
                        if (result !== null && result.length > 0) {
                            for (var i = 0; i < result.length; i++) {
                                $("#" + result[i].fieldName + "_error").text(result[i].error);
                            }
                            setTimeout('$("#"' + result[0].fieldName + ').focus()', 100);
                            vt_loading.hideIconLoading();
                        } else {
                            $("#dialog-formAddNew").dialog("close");
                            doSearch();
                            vt_loading.hideIconLoading();
                            vt_loading.showAlertSuccess("Chỉnh sửa thông tin thành công");
                        }

                    }, error: function (xhr, ajaxOption, throwErr) {
                        console.log(xhr);
                        console.log(ajaxOption);
                        console.log(throwErr);
                    }
                });
            }
        });
    };

	addTblDocumentTypeMethod = function () {
        vt_form.clearError();
        setValueToForm();
        $.ajax({
            traditional: true,
            url: "token.json",
            dataType: "text",
            type: "GET"
        }).success(function (result) {
            if (vt_form.validate1("#evaluatePlan1Form", null, objTblDocumentType.validateRule))
            {
                var formdataTmp = new FormData();
                var formData = new FormData(document.getElementById("evaluatePlan1Form"));
                for (var pair of formData.entries()) {
                    formdataTmp.append(pair[0], pair[1]);
                }
                $.ajax({
                    url: "addevaluateplan1.html",
                    data: formdataTmp,
                    processData: false,
                    contentType: false,
                    enctype: 'multipart/form-data',
                    type: "POST",
                    headers: {"X-XSRF-TOKEN": result},
                    dataType: 'json',
                    beforeSend: function (xhr) {
                        vt_loading.showIconLoading();
                    },
                    success: function (result) {
                        if (result.error != null) {
                        } else if (result !== null && result.length > 0) {
                            for (var i = 0; i < result.length; i++) {
                                $("#" + result[i].fieldName + "_error").text(result[i].error);
                            }
                            setTimeout('$("#' + result[0].fieldName + '").focus()', 100);
                            vt_loading.hideIconLoading();
                        } else {
                            $("#dialog-formAddNew").dialog("close");
                            doSearch();
                            vt_loading.hideIconLoading();
                            vt_loading.showAlertSuccess("Thêm mới thành công");
                        }
                    }, error: function (xhr, ajaxOption, throwErr) {
                        console.log(xhr);
                        console.log(ajaxOption);
                        console.log(throwErr);
                    }
                });
            }

        });
    };
});

var objTblDocumentType = {
	validateRule: {
        rules: {
			expertise_organ: {
                required: true
            },
			plan_number: {
                required: true
            },
			the_participants: {
                required: true
            },
			master_id: {
                required: true
            },
        },
        messages: {
			expertise_organ: {
                required: "test"
            },
			plan_number: {
                required: ""
            },
			the_participants: {
                required: ""
            },
			master_id: {
                required: ""
            },
        }
    },

	editTblDocumentType: function (id) {
        if (id !== null) {
            vt_form.reset($('#evaluatePlan1Form'));
            vt_form.clearError();
            $.ajax({
                async: false,
                data: {gid: id},
                url: "getoneevaluateplan1bygid.json",
                success: function (data, status, xhr) {
                    $("#gid").val(data.gid);
					vt_combobox.buildCombobox("cbexpertise_organ", "gettypeuser.json?cd=701", data.expertise_organ, "dvsName", "null", "- Chọn Cơ quan thẩm định -", 0);
					$("#plan_number").val(data.plan_number);
					vt_combobox.buildCombobox("cbplace1", "gettypeuser.json?cd=705", data.place1, "dvsName", "null", "- Chọn Địa điểm lập kế hoạch 1 -", 0);
					$("#expertise_date").val(data.expertise_dateST);
					vt_combobox.buildCombobox("cbdept_id", "getdepartmentComBoBox.json", data.dept_id, "deptName", "null", "- Chọn Tên tổ chức, đơn vị được thẩm định -", 0);
					$("#dept_other").val(data.dept_other);
					$("#the_bases").val(data.the_bases);
					$("#the_purpose").val(data.the_purpose);
					$("#place_and_time").val(data.place_and_time);
					$("#test_content").val(data.test_content);
					$("#the_participants").val(data.the_participants);
					$("#organization_perform").val(data.organization_perform);
					$("#master_id").val(data.master_id);
					$("#left_long").val(data.left_long);
					$("#right_long").val(data.right_long);

					$('#dialog-formAddNew').dialog({
                        title: "Cập nhật thông tin Kế hoạch Thẩm định đánh giá năng lực"
                    }).dialog('open');
                    // set css to form
                    $('#dialog-formAddNew').parent().addClass("dialogAddEdit");
                    objCommon.setTimeout("code");
                    return false;
                }
            });
        }
    },
	gid: null,

    deleteTblDocumentType: function (gid) {
        if (gid !== null) {
            var tmp_mess = '"' + $("#dataTblDocumentType #" + gid).parent().parent().parent().find(".expand").text() + '"';
            vt_loading.showConfirmDeleteDialog("Bạn có chắc chắn muốn xóa danh mục" + " " + tmp_mess, function (callback) {
                if (callback) {
                    objTblDocumentType.gid = gid;
                    objTblDocumentType.deleteOneTblDocumentType();
                }
            });
        }
    },

	deleteOneTblDocumentType: function () {
        vt_loading.showIconLoading();
        var ids = objTblDocumentType.gid;
        var onDone = function (result) {
            if (result !== null && result.hasError) {
                $("#deleteDialogMessageError").text(result.error);
                vt_loading.hideIconLoading();
            } else {
                $("#dialog-confirmDelete").dialog("close");
                if ($("#allValue").val() === ids && pagenum > 1) {
                    pagenum--;
                }
                doSearch();
                vt_loading.hideIconLoading();
                vt_loading.showAlertSuccess("Xóa thành công");
            }
        };
        vt_form.ajax("POST", "deleteevaluateplan1.html", {lstFirst: ids}, null, null, onDone);
    },

	editView: function(id) {
        if (id !== null) {
            vt_form.reset($('#evaluatePlan1Form'));
            vt_form.clearError();
            $.ajax({
                async: false,
                data: {gid: id},
                url: "getoneevaluateplan1bygid.json",
                success: function (data, status, xhr) {
                    $("#gid").val(data.gid);
					vt_combobox.buildCombobox("cbexpertise_organ", "gettypeuser.json?cd=701", data.expertise_organ, "dvsName", "null", "- Chọn Cơ quan thẩm định -", 0);
					$("#plan_number").val(data.plan_number);
					vt_combobox.buildCombobox("cbplace1", "gettypeuser.json?cd=705", data.place1, "dvsName", "null", "- Chọn Địa điểm lập kế hoạch 1 -", 0);
					$("#expertise_date").val(data.expertise_dateST);
					vt_combobox.buildCombobox("cbdept_id", "getdepartmentComBoBox.json", data.dept_id, "deptName", "null", "- Chọn Tên tổ chức, đơn vị được thẩm định -", 0);
					$("#dept_other").val(data.dept_other);
					$("#the_bases").val(data.the_bases);
					$("#the_purpose").val(data.the_purpose);
					$("#place_and_time").val(data.place_and_time);
					$("#test_content").val(data.test_content);
					$("#the_participants").val(data.the_participants);
					$("#organization_perform").val(data.organization_perform);
					$("#master_id").val(data.master_id);
					$("#left_long").val(data.left_long);
					$("#right_long").val(data.right_long);
				}
			});
		}
	},

	view: function(gid) {
        if (id !== null) {
            vt_form.reset($('#evaluatePlan1Form'));
            vt_form.clearError();
            $.ajax({
                async: false,
                data: {gid: id},
                url: "getoneevaluateplan1bygid.json",
                success: function (data, status, xhr) {
                    $("#gid").val(data.gid);
					$("#cbexpertise_organcombobox").val(data.expertise_organST);
					$("#plan_number").val(data.plan_number);
					$("#cbplace1combobox").val(data.place1ST);
					$("#expertise_date").val(data.expertise_dateST);
					$("#cbdept_idcombobox").val(data.dept_idST);
					$("#dept_other").val(data.dept_other);
					$("#the_bases").val(data.the_bases);
					$("#the_purpose").val(data.the_purpose);
					$("#place_and_time").val(data.place_and_time);
					$("#test_content").val(data.test_content);
					$("#the_participants").val(data.the_participants);
					$("#organization_perform").val(data.organization_perform);
					$("#master_id").val(data.master_id);
					$("#left_long").val(data.left_long);
					$("#right_long").val(data.right_long);

					$('#dialog-formView').dialog({
                        title: "Xem Kế hoạch Thẩm định đánh giá năng lực"
                    }).dialog('open');
                    // set css to form
                    $('#dialog-formView').parent().addClass("dialogAddEdit");
                    objCommon.setTimeout("code");
                    return false;
                }
            });
        }
    }
}