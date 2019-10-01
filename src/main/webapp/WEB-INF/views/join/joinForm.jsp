<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/header.jspf"%>
<%@ include file="/WEB-INF/include/nav.jsp"%>
<meta charset="UTF-8">
<title>회원가입</title>

<style>
.form-control1 {
	width: 100%;
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
@media
(
prefe
</style>

<script>
	$(document).ready(function() {
		$("#check").unbind("click").click(function(e) {
			e.preventDefault();
			fn_idCheck();
		});

		$("#btnJoin").unbind("click").click(function(e) {
			e.preventDefault();
			fn_signUp();
		});

		$("#summonerCheck").unbind("click").click(function(e) {
			e.preventDefault();
			fn_summonerCheck();
		});

	});

	function fn_idCheck() {
		var userId = $("#userId").val();

		if (userId == "") {
			alert("아이디를 입력하세요");
		} else {
			$.ajax({
				type : "POST",
				url : "/lolduo/idCheck",
				data : {
					"MEM_ID" : userId
				},
				dataType : "json",
				error : function(error) {
					alert("서버가 미응답 다시하세요");
				},
				success : function(result) {
					if (result == 0) {
						$("#userId").attr("class", "form-control is-valid");
						$("#userId").css("width", "80%");
						$("#userId").css("display", "inline-block");
						$('#chkMsg').html("사용 가능한 아이디입니다.");
						$('#chkMsg').attr("class", "valid-feedback");
					} else if (result == 1) {
						$("#userId").attr("class", "form-control is-invalid");
						$('#chkMsg').html("이미 존재하는 아이디입니다.");
						$('#chkMsg').attr("class", "invalid-feedback");
					} else {
						alert("에러발생");
					}
				}
			});
		}
	}

	$(document).ready(function() { //비밀번호 일치 확인
		//[1] lblError 레이어 클리어
		$('#userPw').keyup(function() {
			//$('#lblError').remove(); // 제거
			$('#lblError').text(''); // 제거가 아니라 클리어
			$('#userPw2').val('');
		});
		//[2] 암호 확인 기능 구현
		$('#userPw2').keyup(function() {
			if ($('#userPw').val() != $('#userPw2').val()) {
				$("#userPw2").attr("class", "form-control is-invalid");
				$('#lblError').text(''); // 클리어
				$('#lblError').html("암호가 일치하지 않습니다."); //레이어에 HTML 출력
				$('#lblError').attr("class", "invalid-feedback");
			} else {
				$("#userPw2").attr("class", "form-control is-valid");
				$('#lblError').text(''); // 클리어
				$('#lblError').html("암호가 일치합니다.");
				$('#lblError').attr("class", "valid-feedback");
			}
		});
	});

	function fn_summonerCheck() {
		var summoner = $("#MEM_SUMMONER").val();

		if (summoner == "") {
			alert("소환사명을 입력해주세요");
		} else {
			$.ajax({
				type : "POST",
				url : "/lolduo/summonerCheck",
				data : {
					"MEM_SUMMONER" : summoner
				},
				dataType : "json",
				error : function(error) {
					alert("서버 미응답, 다시 시도해주세요");
				},
				success : function(result) {
					if (result == 0) {
						$("#MEM_SUMMONER").attr("class",
								"form-control is-invalid");
						$("#MEM_SUMMONER").css("width", "80%");
						$("#MEM_SUMMONER").css("display", "inline-block");
						$('#chkMsg2').html("존재하지 않는 소환사명입니다.");
						$('#chkMsg2').attr("class", "invalid-feedback");
					} else if (result == 1) {
						$("#MEM_SUMMONER").attr("class",
								"form-control is-invalid");
						$("#MEM_SUMMONER").css("width", "80%");
						$("#MEM_SUMMONER").css("display", "inline-block");
						$('#chkMsg2').html("이미 존재하는 소환사명입니다.");
						$('#chkMsg2').attr("class", "invalid-feedback");
					} else {
						$("#MEM_SUMMONER").attr("class",
								"form-control is-valid");
						$("#MEM_SUMMONER").css("width", "80%");
						$("#MEM_SUMMONER").css("display", "inline-block");
						$('#chkMsg2').html("사용 가능한 소환사명입니다.");
						$('#chkMsg2').attr("class", "valid-feedback");
					}
				}
			});
		}
	}

	function fn_signUp() {
		if ($("#userId").val() == "") {
			alert("사용하실 id를 적어주세요");
			return;
		} else if ($("#chkMsg").html() != "사용 가능한 아이디입니다.") {
			alert("아이디 중복확인을 해주세요.");
			return;
		} else if ($("#userPw").val().length < 1) {
			alert("사용하실 비밀번호를 적어주세요");
			return;
		} else if ($("#chkMsg2").html() != "사용 가능한 소환사명입니다.") {
			alert("아이디 중복확인을 해주세요.");
			return;
		}

		if (window.confirm("회원가입을 하시겠습니까?")) {
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
	<div align=center style="width: 35%; margin: auto; margin-top: 5%;">
		<form id="frm">
			<fieldset>
				<div class="form-group">
					<!-- <label class="form-control-label" for="id">아이디 :</label> -->
					<input type="text" id="userId" name="userId" class="form-control1"
						placeholder="ID" style="width: 80%;"> <a href="#"
						id="check" class="btn btn-info"
						style="margin-bottom: 4px; width: 19%;">중복체크</a>
					<div id="chkMsg"
						style="display: inline-block; text-align: left; margin-left: 12px; margin-top: 8px;"></div>
				</div>

				<div class="form-group">
					<!-- <label class="col-form-label" for="password">비밀번호 :</label> -->
					<input type="password" id="userPw" class="form-control1"
						placeholder="비밀번호">
				</div>
				<div class="form-group">
					<!-- <label class="col-form-label" for="password">비밀번호 확인 :</label>  -->
					<input type="password" id="userPw2" class="form-control1"
						placeholder="비밀번호 확인">
					<div id="lblError"
						style="display: inline-block; text-align: left; margin-left: 12px; margin-top: 12px;"></div>
				</div>
				<div class="form-group">
					<!-- <label class="col-form-label" for="name">이름 :</label> -->
					<input type="text" id="MEM_NAME" class="form-control1"
						placeholder="이름">
				</div>

				<div class="form-group">
					<!-- <label class="col-form-label" for="email">이메일:</label>  -->
					<input type="text" id="MEM_EMAIL" class="form-control1"
						placeholder="lolduo@lolduo.com">
				</div>

				<div class="form-group">
					<!-- <label class="col-form-label" for="summoner">소환사명:</label>  -->
					<input type="text" id="MEM_SUMMONER" class="form-control1"
						placeholder="소환사명을 적어주세요" style="width: 80%;"> 
						<a href="#"id="summonerCheck" class="btn btn-info" style="margin-bottom: 4px; width: 19%;">중복체크</a>
					<div id="chkMsg2"
						style="display: inline-block; text-align: left; margin-left: 12px; margin-top: 8px;"></div>
				</div>

				<div class="form-group" style="width:49%; display:inline-block; margin-right:6px;">
					<select class="custom-select">
						<option selected="">주 라인</option>
						<option value="Top">탑</option>
						<option value="Jungle">정글</option>
						<option value="Mid">미드</option>
						<option value="ADC">원딜</option>
						<option value="Support">서포터</option>
					</select>
				</div>
				
				<div class="form-group" style="width:49%; display:inline-block;">
					<select class="custom-select">
						<option selected="">서브 라인</option>
						<option value="Top">탑</option>
						<option value="Jungle">정글</option>
						<option value="Mid">미드</option>
						<option value="ADC">원딜</option>
						<option value="Support">서포터</option>
					</select>
				</div>

				<div class="form-group">
					<!-- <label class="col-form-label" for="time">접속시간:</label>  -->
					<input type="text" id="MEM_TIME" class="form-control1"
						placeholder="접속하는시간대 입력">
				</div>

				<a href="#" id="btnJoin" class="btn btn-primary">회원가입</a>

			</fieldset>
		</form>
	</div>

	<%@ include file="/WEB-INF/include/include-body.jspf"%>
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