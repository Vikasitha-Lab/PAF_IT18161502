$(document).ready(function() {

    $("#alertSuccess").hide();
    $("#alertError").hide();

});

// Save
$(document).on("click", "#btnSave", function(event) {

    // Clear alerts
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();

    // Form validation
    var status = validateUserForm();
    if (status != true) {
        $("#alertError").text(status);
        $("#alertError").show();
        return;
    }

    // If valid
    var type = ($("#hiduseridSave").val() == "") ? "POST" : "PUT";

    $.ajax(
        {
            url : "UsersAPI",
            type : type,
            data : $("#formUser").serialize(),
            dataType : "text",
            complete : function(response, status)
            {
                onUserSaveComplete(response.responseText, status);
            }
        });

});

function onUserSaveComplete(response, status) {

    if (status == "success") {

        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() == "success") {

            $("#alertSuccess").text("Successfully saved.");
            $("#alertSuccess").show();
            $("#divUsersGrid").html(resultSet.data);

        } else if (resultSet.status.trim() == "error") {

            $("#alertError").text(resultSet.data);
            $("#alertError").show();

        }
    } else if (status == "error") {

        $("#alertError").text("Error while saving.");
        $("#alertError").show();

    } else {

        $("#alertError").text("Unknown error while saving..");
        $("#alertError").show();

    }

    $("#hiduseridSave").val("");
    $("#formUser")[0].reset();

}

// Update
$(document).on("click", ".btnUpdate", function(event)
{
    $("#hiduseridSave").val($(this).closest("tr").find('#hiduseridUpdate').val());
    $("#name").val($(this).closest("tr").find('td:eq(0)').text());
    $("#idno").val($(this).closest("tr").find('td:eq(1)').text());
    $("#address").val($(this).closest("tr").find('td:eq(2)').text());
    $("#dob").val($(this).closest("tr").find('td:eq(3)').text());
    $("#age").val($(this).closest("tr").find('td:eq(4)').text());
    $("#sex").val($(this).closest("tr").find('td:eq(5)').text());
    $("#phone").val($(this).closest("tr").find('td:eq(6)').text());
    $("#email").val($(this).closest("tr").find('td:eq(7)').text());
    $("#password").val($(this).closest("tr").find('td:eq(8)').text());
});

//Remove
$(document).on("click", ".btnRemove", function(event)
{
    $.ajax(
        {
            url : "UsersAPI",
            type : "DELETE",
            data : "userid=" + $(this).data("userid"),
            dataType : "text",
            complete : function(response, status)
            {
                onUserDeleteComplete(response.responseText, status);
            }
        });
});

function onUserDeleteComplete(response, status) {

    if (status == "success") {

        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() == "success") {

            $("#alertSuccess").text("Successfully deleted.");
            $("#alertSuccess").show();
            $("#divUsersGrid").html(resultSet.data);

        } else if (resultSet.status.trim() == "error") {

            $("#alertError").text(resultSet.data);
            $("#alertError").show();

        }

    } else if (status == "error") {

        $("#alertError").text("Error while deleting.");
        $("#alertError").show();

    } else {

        $("#alertError").text("Unknown error while deleting..");
        $("#alertError").show();

    }

}

// Client Model
function validateUserForm() {

    // NAME
    if ($("#name").val().trim() == "")
    {
        return "Insert User Name.";
    }
    // ID NO.
    if ($("#idno").val().trim() == "")
    {
        return "Insert ID Number.";
    }

    // ADDRESS
    if ($("#address").val().trim() == "")
    {
        return "Insert Address.";
    }
    // Numerical Value
    //var tmpPrice = $("#itemPrice").val().trim();
    //if (!$.isNumeric(tmpPrice))
    //{
    //    return "Insert a numerical value for Item Price.";
    //}

    // convert to decimal price
    //$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2));
    
    // DOB
    if ($("#dob").val().trim() == "")
    {
        return "Insert Date of Birth.";
    }
    
    // AGE
    if ($("#age").val().trim() == "")
    {
        return "Insert Age.";
    }
    
    // GENDER
    if ($("#sex").val().trim() == "")
    {
        return "Insert Gender.";
    }
    
    // PHONE NO
    if ($("#phone").val().trim() == "")
    {
        return "Insert Phone Number.";
    }
    
    // E-MAIL ADDRESS
   	if ($("#email").val().trim() == "")
    {
        return "Insert Email Address.";
    }

    
    // PASSWORD
    if ($("#password").val().trim() == "")
    {
        return "Insert Password.";
    }
    return true;
}
