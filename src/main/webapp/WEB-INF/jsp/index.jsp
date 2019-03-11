<%@ page import="com.hihds.robohash4j.*;" %>
<%
String ip = (String) request.getAttribute("ip");
String robo = (String) request.getAttribute("robo");
String[] drquote1 = (String[]) request.getAttribute("drquote1");
String[] drquote2 = (String[]) request.getAttribute("drquote2");
String[] quotes = (String[]) request.getAttribute("quotes");
String[] catquotes = (String[]) request.getAttribute("catquotes");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Robohash4j</title>
<link href="/css/main.css" rel="stylesheet" type="text/css" />
<link href="/css/prettyPhoto.css" rel="stylesheet" type="text/css" />


<link rel="shortcut icon" type="image/x-icon" href="/favicon.ico">
<!-- JavaScript Files -->

<script src="/js/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="/js/jquery.prettyPhoto.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/twitter.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/tipsy.js" type="text/javascript" charset="utf-8"></script>


<!-- *************************************************************************
*********************  THIS IS THE PRETTY PHOTO JS  **************************
************************************************************************** -->

<script type="text/javascript" charset="utf-8">
		$(document).ready(function(){
			$("#wrapper a[rel^='prettyPhoto']").prettyPhoto({animationSpeed:'slow',theme:'facebook',slideshow:5000, autoplay_slideshow: true});
		});
		</script>



<!-- *************************************************************************
*************************  SCROLL TO TOP SCRIPT  *****************************
************************************************************************** -->

<script type='text/javascript'>
$(document).ready(function(){
	$(".scroll").click(function(event){
		//prevent the default action for the click event
		event.preventDefault();

		//get the full url - like mysitecom/index.htm#home
		var full_url = this.href;

		//split the url by # and get the anchor target name - home in mysitecom/index.htm#home
		var parts = full_url.split("#");
		var trgt = parts[1];

		//get the top offset of the target anchor
		var target_offset = $("#"+trgt).offset();
		var target_top = target_offset.top;

		//goto that anchor by setting the body scroll top to anchor top
		$('html, body').animate({scrollTop:target_top}, 800);
	});
});
</script>


</head>

<body>

<!-- *************************************************************************
*************************  TIPSY SCRIPT LEAVE HERE!  *************************
************************************************************************** -->

      <script type='text/javascript'>
  $(function() {

    $('#example-1').tipsy();

    $('#north').tipsy({gravity: 'n'});
    $('#south').tipsy({gravity: 's'});
    $('#east').tipsy({gravity: 'e'});
    $('#west').tipsy({gravity: 'w'});

    $('#auto-gravity').tipsy({gravity: $.fn.tipsy.autoNS});

    $('.fade').tipsy({fade: true});

    $('#example-custom-attribute').tipsy({title: 'id'});
    $('#example-callback').tipsy({title: function() { return this.getAttribute('original-title').toUpperCase(); } });
    $('#example-fallback').tipsy({fallback: "?" });

    $('#example-html').tipsy({html: true });

  });
</script>

<!-- *************************************************************************
******************************  HEADER SECTION  ******************************
************************************************************************** -->

<!-- start of header full width-->
<div id="header_fullwidth">

<!-- start of header-->
<div id="header">

<!-- start of top logo -->
<div id="top_logo">


  <img src="/img/top_logo.png" width="375" height="151" alt="logo" id="toc"/></div><!-- end of top logo -->

</div><!-- end of header-->

</div><!-- end of header full width-->


<!-- *************************************************************************
******************************  MAIN PAGE WRAPPER  ***************************
************************************************************************** -->

<!-- start of page wrapper -->
<div id="wrapper">

<!-- *************************************************************************
******************************  INTRO SECTION  *******************************
************************************************************************** -->

<!-- start of content section -->
<div class="intro_section">

<h1>Generate Unique images from any text </h1>

<p class="intro_text">Robohash is a easy web service that makes it easy to provide unique, robot/alien/monster/whatever images for any text.<br>
 Put in any text, such as IP address, email, filename, userid, or whatever else you like, and get back a pretty image for your site. <br><br> With hundreds of millions of variations, Robohash is the among the leading robot-based hashing tools on the web. </p>

</div><!-- end of content section -->




<!-- *************************************************************************
******************************  IPAD SECTION  ******************************
************************************************************************** -->

<!-- start of content section -->
<div class="content_section">

<!-- start of left content section -->
<div class="left_content" style="position:relative; top:40px">

  <img src="/image/<%=ip%>.png" alt="You!" class="left" id="idimg" /><br>
  <p id='YourIP'><br>https://robohash.org/image/<%=ip%>.png</p>
</div><!-- end of left content section -->

<!-- start of right content section -->
<div class="right_content">

 <div class="section_title">How cool is this?</div>
 <p> That guy to your left there? He was specially generated from your IP address  <i>Just for you.</i></p>
<div class="container" >
	<div class="contain-right" style="width:60%; float:right;">
		<p>Try on your phone, and I bet you get someone different!</p>
  		<p>Keep scrolling down to see some more freshly-assembled RoboHashes.</p>




<script type="text/javascript">

function stopRKey(evt) {
  var evt = (evt) ? evt : ((event) ? event : null);
  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
  if ((evt.keyCode == 13) && (node.type=="text"))  {submitform(); return false;}
}

document.onkeypress = stopRKey;

</script>

<form id="textform" action="">
<p>Enter any text, and press 'generate': </p><input type='text' name='query' onKeyPress="��return disableEnterKey(event);"�� />
<a href="javascript:;" onclick="submitform();"> generate</a>
</form>
<script type="text/javascript">
function submitform()
{
   var x = document.getElementById("idimg");
   var val = document.getElementById("textform").query.value + ".png";
   x.setAttribute("src", "/image/" + val );
   $("#YourIP").text( "https://robohash.org/image/" + val );
}
</script>



	</div>
	<div class="contain-left" >
  		<img src="/img/down-arrow.png" alt="Scroll down!" height=200 class="left" />
	</div>
</div>


</div><!-- end of right content section -->

</div><!-- end of content section -->

<div class="clear"></div>



<!-- *************************************************************************
******************************  QUOTE SECTION  *******************************
************************************************************************** -->

<!-- start of quotes wrapper -->
<div class="quotes_wrapper">

<!-- start of left quote -->
<div class="left_quote">
  <img src="/img/star.png" width="33" height="31" alt="1 star" />
  <img src="/img/star.png" width="33" height="31" alt="1 star" />
  <img src="/img/star.png" width="33" height="31" alt="1 star" />
  <img src="/img/star.png" width="33" height="31" alt="1 star" />
  <img src="/img/star.png" width="33" height="31" alt="1 star" />
  <br />
  <span class="large_quote"><%=drquote1[0]%></span>
  <br />
  <span class="small_quote"><%=drquote1[1]%></span>
</div><!-- end of left quote -->

<!-- start of right quote -->
<div class="right_quote">
  <img src="/img/indented_star.png" width="27" height="25" alt="1 star" />
  <img src="/img/indented_star.png" width="27" height="25" alt="1 star" />
  <img src="/img/indented_star.png" width="27" height="25" alt="1 star" />
  <img src="/img/indented_star.png" width="27" height="25" alt="1 star" />
  <img src="/img/indented_star.png" width="27" height="25" alt="1 star" />
  <br />
  <span class="large_quote"><%=drquote2[0]%></span>
  <br />
  <span class="small_quote"><%=drquote2[1]%></span>
</div><!-- end of right quote -->
</div><!-- end of quotes wrapper -->


<div class="hr"></div>


<!-- *************************************************************************
******************************  GALLERY SECTION  *****************************
************************************************************************** -->

<!-- start of content section -->
<div class="content_section">

 <div class="section_title">Here are five Robots, Randomly generated, Just for you!</div>

  <ul class="gallery">
			<% String c1 = Utils.getRandomString(3) + ".png?set=set1"; %>
			<% String c2 = Utils.getRandomString(3) + ".png?set=set1"; %>
			<% String c3 = Utils.getRandomString(3) + ".png?set=set1"; %>
			<% String c4 = Utils.getRandomString(3) + ".png?set=set1"; %>
			<% String c5 =  Utils.getRandomString(3) + ".png?set=set1"; %>

			<li><a href="/image/<%=c1%>" rel="prettyPhoto[gallery1]"
            title="<%=Utils.choice(quotes)%>" class="fade"><img src="/image/<%=c1%>&size=150x150" width="150" height="150"/></a></li>
			<li><a href="/image/<%=c2%>" rel="prettyPhoto[gallery1]"
            title="<%=Utils.choice(quotes)%>" class="fade"><img src="/image/<%=c2%>&size=150x150" width="150" height="150" /></a></li>
            <li><a href="/image/<%=c3%>" rel="prettyPhoto[gallery1]"
            title="<%=Utils.choice(quotes)%>" class="fade"><img src="/image/<%=c3%>&size=150x150" width="150" height="150" /></a></li>
            <li><a href="/image/<%=c4%>" rel="prettyPhoto[gallery1]"
            title="<%=Utils.choice(quotes)%>" class="fade"><img src="/image/<%=c4%>&size=150x150" width="150" height="150" /></a></li>
            <li><a href="/image/<%=c5%>" rel="prettyPhoto[gallery1]" title="<%=Utils.choice(quotes)%>" class="fade"><img
            src="/image/<%=c5%>&size=150x150" width="150" height="150" /></a></li>
	</ul>

</div><!-- end of content section -->

<div class="hr"></div>


<!-- *************************************************************************
******************************  GALLERY2 SECTION  *****************************
************************************************************************** -->

<!-- start of content section -->
<div class="content_section">

  <p>By appending ?set=set2 to our Image's URLs, we are able to generate a whole slew of Random monsters. Here are 5 we picked out for you.  </p>

  <ul class="gallery">
			<% c1 = Utils.getRandomString(3) + ".png?set=set2"; %>
			<% c2 = Utils.getRandomString(3) + ".png?set=set2"; %>
			<% c3 = Utils.getRandomString(3) + ".png?set=set2"; %>
			<% c4 = Utils.getRandomString(3) + ".png?set=set2"; %>
			<% c5 = Utils.getRandomString(3) + ".png?set=set2"; %>

			<li><a href="/image/<%=c1%>" rel="prettyPhoto[gallery1]"
            title="<%=Utils.choice(quotes)%>" class="fade"><img src="/image/<%=c1%>&size=150x150" width="150" height="150" /></a></li>
			<li><a href="/image/<%=c2%>" rel="prettyPhoto[gallery1]"
            title="<%=Utils.choice(quotes)%>" class="fade"><img src="/image/<%=c2%>&size=150x150" width="150" height="150" /></a></li>
            <li><a href="/image/<%=c3%>" rel="prettyPhoto[gallery1]"
            title="<%=Utils.choice(quotes)%>" class="fade"><img src="/image/<%=c3%>&size=150x150" width="150" height="150" /></a></li>
            <li><a href="/image/<%=c4%>" rel="prettyPhoto[gallery1]"
            title="<%=Utils.choice(quotes)%>" class="fade"><img src="/image/<%=c4%>&size=150x150" width="150" height="150" /></a></li>
            <li><a href="/image/<%=c5%>" rel="prettyPhoto[gallery1]" title="<%=Utils.choice(quotes)%>" class="fade"><img
            src="/image/<%=c5%>&size=150x150" width="150" height="150" /></a></li>
	</ul>

</div><!-- end of content section -->

<div class="hr"></div>


<!-- *************************************************************************
******************************  GALLERY3 SECTION  *****************************
************************************************************************** -->

<!-- start of content section -->
<div class="content_section">

  <p>By appending ?set=set3 to our URLs, we get back to robots. New, suave, disembodied heads. That's sexy. Like a robot. </p>

  <ul class="gallery">
			<% c1 = Utils.getRandomString(3) + ".png?set=set3"; %>
			<% c2 = Utils.getRandomString(3) + ".png?set=set3"; %>
			<% c3 = Utils.getRandomString(3) + ".png?set=set3"; %>
			<% c4 = Utils.getRandomString(3) + ".png?set=set3"; %>
			<% c5 = Utils.getRandomString(3) + ".png?set=set3"; %>

			<li><a href="/image/<%=c1%>" rel="prettyPhoto[gallery1]"
            title="<%=Utils.choice(quotes)%>" class="fade"><img src="/image/<%=c1%>&size=150x150" width="150" height="150" /></a></li>
			<li><a href="/image/<%=c2%>" rel="prettyPhoto[gallery1]"
            title="<%=Utils.choice(quotes)%>" class="fade"><img src="/image/<%=c2%>&size=150x150" width="150" height="150" /></a></li>
            <li><a href="/image/<%=c3%>" rel="prettyPhoto[gallery1]"
            title="<%=Utils.choice(quotes)%>" class="fade"><img src="/image/<%=c3%>&size=150x150" width="150" height="150" /></a></li>
            <li><a href="/image/<%=c4%>" rel="prettyPhoto[gallery1]"
            title="<%=Utils.choice(quotes)%>" class="fade"><img src="/image/<%=c4%>&size=150x150" width="150" height="150" /></a></li>
            <li><a href="/image/<%=c5%>" rel="prettyPhoto[gallery1]"
            title="<%=Utils.choice(quotes)%>" class="fade"><img src="/image/<%=c5%>&size=150x150" width="150" height="150" /></a></li>
	</ul>

</div><!-- end of content section -->

<div class="hr"></div>

<!-- *************************************************************************
******************************  GALLERY3 SECTION  *****************************
************************************************************************** -->

<!-- start of content section -->
<div class="content_section">

  <p>By appending ?set=set4 to our URLs, we can hydroponically grow beautiful kittens. </p>

  <ul class="gallery">
      <% c1 = Utils.getRandomString(3) + ".png?set=set4"; %>
      <% c2 = Utils.getRandomString(3) + ".png?set=set4"; %>
      <% c3 = Utils.getRandomString(3) + ".png?set=set4"; %>
      <% c4 = Utils.getRandomString(3) + ".png?set=set4"; %>
      <% c5 = Utils.getRandomString(3) + ".png?set=set4"; %>

      <li><a href="/image/<%=c1%>" rel="prettyPhoto[gallery1]"
            title="<%=Utils.choice(catquotes)%>" class="fade"><img src="/image/<%=c1%>&size=150x150" width="150" height="150" /></a></li>
      <li><a href="/image/<%=c2%>" rel="prettyPhoto[gallery1]"
            title="<%=Utils.choice(catquotes)%>" class="fade"><img src="/image/<%=c2%>&size=150x150" width="150" height="150" /></a></li>
            <li><a href="/image/<%=c3%>" rel="prettyPhoto[gallery1]"
            title="<%=Utils.choice(catquotes)%>" class="fade"><img src="/image/<%=c3%>&size=150x150" width="150" height="150" /></a></li>
            <li><a href="/image/<%=c4%>" rel="prettyPhoto[gallery1]"
            title="<%=Utils.choice(catquotes)%>" class="fade"><img src="/image/<%=c4%>&size=150x150" width="150" height="150" /></a></li>
            <li><a href="/image/<%=c5%>" rel="prettyPhoto[gallery1]"
            title="<%=Utils.choice(catquotes)%>" class="fade"><img src="/image/<%=c5%>&size=150x150" width="150" height="150" /></a></li>
  </ul>

</div><!-- end of content section -->

<div class="hr"></div>



<!-- *************************************************************************
******************************  BENEFITS SECTION  ****************************
************************************************************************** -->

<!-- start of content section -->
<div class="content_section">

 <div class="section_title">Robohashes are awesome.</div>

<!-- start of left content section -->
<div class="left_content">

	<!-- ********** BENEFITS ITEM 1 ********** -->

    <!-- start of benefit item -->
	<div class="benefit">

  		<img src="/img/star_icon.jpg" width="73" height="72" alt="Super Easy to use" class="left"/>

  		<!-- start of benefit right -->
  		<div class="benefit_right">

  			<span class="benefit_header">Super Easy</span>
  			<p>Anytime you need a Robohash, just embed <br> &lt;img src="https://robohash.org/image/YOUR-TEXT.png"&gt;<br></p>

        </div><!-- end of benefit right -->

  	</div><!-- end of benefit item -->



    <!-- ********** BENEFITS ITEM 2 ********** -->

    <!-- start of benefit item -->
	<div class="benefit">

	  <img src="/img/report_icon.jpg" width="73" height="72" alt="Supported Formats" class="left"/>

  		<!-- start of benefit right -->
  		<div class="benefit_right">

  			<span class="benefit_header">Styles of Robot</span>
  			<p>Want a JPG instead? Fine. PNG? Fine. Want it as a bitmap? I think you're nutty. But fine. Just change the
            URL to request in any format you want. Use ?ignoreext=false to make the bots care about extensions.</p>

        </div><!-- end of benefit right -->

  	</div><!-- end of benefit item -->



    <!-- ********** BENEFITS ITEM 3 ********** -->

    <!-- start of benefit item -->
	<div class="benefit">

  		<img src="/img/tut_icon.jpg" width="73" height="72" alt="Very infrequent murderous rampages." class="left"/>

  		<!-- start of benefit right -->
  		<div class="benefit_right">

  			<span class="benefit_header">Very Infrequent Rampages</span>
  			<p>Due to Robot caching modules and CDN usage, our robots stay speedy, and only rarely go on murderous rampages. That's a Fact!</p>

      </div><!-- end of benefit right -->

  	</div><!-- end of benefit item -->


  </div><!-- end of left content section -->



<!-- start of right content section -->
<div class="right_content">

	<!-- ********** BENEFITS ITEM 1 ********** -->

    <!-- start of benefit item -->
	<div class="benefit">

  		<img src="/img/share_icon.jpg" width="73" height="72" alt="3 classes to choose from" class="left"/>

  		<!-- start of benefit right -->
	  <div class="benefit_right">

  			<span class="benefit_header">Built in multiple sizes</span>
  			<p>From destroying skyscrapers to nonobots, we've got you covered. Try appending ?size=200x200</p>
        </div><!-- end of benefit right -->

  	</div><!-- end of benefit item -->

    <!-- ********** BENEFITS ITEM 2 ********** -->

    <!-- start of benefit item -->
	<div class="benefit">

	  <img src="/img/usability_icon.jpg" width="73" height="72" alt="Robots on Vacation" class="left"/>

  		<!-- start of benefit right -->
	  <div class="benefit_right">

  			<span class="benefit_header">Robots at your Location</span>
  			<p>Our robots like to travel. If you append ?bgset=bg1 (or bg2 or any) to your URL, our robots will add a background as part of the hash.</p>

        </div><!-- end of benefit right -->

  	</div><!-- end of benefit item -->

    <!-- ********** BENEFITS ITEM 3 ********** -->

    <!-- start of benefit item -->
	<div class="benefit">

	  <img src="/img/usability_icon.jpg" width="73" height="72" alt="Robotic Values" class="left"/>

  		<!-- start of benefit right -->
	  <div class="benefit_right">

  			<span class="benefit_header">Robotic Value</span>
  			<p>Robohash.org robots believe in Family, Warmth, and killing-all-humans. To that end, we provide this service for free. </p>

        </div><!-- end of benefit right -->

  	</div><!-- end of benefit item -->

  </div><!-- end of right content section -->

</div><!-- end of content section -->

<div class="hr"></div>


	<!-- start of content section -->
	<div class="content_section">

	 <div class="section_title">Everyone needs Robots!</div>
	<p>
	RoboHash.org is here because Robots are funny, and because I needed the algorithm/art anyway for a Super-Awesome new forum I'm working on. If you use a specific set, or a list of them, like "?sets=1,3"  , it'll probably stay the same as it is now. If you use "set=any", it'll include any new sets I happen to add, so existing hashes may change. </p><p>

	You should email me -

	<a href="mailto:colin@robohash.org" class="fade" title="I am probably not a robot.">colin@robohash.org</a>
</p><p>

	If the bandwidth gets crazy, I might add a [Robohash.org] banner to the bottom of the image. But it'd be super-tasteful.<br>
</p>

	</div><!-- end of content section -->


  <!-- start of content section -->
  <div class="content_section">

   <div class="section_title">Advanced classes in Robotry.</div>
  <p>
    For those inclined, there are several advanced techniques which have been added over time.
  </p><p>
    For Gravatar enthusiasts, you can ask Robohash to use a Gravatar if one is available.<br>
    Put either the email, or the hashed version, in your image where the string normally goes -<br><br>
    For example: https://robohash.org/colin@robohash.org?gravatar=yes<br> or https://robohash.org/620050a4db5104bae758cd75171d64ca?gravatar=hashed
    </p><p>
    <p>We've also conditioned our robots to accept commands either via params or directories-<br> For instance https://robohash.org/set_set3/bgset_bg1/3.14159?size=500x500
    <p>Finally, the <a href="https://github.com/e1ven/Robohash">Blueprints are available</a> to build your own robotic
    factory.</p>
</p>

  </div><!-- end of content section -->



	<div class="hr"></div>






	</div><!-- end of wrapper -->
<!-- *************************************************************************
******************************  FOOTER SECTION  ******************************
************************************************************************** -->


<!-- start of footer wrapper -->
<div id="footer_wrapper">

<!-- start of footer container -->
<div id="footer_container">
<!-- start of footer left -->
<div id="footer_left" style="margin-top:60px;">
<p>
Robohash contains robots created by Zikri Kader (set1), Hrvoje Novakovic (set2), and Julian Peter Arias (set3).<br>
Cats are created by <a href="https://framagit.org/Deevad/cat-avatar-generator/tree/master">David Revoy</a>.
<br>
<p>
You are free to embed under the terms of the CC-BY license. <br> Example wording might be "Robots lovingly delivered by
Robohash.org" or something.
</p>
</div><!-- end of footer left -->

<!-- start of footer right -->
<div id="footer_right">

<span class="follow"><a href="http://www.twitter.com/robohash" class="fade" title="Clicking on this will replace your cat with a robot.">Follow Us</a></span>

<div class="tweet"></div>

</div><!-- end of footer right -->

</div><!-- end of footer container -->

</div><!-- end of footer wrapper -->

</body>
</html>
