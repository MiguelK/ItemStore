    //Category ------------------------------------------- News/Nyheter ---------------------------------------------------------

    //English   ----- News
    [categoryNews addEnglishFeedSelected:@"test" hostName:@"Wired.com" url:@"http://www.wired.com/rss/index.xml "];
    [categoryNews addEnglishFeed:@"test" hostName:@"Reuters.com -Top News" url:@"http://feeds.reuters.com/reuters/topNews"];
    [categoryNews addEnglishFeed:@"test" hostName:@"Reuters.com -US News" url:@"http://feeds.reuters.com/Reuters/domesticNews"];
    [categoryNews addEnglishFeed:@"test" hostName:@"Reuters.com -World" url:@"http://feeds.reuters.com/Reuters/worldNews"];
    [categoryNews addEnglishFeed:@"test" hostName:@"Reuters.com -Top News Video" url:@"http://feeds.reuters.com/reuters/USVideoTopNews"];
    [categoryNews addEnglishFeed:@"test" hostName:@"Usatoday.com" url:@"http://content.usatoday.com/marketing/rss/rsstrans.aspx?feedId=news1"];
    [categoryNews addEnglishFeed:@"test" hostName:@"Usatoday.com -TopStories" url:@"http://rssfeeds.usatoday.com/usatoday-NewsTopStories"];
    [categoryNews addEnglishFeed:@"test" hostName:@"Foxnews.com" url:@"http://feeds.feedburner.com/foxnews/latest"];
    [categoryNews addEnglishFeed:@"test" hostName:@"Kuro5hin.org" url:@"http://www.kuro5hin.org/backend.rdf"];
    [categoryNews addEnglishFeed:@"test" hostName:@"CbsNews.com" url:@"http://feeds.cbsnews.com/CBSNewsMain"];
    [categoryNews addEnglishFeed:@"test" hostName:@"Slate.com" url:@"http://feeds.slate.com/slate"];
    [categoryNews addEnglishFeed:@"test" hostName:@"The Atlantic Wire" url:@"http://feeds.feedburner.com/TheAtlanticWire"];
    [categoryNews addEnglishFeed:@"test" hostName:@"FOXNews.com" url:@"http://feeds.feedburner.com/foxnews/latest"];
    [categoryNews addEnglishFeed:@"test" hostName:@"TheOnion.com" url:@"http://feeds.theonion.com/theonion/daily"];
    [categoryNews addEnglishFeed:@"test" hostName:@"LAtimes.com" url:@"http://www.latimes.com/rss2.0.xml"];
    [categoryNews addEnglishFeed:@"test" hostName:@"Newyorker.com" url:@"http://www.newyorker.com/services/rss/feeds/everything.xml"];

    //--------------------------------------------------------------------------------------------------------------------------------------------
    //Category -------------------------------------------Sport -----------------------------------------------------------------------------------
    ChannelCategory *categorySport = [[ChannelCategory alloc] initWithTextSelected:NSLocalizedString(@"CategorySportKey", @"") imageName:@"sports"];
    [categorySport addSwedishFeedSelected:@"test" hostName:@"Aftonbladet.se -Sportbladet" url:@"http://www.aftonbladet.se/sportbladet/rss.xml"];
    [categorySport addSwedishFeedSelected:@"test" hostName:@"Aftonbladet.se -Fotboll" url:@"http://www.aftonbladet.se/sportbladet/fotbollsbladet/rss.xml"];
    [categorySport addSwedishChannel:@"test" hostName:@"Aftonbladet.se -Hockey" url:@"http://www.aftonbladet.se/sportbladet/hockeybladet/rss.xml"];
    [categorySport addSwedishChannel:@"test" hostName:@"DN.se -Sport" url:@"http://www.dn.se/sport/m/rss/senaste-nytt"];
    [categorySport addSwedishChannel:@"test" hostName:@"SVT.se -Fotboll" url:@"http://www.nyhetsportalen.se/?t=1433&rss=1&na=15"];
    [categorySport addSwedishChannel:@"test" hostName:@"SvD.se" url:@"http://www.svd.se/sport/?service=rss"];
    [categorySport addSwedishChannel:@"test" hostName:@"SVT.se" url:@"http://svt.se/rss/sport/"];
    [categorySport addSwedishChannel:@"test" hostName:@"Sportal.se" url:@"http://www.sportal.se/feed/rss.xml"];
    [categorySport addSwedishChannel:@"test" hostName:@"SR.se Radiosporten" url:@"http://api.sr.se/api/rssfeed/rssfeed.aspx?rssfeed=179"];
    [categorySport addSwedishChannel:@"test" hostName:@"Sydsvenskan.se" url:@"http://www.sydsvenskan.se/sport/?context=xml"];
    [categorySport addSwedishChannel:@"test" hostName:@"Hd.se -Toppar" url:@"http://hd.se/rss/sport/toppar.xml"];
    [categorySport addSwedishChannel:@"test" hostName:@"Hd.se -Senaste" url:@"http://hd.se/rss/sport/senaste.xml"];
    //http://bloggar.aftonbladet.se/handbollsbloggen/2014/01/genant-sverige/


    //English
    [categorySport addEnglishFeedSelected:@"test" hostName:@"BBC.co.uk" url:@"http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/front_page/rss.xml"];
    [categorySport addEnglishFeed:@"test" hostName:@"Nytimes.com" url:@"http://www.nytimes.com/pages/sports/index.html?partner=rss"];
    [categorySport addEnglishFeed:@"test" hostName:@"Usatoday.com -TopStories" url:@"http://rssfeeds.usatoday.com/UsatodaycomSports-TopStories"];
    [categorySport addEnglishFeed:@"test" hostName:@"Sport.co.uk -News" url:@"http://feeds.feedburner.com/SportcoukNewsRssFeed"];
    [categorySport addEnglishFeedSelected:@"test" hostName:@"Sport.co.uk -Fotball" url:@"http://feeds.feedburner.com/SportcoukFootballRssFeed"];
    [categorySport addEnglishFeed:@"test" hostName:@"Sport.co.uk -Video" url:@"http://feeds.feedburner.com/SportcoukVideoRssFeed"];
    [categorySport addEnglishFeed:@"test" hostName:@"SkySports.com -News" url:@"http://www.skysports.com/rss/0,20514,12040,00.xml"];
    [categorySport addEnglishFeed:@"test" hostName:@"SkySports.com -NHL" url:@"http://www.skysports.com/rss/0,20514,12118,00.xml"];
    [categorySport addEnglishFeed:@"test" hostName:@"SkySports.com -Fotball" url:@"http://www.skysports.com/rss/0,20514,11661,00.xml"];
    [categorySport addEnglishFeed:@"test" hostName:@"SkySports.com -Golf" url:@"http://www.skysports.com/rss/0,20514,12176,00.xml"];
    [categorySport addEnglishFeed:@"test" hostName:@"SkySports.com -Tennis" url:@"http://www.skysports.com/rss/0,20514,12110,00.xml"];
    [categorySport addEnglishFeed:@"test" hostName:@"Sports.yahoo.com -NHL" url:@"http://sports.yahoo.com/nhl/rss.xml"];

    //--------------------------------------------------------------------------------------------------------------------------------------------
    //Category -------------------------------------------Computor/Mobile /Data /Mobilt --------------------------------------------------------------------
    ChannelCategory *categoryComputer = [[ChannelCategory alloc] initWithTextSelected:NSLocalizedString(@"CategoryComputerMobileKey", @"") imageName:@"computer"];
    [categoryComputer addSwedishFeedSelected:@"test" hostName:@"Array.se" url:@"http://array.se/feed/rss/"];
    [categoryComputer addSwedishChannel:@"test" hostName:@"Unix FAQ (Eng)" url:@"http://www.cyberciti.biz/faq/feed/rss/"];
    [categoryComputer addSwedishChannel:@"test" hostName:@"Aapl.se Iphone nyheter" url:@"http://feeds.feedburner.com/aaplse?format=xml"];
    [categoryComputer addSwedishFeedSelected:@"test" hostName:@"PC för Alla, Senaste nyheterna" url:@"http://pcforalla.idg.se/rss/senaste+nytt"];
    [categoryComputer addSwedishChannel:@"test" hostName:@"PCforalla.se -Dagens surftips" url:@"http://pcforalla.idg.se/rss/dagens+surftips"];
    [categoryComputer addSwedishChannel:@"test" hostName:@"PCforalla.se -Senaste testerna" url:@"http://pcforalla.idg.se/rss/tester"];
    [categoryComputer addSwedishChannel:@"test" hostName:@"PCforalla.se -Tips och skolor" url:@"http://pcforalla.idg.se/rss/tips+och+skolor"];
    [categoryComputer addSwedishChannel:@"test" hostName:@"Mobil.se" url:@"http://www.mobil.se/cmlink/Mobil-se-Nyheter-1.250085.xml"];
    [categoryComputer addSwedishChannel:@"test" hostName:@"ComputerSweden.se -Tjänster" url:@"http://computersweden.idg.se/tjanster/rss/rss.xml"];
    [categoryComputer addSwedishChannel:@"computersweden" hostName:@"ComputerSweden.se -Affärer och företag" url:@"http://feeds.idg.se/CsAffarer"];
    [categoryComputer addSwedishChannel:@"computersweden" hostName:@"ComputerSweden.se -Affärssystem" url:@"http://feeds.idg.se/CsAffrssystem"];
    [categoryComputer addSwedishChannel:@"computersweden" hostName:@"ComputerSweden.se -Affärsutveckling" url:@"http://computersweden.idg.se/rss/aff%C3%A4rsutveckling"];
    [categoryComputer addSwedishChannel:@"computersweden" hostName:@"ComputerSweden.se -Hårdvara" url:@"http://computersweden.idg.se/rss/h%C3%A5rdvara"];
    [categoryComputer addSwedishChannel:@"computersweden" hostName:@"ComputerSweden.se -It-säkerhet" url:@"http://computersweden.idg.se/rss/it-s%C3%A4kerhet"];
    [categoryComputer addSwedishChannel:@"computersweden" hostName:@"ComputerSweden.se -Lagring" url:@"http://computersweden.idg.se/rss/lagring"];
    [categoryComputer addSwedishChannel:@"computersweden" hostName:@"ComputerSweden.se -Nät & kommunikation" url:@"http://computersweden.idg.se/rss/n%C3%A4t+%26+kommunikation"];
    [categoryComputer addSwedishChannel:@"computersweden" hostName:@"ComputerSweden.se -Operativsystem" url:@"http://feeds.idg.se/CsOperativsystem"];
    [categoryComputer addSwedishFeedSelected:@"computersweden" hostName:@"ComputerSweden.se -Senaste nyheterna" url:@"http://feeds.idg.se/ComputerSweden20SenasteNyheter"];
    [categoryComputer addSwedishChannel:@"computersweden" hostName:@"ComputerSweden.se -Systemutveckling" url:@"http://computersweden.idg.se/rss/systemutveckling"];
    [categoryComputer addSwedishChannel:@"computersweden" hostName:@"ComputerSweden.se -Öppen källkod" url:@"http://computersweden.idg.se/rss/%C3%B6ppen+k%C3%A4llkod"];
    [categoryComputer addSwedishChannel:@"it24.se, it-konsulter" hostName:@"It24.se -it-konsulter" url:@"http://it24.idg.se/rss/it-konsulter"];
    [categoryComputer addSwedishChannel:@"it24.se, it-konsulter" hostName:@"It24.se -SenasteArtiklar" url:@"http://feeds.idg.se/It24-SenasteArtiklar"];
    [categoryComputer addSwedishChannel:@"" hostName:@"Mjukvara.se -blogg" url:@"http://www.mjukvara.se/blogg/feed/"];
    [categoryComputer addSwedishChannel:@"" hostName:@"Techworld.idg.se -senaste" url:@"http://techworld.idg.se/rss/senaste+artiklar"];
    [categoryComputer addSwedishChannel:@"test" hostName:@"Techworld.idg.se -alla artiklar" url:@"http://techworld.idg.se/rss/alla+artiklar+inom+techworld"];
    [categoryComputer addSwedishChannel:@"test" hostName:@"Microsoftnyheter" url:@"http://blogs.technet.com/microsoftnyheter/rss.xml"];
    [categoryComputer addSwedishChannel:@"test" hostName:@"Linux.se" url:@"http://www.linux.se/feed/"];
    [categoryComputer addSwedishChannel:@"test" hostName:@"Apple.com.se" url:@"http://www.apple.com/se/main/rss/hotnews/hotnews.rss"];
    [categoryComputer addSwedishChannel:@"test" hostName:@"Swedroid.se" url:@"http://www.swedroid.se/feed/"];

    //ENglish, CategoryComputerMobileKey
    [categoryComputer addEnglishFeedSelected:@"test" hostName:@"Idev101.com" url:@"http://feeds.feedburner.com/idev101"];
    [categoryComputer addEnglishFeedSelected:@"test" hostName:@"PCWorld.com -Latest" url:@"http://feeds.pcworld.com/pcworld/latestnews"];
    [categoryComputer addEnglishFeedSelected:@"test" hostName:@"Android+Phones" url:@"http://www.rssmicro.com/rss.web?q=Android+Phones"];
    [categoryComputer addEnglishFeedSelected:@"test" hostName:@"TheVerge.com" url:@"http://www.theverge.com/rss/index.xml"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Apple -Hot news" url:@"http://images.apple.com/main/rss/hotnews/hotnews.rss"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Slashdot" url:@"http://rss.slashdot.org/Slashdot/slashdot"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Googleblog" url:@"http://googleblog.blogspot.com/feeds/posts/default?alt=rss"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Google blogspace" url:@"http://google.blogspace.com/rss"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"PCMag.com New Product Review" url:@"http://feeds2.feedburner.com/ziffdavis/pcmag"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Lifehacker.com" url:@"http://lifehacker.com/tag/top/index.xml"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Betanews.com" url:@"http://www.betanews.com/rss"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Webdeveloper.com" url:@"http://www.webdeveloper.com/forum/external.php?type=RSS"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Webdesign.about.com" url:@"http://z.about.com/6/g/webdesign/b/rss2.xml"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Webdesign.about.com Latest" url:@"http://z.about.com/6/o/m/webdesign_p2.xml"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Dig.com" url:@"http://digg.com/rss/popular.rss"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"MacRumors.com" url:@"http://feeds.macrumors.com/MacRumors-All"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Pcworld.com -howto" url:@"http://feeds.pcworld.com/pcworld/update/howto"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"CNN.com -Technology" url:@"http://rss.cnn.com/rss/cnn_tech.rss"];
    [categoryComputer addEnglishFeedSelected:@"test" hostName:@"Zdnet.com" url:@"http://www.zdnet.com/search?t=1,7&mode=rss"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Cnet.com -News" url:@"http://news.cnet.com/2547-1_3-0-20.xml"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Computerworld.com -News" url:@"http://rss.computerworld.com/computerworld/s/feed/type/newsanalysis"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Infoworld.com -News" url:@"http://www.infoworld.com/homepage/feed"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Itworld.com" url:@"http://www.itworld.com/taxonomy/term/16/all/feed"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Engadget.com" url:@"http://www.engadget.com/rss.xml"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Gizmodo.com" url:@"http://feeds.gawker.com/gizmodo/full"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Googleblog.com" url:@"http://googleblog.blogspot.com/feeds/posts/default?alt=rss"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Lifehacker.com" url:@"http://feeds.gawker.com/lifehacker/full"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Thenextweb.com" url:@"http://feeds2.feedburner.com/thenextweb"];
    [categoryComputer addEnglishFeed:@"test" hostName:@"Epicenter.com" url:@"http://www.wired.com/epicenter/feed/"];

    //---------------------------------------------------------------------------------------------------------------------------------------

    //English
    [categoryBlog addEnglishFeed:@"test" hostName:@"Huffingtonpost.com" url:@"http://feeds.huffingtonpost.com/huffingtonpost/TheBlog"];
    [categoryBlog addEnglishFeed:@"test" hostName:@"Falkvinge.net" url:@"http://feeds.falkvinge.net/Falkvinge-on-Infopolicy"];
    [categoryBlog addEnglishFeed:@"test" hostName:@"Zooeydeschanel.tumblr.com" url:@"http://zooeydeschanel.tumblr.com/rss"];
    [categoryBlog addEnglishFeed:@"test" hostName:@"Ashleytisdale.com" url:@"http://ashleytisdale.com/feed/"];
    [categoryBlog addEnglishFeed:@"test" hostName:@"Gublernation.com" url:@"http://gublernation.tumblr.com/rss"];
    [categoryBlog addEnglishFeed:@"test" hostName:@"Whitneyport.com" url:@"http://whitneyport.celebuzz.com/feed"];
    [categoryBlog addEnglishFeed:@"test" hostName:@"Iamnikkireed.com" url:@"http://iamnikkireed.com/feed/"];
    [categoryBlog addEnglishFeed:@"test" hostName:@"Felldowntherabbithole" url:@"http://felldowntherabbithole.tumblr.com/rss"];
    [categoryBlog addEnglishFeed:@"test" hostName:@"TechCrunch.com" url:@"http://feedproxy.google.com/TechCrunch"];
    [categoryBlog addEnglishFeed:@"test" hostName:@"Dailypost.com" url:@"http://dailypost.wordpress.com/feed/"];
    [categoryBlog addEnglishFeed:@"test" hostName:@"Boingboing.net" url:@"http://feeds.boingboing.net/boingboing/iBag"];
    [categoryBlog addEnglishFeed:@"test" hostName:@"Tmz.com" url:@"http://www.tmz.com/rss.xml"];
    [categoryBlog addEnglishFeed:@"test" hostName:@"Telegraph.co.uk" url:@"http://blogs.telegraph.co.uk/news/feed/"];
    [categoryBlog addEnglishFeed:@"test" hostName:@"Gawker.com" url:@"http://feeds.gawker.com/gawker/full"];
    [categoryBlog addEnglishFeed:@"test" hostName:@"Ommalik.com" url:@"http://feeds.feedburner.com/ommalik"];
    [categoryBlog addEnglishFeed:@"test" hostName:@"Politicalticker, CNN" url:@"http://politicalticker.blogs.cnn.com/feed/"];
    [categoryBlog addEnglishFeed:@"test" hostName:@"Wired.com" url:@"http://feeds.wired.com/wired/index"];

    //------------------------------------------------------------------------------------------------------------------------------------------------
    //Category -------------------------------------------Economy/Ekonomi --------------------------------------------------------------------
    ChannelCategory *categoryEconomy = [[ChannelCategory alloc] initWithText:NSLocalizedString(@"CategoryEconomyKey", @"") imageName:@"economy"];
    [categoryEconomy addSwedishChannel:@"test" hostName:@"DI.se" url:@"http://di.se/rss"];
    [categoryEconomy addSwedishChannel:@"test" hostName:@"DN.se -ToppNyheterna" url:@"http://www.dn.se/ekonomi/m/rss"];
    [categoryEconomy addSwedishChannel:@"test" hostName:@"DN.se -Senaste nytt" url:@"http://www.dn.se/ekonomi/m/rss/senaste-nytt"];
    [categoryEconomy addSwedishChannel:@"test" hostName:@"Privataaffarer.se" url:@"http://www.privataaffarer.se/rss"];
    [categoryEconomy addSwedishChannel:@"test" hostName:@"Avdragslexikon.se" url:@"http://www.avdragslexikon.se/index.php?format=feed&type=rss"];
    [categoryEconomy addSwedishChannel:@"test" hostName:@"Realtid.se" url:@"http://www.realtid.se/rss/senaste/"];
    [categoryEconomy addSwedishChannel:@"test" hostName:@"SVD.se -naringsliv" url:@"http://www.svd.se/naringsliv/?service=rss&type=senastenytt"];
    [categoryEconomy addSwedishChannel:@"test" hostName:@"Unt.se" url:@"http://www.unt.se/rss/ekonomi"];
    [categoryEconomy addSwedishChannel:@"test" hostName:@"E24.se -Toppnyheter" url:@"http://www.e24.se/?service=feed-rss"];
    [categoryEconomy addSwedishChannel:@"test" hostName:@"E24.se -Senaste" url:@"http://www.e24.se/?service=feed-rss&type=latest"];
    [categoryEconomy addSwedishChannel:@"test" hostName:@"Affarsvarlden.se" url:@"http://www.affarsvarlden.se/?service=rss"];
    [categoryEconomy addSwedishChannel:@"test" hostName:@"Sydsvenskan.se" url:@"http://www.sydsvenskan.se/ekonomi/?context=xml"];

    //English
    [categoryEconomy addEnglishFeed:@"test" hostName:@"Feedage.com" url:@"http://www.feedage.com/feeds/11058/finance-economy-news-the-economic-times"];
    [categoryEconomy addEnglishFeed:@"test" hostName:@"Smartmoney.com, headlines" url:@"http://feeds.smartmoney.com/smartmoney/headlines"];
    [categoryEconomy addEnglishFeed:@"test" hostName:@"Smartmoney.com, investing" url:@"http://feeds.smartmoney.com/smartmoney/video/investing"];
    [categoryEconomy addEnglishFeed:@"test" hostName:@"SmallBusinessTrends" url:@"http://feeds.feedburner.com/SmallBusinessTrends"];
    [categoryEconomy addEnglishFeed:@"test" hostName:@"Kiplinger" url:@"http://kiplinger.com/about/rss/kiplinger.rss"];
    [categoryEconomy addEnglishFeed:@"test" hostName:@"CNN, News" url:@"http://rss.cnn.com/rss/money_news_economy.rss"];
    [categoryEconomy addEnglishFeed:@"test" hostName:@"CNN, Topstories" url:@"http://rss.cnn.com/rss/money_topstories.rss"];
    [categoryEconomy addEnglishFeed:@"test" hostName:@"CNN, Personal Finance" url:@"http://rss.cnn.com/rss/magazines_moneymag.rss"];
    [categoryEconomy addEnglishFeed:@"test" hostName:@"BBC, News" url:@"http://feeds.bbci.co.uk/news/business/economy/rss.xml"];
    [categoryEconomy addEnglishFeed:@"test" hostName:@"Nytimes.com, News" url:@"http://www.nytimes.com/services/xml/rss/nyt/Economy.xml"];
    [categoryEconomy addEnglishFeed:@"test" hostName:@"Businessinsider.com, News" url:@"http://feeds2.feedburner.com/businessinsider"];
    [categoryEconomy addEnglishFeed:@"test" hostName:@"Usatoday.com, TopStories" url:@"http://rssfeeds.usatoday.com/UsatodaycomMoney-TopStories"];
    [categoryEconomy addEnglishFeed:@"test" hostName:@"Thisismoney.co.uk" url:@"http://www.thisismoney.co.uk/money/index.rss"];
    [categoryEconomy addEnglishFeed:@"test" hostName:@"Ft.com, global-economy" url:@"http://www.ft.com/rss/global-economy"];

    //------------------------------------------------------------------------------------------------------------------------------------------------
    //Category -------------------------------------------Film /Movie --------------------------------------------------------------------------------
    ChannelCategory *categoryFilm = [[ChannelCategory alloc] initWithText:NSLocalizedString(@"CategoryMoviewKey", @"") imageName:@"movie"""];
    [categoryFilm addSwedishChannel:@"test" hostName:@"SvD.se" url:@"http://www.svd.se/kulturnoje/film/?service=rss"];
    [categoryFilm addSwedishChannel:@"test" hostName:@"Voodoofilm.org" url:@"http://www.voodoofilm.org/syndikering/nyheter.aspx"];
    [categoryFilm addSwedishChannel:@"test" hostName:@"Russin.nu" url:@"http://www.russin.nu/rss/artiklar.php"];
    [categoryFilm addSwedishChannel:@"test" hostName:@"Dvdforum.nu" url:@"http://www.filmforum.se/cmlink/senaste-fran-filmforum-se-1.387338.xml"];
    [categoryFilm addSwedishChannel:@"test" hostName:@"SF.se -Nyheter" url:@"http://www.sf.se/sfmedia/external/rss/news.rss"];
    [categoryFilm addSwedishChannel:@"test" hostName:@"SF.se -Topplistan" url:@"http://www.sf.se/sfmedia/external/rss/topten.rss"];
    [categoryFilm addSwedishChannel:@"test" hostName:@"SF.se -Premiärer" url:@"http://www.sf.se/sfmedia/external/rss/premieres.rss"];
    [categoryFilm addSwedishChannel:@"test" hostName:@"Giff.se" url:@"http://www.giff.se/publik/rss/chash/d8d85abe6c/item-cat/1%2C6%2C10%2C2%2C15%2C14/feed-rss.html"];

    //ENglish  Movie
    [categoryFilm addEnglishFeed:@"test" hostName:@"DVD News" url:@"http://feeds.movieweb.com/movieweb_dvdnews"];
    [categoryFilm addEnglishFeed:@"test" hostName:@"Apple.com -New trailers" url:@"http://trailers.apple.com/trailers/home/rss/newtrailers.rss"];
    [categoryFilm addEnglishFeed:@"test" hostName:@"Contactmusic.com -Film Trailers" url:@"http://www.contactmusic.com/rss/latest_film_trailers.xml"];
    [categoryFilm addEnglishFeed:@"test" hostName:@"Moviefone.com" url:@"http://news.moviefone.com/rss.xml"];
    [categoryFilm addEnglishFeed:@"test" hostName:@"Vh1.com -Latest News" url:@"http://www.vh1.com/news/home/modules/latest_news/rss/feed.jhtml"];
    [categoryFilm addEnglishFeed:@"test" hostName:@"Totalfilm.com -News" url:@"http://feeds.feedburner.com/totalfilm/news"];
    [categoryFilm addEnglishFeed:@"test" hostName:@"Filmjabber.com -Movie Trailers" url:@"http://feeds.filmjabber.com/Movie-sourcecomMovieTrailers"];
    [categoryFilm addEnglishFeed:@"test" hostName:@"Slashfilm.com" url:@"http://feeds2.feedburner.com/slashfilm"];

    //------------------------------------------------------------------------------------------------------------------------------------------------
    //Category -------------------------------------------Photo /Foto -------------------
    ChannelCategory *categoryPhoto = [[ChannelCategory alloc] initWithText:NSLocalizedString(@"CategoryPhotoKey", @"") imageName:@"photo"""];
    [categoryPhoto addSwedishChannel:@"test" hostName:@"Firstfoto, Aktuellt" url:@"http://www.firstfoto.se/kategorier/aktuellt/feed/"];
    [categoryPhoto addSwedishChannel:@"test" hostName:@"Firstfoto, Guider" url:@"http://www.firstfoto.se/kategorier/guider/feed/"];
    [categoryPhoto addSwedishChannel:@"test" hostName:@"Firstfoto, Systsemkameror" url:@"http://www.firstfoto.se/kategorier/fotonyheter/systemkameror-fotonyheter/feed/"];
    [categoryPhoto addSwedishChannel:@"test" hostName:@"Firstfoto, Nyheter" url:@"http://www.firstfoto.se/kategorier/fotonyheter/feed/"];
    [categoryPhoto addSwedishChannel:@"test" hostName:@"Firstfoto, Tester" url:@"http://www.firstfoto.se/kategorier/tester/feed/"];
    [categoryPhoto addSwedishChannel:@"test" hostName:@"Tidningenfoto.se" url:@"http://feeds.feedburner.com/tidningenfoto"];
    [categoryPhoto addSwedishChannel:@"test" hostName:@"Kamerabild.se, tester" url:@"http://www.kamerabild.se/rss"];
    [categoryPhoto addSwedishChannel:@"test" hostName:@"Kameravaskan.se" url:@"http://www.kameravaskan.se/feed/"];
    [categoryPhoto addSwedishChannel:@"test" hostName:@"Nordensfotoskola.com" url:@"http://www.nordensfotoskola.com/news/feed"];
    [categoryPhoto addSwedishChannel:@"test" hostName:@"Fotosidan.se" url:@"http://www.fotosidan.se/newsfeeds/page1articles.xml"];
    [categoryPhoto addSwedishChannel:@"test" hostName:@"Fotosidan.se, Senaste Anonnser" url:@"http://www.fotosidan.se/newsfeeds/classifieds.xml"];
    [categoryPhoto addSwedishChannel:@"test" hostName:@"Fotografiska.eu" url:@"http://fotografiska.eu/feed/?post_type=calendar"];



    //------------------------------------------------------------------------------------------------------------------------------------------------
    //Category -------------------------------------------Mix / Blandat ---------------------
    ChannelCategory *categoryMix = [[ChannelCategory alloc] initWithText:NSLocalizedString(@"CategoryMixKey", @"") imageName:@"mix"""];
    [categoryMix addSwedishChannel:@"test" hostName:@"Svenskasajter" url:@"http://svenskasajter.blogspot.com/feeds/posts/default/-/Nyheter"];
    [categoryMix addSwedishChannel:@"test" hostName:@"Pusha.se" url:@"http://www.pusha.se/rss/index...."];
    [categoryMix addSwedishChannel:@"test" hostName:@"Prylkoll.se" url:@"http://feedproxy.google.com/prylkoll/"];
    [categoryMix addSwedishChannel:@"test" hostName:@"Barn.se, roliga barnspel" url:@"http://www.barn.se/rss.php?type=newest_content&limit=15"];

    //ENglish
    [categoryMix addEnglishFeed:@"test" hostName:@"Dilbert.com, Most popular" url:@"http://feed.dilbert.com/dilbert/most_popular"];
    [categoryMix addEnglishFeed:@"test" hostName:@"Dilbert.com, Daily strip" url:@"http://feed.dilbert.com/dilbert/daily_strip"];
    [categoryMix addEnglishFeed:@"test" hostName:@"Playstation" url:@"http://www.rssmicro.com/rss.web?q=Playstation"];
    [categoryMix addEnglishFeed:@"test" hostName:@"Wired Top Stories" url:@"http://feeds.wired.com/wired/index"];
    [categoryMix addEnglishFeed:@"test" hostName:@"Designsponge" url:@"http://www.designsponge.com/feed"];
    [categoryMix addEnglishFeed:@"test" hostName:@"Joystiq.com" url:@"http://www.joystiq.com//rss.xml"];
    [categoryMix addEnglishFeed:@"test" hostName:@"Popsugar" url:@"http://feeds.feedburner.com/popsugar"];
    [categoryMix addEnglishFeed:@"test" hostName:@"Carscarscars.blogs.com" url:@"http://carscarscars.blogs.com/index/rss.xml"];
    [categoryMix addEnglishFeed:@"test" hostName:@"Celebuzz.com" url:@"http://feeds2.feedburner.com/celebuzz/Kggb"];
    //-----------------------------------------------------------------------------------------------------------------------------------------------

    //-----------------------------------------------------------------------------------------------------------------------------------------------
    //Category -------------------------------------------Vetenskap / Teknik --------------------------------------------------
    ChannelCategory *categoryTechnology = [[ChannelCategory alloc] initWithText:NSLocalizedString(@"CategoryScienceAndTechnicKey", @"") imageName:@"science"];
    [categoryTechnology addSwedishChannel:@"test" hostName:@"Kunskapsbloggen" url:@"http://www.kunskapsbloggen.se/feed/"];
    [categoryTechnology addSwedishChannel:@"test" hostName:@"Vetenskapsradion" url:@"http://api.sr.se/api/rssfeed/rssfeed.aspx?rssfeed=406"];
    [categoryTechnology addSwedishChannel:@"test" hostName:@"NyTeknik" url:@"http://www.nyteknik.se/?service=rss"];
    [categoryTechnology addSwedishChannel:@"test" hostName:@"Wikipedia, senaste ändringar" url:@"http://sv.wikipedia.org/w/index.php?title=Special:Senaste_%C3%A4ndringar&feed=rss"];
    [categoryTechnology addSwedishChannel:@"test" hostName:@"Historiesajten.se" url:@"http://www.historiesajten.se/rss.xml"];
    [categoryTechnology addSwedishChannel:@"test" hostName:@"Uppsalainitiativet" url:@"http://uppsalainitiativet.blogspot.com/feeds/posts/default?alt=rss"];
    [categoryTechnology addSwedishChannel:@"test" hostName:@"Tidningencurie.se" url:@"http://www.tidningencurie.se/4.13384c8f135aad61b55183e/12.13384c8f135aad61b551847.portlet?state=rss&sv.contenttype=text/xml;charset=UTF-8"];
    [categoryTechnology addSwedishChannel:@"test" hostName:@"Forskning.se" url:@"http://www.forskning.se/RSS"];


    //English
    [categoryTechnology addEnglishFeed:@"test" hostName:@"Nasa, news" url:@"http://www.nasa.gov/rss/breaking_news.rss"];
    [categoryTechnology addEnglishFeed:@"test" hostName:@"Nasa, Shuttle Updates" url:@"http://www.nasa.gov/rss/shuttle_updates.xml"];
    [categoryTechnology addEnglishFeed:@"test" hostName:@"BBC News, Technology" url:@"http://feeds.bbci.co.uk/news/technology/rss.xml"];
    [categoryTechnology addEnglishFeed:@"test" hostName:@"Sciencemag.org" url:@"http://www.sciencemag.org/rss/current.xml"];
    [categoryTechnology addEnglishFeed:@"test" hostName:@"Sciencenews.org" url:@"http://www.sciencenews.org/view/feed/name/all.rss"];
    [categoryTechnology addEnglishFeed:@"test" hostName:@"Popsci.com" url:@"http://www.popsci.com/rss.xml"];
    [categoryTechnology addEnglishFeed:@"test" hostName:@"Newscientist.com,60-sec" url:@"http://feeds.newscientist.com/60-seconds"];
    [categoryTechnology addEnglishFeed:@"test" hostName:@"Newscientist.com, Latest" url:@"http://feeds.newscientist.com/science-news"];
    [categoryTechnology addEnglishFeed:@"test" hostName:@"Newscientist.com, Daily" url:@"http://feeds.newscientist.com/today-on-ns"];

    //-----------------------------------------------------------------------------------------------
    //Category -------------------------------------------Travle/Resor ----------------------------------------------------------
    ChannelCategory *categoryTravel = [[ChannelCategory alloc] initWithText:NSLocalizedString(@"CategoryTravelKey", @"") imageName:@"travel"];
    [categoryTravel addSwedishChannel:@"test" hostName:@"Blueberrysprak.nu" url:@"http://www.blueberrysprak.nu/rss/rss.xml"];
    [categoryTravel addSwedishChannel:@"test" hostName:@"Vagabond.se" url:@"http://www.vagabond.se/rss.php"];
    [categoryTravel addSwedishChannel:@"test" hostName:@"Travelaid.se" url:@"http://www.travelaid.se/rss"];
    [categoryTravel addSwedishChannel:@"test" hostName:@"Sistaminutentips.se" url:@"http://www.sistaminutentips.se/feed/"];
    [categoryTravel addSwedishChannel:@"test" hostName:@"SvD.se" url:@"http://www.svd.se/articleexport/rss20/resor.xml"];
    [categoryTravel addSwedishChannel:@"test" hostName:@"Fritidsresor.nu" url:@"http://feeds.feedburner.com/fritidsresornu"];
    [categoryTravel addSwedishChannel:@"test" hostName:@"Aftonbladet.se" url:@"http://www.aftonbladet.se/resa/rss.xml"];
    [categoryTravel addSwedishChannel:@"test" hostName:@"DN.se" url:@"http://www.dn.se/resor/m/rss"];
    [categoryTravel addSwedishChannel:@"test" hostName:@"SvD.se" url:@"http://www.svd.se/resor/?service=rss"];
    [categoryTravel addSwedishChannel:@"test" hostName:@"Sydsvenskan.se" url:@"http://www.sydsvenskan.se/resor/?context=xml"];
    [categoryTravel addSwedishChannel:@"test" hostName:@"UD" url:@"http://www.regeringen.se/rss/avradan"];
    [categoryTravel addSwedishChannel:@"test" hostName:@"Res.se" url:@"http://res.se/rss"];

    //ENglish
    [categoryTravel addEnglishFeed:@"test" hostName:@"Travelchannel.com -Travel Ideas" url:@"http://www.travelchannel.com/Rss_Home/rssfeed?c=Travel%20Ideas"];
    [categoryTravel addEnglishFeed:@"test" hostName:@"Travelchannel.com -Video" url:@"http://travelchannel-cms.feeds.theplatform.com/ps/getRSS?client=Standard&PID=3RJi3f44L8ChyBdEYLqZeB2WxyB43X2a&startIndex=1&endIndex=500&sortField=lastModified&sortDescending=true"];
    [categoryTravel addEnglishFeed:@"test" hostName:@"New York City Travel" url:@"http://z.about.com/6/g/gonyc/b/rss2.xml"];
    [categoryTravel addEnglishFeed:@"test" hostName:@"Abcnews.com -Travelheadlines" url:@"http://feeds.abcnews.com/abcnews/travelheadlines"];
    [categoryTravel addEnglishFeed:@"test" hostName:@"Telegraph.co.uk" url:@"http://www.telegraph.co.uk/travel/rss"];
    [categoryTravel addEnglishFeed:@"test" hostName:@"Australia" url:@"http://www.telegraph.co.uk/travel/destinations/australiaandpacific/australia/rss"];
    [categoryTravel addEnglishFeed:@"test" hostName:@"Barcelona" url:@"http://www.telegraph.co.uk/travel/destinations/europe/spain/barcelona/rss"];
    [categoryTravel addEnglishFeed:@"test" hostName:@"Denmark" url:@"http://www.telegraph.co.uk/travel/destinations/europe/denmark/rss"];
    [categoryTravel addEnglishFeed:@"test" hostName:@"Europe" url:@"http://www.telegraph.co.uk/travel/destinations/europe/rss"];
    [categoryTravel addEnglishFeed:@"test" hostName:@"Finland" url:@"http://www.telegraph.co.uk/travel/destinations/europe/finland/rss"];
    [categoryTravel addEnglishFeed:@"test" hostName:@"China" url:@"http://www.telegraph.co.uk/travel/destinations/asia/china/rss"];

    //-----------------------------------------------------------------------------------------------
    //------ Category ----------------------------- Musik/Music ----------------------------------
    ChannelCategory *categoryMusic = [[ChannelCategory alloc] initWithText:NSLocalizedString(@"CategoryMusicKey", @"") imageName:@"music"];
    [categoryMusic addSwedishChannel:@"test" hostName:@"DN.se" url:@"http://www.nyhetsportalen.se/?t=1625&rss=1&na=15"];
    [categoryMusic addSwedishChannel:@"test" hostName:@"SvD.se Skivor" url:@"http://www.nyhetsportalen.se/?t=1172&rss=1&na=15"];
    [categoryMusic addSwedishChannel:@"test" hostName:@"Vimusiker.se" url:@"http://www.vimusiker.se/news_feed.xml"];
    [categoryMusic addSwedishChannel:@"test" hostName:@"Universalmusic.se" url:@"http://www.universalmusic.se/blogg/nyheter/rss"];
    [categoryMusic addSwedishChannel:@"test" hostName:@"Gaffa.se" url:@"http://gaffa.se/feeds"];
    [categoryMusic addSwedishChannel:@"test" hostName:@"Nutidamusik.com" url:@"http://www.nutidamusik.com/feed/"];
    [categoryMusic addSwedishChannel:@"test" hostName:@"Orkesterjournalen.com" url:@"http://www.orkesterjournalen.com/index.php?format=feed&type=rss"];
    [categoryMusic addSwedishChannel:@"test" hostName:@"Slavestate.se" url:@"http://www.slavestate.se/rss.xml"];
    [categoryMusic addSwedishChannel:@"test" hostName:@"Sonicmagazine.com" url:@"http://www.sonicmagazine.com/cache/rss20.xml"];
    [categoryMusic addSwedishChannel:@"test" hostName:@"Studio.idg.se" url:@"http://studio.idg.se/rss/senaste+nytt"];

    [categoryMusic addEnglishFeed:@"test" hostName:@"Musicfeeds" url:@"http://feeds.feedburner.com/MusicFeeds"];
    [categoryMusic addEnglishFeed:@"test" hostName:@"Interviews Reviews" url:@"http://www.contactmusic.com/rss/interviews_reviews.xml"];
    [categoryMusic addEnglishFeed:@"test" hostName:@"Latest Music Streams" url:@"http://www.contactmusic.com/rss/latest_music_streams.xml"];
    [categoryMusic addEnglishFeed:@"test" hostName:@"MTVNewsLatest" url:@"http://feeds.feedburner.com/MTVNewsLatest"];
    [categoryMusic addEnglishFeed:@"test" hostName:@"BBC.co.uk " url:@"http://www.bbc.co.uk/music/reviews/latest.rss"];
    [categoryMusic addEnglishFeed:@"test" hostName:@"Npr.org " url:@"http://www.npr.org/rss/rss.php?id=1039"];

    //Category -------------------------------------------Police/Polisen ----------------------------------------------------------
    //http://www.polisen.se/Aktuellt/RSS/
    ChannelCategory *categoryPolice = [[ChannelCategory alloc] initWithText:NSLocalizedString(@"CategoryPolice", @"") imageName:@"police"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se Pressmeddelanden hela-landet" url:@"http://polisen.se/Aktuellt/Pressmeddelanden/Pressmeddelanden-hela-landet/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se Nyheter hela-landet" url:@"http://polisen.se/Aktuellt/Nyheter/Nyheter-hela-landet/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se Handelser hela-landet" url:@"http://polisen.se/Aktuellt/Handelser/Handelser-i-hela-landet/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se Trafikovervakning hela-landet" url:@"http://polisen.se/Aktuellt/Trafikovervakning/Trafikovervakning-hela-landet/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se Youtube" url:@"http://gdata.youtube.com/feeds/base/users/polisen/uploads?alt=rss&v=2&orderby=published&client=ytapi-youtube-profile"];

    //http://polisen.se/Stockholms_lan/Aktuellt/RSS/Lokal-RSS---Nyheter/Lokala-RSS-lankar/Nyheter---Stockholm---RSS-lank/
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Stockholm Handelser" url:@"http://polisen.se/Stockholms_lan/Aktuellt/RSS/Lokal-RSS---Nyheter/Lokala-RSS-lankar/Nyheter---Stockholm---RSS-lank/"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Blekinge Handelser" url:@"http://www.polisen.se/Blekinge/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Blekinge/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Dalarna Handelser" url:@"http://www.polisen.se/Dalarna/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Dalarna/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Gotland Handelser" url:@"http://www.polisen.se/Gotlands_lan/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Gotland/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Gävleborg Handelser" url:@"http://www.polisen.se/Gavleborg/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Gavleborg/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Halland Handelser" url:@"http://www.polisen.se/Halland/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Halland/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Jämtland Handelser" url:@"http://www.polisen.se/Jamtland/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Jamtland/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Jönköping Handelser" url:@"http://www.polisen.se/Jonkopings_lan/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Jonkoping/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Kalmar Handelser" url:@"http://www.polisen.se/Kalmar_lan/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Kalmar-lan/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Kronoberg Handelser" url:@"http://www.polisen.se/Kronoberg/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Kronoberg/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Norrbotten Handelser" url:@"http://www.polisen.se/Norrbotten/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Norrbotten/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Skåne Handelser" url:@"http://www.polisen.se/Skane/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Skane/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Södermanland Handelser" url:@"http://www.polisen.se/Sodermanland/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Sodermanland/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Uppsala Handelser" url:@"http://www.polisen.se/Uppsala_lan/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Uppsala-lan/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Värmland Handelser" url:@"http://www.polisen.se/Varmland/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Varmland/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Västerbotten Handelser" url:@"http://www.polisen.se/Vasterbotten/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Vasterbotten/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Västernorrland Handelser" url:@"http://www.polisen.se/Vasternorrland/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Vasternorrland/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Västmanland Handelser" url:@"http://www.polisen.se/Vastmanland/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Vastmanland/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Västra Götaland Handelser" url:@"http://www.polisen.se/Vastra_Gotaland/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Vastra-Gotaland/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Örebro län Handelser" url:@"http://www.polisen.se/Orebro_lan/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Orebro-lan/?feed=rss"];
    [categoryPolice addSwedishChannel:@"test" hostName:@"Polisen.se -Östergötland Handelser" url:@"http://www.polisen.se/Ostergotland/sv/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Ostergotland/?feed=rss"];

    //English -------
    [categoryPolice addEnglishFeed:@"test" hostName:@"NYPD.gov, Press releases" url:@"http://www.nyc.gov/html/nypd/rss/pr_rss.xml"];
    [categoryPolice addEnglishFeed:@"test" hostName:@"Thames Valley Police News" url:@"http://www.thamesvalley.police.uk/rss-news.xml"];
    [categoryPolice addEnglishFeed:@"test" hostName:@"Thames Valley Police Press" url:@"http://www.thamesvalley.police.uk/rss-pressreleases.xml"];
    [categoryPolice addEnglishFeed:@"test" hostName:@"FBI.gov Stories" url:@"http://www.fbi.gov/news/stories/all-stories/rss.xml"];
    [categoryPolice addEnglishFeed:@"test" hostName:@"FBI.gov Current" url:@"http://www.fbi.gov/news/current/rss.xml"];
    [categoryPolice addEnglishFeed:@"test" hostName:@"FBI.gov Press" url:@"http://www.fbi.gov/news/pressrel/current/rss.xml"];

    //-----------------------------------------------------------------------------------------------
    //Category -------------------------------------------Humor/Humour 14 ----------------------------------------------------------
    ChannelCategory *categoryHumour = [[ChannelCategory alloc] initWithText:NSLocalizedString(@"CategoryHumour", @"") imageName:@"humor"];
    [categoryHumour addSwedishChannel:@"test" hostName:@"Roligaklipp.se" url:@"http://www.roligaklipp.se/index.xml"];
    [categoryHumour addSwedishChannel:@"test" hostName:@"Jesper.nu" url:@"http://jesper.nu/feed.xml"];
    [categoryHumour addSwedishChannel:@"test" hostName:@"Jesper.nu, Rolig Video" url:@"http://jesper.nu/video/feed.all.xml"];
    [categoryHumour addSwedishChannel:@"test" hostName:@"Jesper.nu, Roliga Bilder" url:@"http://jesper.nu/roliga-bilder/feed.xml"];
    [categoryHumour addSwedishChannel:@"test" hostName:@"Jesper.nu, Roliga Länkar" url:@"http://jesper.nu/lankar/feed.xml"];
    [categoryHumour addSwedishChannel:@"test" hostName:@"Lustigkurre" url:@"http://lustigkurre.svenskablogg.se/feed/"];

    //English -------
    [categoryHumour addEnglishFeed:@"test" hostName:@"Jokes4all.net" url:@"http://jokes4all.net/rss/540010513/jokes.xml"];
    [categoryHumour addEnglishFeed:@"test" hostName:@"Starjokes.com" url:@"http://www.starjokes.com/rss/new-jokes-rss.xml"];
    [categoryHumour addEnglishFeed:@"test" hostName:@"AllFunnyPictures.com" url:@"http://feeds.feedburner.com/AllFunnyPictures"];
    [categoryHumour addEnglishFeed:@"test" hostName:@"Dearjokes.net" url:@"http://dearjokes.net/rss"];
    [categoryHumour addEnglishFeed:@"test" hostName:@"Dailyjokes" url:@"http://dailyjokes.somelifeblog.com/feeds/posts/default?alt=rss"];
    [categoryHumour addEnglishFeed:@"test" hostName:@"Humorxl.com" url:@"http://rss.humorxl.com/"];
    [categoryHumour addEnglishFeed:@"test" hostName:@"50plus.com" url:@"http://www.50plus.com/feed/"];
    [categoryHumour addEnglishFeed:@"test" hostName:@"Collegehumor.com -Video" url:@"http://www.collegehumor.com/videos/rss"];
    [categoryHumour addEnglishFeed:@"test" hostName:@"Jokes2go.com" url:@"http://www.jokes2go.com/jspq.xml"];
    [categoryHumour addEnglishFeed:@"test" hostName:@"FunnyVideosRss" url:@"http://feeds.feedburner.com/FunnyVideosRss"];
    [categoryHumour addEnglishFeed:@"test" hostName:@"Videobash.com -Video" url:@"http://www.videobash.com/rss/video"];
    [categoryHumour addEnglishFeed:@"test" hostName:@"Videobash.com -Photo" url:@"http://www.videobash.com/rss/photo"];

    //Category -----------------------CategoryMode/Mode ----------------------------
    ChannelCategory *categoryFashion = [[ChannelCategory alloc] initWithText:NSLocalizedString(@"CategoryMode", @"") imageName:@"fashion"];
    [categoryFashion addSwedishChannel:@"test" hostName:@"Cafe.se" url:@"http://cafe.se/feed/"];
    [categoryFashion addSwedishChannel:@"test" hostName:@"Caminomagasin.se" url:@"http://www.caminomagasin.se/blog/feed"];
    [categoryFashion addSwedishChannel:@"test" hostName:@"Chic.se" url:@"http://chic.se/feed/"];
    [categoryFashion addSwedishChannel:@"test" hostName:@"Cosmopolitan.se" url:@"http://cosmopolitan.se/feed/"];
    [categoryFashion addSwedishChannel:@"test" hostName:@"Damernasvarld.se" url:@"http://damernasvarld.se/damernas/rss/"];
    [categoryFashion addSwedishChannel:@"test" hostName:@"Elle.se" url:@"http://elle.se/feed/"];
    [categoryFashion addSwedishChannel:@"test" hostName:@"Kingmagazine.se" url:@"http://www.kingmagazine.se/RSS/RSS-test/"];
    [categoryFashion addSwedishChannel:@"test" hostName:@"Mabra.com" url:@"http://mabra.com/wp-content/plugins/nextgen-gallery/xml/media-rss.php"];
    [categoryFashion addSwedishChannel:@"test" hostName:@"Posh24.se" url:@"http://www.posh24.se/feed"];     //http://www.posh24.se/feed
    [categoryFashion addSwedishChannel:@"test" hostName:@"Stureplan.se" url:@"http://stureplan.se/feed"];

    //English -------
    [categoryFashion addEnglishFeed:@"test" hostName:@"Style.com" url:@"http://www.style.com/homepage/rss"];
    [categoryFashion addEnglishFeed:@"test" hostName:@"Associatedcontent.com" url:@"http://www.associatedcontent.com/rss/recent_7.xml"];
    [categoryFashion addEnglishFeed:@"test" hostName:@"Mystyle.com -Ask Style" url:@"http://www.mystyle.com/syndication/feeds/rssfeeds/askstyle.xml"];
    [categoryFashion addEnglishFeed:@"test" hostName:@"Mystyle.com -Style Secret" url:@" http://www.mystyle.com/syndication/feeds/rssfeeds/style-secret.xml"];
    [categoryFashion addEnglishFeed:@"test" hostName:@"Mystyle.com -My Style" url:@"http://www.mystyle.com/syndication/feeds/rssfeeds/mystyle.xml"];
    [categoryFashion addEnglishFeed:@"test" hostName:@"Fashioncentral.pk, Ask Style" url:@"http://www.fashioncentral.pk/rss/top-stories.xml"];
    [categoryFashion addEnglishFeed:@"test" hostName:@"Refinery29.com" url:@"http://www.refinery29.com/index.xml"];
    [categoryFashion addEnglishFeed:@"test" hostName:@"Fashionmagazine.com" url:@"http://www.fashionmagazine.com/blogs/feed/"];
    [categoryFashion addEnglishFeed:@"test" hostName:@"Fashionsquad.com" url:@"http://www.fashionsquad.com/feed/"];
    [categoryFashion addEnglishFeed:@"test" hostName:@"Style.com -Homepage" url:@"http://www.style.com/homepage/rss"];
    [categoryFashion addEnglishFeed:@"test" hostName:@"Style.com -Latest" url:@"http://feeds.style.com/stylestudio?format=xml"];


    //Category -----------------------Category Mat och Dryck/Food & drink----------------------------
    ChannelCategory *categoryFood = [[ChannelCategory alloc] initWithText:NSLocalizedString(@"CategoryFoodDrink", @"") imageName:@"food"];
    [categoryFood addSwedishChannel:@"test" hostName:@"Alltommat.se" url:@"http://www.alltommat.se/rss"];
    [categoryFood addSwedishChannel:@"test" hostName:@"Alltomwhisky.se" url:@"http://www.alltomwhisky.se/feed/"];
    [categoryFood addSwedishChannel:@"test" hostName:@"Matochvanner.se" url:@"http://www.matochvanner.se/rss.xml"];
    [categoryFood addSwedishChannel:@"test" hostName:@"Matmagasinet.se" url:@"http://feeds2.feedburner.com/matmagasinet"];
    [categoryFood addSwedishChannel:@"test" hostName:@"Tasteline.se -Middagstipset" url:@"http://www.tasteline.com/rss/middagstipset/?imgitem=1"];
    [categoryFood addSwedishChannel:@"test" hostName:@"Tasteline.se Veckansmeny" url:@"http://www.tasteline.com/rss/veckansmeny/?imgItem=1"];

    //English -------
    [categoryFood addEnglishFeed:@"test" hostName:@"Foodbev.com" url:@"http://www.foodbev.com/rss"];
    [categoryFood addEnglishFeed:@"test" hostName:@"Delish.com" url:@"http://www.delish.com/recipes/recipes-rss"];
    [categoryFood addEnglishFeed:@"test" hostName:@"Npr.org" url:@"http://www.npr.org/rss/podcast.php?id=1053"];
    [categoryFood addEnglishFeed:@"test" hostName:@"Food.org" url:@"http://www.food.com/rss?"];


    //Category -----------------------Programming/Programmering----------------------------
    ChannelCategory *categoryProgramming = [[ChannelCategory alloc] initWithText:NSLocalizedString(@"CategoryProgramming", @"") imageName:@"programming"];
    [categoryProgramming addSwedishChannel:@"test" hostName:@"Javabloggen.se" url:@"http://www.javabloggen.se/feed/"];
    [categoryProgramming addSwedishChannel:@"test" hostName:@"Mathias Amnell -iPhone" url:@"http://feeds.feedburner.com/MathiasAmnell"];
    [categoryProgramming addSwedishChannel:@"test" hostName:@"Webforum.nu" url:@"http://www.webforum.nu/external.php?type=RSS2"];
    [categoryProgramming addSwedishChannel:@"test" hostName:@"Wn.se -Webmaster" url:@"http://www.wn.se/external.php?type=RSS2"];


    //English -------
    [categoryProgramming addEnglishFeed:@"test" hostName:@"Stackoverflow.com" url:@"http://stackoverflow.com/feeds"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"Techmeme.com" url:@"http://www.techmeme.com/feed.xml"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"ASP.net -Team" url:@"http://weblogs.asp.net/aspnet-team/rss.aspx"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"ASP.net -Forum" url:@"http://forums.asp.net/f/rss/"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"ASP.net -News" url:@"http://www.asp.net/rss/news"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"ASP.net -Spotlight" url:@"http://www.asp.net/rss/spotlight"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"ASP.net -Events" url:@" http://www.asp.net/rss/events"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"ASP.net -Tutorials" url:@"http://www.asp.net/rss/tutorials"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"ASP.net -Videos" url:@"http://www.asp.net/rss/videos"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"Javarss.com" url:@"http://www.javarss.com/feed/"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"Javacodeexamples.com" url:@"http://www.javacodeexamples.com/feed/"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"Eclipse.org -News" url:@"http://www.eclipse.org/home/eclipsenews.rss"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"Visualstudiomagazine.com, C# Corner" url:@"http://visualstudiomagazine.com/rss-feeds/c-corner.aspx"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"Visualstudiomagazine.com, News" url:@"http://visualstudiomagazine.com/rss-feeds/news.aspx"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"Javaworld.com, Latest" url:@"http://www.javaworld.com/index.xml"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"Javaprogrammingforums.com" url:@"http://www.javaprogrammingforums.com/external.php?type=RSS2"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"Javaworld.com, Core" url:@"http://www.javaworld.com/community/node/1390/feed"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"Javaworld.com, Enterprise" url:@"http://www.javaworld.com/community/node/1349/feed"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"Javaworld.com, Mobile" url:@"http://www.javaworld.com/community/node/1351/feed"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"Javaworld.com, Beginner" url:@"http://www.javaworld.com/community/node/1253/feed"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"Javaworld.com, Tools" url:@"http://www.javaworld.com/community/node/1350/feed"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"TheServerSide.com, News" url:@"http://www.theserverside.com/rss/forum.tss?forum_id=2"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"Bitpipe.com, News" url:@"http://www.bitpipe.com/data/bpXchange?b=ka_bp_internetworking&d=31&f=rss&u=rss"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"Scala-lang.org, Frontpage" url:@"http://www.scala-lang.org/rss.xml"];
    [categoryProgramming addEnglishFeed:@"test" hostName:@"Cocoadevcentral.com, Objective-C" url:@"http://cocoadevcentral.com/index.rdf"];

    //--------------------------------------------------------------------------------------------
    NSMutableArray *channelCategoriesTemp = [[NSMutableArray alloc] init];

    [channelCategoriesTemp addObject:categoryNews];
    [channelCategoriesTemp addObject:categorySport];
    [channelCategoriesTemp addObject:categoryComputer];
    [channelCategoriesTemp addObject:categoryBlog];
    [channelCategoriesTemp addObject:categoryEconomy];
    [channelCategoriesTemp addObject:categoryFilm];
    [channelCategoriesTemp addObject:categoryPhoto];
    [channelCategoriesTemp addObject:categoryMix];
    [channelCategoriesTemp addObject:categoryTechnology];
    [channelCategoriesTemp addObject:categoryTravel];
    [channelCategoriesTemp addObject:categoryMusic];
    [channelCategoriesTemp addObject:categoryPolice];
    [channelCategoriesTemp addObject:categoryHumour];
    [channelCategoriesTemp addObject:categoryFashion];
    [channelCategoriesTemp addObject:categoryFood];
    [channelCategoriesTemp addObject:categoryProgramming];

    NSSortDescriptor *sortByCategoryName = [[NSSortDescriptor alloc] initWithKey:@"categoryName" ascending:YES];
    [channelCategoriesTemp sortUsingDescriptors:[NSArray arrayWithObject:sortByCategoryName]];

    [categoryNews sortFeeds];
    [categorySport sortFeeds];
    [categoryComputer sortFeeds];
    [categoryBlog sortFeeds];
    [categoryEconomy sortFeeds];
    [categoryFilm sortFeeds];
    [categoryPhoto sortFeeds];
    [categoryMix sortFeeds];
    [categorySport sortFeeds];
    [categoryTravel sortFeeds];
    [categoryMusic sortFeeds];
    [categoryPolice sortFeeds];
    [categoryHumour sortFeeds];
    [categoryFashion sortFeeds];
    [categoryFood sortFeeds];
    [categoryProgramming sortFeeds];


    [self rebuildChannelIndex:channelCategoriesTemp];

}

- (void)rebuildChannelIndex:(NSMutableArray *)channelCategoriesTemp {

    //FIXME Timeer profile??
  //  NSLog(@"rebuildChannelIndex %d", [channelCategoriesTemp count]);

    NSMutableDictionary *channelsByChannelURLTemp = [[NSMutableDictionary alloc] init];

    NSMutableDictionary *channelCategoryByCategoryNameTemp = [[NSMutableDictionary alloc] init];

    for (ChannelCategory *cat in channelCategoriesTemp) {
        [channelCategoryByCategoryNameTemp setObject:cat forKey:cat.categoryName];

        for (Channel *c in cat.channels) {
            [channelsByChannelURLTemp setObject:c forKey:c.url];
        }
    }

    [self setChannelCategoryByCategoryName:channelCategoryByCategoryNameTemp];
    [self setChannelCategories:channelCategoriesTemp];
    [self setChannelsByChannelURL:channelsByChannelURLTemp];
}


- (BOOL)isShowAllFilter:(NSString *)categoryName {

    if ([categoryName isEqualToString:NSLocalizedString(NO_CATEGORY_FILTER_KEY, @"")]) {
        return TRUE;
    } else {
        return FALSE;
    }
}

- (ChannelCategory *)getChannelCategoryAtIndex:(NSInteger)index {
    return [_channelCategories objectAtIndex:index];
}

- (ChannelCategory *)getCurrentChannelCategory {

    NSString *currentCategory = [self getSelectedCategoryFilterName];

    ChannelCategory *channelCategory = [_channelCategoryByCategoryName objectForKey:currentCategory];
    return channelCategory;//   nil = All category filter
}

- (ChannelCategory *)getChannelCategoryByName:(NSString *)name {
    return [_channelCategoryByCategoryName objectForKey:name];
}

- (BOOL)isCurrentFilterShowAll {

    NSString *selectedCategoryName = [self getSelectedCategoryFilterName];
    return [self isShowAllCategory:selectedCategoryName];
}

- (NSString *)getSelectedCategoryFilterName {

    if (_activeCategoryFilterName) {
        return _activeCategoryFilterName;
    }

    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *documentsDirectory = [paths objectAtIndex:0];
    NSString *dataFile = [documentsDirectory stringByAppendingPathComponent:
            FILE_NAME_FILTER_SELECT];
    NSString *storedActiveCategoryFilterName = [NSKeyedUnarchiver unarchiveObjectWithFile:dataFile];


    if (storedActiveCategoryFilterName == nil) {
        storedActiveCategoryFilterName = NSLocalizedString(NO_CATEGORY_FILTER_KEY, @"");
    }

    [self setActiveCategoryFilterName:storedActiveCategoryFilterName];

    return storedActiveCategoryFilterName;
}

- (void)setSelectedCategoryFilterName:(NSString *)name {
    //NSLog(@"setSelectedCategoryFilterName() Store on disk.%@..", name);

    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *documentsDirectory = [paths objectAtIndex:0];
    NSString *dataFile = [documentsDirectory stringByAppendingPathComponent:FILE_NAME_FILTER_SELECT];

    [NSKeyedArchiver archiveRootObject:name toFile:dataFile];

    [self setActiveCategoryFilterName:name];

}

- (void)deselectChannelAndChannelCategory:(NSString *)channelURL {

    Channel *channel = [_channelsByChannelURL objectForKey:channelURL];
    ChannelCategory *locatedCategory = [_channelCategoryByCategoryName objectForKey:channel.articleCategoryName];
    channel.selected = FALSE;

    BOOL hasSelectedChannels = FALSE;
    for (Channel *channel in [locatedCategory channels]) {

        if ([self isChannelHidden:channel]) {
            continue;
        }

        if (channel.selected) {
            hasSelectedChannels = TRUE;
            break;
        }
    }

    if (!hasSelectedChannels) {
        NSLog(@"All channles for category %@ are deselected", locatedCategory.categoryName);
        locatedCategory.selected = FALSE;
    }

    //FIXME notify if all channels in category are deselected

    [self saveChannelCategoriesToDisk];

    if (!hasSelectedChannels) {
        [[MasterController defaultMasterController] showNoChannelsSelectedAlert];//TEST
    }
}

- (void)selectChannelAndCategory:(NSString *)channelURL {

    Channel *channel = [_channelsByChannelURL objectForKey:channelURL];
    ChannelCategory *channelCategory = [_channelCategoryByCategoryName objectForKey:channel.articleCategoryName];

    channel.selected = TRUE;
    channelCategory.selected = TRUE;


    [self saveChannelCategoriesToDisk];
}


- (BOOL)isSelected:(NSString *)channelURL {

    Channel *channel = [_channelsByChannelURL objectForKey:channelURL];
    ChannelCategory *channelCategory = [_channelCategoryByCategoryName objectForKey:channel.articleCategoryName];

    BOOL b = [channelCategory isChannelSelectedForCurrentLanguage:channel];
//    NSLog(@"isSelected %d channel=%@ selected=%d hash=%d", b, channelURL, channel.selected, channel.hash);
    return b;

}

- (BOOL)isChannelHidden:(Channel *)channel {
    NSUInteger defaultLanguageForCurrentUser = [[ApplicationConfig defaultApplicationConfig] getDefaultLanguageForCurrentUser];

    return defaultLanguageForCurrentUser != [channel.language intValue] && defaultLanguageForCurrentUser != LANG_BOTH;
}


- (NSMutableArray *)getSelectedChannelsForCurrentCategory {

    NSString *selectedCategoryName = [self getSelectedCategoryFilterName];

    NSArray *onlySelectedCategory = nil;
    if ([self isShowAllCategory:selectedCategoryName]) {
        onlySelectedCategory = _channelCategories;
    } else {
        NSPredicate *predicate = [NSPredicate predicateWithFormat:@"%K like %@",
                                                                  @"categoryName", selectedCategoryName];
        onlySelectedCategory = [_channelCategories filteredArrayUsingPredicate:predicate];
    }

    NSMutableArray *result = [[NSMutableArray alloc] init];

    for (ChannelCategory *category in onlySelectedCategory) {

        if (!category.selected) {
            continue;
        }

        NSMutableSet *channelsForCurrentLanguage = [category getSelectedChannelsForCurrentLanguage];

        [result addObjectsFromArray:[channelsForCurrentLanguage allObjects]];
    }

    return result;
}


- (BOOL)isShowAllCategory:(NSString *)categoryName {

    if ([categoryName isEqualToString:NSLocalizedString(NO_CATEGORY_FILTER_KEY, @"")]) {
        return TRUE;
    }

    return false;

}


- (NSInteger)getCurrentChannelCategoryIndex {

    NSString *currentCategory = [self getSelectedCategoryFilterName];

    int index = 0;
    for (ChannelCategory *cat in _channelCategories) {
        if ([cat.categoryName isEqualToString:currentCategory]) {
            return index;
        }

        index++;
    }

    return 0;//All category filter
}

- (NSInteger)getCurrentSelectedCategoryIndex {

    NSString *currentCategory = [self getSelectedCategoryFilterName];

    NSMutableArray *selectedCategories = [self getAllSelectedCategories];

    int index = 0;
    for (ChannelCategory *cat in selectedCategories) {
        if ([cat.categoryName isEqualToString:currentCategory]) {
            return (NSInteger) index;
        }

        index++;
    }

    return 0;//All category filter
}

- (NSInteger)countSelectedCategories {
    return [[self getAllSelectedCategories] count];
}

- (ChannelCategory *)getSelectedChannelCategoryAtIndex:(NSInteger)index {

    NSMutableArray *array = [self getAllSelectedCategories];

    return [array objectAtIndex:index];

}

- (NSMutableArray *)getAllSelectedCategories {

    NSMutableArray *result = [[NSMutableArray alloc] init];
    for (ChannelCategory *cat in _channelCategories) {
        if (cat.selected) {
            [result addObject:cat];
        }
    }

    //ChannelCategory *showAllProxy = [[ChannelCategory alloc] initWithText:NSLocalizedString(NO_CATEGORY_FILTER_KEY, @"") imageName:@"show-all"];

    [result insertObject:_showAllChannelCategoryProxy atIndex:0];

    return result;
}


@end
