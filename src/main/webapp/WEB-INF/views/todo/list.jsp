<%@include file="/WEB-INF/views/includes/header.jsp"%>
<style>
    .table tbody tr{
        cursor: pointer;
    }

    .table tbody tr:hover{
        background-color: beige;
    }
</style>

<div class="container-fluid px-4">
    <h1 class="mt-4">TODO
        <c:if test="${loginResult != null}">
            <button class="btn btn-secondary" onclick="location.href='/logout'">Logout</button>
        </c:if>
    </h1>
    <div class="card mb-4">
        <div class="card-header">
            <i class="fas fa-table me-1"></i>
            TODO LIST
            <a href="/todo/add"><button type="button" class="btn btn-primary float-end" aria-disabled="true">Add</button></a>
        </div>
        <div class="card-body">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">Title</th>
                        <th scope="col">Due Date</th>
                        <th scope="col">Complete</th>
                    </tr>
                </thead>
                <tbody>
                    <%--item 으로 java 데이터를 받아서 var에다가 변수명을 붙여준다.--%>
                    <c:forEach items="${dtoList}" var="todo">
                        <tr class="cursor-pointer" onclick="location.href='/todo/detail?page=${pageDTO.pageRequestDTO.page}&size=${pageDTO.pageRequestDTO.size}&tno=${todo.tno}'">
                            <th scope="row">${todo.tno} </th>
                            <!--<td><a href="/todo/detail?tno=${todo.tno}" style="text-decoration: none; color: black;"><c:out value="${todo.title}"/></a></td>-->
                            <td><c:out value="${todo.title}"/></td>
                            <td>${todo.dueDate} </td>
                            <td>${todo.complete ? "YES" : "NOT YET"} </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="4">
                            <nav aria-label="Page navigation">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item <c:if test='${!pageDTO.prev}'>disabled</c:if>">
                                        <a class="page-link" href="/todo/list?page=${pageDTO.start - 1}">Prev</a>
                                    </li>
                                    <c:forEach begin="${pageDTO.start}" end="${pageDTO.end}" var="num">
                                        <li class="page-item <c:if test="${pageDTO.pageRequestDTO.page == num}">active</c:if>">
                                            <a class="page-link" href="/todo/list?page=${num}&size=${pageDTO.pageRequestDTO.size}">${num}</a>
                                        </li>
                                    </c:forEach>
                                    <li class="page-item <c:if test='${!pageDTO.next}'>disabled</c:if>">
                                        <a class="page-link" href="/todo/list?page=${pageDTO.end  + 1}">Next</a>
                                    </li>
                                </ul>
                            </nav>
                        </td>
                    </tr>
                </tfoot>
            </table>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                ${work} is ${result == 1 ? "success" : "fail"}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <%--                <button type="button" class="btn btn-primary">Save changes</button>--%>
            </div>
        </div>
    </div>
</div>
<script>
    let myModal = new bootstrap.Modal(document.querySelector('#exampleModal'));
    let work = "${work}";
    let result = "${result}";

    if(result == 1){
        myModal.show();
        //모달 출력 후 url 파라미터 값 제거
        history.replaceState({}, null, "?page=${pageDTO.pageRequestDTO.page}&size=${pageDTO.pageRequestDTO.size}");
    }
</script>


<%@include file="/WEB-INF/views/includes/footer.jsp"%>
