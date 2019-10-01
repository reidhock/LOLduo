<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/header.jspf" %>
<%@ include file="/WEB-INF/include/nav.jsp" %>
<meta charset="UTF-8">
<title>티어 게시판</title>
<style type="text/css">
#modalStyle {
		max-width: 800px;
		margin: 1.75rem auto
	}
</style>
</head>
<body>
<div style=width:80%;margin:auto;  >
<div style=text-align:center;font-size:30px;>티어가 들어갑니다</div>
<table class="table table-hover" >
  <thead>
    <tr class="table-info">
      <th scope="col">소환사 이름</th>
      <th scope="col">티어</th>
      <th scope="col">플레이 가능 시간</th>
      <th scope="col">선호 포지션</th>
    </tr>
  </thead>
 <tbody class="table-hover">
<!-- <tr>
      <th scope="row">Default</th>
      <td>Column content</td>
      <td>Column content</td>
      <td>Column content</td>
	</tr>  -->
	<c:forEach items="${list}" var="list">
		<tr class="table-light" onclick="fnOpen(${list.MEM_NUM})">
			<th scope="row">${list.MEM_SUMMONER}</th>
			<td>${list.MEM_TIER}</td>
			<td>${list.MEM_TIME}</td>
			<td>${list.MEM_POSITION}</td>
		</tr>
	</c:forEach>
	</tbody>
</table> 
</div>



<div class="modal" id="myModal2" style="overflow:auto;">
	<div class="modal-dialog" role="document" id="modalStyle">
		<div class="modal-content" id="modal-content" style="margin-top: 40%;" >
			<div class="modal-header">
				<h5 class="modal-title">소환사명</h5>
	
			</div>
			<div class="modal-body">
			<div class="form-group">
			<table class="table table-hover">
				<thead>
					<tr class="table-primary">
						<th scope="col">승률</th>
						<th scope="col">KDA</th>
						<th scope="col">선호포지션</th>
						<th scope="col">솔랭</th>
					</tr>
				</thead>
				<tbody class="table-hover">
					<tr>
							<th scope="row">Default</th>
							<td>Column content</td>
							<td>Column content</td>
							<td>Column content</td>
					</tr> 
				</tbody>
			</table>
			<table class="table table-hover">
				<thead>
					<tr class="table-primary">
						<th scope="col">최근 전적</th>
						<th scope="col">총 전적</th>
						
					</tr>
				</thead>
				<tbody class="table-hover">
					<tr>
							<td>
							<span class="badge badge-info">승</span>
							Column content
							</td>
							<td rowspan="5" style=" width: 504px;">
<div class="card border-secondary mb-3" style="max-width: 30rem;">
  <div class="card-header">챔피언 / 게임/ 승률 / KDA</div>
  <div class="card-body">
    <h4 class="card-title">Secondary card title</h4>
    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
  </div>
</div></td>
					</tr> 
					<tr>
					
							<td>
							
							<span class="badge badge-danger">패</span>
							Column content
							</td>
							
					</tr>
				</tbody>
			</table>
			</div>
		<div class="form-group">
		
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-danger">듀오 신청하기</button>&nbsp;&nbsp;
		<button type="button" class="btn btn-secondary" id="cancel2" data-dismiss="modal" onclick="cancel2();">닫기</button>
	</div>
		</div>
	</div>
</div>


	
<script type="text/javascript">
function cancel2(){
	var modal3 = document.getElementById('myModal2');
	modal3.style.display = "none";
}
function fnOpen(mem_num){

	$.ajax({
	    url: "/lolduo/Refresh",
	    type: "POST",
	    cache: false,
	    dataType: "json",
	    data: {"num" : mem_num},
	    success: function(qq){
	    	alert('성공');
	    	var modal = document.getElementById("modal-content")
	    	
	    },
	    
	    error: function (request, status, error){        
	        var msg = "ERROR : " + request.status + "<br>"
	      msg +=  + "내용 : " + request.responseText + "<br>" + error;
	      console.log(msg);              
	    }
	  });
	
	var modal2 = document.getElementById('myModal2');
	modal2.style.display = "block";

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == modal2) {
			modal2.style.display = "none";
		}
	}
}

$(document).ready(function(){
	
});
</script>
</body>
</html>