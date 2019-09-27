<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<script type="text/javascript">


var idChk = "<%=(String)session.getAttribute("MEM_ID")%>";
sessionStorage.setItem("MEM_ID",idChk);
console.log(idChk);
$(document).ready(function(){
	if(idChk=="null"){
		$(".login").hide();
		$(".logout").show();
	}else{
		$(".login").show();
		$(".logout").hide();
	}
});

function fn_login(){	
	var id = $("#MEM_ID").val();
	var pw = $("#MEM_PW").val();
	if(id==""){
		alert("아이디를 입력하세요");
	} else if(id!="" && pw==""){
		alert("비밀번호를 입력하세요");
	} else{
		$.ajax({
			type:"POST",
			url:"/lolduo/login",
			data:{"MEM_ID" :id,"MEM_PW" :pw},
			dataType: "json",
			error: function(error){
				alert("서버가 응답하지 않습니다. 다시 시도해주세요");
			},
			success: function(result){
				if(result==0){
					alert("아이디 또는 비밀번호를 확인해주세요");
				}
				else if(result==1){
					location.href="/lolduo/";
				}
			}
		});
	}
};


function openMenu(){
	var e = document.getElementById("mymenu");
	if(e.style.display=='block')
		$(".mymenu").hide();
	else
		$(".mymenu").show();
};
function goMyPage(){
	path = "<%=request.getContextPath()%>";
	str = "<form id='id' action='"+path+"/myPage/messageList' method='post'>"
		+ "<input type='hidden' name='MEM_ID' value='"+idChk+"'>"
		+ "</form>";
	$("div.login").append(str);
	id.submit();
};
function goService(){
	path = "<%=request.getContextPath()%>";
	str = "<form id='id' action='"+path+"/qna' method='post'>"
		+ "<input type='hidden' name='MEM_ID' value='"+idChk+"'>"
		+ "</form>";
	$("div.login").append(str);
	id.submit();
};
$(document).ready(function() {
		// Get the modal
		var modal = document.getElementById('myModal');
		// Get the button that opens the modal
		var btn = document.getElementById("myBtn");
		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];
		// 취소버튼
		var cancel=document.getElementById("cancel");
		// 모달 닫을 때 아이디비밀번호 초기화시키기 위해서
		var re_id=document.getElementById("MEM_ID");
		var re_pw=document.getElementById("MEM_PW");
		// When the user clicks on the button, open the modal 
			btn.onclick = function() {
			modal.style.display = "block";
		}
		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modal.style.display = "none";
			re_id.value="";
			re_pw.value="";
		}
		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
				re_id.value="";
				re_pw.value="";
			}
		}
		//로그인에서 취소누르면 modal 숨기기
		cancel.onclick=function(event){
			modal.style.display = "none";
			re_id.value="";
			re_pw.value="";
		};
	});
</script>
<title>Insert title here</title>
</head>
<body>
<div class="bs-component">
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<div class="img" style=cursor:pointer;>
		<img src="<%=request.getContextPath()%>/images/lolduologo.png" onclick="location.href='<%=request.getContextPath()%>/'" width="100px">
	</div>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarColor01">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="/lolduo/tierBoard">티어 게시판<span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Features</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Pricing</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">About</a>
				</li>
			</ul>
			<div style=text-align:right;display:inline;>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="text" placeholder="Search">
				<button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>&nbsp;&nbsp;
			</form>
			</div>
			<div style=text-align:right;display:inline;>
			<ul class="navbar-nav mr-auto logout">
				<li class="nav-item active">
					<a class="nav-link" href="/lolduo/joinForm">회원가입</a>
				</li>
				<li class="nav-item active">
					<a class="nav-link" href="#" id="myBtn">로그인</a>
				</li>
			</ul>
			</div>
			<ul class="nav nav-pills mymenu login" id="mymenu" style="display:block;">
  				<li class="nav-item dropdown">
    				<a class="nav-link dropdown-toggle mymenu" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false" onclick="openMenu();"><%=(String)session.getAttribute("MEM_ID")%>님</a>
    				<div class="dropdown-menu" x-placement="bottom-start" style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 38px, 0px);">
      					<a class="dropdown-item" href="#">마이페이지</a>
      					<a class="dropdown-item" href="#">로그아웃</a>
					</div>
  				</li>
			</ul>
		</div>
	</nav>
</div>


<!-- 로그인 -->
<div class="modal" id="myModal">
	<div class="modal-dialog" role="document">
		<div class="modal-content" style="margin-top: 40%;" >
			<div class="modal-header">
				<h5 class="modal-title">로그인</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close" >
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">	
				<div class="form-group">
					<label class="col-form-label" for="inputDefault">ID</label> 
					<input type="text" class="form-control" id="MEM_ID" name="MEM_ID" placeholder="아이디를 입력하세요">
				</div>
				<div class="form-group">
					<label class="col-form-label" for="inputDefault">Password</label>
					<input type="password" class="form-control" id="MEM_PW" name="MEM_PW" placeholder="비밀번호를 입력하세요"	 onkeypress="if( event.keyCode == 13 ){fn_login();}">
					<!-- keycode==13 은 enter를 의미해서 enter누르면 로그인 버튼 누르는거랑 똑같이 동작한다는 내용-->
				</div>	
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="login" onclick="fn_login();">로그인</button>
				<button type="button" class="btn btn-secondary" id="cancel" data-dismiss="modal" onclick="cancel();">취소</button>
			</div>
		</div>
	</div>
</div>

</body>
</html>