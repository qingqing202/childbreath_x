
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>


<head>
    <!-- config -->
    <meta charset="utf-8">
    <meta name="viewport" id="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0" />
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-itunes-app" content="app-id=976577934">
    <meta name="title" content="给你更多家居灵感 - IKEA 宜家家居 官网">
    <meta name="description" content="客厅，卧室，厨房，浴室，带你领略家居生活的无限可能 "/>
    <!-- libraries -->
    <link rel="stylesheet" href="http://m.ikea.com/irmw-resources/external/jquery.mobile-1.2.0.min.css"/>
    <script type="text/javascript" src="http://m.ikea.com/irmw-resources/external/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="http://m.ikea.com/irmw-resources/external/jquery.mobile.custom-1.2.0.min.js"></script>
    <!-- custom stylesheets and scripts -->
    <link rel="stylesheet" href="http://m.ikea.com/irmw-resources/css/ikea.mobile.min.css?v=5.20"/>
    <script type="text/javascript" charset="utf-8" src="http://m.ikea.com/irmw-resources/js/ikea.mobile.min.js?v=5.20"></script>
    <!-- page specifc scripts -->
    <script>
        (function($){
            function loadSettings(){
//Initialize dynamic configuration.
                $.mobile.retailUnit = 'cn';
                $.mobile.languageCode = 'zh';
                $.mobile.loadingMessage = "加载中";
                $.mobile.defaultShoppingBagName = "购物清单";
                $.mobile.offlineLoadError = "您请求的页面当前无法显示，但购物清单仍可访问。";
                $.mobile.loggedInAsMessage ="已登录：{1}";
                $.mobile.currencySettings = {symbol: "¥", decimalSymbol: ".", positiveFormat: "%s %n", negativeFormat: "-%s %n", groupSymbol: ",", fractionDigits: "2", showFractionDigitsOnInteger: "true", integerSymbol: "", currencyCode: "CNY"};
                $.mobile.showLocalPrices = true;
                $.mobile.baseUrl = '/cn/zh/';
                $.mobile.isProduction = true;
                $.mobile.cookieDomain = '.ikea.com';
                $.mobile.fullSiteUrl = 'www.ikea.com';
                $.mobile.secureFullSiteUrl = 'secure.ikea.com';
                $.mobile.enableTealium =false;
            }
            loadSettings();
        }(jQuery));
    </script>
    <title id="txtHtmlTitle">给你更多家居灵感 - IKEA 宜家家居 官网</title>
    <link rel="shortcut icon" type="image/ico" href="http://m.ikea.com/irmw-resources/img/favicon.ico"/>
    <link rel="apple-touch-icon-precomposed" href="http://m.ikea.com/irmw-resources/img/apple-touch-icon-57x57-precomposed.png" />
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="http://m.ikea.com/irmw-resources/img/apple-touch-icon-114x114-precomposed.png" />
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="http://m.ikea.com/irmw-resources/img/apple-touch-icon-144x144-precomposed.png" />
    <link rel="apple-touch-icon-precomposed" sizes="32x32" href="http://m.ikea.com/irmw-resources/img/apple-touch-icon-32x32-precomposed.png" />
    <link rel="apple-touch-icon-precomposed" sizes="57x57" href="http://m.ikea.com/irmw-resources/img/apple-touch-icon-57x57-precomposed.png" />
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="http://m.ikea.com/irmw-resources/img/apple-touch-icon-72x72-precomposed.png" />
    <!-- For Windows Phone (only 144x144 used)-->
    <meta name="msapplication-TileImage" content="http://m.ikea.com/irmw-resources/img/apple-touch-icon-144x144-precomposed.png" />
</head>
<body itemscope itemtype="http://schema.org/WebPage">
<div data-role="page" data-theme="d" id="mls1449496074589" data-dom-cache="false">
    <div data-role="header" id="ikea-homeheader" data-backbtn="false">
        <div id="ikea-home-topbar">
            <div id="ikea-topbar-logocell">
                <a tabindex="-1" href="/cn/zh/" rel=external><span class="ikea-logo" title="" ></span></a>
            </div>
            <div id="ikea-topbar-iconcell">
                <div id="search-icon" onClick="$(this).parent().siblings('#ikea-topbar-searchfield').first().searchdecorate('toggle');"></div>
                <div id="list-icon">
                    <a href="http://m.ikea.com/cn/zh/shoppinglist/" rel=external></a>
                    <div class="list-icon"></div>
                </div>
            </div>
            <div data-role="searchdecorate" id="ikea-topbar-searchfield"></div>
        </div>
        <div id="ikea-navbar" data-role="headerbar" data-moreListId="#ikea-more-list">
            <ul>
                <li class="ikea-navbar-item " data-icon="none">
                    <a href="http://m.ikea.com/cn/zh/catalog/functional/" >产品</a>
                </li>
                <li class="ikea-navbar-item " data-icon="none">
                    <a href="http://m.ikea.com/cn/zh/campaigns/" >活动和特惠</a>
                </li>
                <li class="ikea-navbar-item " data-icon="none">
                    <a href="http://m.ikea.com/cn/zh/stores/" >商场信息</a>
                </li>
                <li class="ikea-navbar-item" id="ikea-nav-li">
                    <div id="footer-login" style="display: none">
                        <a id="login1" href="/cn/zh/login/" rel="nofollow" data-ajax="false">登录</a>
                    </div>
                    <div id="footer-logout" style="display: none">
                        <span id="logged-in-as"></span>
                        <div class="header-logout">
                            <a style="cursor:pointer;" onclick="Login.logout();return false;" data-ajax="false" rel="nofollow">退出</a>
                        </div>
                    </div>
                </li>
                <li class="ui-headerbar-more-item" data-role="headerbar-more-item">
                    <a data-role="popupbutton" id="buttonMenu" class="onlyText"><span>查看更多</span></a>
                </li>
            </ul>
        </div>
        <div data-role="selectlist" id="more-item-popup">
            <ul data-role="listview" data-theme="d" id="ikea-more-list">
            </ul>
        </div>
    </div>
    <!-- page content-->
    <script language="JavaScript" type="text/javascript">
        $('#mls1449496074589').bind('pageshow', function(){
            waMLS.initWebAnalyticsStartpage();
            Login.checkLoggedOut();
        });
    </script>
    <div id="loggedOut" style="display:none">
        <div class="ui-body-d ui-corner-all shoppingListNotification">
            <div class="ikea-icon-success shoppingListNotificationIcon"></div>
            <div class="ikea-shoppingListNotificationTextWrapper">
                <div class="ikea-shoppingListNotificationText">您已退出登录</div>
            </div>
        </div>
    </div>
    <!-- page content-->
    <div class="startpage-vertical-navigation" data-role="content" tabindex="-1">
        <ul data-role="listview" data-theme="d" data-inset="true">
            <li>
                <div class="ikea-campaign" data-role="carousel">
                    <div data-role="carouselItem" data-campaignurl="/cn/zh/campaigns/wQAbv6wUFc/">
                        <img src="http://m.ikea.com//ms/zh_CN/img/2016_img/mobile_campaign/michelinchef_banner_m.jpg" data-role="carouselImage"/>
                    </div>
                    <div data-role="carouselItem" data-campaignurl="/cn/zh/campaigns/L70uBHUfwm/">
                        <img src="http://m.ikea.com//ms/zh_CN/img/2016_img/mobile_campaign/cook_for_love_m.jpg" data-role="carouselImage"/>
                    </div>
                    <div data-role="carouselItem" data-campaignurl="/cn/zh/campaigns/vrl2y7CzOJ/">
                        <img src="http://m.ikea.com//ms/img/mobile/good-cause-campaign/201623_gcvm01a_01_PH130743.jpg" data-role="carouselImage"/>
                        <h2 style="background: #FFF; color: #333333;">照亮难民的生活</h2>
                    </div>
                    <div data-role="carouselItem" data-campaignurl="/cn/zh/campaigns/OwK70RiuUC/">
                        <img src="http://m.ikea.com//ms/zh_CN/img/2016_img/mobile_campaign/365cookware_m_zh.jpg" data-role="carouselImage"/>
                    </div>
                    <div data-role="carouselItem" data-campaignurl="/cn/zh/campaigns/kfVFeEbFop/">
                        <img src="http://m.ikea.com//ms/img/mobile/20162/201620_cmvm01a_01_PH130596.jpg" data-role="carouselImage"/>
                        <h2 style="background: #FFF; color: #333333;">你喜欢哪一种节日风格？</h2>
                    </div>
                    <div data-role="carouselItem" data-campaignurl="/cn/zh/campaigns/aAx7cOhUWm/">
                        <img src="http://m.ikea.com//ms/zh_CN/img/2016_img/mobile_campaign/70296296_m.jpg" data-role="carouselImage"/>
                    </div>
                    <div data-role="carouselItem" data-campaignurl="/cn/zh/campaigns/30KpoM4lFm/">
                        <img src="http://m.ikea.com//ms/zh_CN/img/2016_img/mobile_campaign/family_offer/familyoffer_mobile_zh.jpg" data-role="carouselImage"/>
                    </div>
                    <div data-role="carouselItem" data-campaignurl="/cn/zh/campaigns/fUKoWVFv06/">
                        <img src="http://m.ikea.com//ms/zh_CN/img/2016_img/mobile_campaign/elp_m_zh01.jpg" data-role="carouselImage"/>
                    </div>
                    <div data-role="carouselItem" data-campaignurl="/cn/zh/campaigns/lmD2BFNGNG/">
                        <img src="http://m.ikea.com//ms/zh_CN/img/2015_img/mobile_campaign/IKEA_store_1.jpg" data-role="carouselImage"/>
                    </div>
                </div>
            </li>
            <li><a href="/cn/zh/campaigns/">所有活动和特惠</a></li>
        </ul>
        <ul data-role="listview" data-theme="d" data-inset="true">
            <li><a href="https://yijia.acxiom.com.cn/mobile/index.action?store=000">加入宜家俱乐部</a></li>
            <li><a href="http://onlinecatalogueasia.ikea.com/CN/zh/2012/IKEA_Catalogue/">在线《家居指南》</a></li>
        </ul>
    </div>
    <!-- /page content-->
    <!-- common footer-->
    <div class="ikea-footer">
        <div class="ikea-right-link-new" >
            <a onClick="$.mobile.silentScroll(1);">返回顶部</a>
        </div>
        <div class="ikea-footer-divider"></div>
        <div class="ikea-footer-label">
            <div class="ikea-footer-label-inner">
                <div class="linksRow">
                    <label>关注我们</label>
                    <a data-ajax="false" href="http://m.ikea.com//ms/zh_CN/img/2016_img/mobile_campaign/wechat_qr.jpg" target="_blank">微信</a>
                    <span>|</span>
                    <a data-ajax="false" href="http://m.weibo.cn/d/ikea" target="_blank">微博</a>
                    <span>|</span>
                    <a data-ajax="false" href="http://www.oneniceapp.com/user/11290829" target="_blank">nice好赞</a>
                    <span>|</span>
                    <a data-ajax="false" href="http://site.douban.com/ikea/" target="_blank">豆瓣</a>
                </div>
            </div>
            <div class="ikea-footer-label-inner">
                <div class="linksRow">
                    <label></label>
                    <a data-ajax="false" href="http://a.app.qq.com/o/simple.jsp?pkgname=com.ikea.kompis" target="_blank">下载 IKEA Store APP</a>
                </div>
            </div>
            <div class="ikea-footer-label-inner">
                <div class="linksRow">
                    <label></label>
                    <a data-ajax="false" href="http://a.app.qq.com/o/simple.jsp?pkgname=com.ikea.catalogue.android" target="_blank">下载宜家《家居指南》APP</a>
                </div>
            </div>
        </div>
        <div class="ikea-footer-label">
            <div class="ikea-footer-label-inner">
                <div class="linksRow">
                    <label></label>
                    <a data-ajax="false" href="#" class="currentLanguage">中文</a>
                    <span>|</span>
                    <a data-ajax="false" href="http://m.ikea.com/cn/en/">English</a>
                </div>
            </div>
        </div>
        <div class="ikea-full-site">
            <a href="http://www.ikea.com/cn/zh/?preferedui=desktop" data-location="footer" onClick="return waMLS.appendIRWURL(this)">IKEA电脑完整版网站</a>
        </div>
        <div class="ikea-footer-copyright">
            <div class="ikea-footer-legal">
                <div class="ikea-footer-label">
                    <div class="ikea-footer-label-inner">
                        <div class="linksRow">
                            <a data-ajax="false" href="http://www.ikea.com/ms/zh_CN/privacy_policy/privacy_policy.html" target="_blank">隐私政策</a>
                        </div>
                    </div>
                </div>
            </div>
            © Inter IKEA Systems B.V. 1999-2015
        </div>
    </div>
</div>
<noscript>
    <div style="display: none;">
        <img src="https://smetrics.ikea.com/b/ss/ikeaallmlsnojavascriptprod/5/?c8=cn&amp;v7=cn&amp;pageName=nojavascript" alt="" width="5" height="5" />
    </div>
</noscript>
</body>
</html>

