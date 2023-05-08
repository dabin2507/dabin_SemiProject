<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="/main/header.jsp"></jsp:include>
<div class="page-content">
    <div class="container-fluid">
        <!-- start page title -->
        <div class="row">
            <div class="col-lg-12">
                <div class="">
                    <div class="table-responsive">
                        <table class="table project-list-table table-nowrap align-middle table-borderless">
                            <thead>
                                <tr>
                                    <th scope="col">공지사항</th>
                                </tr>
                            </thead>
                        </table>
            </div>
        </div>
        <!-- end page title -->

        <input type="hidden" name="USER_ID" value="${userinfo.USER_ID }" id="USER_ID">  <!-- 여기서 name은 dao에서 받는 매개변수와 같아야함 -->
        <div class="row">
            <div class="col-xl-4">
                <div class="card overflow-hidden">
                    <div class="bg-primary bg-soft">
                        <div class="row">
                            <div class="col-7">
                                <div class="text-primary p-3">
                                    <h5 class="text-primary">환영합니다 !</h5>
                                    <p>${userinfo.USER_ID }님</p>
                                    
                                </div>
                            </div>
                            <div class="col-5 align-self-end">
                                <img src=image/profile.png" alt="" class="img-fluid">
                            </div>
                        </div>
                    </div>
                    <div class="card-body pt-0">
                        <div class="row">
                            <div class="col-sm-4">
   <!-- 이미지 여기-->
                                <div class="avatar-md profile-user-wid mb-4">
                                <c:if test="{}"></c:if>
                                <img src="image/${userinfo.USER_IMG }" alt="profile" class="img-thumbnail rounded-circle">
                             <!--  <img src="<%=request.getContextPath() %>/saveFolder/${userinfo.USER_IMG}"      
                                   style= "width:95px; height:65px; center;" alt=""
                                        class="img-thumbnail rounded-circle">   --> 
                                </div>
                                <h5 class="font-size-15 text-truncate">${userinfo.USER_NAME }</h5>
                                <p class="text-muted mb-0 text-truncate">${userinfo.USER_DEPT }</p>
                            </div>

                            <div class="col-sm-8">
                                <div class="pt-4">

                                    <div class="row">
                                        <div class="col-6">
                                            <h5 class="font-size-15" >받은 이메일</h5>
                                            <h5 class="text-muted mb-0">12</h5>
                                        </div>
                                        <div class="col-6">
                                            <h5 class="font-size-15">새로온 메신저</h5>
                                            <h5 class="text-muted mb-0">100</h5>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        
                
            </div>
            <div class="col-xl-8">
                <div class="row">
                    <div class="col-md-4">
                        <div class="card mini-stats-wid">
                            <div class="card-body">
                                <div class="d-flex">
                                    <div class="flex-grow-1">
                                        <p class="text-muted fw-medium">진행중인 프로젝트</p>
                                        <h4 class="mb-0">2</h4>
                                    </div>
                                    

                                    <div class="mini-stat-icon avatar-sm rounded-circle bg-primary align-self-center">
                                        <span class="avatar-title">
                                            <i class="bx bx-copy-alt font-size-24"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card mini-stats-wid">
                            <div class="card-body">
                                <div class="d-flex">
                                    <div class="flex-grow-1">
                                        <p class="text-muted fw-medium">요청한 프로젝트</p>
                                        <h4 class="mb-0">2</h4>
                                    </div>

                                    <div class="avatar-sm rounded-circle bg-primary align-self-center mini-stat-icon">
                                        <span class="avatar-title rounded-circle bg-primary">
                                            <i class="bx bx-archive-in font-size-24"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card mini-stats-wid">
                            <div class="card-body">
                                <div class="d-flex">
                                    <div class="flex-grow-1">
                                        <p class="text-muted fw-medium">전자결재</p>
                                        <h4 class="mb-0">1</h4>
                                    </div>

                                    <div class="avatar-sm rounded-circle bg-primary align-self-center mini-stat-icon">
                                        <span class="avatar-title rounded-circle bg-primary">
                                            <i class="bx bx-purchase-tag-alt font-size-24"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                
                <!-- end row -->
						
        <!-- end row -->


                    <!-- end table-responsive -->
                    </div>
                </div>
            </div>
        </div>
        
        <!-- end row -->
       <%-- <input type="hidden" name="PROJECT_NAME" value="${mainbean.PROJECT_NAME }" id="PROJECT_NAME">다시실행해주--%>
         <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title mb-4">내 프로젝트</h4>
                        <div class="table-responsive">
                            <table class="table align-middle table-nowrap mb-0">
                                <thead class="table-light">
                                    <tr>
                                        <th class="align-middle">프로젝트</th>
                                        <th class="align-middle">상태</th>
                                        <th class="align-middle">PROGRESS</th>
                                        <th class="align-middle">시작일</th>
                                        <th class="align-middle">종료일</th>
                                        <th class="align-middle">우선순위</th>
                                        <th class="align-middle">참여자</th>
                                        <th class="align-middle">관리자</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                       <!-- <td><a href="javascript: void(0);" class="text-body fw-bold">로고</a> </td> --> 
                                        <td>프로젝트1</td>
                                        <td>
                                            진행 중
                                        </td>
                                        <td>
                                            0%
                                        </td>
                                        <td>
                                            2023-02-13
                                        </td>
                                        <td>
                                           2023-02-15
                                        </td>
                                        <td>1
                                        </td>
                                         </td>
                                        <td>멤버1 외 2인
                                        </td>
                                         </td>
                                        <td>scott
                                        </td>
                                    </tr>

                                    <tr>
                                       <!-- <td><a href="javascript: void(0);" class="text-body fw-bold">로고</a> </td> --> 
                                        <td>프로젝트2</td>
                                        <td>
                                            진행 중
                                        </td>
                                        <td>
                                            15%
                                        </td>
                                        <td>
                                            2023-02-15
                                        </td>
                                        <td>
                                           2023-02-28
                                        </td>
                                        <td>2
                                        </td>
                                         </td>
                                        <td>아이유 외 2인
                                        </td>
                                         </td>
                                        <td>나사원
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                       <!-- <td><a href="javascript: void(0);" class="text-body fw-bold">로고</a> </td> --> 
                                        <td>프로젝트3</td>
                                        <td>
                                            완료
                                        </td>
                                        <td>
                                            100%
                                        </td>
                                        <td>
                                            2023-02-13
                                        </td>
                                        <td>
                                           2023-02-13
                                        </td>
                                        <td>없음
                                        </td>
                                         </td>
                                        <td>멤버4
                                        </td>
                                         </td>
                                        <td>나사장
                                        </td>
                                    </tr>
                                    <tr>
                                       <!-- <td><a href="javascript: void(0);" class="text-body fw-bold">로고</a> </td> --> 
                                        <td>프로젝트4</td>
                                        <td>
                                            진행예정
                                        </td>
                                        <td>
                                            0%
                                        </td>
                                        <td>
                                            2023-03-14
                                        </td>
                                        <td>
                                           2023-04-15
                                        </td>
                                        <td>없음
                                        </td>
                                         </td>
                                        <td>멤버3 외 5인
                                        </td>
                                         </td>
                                        <td>나선임
                                        </td>
                                    </tr>
                                    <tr>
                                       <!-- <td><a href="javascript: void(0);" class="text-body fw-bold">로고</a> </td> --> 
                                        <td>프로젝트5</td>
                                        <td>
                                            진행예정
                                        </td>
                                        <td>
                                            0%
                                        </td>
                                        <td>
                                            2023-04-13
                                        </td>
                                        <td>
                                           2023-05-13
                                        </td>
                                        <td>없음
                                        </td>
                                         </td>
                                        <td>멤버2외 4인
                                        </td>
                                         </td>
                                        <td>나팀장
                                        </td>
                                    </tr>
                                  
                                </tbody>
                            </table>
                        </div>
                        <!-- end table-responsive -->
                    </div>
                </div>
            </div>
        </div>
        <!-- end row -->
    </div>
    <!-- container-fluid -->
</div>
   
<!-- apexcharts -->

<jsp:include page="/main/footer.jsp"></jsp:include>
<script src="assets/libs/apexcharts/apexcharts.min.js"></script>
<script src="assets/js/pages/dashboard.init.js"></script>
</html>
