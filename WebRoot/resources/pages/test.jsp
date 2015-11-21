<title>CSS3圆角气泡框，评论对话框</title>
<script>
  var _gaq = _gaq || []; 	_gaq.push(['_setAccount', 'UA-7489188-1']); _gaq.push(['_trackPageview']);
  (function() {
    var ga = document.createElement('script'); ga.async = true; ga.src = 'http://www.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(ga, s);
  })();
</script>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<link rel="stylesheet" href="<%=basePath%>/resources/css/bootstrap.min.css" />
</head>
<body>
<div id="container">
    <p class="triangle-isosceles">This only needs one HTML element.</p>
    <p class="triangle-isosceles top">For example, <code><p>[text]</p></code>.</p>
    <p class="triangle-isosceles left">But it could be any element you want.</p>
    <p class="triangle-isosceles right">The entire appearance is created only with CSS.</p>
    <p class="triangle-right">This only needs one HTML element.</p>
    <p class="triangle-right top">For example, <code><p>[text]</p></code>.</p>
    <p class="triangle-right left">But it could be any element you want.</p>
    <p class="triangle-right right">The entire appearance is created only with CSS.</p>
    <p class="triangle-obtuse">This only needs one HTML element.</p>
    <p class="triangle-obtuse top">For example, <code><p>[text]</p></code>.</p>
    <p class="triangle-obtuse left">But it could be any element you want.</p>
    <p class="triangle-obtuse right">The entire appearance is created only with CSS.</p>
    <p class="triangle-border">This only needs one HTML element.</p>
    <p class="triangle-border top">For example, <code><p>[text]</p></code>.</p>
    <p class="triangle-border left">But it could be any element you want.</p>
    <p class="triangle-border right">The entire appearance is created only with CSS.</p>
    <h2>Simple examples</h2>
    <h3 class="example-commentheading">125 comments</h3>
    <blockquote class="example-right">
      <p>Design is directed toward human beings. To design is to solve human problems by identifying them and executing the best solution.</p>
    </blockquote>
    <p>Ivan Chermayeff</p>
    <blockquote class="example-obtuse">
      <p>It’s not what you look at that matters, it’s what you see.</p>
    </blockquote>
    <p>Henry David Thoreau</p>
    <blockquote class="example-twitter" cite="http://twitter.com/necolas/status/9880187933">
      <p>Takes me longer to write up blog posts on experiments or projects than to create them in the first place.</p>
    </blockquote>
    <p><a href="http://twitter.com/necolas">@necolas</a> at <a href="http://twitter.com/necolas/status/9880187933">4:05 PM March 2nd 2010</a></p>
    <div class="example-number">57</div>
    <h2>More complex CSS3 examples</h2>
    <p>Some more experimental speech bubbles that try to limit the damage in browsers lacking the necessary CSS3 support.</p>
    <div class="pinched">
      <p><strong>It doesn’t matter what the first child element of this div is</strong>...but it does need a child element.</p>
    </div>
    <blockquote class="oval-speech">
      <p>This is a blockquote that is styled to look like a speech bubble</p>
    </blockquote>
    <blockquote class="oval-thought">
      <p>This is a blockquote that is styled to look like a thought bubble</p>
    </blockquote>
    <blockquote class="oval-quotes">
      <p>No, Donny, these men are nihilists, there’s nothing to be afraid of.</p>
    </blockquote>
    <p>Walter Sobchak</p>
    <blockquote class="rectangle-speech-border">
      <p>This is a blockquote that is styled to look like a speech bubble</p>
    </blockquote>
    <blockquote class="oval-speech-border">
      <p>This is a blockquote that is styled to look like a speech bubble</p>
    </blockquote>
    <blockquote class="oval-thought-border">
      <p>This is a blockquote that is styled to look like a thought bubble</p>
    </blockquote>


    <section class="tn-Powered-by-XIUMI" style="white-space: normal; border: 0px; max-width: 100%; margin: 0.5em 0px 0px; padding: 0px 0.5em 0.5em 0px; box-sizing: border-box;">
        <section class="tn-Powered-by-XIUMI" style="border: 2px dotted white; padding: 10px; border-top-left-radius: 8px 64px; border-top-right-radius: 48px 8px; border-bottom-right-radius: 8px 16px; border-bottom-left-radius: 0px 48px; box-shadow: rgb(225, 225, 225) 8px 8px 3px; line-height: 1.4; font-size: 1em; font-family: inherit; text-decoration: inherit; color: rgb(255, 255, 255); background-color: rgb(71, 193, 168); box-sizing: border-box; word-wrap: break-word !important;"><section class="tn-Powered-by-XIUMI" style="box-sizing: border-box;">
        市经信委说，沪5个重点区域将调整产业结构，关停大批高污染高能耗企业。5个区域为：浦东合庆镇G1501东侧区域(一期)、浦东周浦申江路以东区域、金山廊下郊野公园区域、黄浦江上游水源保护区(金泽镇)、奉贤经济开发区生物科技园区。预计将腾地3000亩，每年节约能耗4万吨标煤。
        </section>
    </section>

  </div>
</div>

</html>
