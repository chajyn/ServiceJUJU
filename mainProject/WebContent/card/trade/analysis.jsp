<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>

<meta charset="utf-8">
<!-- CSS Reset -->

<link rel="stylesheet" type="text/css" href="../../css/normalize.css">
<!-- progress bar -->
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<!-- download fontawesome.com -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- ref analysis.css -->
<link rel="stylesheet" href="../../css/trade/analysis.css">

</head>
<body>
<div id="analysis-container">
	<!-- --------------- page-top -------------- -->
	<header class="page-top">
		<div id="stockName">
			<div>기현 알고리즘</div>
			<div>204,000</div>
		</div>
	</header>

	<!-- --------------- page-mid -------------- -->
	<section class="page-mid">
		<div class="page-left-chart">차트1</div>
		<div class="page-right-chart">
		 <!--  <div class="progress">
	            <div class="progress-bar progress-red" style="width: 80%;">
	            <span class="progress-type">80%</span>
	            </div>
	        </div>		       	
	        <div class="progress">
	            <div class="progress-bar progress-red" style="width: 30%">
	            <span class="progress-type">30%</span>
	            </div>
	        </div>
	        <div class="progress">
	            <div class="progress-bar progress-blue" style="width: 60%">
	            <span class="progress-type">60%</span>
	            </div>
	        </div>	
	        <div class="progress">
	            <div class="progress-bar progress-blue" style="width: 60%">
	            <span class="progress-type">60%</span>
	            </div>
	        </div>
	        -->		        	        
		</div>
	</section>

	<!-- --------------- page-bottom -------------- -->
	<section class="page-bottom">
		<div id="comment">분석 내용 </div>
		<div id="chart-bottom">
	        <div class="left-content">
	        	<div>관심도<i class="fa fa-question-circle fa-lg" aria-hidden="true"></i></div> 
	        	<div>재무상황<i class="fa fa-question-circle fa-lg" aria-hidden="true"></i></div>
	        	<div>미정<i class="fa fa-question-circle fa-lg" aria-hidden="true"></i></div>
	        </div>
	        <div class="mid-content">
			    <div class="progress">
		            <div class="progress-bar progress-red" style="width: 80%;">
		            <span class="progress-type">80%</span>
		            </div>
		        </div>		       	
		        <div class="progress">
		            <div class="progress-bar progress-red" style="width: 30%">
		            <span class="progress-type">30%</span>
		            </div>
		        </div>
		        <div class="progress">
		            <div class="progress-bar progress-blue" style="width: 60%">
		            <span class="progress-type">60%</span>
		            </div>
		        </div>		        
	        </div>
	        <div class="right-content">
	        	<div><i class="fa fa-arrow-up fa-2x" aria-hidden="true"></i></div>
	        	<div><i class="fa fa-arrow-down fa-2x" aria-hidden="true"></i></div>
	        	<div><i class="fa fa-arrow-down fa-2x" aria-hidden="true"></i></div>
	        </div>
		</div>
	</section>
	
</div>
</body>
</html>