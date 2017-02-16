$(function(){ 
	
	
	$.ajax({
		   type: "GET",
		   url: "user/findAll",
		   success: function(data){
			   var trs = "";  
			   $.each(data,function(n,value) {   
		             trs += "" + value.name +"<input type='button' onclick='del("+value.id+")' value='delete'></input>";  
		           });  
		  
			   $("#userName").html(trs);
		   }
		});
	

	
	$("#add").click( function () { 
		$.ajax({
			   type: "POST",
			   url: "user/save",
			   data: {
				   name: $("#name").val(),
				   age:$("#age").val()
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

function del(id){
	if(confirm('delete?')){
		$.ajax({
			   type: "GET",
			   url: "user/delete",
			   data: {
				   id:id
			   },
			   success: function(data){
				   alert(data.msg);  
			   
				   if(data.ret==true || data.ret=='true'){
					   location.reload();
				   }
			   }
			});
	}

}

function page(pageNum){
	$.ajax({
		   type: "GET",
		   url: "user/findPage",
		   data: {
			   pageNum:pageNum
		   },
		   success: function(data){
			   var trs = "";  
			   $.each(data,function(n,value) {   
		             trs += "" + value.name +"<input type='button' onclick='del("+value.id+")' value='delete'></input>";  
		           });  
		  
			   $("#userName").html(trs);
		   }
		});

}

function show(pageNum){
	$.ajax({
		   type: "GET",
		   url: "user/findRoleMsg"+pageNum,
		   success: function(data){
			   var trs = "";  
			   $.each(data,function(n,value) {   
		             trs += "" + value.roleName +"</br>"; 
		             if(pageNum ==2 ){
		            	 trs += "" + value.userStr +";"; 
		             }
		             if(pageNum ==1 ){
		            	 var logData = value.userLog;
			             for(var key in logData) { 
			            	 trs +=  key +  ":" + logData[key] +"</br>";  
			            	}  
		             }
		             
		           });  
		  
			   $("#roleMsg").html(trs);
		   }
		});

}