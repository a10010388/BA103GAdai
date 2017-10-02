//func bar toogle
//0926失效
var scrollPos = 0;
window.onscroll = function() {myFunction()};

function myFunction() {
  if (document.body.scrollTop < 1) {
    $("#funcbar").stop(true);
        $("#funcbar").slideDown("300");
        scrollPos = document.body.scrollTop;
        return;
  }
    if ((document.body.scrollTop-scrollPos) >= 0) {
      $("#funcbar").stop(true);
        $("#funcbar").slideUp("300");
        scrollPos = document.body.scrollTop;
    } else {
      $("#funcbar").stop(true);
        $("#funcbar").slideDown("300");
        scrollPos = document.body.scrollTop;
    }
}

//modal closed toTop
$('#modal-id').on('hide.bs.modal', function (e) {
	$modalX.scrollTop(0);
	return;
});

//bank or credit choose one only
$(document).ready(function(){
	$('.payWay').blur(function(){
		if($(this).attr('id')=='bankAc'){
			//bank
			if($('#bankAc').val()!=''){
				$('.card').attr('readonly', true);
			} else {
				$('.card').attr('readonly', false);
			}
			return;

		} else {
			//credit
			var $card = $($('input.card'));
			for(var i = 0; i<$card.length; i++){
				if($($card[i]).val()!=''){
					$('.atm').attr('readonly', true);
					return;
				}
				$('.atm').attr('readonly', false);
			}
		}
	});
});
//pay from check
$('#modal-pay :submit').click(function(){
	console.log('333');
	var isNG=false;
	if(!$('.card').attr('readonly')&&!$('.atm').attr('readonly')){
		var $point = $('#bankAc');
		$point.attr("data-toggle","tooltip");
		$point.attr("data-placement","top");
		$point.attr("data-original-title","請填入付款資訊");
		isNG=true;	
	} else {
		var $point = $('#bankAc');
		cleanAttr($point);
	}
	
	if($('.card').attr('readonly')){
		cleanAttr($('#crdNo1'));
		cleanAttr($('#crdNo2'));
		cleanAttr($('#crdNo3'));
		cleanAttr($('#crdNo4'));
		cleanAttr($('#crdChk'));
		cleanAttr($('#crdVal'));
		
		if ($('#bankAc').val().trim().length!=5){
			var $point = $('#bankAc');
			$point.attr("data-toggle","tooltip");
			$point.attr("data-placement","bottom");
			$point.attr("data-original-title","請輸入數字5碼");
			isNG=true;	
		} else {
			var $point = $('#bankAc');
			cleanAttr($point);
		}
	}
			
	if($('.atm').attr('readonly')){
		cleanAttr($('#bankAc'));
		
		if($('#crdNo1').val().trim().length!=4){
			var $point = $('#crdNo1');
			$point.attr("data-toggle","tooltip");
			$point.attr("data-placement","top");
			$point.attr("data-original-title","格式錯誤");
			isNG=true;
		} else {
			var $point = $('#crdNo1');
			cleanAttr($point);
		}
		
		if($('#crdNo2').val().trim().length!=4){
			var $point = $('#crdNo2');
			$point.attr("data-toggle","tooltip");
			$point.attr("data-placement","top");
			$point.attr("data-original-title","格式錯誤");
			isNG=true;
		} else {
			var $point = $('#crdNo2');
			cleanAttr($point);
		}
		
		if($('#crdNo3').val().trim().length!=4){
			var $point = $('#crdNo3');
			$point.attr("data-toggle","tooltip");
			$point.attr("data-placement","top");
			$point.attr("data-original-title","格式錯誤");
			isNG=true;
		} else {
			var $point = $('#crdNo3');
			cleanAttr($point);
		}
		
		if($('#crdNo4').val().trim().length!=4){
			var $point = $('#crdNo4');
			$point.attr("data-toggle","tooltip");
			$point.attr("data-placement","top");
			$point.attr("data-original-title","格式錯誤");
			isNG=true;
		} else {
			var $point = $('#crdNo4');
			cleanAttr($point);
		}
		
		if($('#crdChk').val().trim().length!=3){
			var $point = $('#crdChk');
			$point.attr("data-toggle","tooltip");
			$point.attr("data-placement","bottom");
			$point.attr("data-original-title","格式錯誤");
			isNG=true;
		} else {
			var $point = $('#crdChk');
			cleanAttr($point);
		}
		
		if($('#crdVal').val().trim()==''){
			var $point = $('#crdVal');
			$point.attr("data-toggle","tooltip");
			$point.attr("data-placement","bottom");
			$point.attr("data-original-title","格式錯誤");
			isNG=true;
		} else {
			var $point = $('#crdVal');
			cleanAttr($point);
		}

	}
	if(isNG){
		$("[data-toggle='tooltip']").tooltip('show'); 
		return false;
	}
});

//modal-Pay closed Clean
$('#modal-pay').on('hide.bs.modal', function (e) {
	$('#modal-pay :text').each(function(){$(this).val('')});
	$('#crdVal').val('');
	$('.card').attr('readonly', false);
	$('.atm').attr('readonly', false);
	cleanAttr($('#bankAc'));
	cleanAttr($('#crdNo1'));
	cleanAttr($('#crdNo2'));
	cleanAttr($('#crdNo3'));
	cleanAttr($('#crdNo4'));
	cleanAttr($('#crdChk'));
	cleanAttr($('#crdVal'));
	return;
});
function cleanAttr(node){
	node.removeAttr("data-toggle");
	node.removeAttr("data-placement");
	node.removeAttr("data-original-title");
}
