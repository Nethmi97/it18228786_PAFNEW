$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}
$("#alertError").hide();
});

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true)
	{
	$("#alertError").text(status);
	$("#alertError").show();
	return;
	}
	
	// If valid------------------------
	var type = ($("#refID").val() == "") ? "POST" : "PUT";
	$.ajax(
	{
	url : "ScholarAPI",
	type : type,
	data : $("#formItem").serialize(),
	dataType : "text",
	complete : function(response, status)
	{
	onItemSaveComplete(response.responseText, status);
	}
	});
});


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#refID").val($(this).data("itemid"));
	$("#researchID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#progress").val($(this).closest("tr").find('td:eq(1)').text());
	$("#comment").val($(this).closest("tr").find('td:eq(2)').text());
	$("#approval").val($(this).closest("tr").find('td:eq(3)').text());
});

//UPDATE==========================================
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "ScholarAPI",
		type : "DELETE",
		data : "refID=" + $(this).data("itemid"),
		dataType : "text",
		complete : function(response, status)
		{
			onItemDeleteComplete(response.responseText, status);
		}
	});
});


//CLIENT-MODEL================================================================
function validateItemForm()
{
		
		if ($("#researchID").val().trim() == "")
		{
			return "Insert researchID.";
		}
		
		
		if ($("#progress").val().trim() == "")
		{
			return "Insert progress.";
		}
		
		
		if ($("#comment").val().trim() == "")
		{
			return "Insert comment.";
		}
		
		
		if ($("#approval").val().trim() == "")
		{
			return "Insert  approval.";
		}
		
}



function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		} else if (status == "error")
		{
			$("#alertError").text("Error while saving.");
			$("#alertError").show();
		} else
		{
			$("#alertError").text("Unknown error while saving..");
			$("#alertError").show();
	}
		$("#refID").val("");
		$("#formItem")[0].reset();
}



function onItemDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		} else if (status == "error")
		{
			$("#alertError").text("Error while deleting.");
			$("#alertError").show();
		} else
		{
			$("#alertError").text("Unknown error while deleting..");
			$("#alertError").show();
	}
}
