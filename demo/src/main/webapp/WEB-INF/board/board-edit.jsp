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
    <style>
        body{
            box-sizing: border-box;
        }
        #container{
            width : 800px;
            margin : 50px auto;
        }
        .contents{
            width : 750px;
        }
        table, tr, td, th{
            border : 1px solid black;
            border-collapse: collapse;
            padding : 5px 10px;
            text-align: center;
        }
        th{
            background-color: beige;
        }
        table{
            width : 100%;
        }
        th{
            width : 20%;
        }
        td{
            text-align: left;
        }
        select{
            height : 30px;
            width : 100px;
        }
        .title{
            height: 30px;
            width : 90%;
        }
        .btn-area{
            text-align: center;
            margin-top: 20px;
        }
        button{
            height: 40px;
            width : 125px;
            margin: 5px;
            background-color: #e1f5f7;
            border-radius: 10px;
            border: 1px solid #ababab;
            box-shadow: 2px 2px 2px black;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div id="app">
        <!-- html 코드는 id가 app인 태그 안에서 작업 -->
         <div id="container">
            <div class="contents">
         <table>
            <tr>
                <th>게시판</th>
                <td>
                    <select v-model="info.kind">
                        <option value="1">공지사항</option>
                        <option value="2">자유게시판</option>
                        <option value="3">문의게시판</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>제목</th>
                <td>
                    <input v-model="info.title" class="title">
                </td>
            </tr>
             <tr>
                <th>내용</th>
                <td>
                    <textarea v-model="info.contents" cols="75" rows="10"></textarea>
                </td>
            </tr>

         </table>
         <div class="btn-area">
            <button @click="fnEdit">수정</button>
            <button>되돌아가기</button>
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
                boardNo : "${boardNo}",
                info : {
                    kind : "1",
                    title : "",
                    contents : ""
                }
               
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnGetBoard: function () {
                let self = this;
                let param = {
                    boardNo : self.boardNo,
                    kind : "edit"
                };
                $.ajax({
                    url: "http://localhost:8080/board/info.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        self.info = data.info;

                    }
                });
            },
            fnEdit : function(){
                let self = this;
                let param = self.info;
                self.info.boardNo = self.boardNo;
                $.ajax({
                    url: "http://localhost:8080/board/edit.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        alert(data.message);
                        location.href="/board/list.do";
                    }
                });
            }
        }, // methods
        mounted() {
            // 처음 시작할 때 실행되는 부분
            let self = this;
            self.fnGetBoard();
        }
    });

    app.mount('#app');
</script>