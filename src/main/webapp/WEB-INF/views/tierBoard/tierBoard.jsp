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
</head>
<body>
<div style=width:80%;margin:auto;>
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



<div class="modal" id="myModal">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="margin-top: 40%;">
				<div class="modal-header">
					<h5 class="modal-title">로그인</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label class="col-form-label" for="inputDefault">ID</label> <input
							type="text" class="form-control" id="inputDefault">
					</div>
					<div class="form-group">
						<label class="col-form-label" for="inputDefault">Password</label>
						<input type="text" class="form-control" id="inputDefault">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">로그인</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">취소</button>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
$(document).ready(function(){
	function fnOpen(mem_num){
		
		var modal = document.getElementById('myModal');

		// Get the button that opens the modal
		var btn = document.getElementById("myBtn");

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];

		// When the user clicks on the button, open the modal 
		btn.onclick = function() {
			modal.style.display = "block";
		}

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modal.style.display = "none";
		}

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
	}
});
</script>
</body>
</html>