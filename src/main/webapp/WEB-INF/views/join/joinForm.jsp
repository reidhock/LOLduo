<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/header.jspf" %>
<%@ include file="/WEB-INF/include/nav.jsp" %>
<%@ include file="/WEB-INF/include/include-body.jspf" %>
<meta charset="UTF-8">
<title>Join Form</title>

<style>
.form-control1 {
	width: 20%;
	height: calc(1.5em + 0.75rem + 2px);
	padding: 0.375rem 0.75rem;
	font-size: 0.9375rem;
	font-weight: 400;
	line-height: 1.5;
	color: #7b8a8b;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #ced4da;
	border-radius: 0.25rem;
	-webkit-transition: border-color 0.15s ease-in-out, -webkit-box-shadow
		0.15s ease-in-out;
	transition: border-color 0.15s ease-in-out, -webkit-box-shadow 0.15s
		ease-in-out;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out,
		-webkit-box-shadow 0.15s ease-in-out
}

@media ( prefe
</style>

<script>
	$(document).ready(function(){
		$("#check").unbind("click").click(function(e){
			e.preventDefault();
			fn_idCheck();
		});
		
		$("#btnJoin").unbind("click").click(function(e){
			e.preventDefault();
			fn_signUp();
		});
		
		$("#summonerCheck").unbind("click").click(function(e){
			e.preventDefault();
			fn_summonerCheck();
		});
	});
	
	function fn_idCheck(){
		var userId = $("#userId").val();

		if(userId == ""){
			alert("아이디를 입력하세요");
		}
		else {
			$.ajax({
				type:"POST",
				url: "/lolduo/idCheck",
				data: {"MEM_ID" :userId},
				dataType: "json",
				error: function(error){
					alert("서버가 미응답 다시하세요");
				},
				success:function(result){
					if(result == 0)
					{
						$("#userId").attr("disabled",true);
						alert("사용가능한 아이디입니다.");
					}
					else if(result == 1){
						alert("이미 존재하는 아이디입니다.");
					}
					else
					{
						alert("에러발생");
					}
				}
			});
		}
	}
	
	function fn_summonerCheck(){
		var summoner = $("#MEM_SUMMONER").val();

		if(summoner == ""){
			alert("소환사명을 입력해주세요");
		}
		else {
			$.ajax({
				type:"POST",
				url: "/lolduo/summonerCheck",
				data: {"MEM_SUMMONER" :summoner},
				dataType: "json",
				error: function(error){
					alert("서버가 미응답 시도해주세요");
				},
				success:function(result){
					if(result == 0)
					{
						alert("존재하지 않는 소환사명입니다.");
					}
					else if(result == 1){
						alert("이미 존재하는 소환사명입니다.");
					}
					else
					{
						$("#MEM_SUMMONER").attr("disabled",true);
						alert("사용 가능한 소환사명입니다.");
					}
				}
			});
		}
	}
	
	function fn_signUp(){
		if($("#userId").val()==""){
			alert("사용하실 id를 적어주세요");
			return;
		}
		else if(!$("#userId").attr("disabled")){
			alert("아이디 중복체크를 해주세요");
			return;
		}
		else if($("#userPw").val().length < 1){
			alert("사용하실 비밀번호를 적어주세요");
			return;
		}
		else if(!$("#MEM_SUMMONER").attr("disabled")){
			alert("소환사명 중복체크를 해주세요");
			return;
		}
		
		if(window.confirm("회원가입을 하시겠습니까?")){
			var comSubmit = new ComSubmit("frm");
			comSubmit.setUrl("/lolduo/signUpComplete");
			comSubmit.addParam("MEM_ID", $("#userId").val());
			comSubmit.addParam("MEM_PW", $("#userPw").val());
			comSubmit.addParam("MEM_NAME", $("#MEM_NAME").val());
			comSubmit.addParam("MEM_EMAIL", $("#MEM_EMAIL").val());
			comSubmit.addParam("MEM_SUMMONER", $("#MEM_SUMMONER").val());
			comSubmit.addParam("MEM_POSITION", $("#MEM_POSITION").val());
			comSubmit.addParam("MEM_TIME", $("#MEM_TIME").val());
			comSubmit.submit();
			alert("회원가입이 완료되었습니다!");
			
		}
	}
	
</script>

</head>
<body>

	<div>
		<h1>
		Logo
		</h1>
	</div>
	<div align=center>
			<form id="frm">
				<fieldset>
					<div class="form-group has-success">
						<label class="form-control-label" for="id">아이디:</label>
						 <input	type="text" id="userId" name="userId" class="form-control1" placeholder="ID">
						 <a href="#" id="check" class="btn btn-info">중복체크</a>
					</div>

					<div class="form-group">
						<label class="col-form-label" for="password">비밀번호:</label> <input
							type="password" id="userPw" class="form-control1"
							placeholder="******">
					</div>
					
					<div class="form-group">
						<label class="col-form-label" for="name">이름:</label> <input
							type="text" id="MEM_NAME" class="form-control1"
							placeholder="염따">
					</div>

					<div class="form-group">
						<label class="col-form-label" for="email">이메일:</label> <input
							type="text" id="MEM_EMAIL" class="form-control1"
							placeholder="asds@naver.com">
					</div>
					
					<div class="form-group">
						<label class="col-form-label" for="summoner">소환사명:</label> 
						<input type="text" id="MEM_SUMMONER" class="form-control1"
							placeholder="소환사명을 적어주세요">
						<a href="#" id="summonerCheck" class="btn btn-info">중복체크</a>
					</div>
					
					<div class="form-group">
						<label class="col-form-label" for="position">포지션:</label> <input
							type="text" id="MEM_POSITION" class="form-control1"
							placeholder="선호 포지션">
					</div>
					
					<div class="form-group">
						<label class="col-form-label" for="time">접속시간:</label> <input
							type="text" id="MEM_TIME" class="form-control1"
							placeholder="접속하는시간대 입력">
					</div>
					
					<a href="#"  id="btnJoin" class="btn btn-primary">회원가입</a>
					
				</fieldset>
			</form>
			</div>
		

</body>

<script type="text/javascript">
	// Get the modal
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
</script>
</html>