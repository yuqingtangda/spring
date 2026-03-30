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
         <table>
             <tr>
                <th>선택</th>
                <th>아이디</th>
                <th>이름</th>
                <th>성별</th>
                <th>삭제</th>
                
             </tr>
             <tr v-for="item in list">
                  <td>
                    <input type="radio" name="user" v-model="selectUserId" :value="item.userId">
                </td>
                <td>{{item.userId}}</td>
                <td>{{item.userName}}</td>
                <td>
                    <span v-if="item.gender== 'M'">남자</span>
                    <span v-else-if="item.gender== 'F'">여자</span>
                    <span v-else>정보 없음</span>
                </td>
                <td>
                    <button @click="fnRemove(item.userId)">삭제</button>
                </td>
             </tr>
         </table>
         <button @click="fnDelete()">삭제</button>
    </div>
</body>
</html>

<script>
    const app = Vue.createApp({
        data() {
            return {
                // 변수 - (key : value)
                list : [],
                selectUserId : ""
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnGetList: function () {
                let self = this;
                let param = {};
                $.ajax({
                    url: "http://localhost:8080/user/list.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        console.log(data);
                        self.list = data.list;
                    }
                });
            },
             fnRemove: function (userId) {
                let self = this;
                if(!confirm("삭제 할래?")){
                    return;
                }
                let param = {
                    userId : userId
                };
                $.ajax({
                    url: "http://localhost:8080/user/remove.dox",
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
                    userId : self.selectUserId
                };
                $.ajax({
                    url: "http://localhost:8080/user/remove.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        alert(data.message);
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