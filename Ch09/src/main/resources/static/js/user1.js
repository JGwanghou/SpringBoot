/**
 * 
 */
$(document).ready(function(){
				
				// user1 목록1
				$('.user1.list1').click(function(){
					
					$.ajax({
						url:'/Ch09/user1',
						method:'GET',
						dataType:'json',
						success: function(data){
							console.log(data);
						}
					});
				});
				
				$('.user1.list2').click(function(){
					
					let uid = 'a104';
					
					$.ajax({
						url:'/Ch09/user1/'+uid,
						method:'GET',
						dataType:'json',
						success: function(data){
							console.log(data);
						}
					});
				})
				
				$('.user1.register').click(function(){
					
					let jsonData = {
							"uid":"a107",
							"name":"김동신",
							"hp":"010-1234-5131",
							"age":"25"
					};
					
					$.ajax({
						url:'/Ch09/user1/',
						method:'POST',
						data: jsonData,
						dataType:'json',
						success: function(data){
							console.log(data);
						}
					});
				})
				
				$('.user1.modify').click(function(){
					let jsonData = {
							"uid":"s107",
							"name":"감동신",
							"hp":"010-1232-1234",
							"age":52
					};
					
					$.ajax({
						url:'/Ch09/user1/',
						method:'PUT',
						data: jsonData,
						dataType:'json',
						success: function(data){
							console.log(data);
						}
					});
				})
				
				$('.user1.delete').click(function(){
					let uid = 'a107';
					
					$.ajax({
						url:'/Ch09/user1/'+uid,
						method:'DELETE',
						dataType:'json',
						success: function(data){
							console.log(data);
						}
					});
				})
				
			});