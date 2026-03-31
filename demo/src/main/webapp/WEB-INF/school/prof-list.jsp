<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
    <script src="/js/page-change.js"></script>
    <style>
        table, tr, td, th{
            border : 1px solid black;
            border-collapse: collapse;
            padding : 5px 10px;
            text-align: center;
        }
        th{
            background-color: beige;
        }
        tr:nth-child(even){
            background-color: azure;
        }
    </style>
</head>
<body>
    <div id="app">
        <!-- html 코드는 id가 app인 태그 안에서 작업 -->
          <div id="container">
            <div class="search-area">
                <label>
                    포지션 :
                    <select v-model="position" @change="fnGetList">
                        <option value="">:: 전체 ::</option>
                        <option value="정교수">정교수</option>
                        <option value="조교수">조교수</option>
                        <option value="전임강사">전임강사</option>
                    </select>
                </label>
                <label>
                    학과 : 
                    <select v-model="deptNo" @change="fnGetList">
                        <option value="">:: 전체 ::</option>
                        <option v-for="item in deptList" :value="item.deptNo">{{item.dName}}</option>
                    </select>
                </label>
                </div>
                <div class="table-area">
          <table>
             <tr>
                <th>선택</th>
                <th>번호</th>
                <th>이름</th>
                <th>포지션</th>
                <th>급여</th>
                <th>학부</th>
                <th>학과</th>
                <th>삭제</th>               
             </tr>
             <tr v-for="item in list">
                 <td>
                    <input type="radio" name="prof" v-model="selectprofNo" :value="item.profNo">
                </td>
                <td>{{item.profNo}}</td>
                <td>{{item.name}}</td>
                <td>{{item.position}}</td>
                <td>{{item.pay}}</td>
                <td>{{item.dName2}}</td>
                <td>{{item.dName3}}</td>
                <td><button @click="fnRemove(item.profNo)">삭제</button></td>
            </tr>
         </table>
          <div class="btn-area">
              <a href="/prof/add.do"><button>교수 추가</button></a>
              <button @click="fnDelete()">삭제</button>
        </div>
         </div>
    </div>
    </div>
</body>
</html>

<script>
    const app = Vue.createApp({
        data() {
            return {
                // 변수 - (key : value)
                list : [],
                position : "",
                deptList : [],
                deptNo : "",
                selectprofNo : ""
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnGetList: function () {
                let self = this;
                let param = {
                    position : self.position,
                    deptNo : self.deptNo
                };
                $.ajax({
                    url: "http://localhost:8080/prof/list.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        console.log(data);
                        self.list = data.list;
                        self.deptList = data.deptList;
                    }
                });
            },
             fnRemove: function (profNo) {
                let self = this;
                let param = {
                    profNo : profNo
                };
                $.ajax({
                    url: "http://localhost:8080/prof/remove.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        alert(data.message);
                        self.fnGetList();
                    }
                });
            },
            fnDelete: function () {
                let self = this;
                 if(!confirm("삭제 할래?")){
                    return;
                }
                let param = {
                   profNo : self.selectprofNo
                };
                $.ajax({
                    url: "http://localhost:8080/user/remove.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        alert(data.message);
                        self.selectprofNo = "";
                        self.fnGetList();
                    }
                });
            }
        }, // methods
        mounted() {
            // 처음 시작할 때 실행되는 부분
            let self = this;   
            self.fnGetList();
        }
    });

    app.mount('#app');
</script>