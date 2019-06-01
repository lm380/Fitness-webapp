$(document).ready(function(){
$('.item header').click(function(){
  var c = $(this).next();
  if($(this).hasClass('expanded')) {
    c.animate({
      'max-height': 0,
      'margin-top': '-1.4em'
    }, 1000);
  }
  else {
    c.animate({
      'max-height': '800px',
      'margin-top': 0
    }, 1000);
  }
  $(this).toggleClass('expanded');
});
});
