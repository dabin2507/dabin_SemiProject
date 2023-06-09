<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="/main/header.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>
<script src=board_js/notice/write.js></script>
<style>

textarea{
	resize: none;
}

#upfile{display:none}
img{width:20px;}

.container-fluid{
	width:80%;
	margin: 0 auto;
}

.float-date{
	float: right;	
}

#user_id{
	width:80px;
	text-align: center;
}
.form-content{
	margin-top: 0.1em;
}

.bottom-active{
	margin-top: 0.1em;
}
.button{
	float:right;
}

label {
	text-align: right;
}

	
</style>
<head>
<title>공지사항 작성</title>
</head>
<body>
<div class="page-content">
    <div class="container-fluid">
		<form action="NoticeAddAction.bon" method="post" enctype="multipart/form-data" name="noticeform">	
			<!-- start page title -->
			<div class="row">
			    <div class="col-12">
			        <div class="page-title-box d-sm-flex align-items-center justify-content-between">
						<h4 class="mb-sm-0 font-size-18">커뮤니티 > 공지사항</h4>
			        </div>
			    </div>
			</div>
	        <!-- end page title -->
	        <div class="row">
	            <div class="col-lg-12">
	                <div class="card">
	                    <div class="card-body">
	                    	<div class="input-group">
	                    		<input name="notice_subject" id="autoSizingInputGroup" type="text" maxlength="100"
										 class="form-control" placeholder="제목 입력">
								<input class="input-group-text" name="user_id" id="user_id" value="${id}" type="text" readOnly>
                         	</div>
							<div class="form-content">
								<textarea name="notice_content" id="notice_content"
											 rows="20" class="form-control"
											 placeholder="내용을 입력하세요"></textarea>
							</div>
							<div class="bottom-active" >
								<div class="file-value">
									<label class="form-control" >
									<span id="filevalue"></span>
										<input type="file" id="upfile" name="notice_file">
										<img src="image/board/attach.png">
									</label>
								</div>
								<div class="button">
									<button type=reset class="btn btn-danger" onClick="history.go(-1)">취소</button>
									<button type=submit class="btn btn-primary">등록</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div> <!-- end row -->
   </div> <!-- container-fluid -->
<!-- End Page-content -->
<jsp:include page="/main/footer.jsp"></jsp:include>
</body>
</html>