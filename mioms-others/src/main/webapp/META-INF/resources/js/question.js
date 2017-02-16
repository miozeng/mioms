$(function(){ 
	
	$.ajax({
		   type: "GET",
		   url: "../question/findAll",
		   success: function(data){
			   var trs = "";  
			   $.each(data,function(n,value) {   
		             trs += "" + value.title +"";  
		           });  
		  
			   $("#qtitle").html(trs);
		   }
		});
	

	
	$("#add").click( function () { 
		$.ajax({
			   type: "POST",
			   url: "../question/save",
			   data: {
				   title: $("#title").val(),
				   content:$("#content").val()
			   },
			   success: function(data){
				   alert(data.msg);  
				   if(data.ret==true || data.ret=='true'){
					   location.reload();
				   }
			   }
			});
		
	});
}); 





