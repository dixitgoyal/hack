
var API="http://harshgoyal.xyz/locmon/api/index.php";

/*****************LOGIN API CALL************************/
$('#loginform').submit(function(e) {
	
    document.getElementById("loginButton").disabled = true;
    e.preventDefault(); // to stop the form from submitting

    $.ajax
    ({
    	url: API,
    	type: "post",
    	data: new FormData(this),
    	contentType: false,
    	cache: false,
    	processData:false,
    	dataType:'JSON',
    	success: function(result){
            document.getElementById("loginButton").disabled = false;
    		
            $('#loginform')[0].reset();

            if(result == 'true')
                window.open('../index.php','_self');
            else
                alert(result);
    	
        },
    	error: function (error) {
            document.getElementById("loginButton").disabled = false;

			alert(JSON.stringify(error));
        }
    });    

});


/*****************LOGOUT API CALL************************/

function logout()
{   
	$.ajax
	({
		url: API,
		type: "post",
		data: {'action':'logout'},
		dataType:'JSON',
		success: function(result){
            //alert(result);
            if(result == 'true')
                window.location.reload();

		},
		error: function (error) {
			alert(JSON.stringify(error));
        }
    });    
}



/*****************SIGNUP API CALL************************/
$('#signupform').submit(function(e) {

    e.preventDefault(); // to stop the form from submitting

    var fData =new FormData(this);

    	document.getElementById("signupButton").disabled = true;

    	$.ajax
    	({
    		url: API,
    		type: "post",
    		data: fData,
    		contentType: false,
    		cache: false,
    		processData:false,
    		dataType:'JSON',
    		success: function(result){

                document.getElementById("signupButton").disabled = false;

    			$('#signupform')[0].reset();	
                
                if(result == 'true')
                {
                    window.open('../login/index.php','_self');
                }
                else
                alert(result);

        	},
        	error: function (error) {
                    document.getElementById("signupButton").disabled = false;

        			alert(JSON.stringify(error));
            }
        });

});
