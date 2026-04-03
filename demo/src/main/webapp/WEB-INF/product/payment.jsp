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
    <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
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
            <input placeholder="가격" v-model="price">
            <button @click="fnPayment">결제</button>
         </div>
    </div>
</body>
</html>

<script>
    IMP.init("imp57208741");
    const app = Vue.createApp({
        data() {
            return {
                // 변수 - (key : value)
                price : 1
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnPayment: function () {
               IMP.request_pay(
  {
    channelKey: "channel-key-fc2acc35-72ac-4185-a8da-0c1570f1fde4",
    pay_method: "card",
    merchant_uid:new Date().toLocaleString(), // 주문 고유 번호
    name: "라면",
    amount: 1,
    buyer_email: "gildong@gmail.com",
    buyer_name: "홍길동",
    buyer_tel: "010-4242-4242",
    buyer_addr: "서울특별시 강남구 신사동",
    buyer_postcode: "01181",
  },
  function (response) {
    console.log(response);
    if(response.success){
        alert("결제되었습니다.");
        //우리쪽 db에 결제 정보 저장
        //페이지 이동 필요하면 페이지 이동
    } else {
        alert("결제 실패!")
    }
  },
);
            }
        }, // methods
        mounted() {
            // 처음 시작할 때 실행되는 부분
            let self = this;
        }
    });

    app.mount('#app');
</script>