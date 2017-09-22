//func bar toogle
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

//odd row change side
$(document).ready(function(){
  $('.row.mgb30.mgt20:odd').children('div:first-child').addClass('col-sm-push-6')
  $('.row.mgb30.mgt20:odd').children('div:last-child').addClass('col-sm-pull-6')
});

// scrollbar
jQuery(document).ready(function(){
    jQuery('.scrollbar-macosx').scrollbar();
});


//$(document).ready(function () {
// $("a[href='#prod${param.prodNo}']").click();
//// });