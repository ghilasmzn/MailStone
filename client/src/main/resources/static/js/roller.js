function startTimer(duration, display) {
	var timer = duration;
	var minutes, seconds;
	var warning = 60;
	var ok=false;
	var form=document.forms.timer;
	
	var t=setInterval(function() {
        if (--timer <= 0) {
			timer = duration;

			if( !isNaN( t ) )clearInterval( t );
			
			// submit the form...
			form.submit();
		}
	}, 1000);
}

window.onload = function() {
	var examTime = 3;
	var display = document.querySelector('#time');
	startTimer(examTime, display);
};