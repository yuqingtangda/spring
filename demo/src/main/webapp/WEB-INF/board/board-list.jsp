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
         <div>
            <div>
                검색어 : <input v-model="keyword">
                <button @click="fnGetList">검색</button>
            </div>
           <table>
             <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>조회수</th>
                <th>작성일</th> 
             </tr>
             <tr v-for="item in list">
                <td>{{item.boardNo}}</td>
                <td><a href="javascript:;" @click="fnView(item.boardNo)">{{item.title}}</a></td>
                <td>{{item.userId}}</td>
                <td>{{item.cnt}}</td>
                <td>{{item.cDateTime}}</td>
             </tr>
            </table>
            </div>
            <div>
                <a href="/board/add.do"><button>글쓰기</button></a>
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
                keyword : ""
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnGetList : function () {
                let self = this;
                let param = {
                    keyword : self.keyword
                };
                $.ajax({
                    url: "http://localhost:8080/board/list.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        console.log(data);
                        self.list = data.list;
                    }
                });
            },
            fnView : function(boardNo){
                // alert(boardNo);
                pageChange("/board/view.do",{boardNo : boardNo});

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