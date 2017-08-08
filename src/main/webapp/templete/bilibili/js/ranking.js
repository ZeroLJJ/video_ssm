(function() {
    var a = [];
    var i = {};
    var e = {};
    var n = ["all", "animel", "music", "dance", "game", "tech", "life", "kichiku", "fashion", "ent", "film", "tv", null, "bangumi"];
    window.rankPanel = {
        main: function() {
            this.init()
        },
        data: {},
        init: function() {
            this.checkPath();
            this.build();
            this.watch()
        },
        grow: function() {
            $("body").animate({
                scrollTop: 0
            }, 100);
            $(".main-inner").children().each(function(a, e) {
                var n = 1;
                for (var t in i) {
                    if (e == i[t][0]) {
                        n = 0
                    }
                }
                if (n) {
                    $(e).remove()
                }
            });
            $(".main-inner").append(i.sidebar).append(i.title).append(i.main)
        },
        checkPath: function() {
            a = location.hash.split("/");
            a[0] = a[0] === "" ? "all" : v(a[0])
        },
        build: function() {
            i.sidebar = t();
            i.title = l();
            i.main = r();
            this.grow()
        },
        watch: function() {
            var e = this;a
            $(document).on("click", ".side-bar .side-bar-tag[data]", function(n) {
                var t = $(n.currentTarget);
                if (t.hasClass("on") && a.length <= 1) {
                    return
                } else {
                    t.siblings().removeClass("on");
                    t.addClass("on");
                    a = [t.attr("data")];
                    if (t.attr("data") === "bangumi") {
                        a[1] = "all"
                    }
                    location.hash = a.join("/");
                    i.main = r();
                    i.title = l();
                    e.grow()
                }
            });
            $(document).on("click", ".main .ranking-list *[data-istag][data-id]", function(n) {
                var t = $(n.currentTarget);
                var c = t.attr("data-istag");
                var s = t.attr("data-id");
                if (c === "1") {
                    if (t.hasClass("title")) {
                        window.open(t.siblings("a").attr("href"))
                    }
                } else if (s == "bangumi") {
                    a = ["bangumi", "all"],
                        i.main = r();
                    i.title = l();
                    $(".side-bar li").removeClass("on");
                    $(".side-bar li[data=" + s + "]").addClass("on");
                    e.grow()
                } else {
                    if (a[0] == "all" || a.length === 0) {
                        if (s == "all") {
                            a = [s, "all", "all", "1"]
                        } else {
                            a = [s]
                        }
                    } else {
                        a = [s, "all", "all", "1"]
                    }
                    location.hash = a.join("/");
                    i.main = r();
                    i.title = l();
                    $(".side-bar li").removeClass("on");
                    $(".side-bar li[data=" + s + "]").addClass("on");
                    e.grow()
                }
            });
            $(document).on("click", ".main-title .filter-dock li", function(n) {
                var t = $(n.currentTarget);
                var l;
                if (t.hasClass("on")) {
                    return
                } else {
                    l = t.hasClass("left") ? "left" : "right";
                    t.siblings("." + l).removeClass("on");
                    t.addClass("on");
                    a[l == "left" ? 2 : 3] = t.attr("data");
                    location.hash = a.join("/");
                    i.main = r();
                    e.grow()
                }
            });
            $(document).on("click", ".main-title .filter-dock", function(a) {
                a.stopPropagation()
            });
            $(document).on("click", ".main-title.min-title", function() {
                if (a[0] === "bangumi") {
                    return
                }
                a = [location.hash = a[0]];
                i.main = r();
                i.title = l();
                e.grow()
            });
            $(window).scroll(function() {
                var a = $(document).height() - ($(window).height() + $(document).scrollTop());
                var i = $(document).scrollTop();
                if (i > $(window).height()) {
                    $(".rocket").fadeIn()
                } else {
                    $(".rocket").fadeOut()
                }
                if (a > 450) {
                    $(".rocket").css("bottom", "100px")
                } else {
                    $(".rocket").css("bottom", "260px")
                }
                if ($(window).height() < 940) {
                    if (a < 260) {
                        $(".side-bar-holder").css({
                            top: "initial",
                            bottom: "260px"
                        })
                    } else {
                        $(".side-bar-holder").css({
                            top: "167px",
                            bottom: "initial"
                        })
                    }
                }
            });
            $(window).on("hashchange", function() {
                var n = 1;
                var c = location.hash.split("/");
                c[0] = v(c[0]);
                for (var s in a) {
                    if (a[s] != c[s]) {
                        n = 0
                    }
                }
                if (!n) {
                    a = c;
                    i.sidebar = t();
                    i.main = r();
                    i.title = l();
                    e.grow()
                }
            });
            var n = {
                state: 0,
                clock: null
            };
            var c = location.protocol == "https" ? "https//static-s.bilibili.com" : "http://static.hdslb.com";
            $(document).on("mouseenter", ".rocket", function(a) {
                var i = $(a.currentTarget);
                n.clock = setInterval(function() {
                    i.css({
                        "background-image": "url(" + c + '"/bl2se/images/rocket_frame.png")',
                        "background-position": n.state++ % 4 * -150 + "px 0px"
                    })
                }, 100)
            }).on("mouseout", ".rocket", function(a) {
                clearInterval(n.clock);
                $(a.currentTarget).css({
                    "background-image": "url(" + c + '"/bl2se/images/rocket_top.png")',
                    "background-position": "initial"
                })
            }).on("click", ".rocket", function() {
                $("body").animate({
                    scrollTop: 0
                }, 100)
            })
        }
    };
    function t() {
        var i = v(a[0]);
        var e = $('<div class="side-bar-holder"><ul class="side-bar"><li data="all" class="side-bar-tag">ȫվ</li><li data="animel" class="side-bar-tag">    </li><li data="bangumi" class="side-bar-tag">    </li><li data="music" class="side-bar-tag">    </li><li data="dance" class="side-bar-tag"> 赸</li><li data="game" class="side-bar-tag">  Ϸ</li><li data="tech" class="side-bar-tag"> Ƽ </li><li data="kichiku" class="side-bar-tag">    </li><li data="life" class="side-bar-tag">    </li><li data="ent" class="side-bar-tag">    </li><li data="fashion" class="side-bar-tag">ʱ  </li><li data="film" class="side-bar-tag">  Ӱ</li><li data="tv" class="side-bar-tag">   Ӿ </li></ul></div>');
        i ? $("li[data=" + i + "]", e).addClass("on") : $("li:eq(1)", e).addClass("on");
        return e
    }
    function l() {
        var i;
        if (a[0] === "") {
            i = $('<div class="main-title all"></div>')
        } else if (a.length === 1) {
            i = $('<div class="main-title"></div>');
            i.addClass(v(a[0]))
        } else if (a.length > 1 && a[1] == "all") {
            if (a[0] == "bangumi") {
                i = $('<div class="main-title min-title"><div class="filter-dock"><ul><li class="right" data="30">      </li><li class="right" data="7">      </li><li class="right" data="3">        </li><li class="right clear-margin" data="1">      </li></ul></div></div>')
            } else {
                i = $('<div class="main-title min-title"><div class="filter-dock"><ul><li class="left" data="all">ȫ  </li><li class="left" data="origin">ԭ  </li><li class="left" data="rookie">    </li><li class="right" data="30">      </li><li class="right" data="7">      </li><li class="right" data="3">        </li><li class="right clear-margin" data="1">      </li></ul></div></div>')
            }
            i.addClass(v(a[0]));
            $("li.left[data=" + a[2] + "]", i).addClass("on");
            $("li.right[data=" + a[3] + "]", i).addClass("on");
            if ($("li.left.on", i).length === 0) {
                $("li.left:eq(0)", i).addClass("on")
            }
            if ($("li.right.on", i).length === 0) {
                $("li.right:eq(3)", i).addClass("on")
            }
        } else {
            i = $('<div class="main-title all"></div>')
        }
        return i
    }
    function r() {
        var i = v(a[0]);
        var e, t, l, r;
        e = $('<div class="main"></div>');
        if (a.length <= 1) {
            if (a[0] === "" || i === "all") {
                t = s(3);
                e.append(t);
                t = s(3);
                e.append(o());
                e.append(t);
                e.append(k());
                t = s(6).addClass("floors");
                e.append(t);
                l = e.children().hide();
                r = u();
                e.append(r);
                _({
                    url: "//api.bilibili.com/x/tag/ranking/region",
                    dataType: "jsonp",
                    timeout: 15e3,
                    success: function(a) {
                        l.show();
                        r.remove();
                        var i = a && a.data || {};
                        f.store = [];
                        for (var e in i) {
                            if (e >= $(".ranking-list").length) {
                                t.append(c(i[e]))
                            } else {
                                c(i[e], $(".ranking-list").eq(e))
                            }
                        }
                        f.patrol()
                    },
                    error: function() {
                        $(".loading,.loading-holder", r).addClass("die")
                    }
                })
            } else {
                t = s(3);
                e.append(t);
                e.append(k());
                l = e.children().hide();
                r = u();
                e.append(r);
                _({
                    url: "//api.bilibili.com/x/tag/ranking/region",
                    dataType: "jsonp",
                    timeout: 1e4,
                    data: {
                        rid: n.indexOf(a[0]),
                        jsonp: "jsonp"
                    },
                    success: function(i) {
                        l.show();
                        r.remove();
                        var e = i && i.data || {};
                        e[0].id = b(a[0]);
                        f.store = [];
                        for (var n in e) {
                            if (e.hasOwnProperty(n)) {
                                if (n > 5) {
                                    break
                                }
                                e[n].id = b(a[0]);
                                c(e[n], $(".ranking-list").eq(n))
                            }
                        }
                        f.patrol()
                    },
                    error: function() {
                        $(".loading", r).addClass("die")
                    }
                })
            }
        } else {
            e.addClass("video-list");
            var g = "//www.bilibili.com/index/rank/all-1.json";
            if (a[0] == "all") {
                g = "//www.bilibili.com/index/rank/" + (a[2] || "all") + "-" + (a[3] || "1") + ".json"
            } else {
                g = "//www.bilibili.com/index/rank/" + (a[2] || "all") + "-" + (a[3] || "1") + "-" + b(a[0]) + ".json"
            }
            r = u();
            e.append(r);
            $.getJSON(g, function(a) {
                r.remove();
                var i = a && a.rank && a.rank.list || [];
                var n = [];
                for (var t in i) {
                    e.append(d(i[t]))
                }
                $(".video-block .cover:eq(0)", e).append('<div class="rank-number">1</div>');
                $(".video-block .cover:eq(1)", e).append('<div class="rank-number">2</div>');
                $(".video-block .cover:eq(2)", e).append('<div class="rank-number">3</div>')
            }).error(function() {
                $(".wait-tip", e).text("    ʧ  ")
            })
        }
        e.append("<div class='rocket-launch'><div class='rocket'></div></div>");
        return e
    }
    function c(i, e) {
        var n;
        var t;
        if (e && e[0]) {
            $(".head .title", e).text(i.target_name);
            $(".head .icon", e).addClass(i.is_tag ? "tag" : g(i.target_id));
            $(".head .more,.head .title", e).attr("data-istag", i.is_tag).attr("data-id", i.is_tag ? i.target_id : g(i.target_id));
            $("ul.list li", e).each(function(a, i) {
                $(".title a", i).text();
                $(".title a", i).attr("href")
            });
            $(".desc-button", e).text(i.is_tag ? "     ǩҳ 鿴" : " 鿴       а ").attr("data-istag", i.is_tag).attr("data-id", i.is_tag ? i.target_id : g(i.target_id));
            if (i.is_tag) {
                $(".head .more,.desc-button", e).attr("href", "http://search.bilibili.com/all?keyword=" + encodeURI(i.target_name)).attr("target", "_blank")
            }
            if (i.is_tag) {
                n = "//www.bilibili.com/index/tag/" + b(a[0]) + "/" + i.target_id + "/rank.json"
            } else {
                if (i.target_id) {
                    n = "//www.bilibili.com/index/rank/all-1-" + i.target_id + ".json"
                } else {
                    n = "//www.bilibili.com/index/rank/all-1.json"
                }
            }
            e.children().hide();
            t = u();
            e.append(t);
            $.data(e, "obj", i);
            f.watch(e, i)
        } else {
            var l = $('<div class="ranking-list"><div class="head"><div class="icon"></div><div class="title-wrap"><div class="title"></div><a class="more">    </a></div></div><ul class="list"></ul><div class="foot"><a class="desc-button"></a></div></div>');
            var r = [];
            for (var s = 0; s < 10; s++) {
                r.push('<li class="empty"><div class="icon">' + (s > 2 ? s + 1 : "") + '</div><div class="image-holder"><a target=\'_blank\'><img></a></div><div class="title"><a target="_blank"></a></div><div class="rank-arrow"></div></li>')
            }
            $("ul", l).append(r.join(""));
            $("li .icon:eq(0)", l).addClass("top top1");
            $("li .icon:eq(1)", l).addClass("top top2");
            $("li .icon:eq(2)", l).addClass("top top3");
            $("li:last", l).addClass("clear-border");
            if (i) {
                c(i, l)
            }
            return l
        }
    }
    function s(a) {
        var i = $("<div class='ranking-list-sheet'></div>");
        if (typeof a == "number" && a > 0) {
            for (var e = 0; e < a; e++) {
                i.append(c())
            }
        }
        return i
    }
    function o(a, i) {
        var e = [];
        var n = [];
        var t;
        if (a) {
            for (var l in a) {
                if (a.hasOwnProperty(l)) {
                    t = a[l];
                    e[l] = $('<a class="bangumi-block" target="_blank" href="http://bangumi.bilibili.com/anime/' + t.season_id + '" style="background-color:#' + t.color + '"><div class="portrait" style="background-image:url(' + t.cover + ')"></div><span target="_blank"  class="title">' + (t.title || "") + '</span><div class="data-cell line"><div> ܲ   ' + m(t.play_count) + '</div></div><div class="data-cell"><div>׷      ' + m(t.follow_count) + '</div></div><div class="tag-list"></div></a>');
                    i.append(e[l]);
                    for (var r in t.tags) {
                        if (t.tags.hasOwnProperty(r)) {
                            if (r >= 3) {
                                break
                            }
                            var c = t.tags[r] || {};
                            $(".tag-list", e[l]).append('<a target="_blank" href="http://search.bilibili.com/all?keyword=' + encodeURI(c.tag_name) + '">' + c.tag_name + "</a>")
                        }
                    }
                }
            }
        } else {
            i = $('<div class="bangumi-recommend-banner"></div>');
            _({
                url: "//api.bilibili.com/x/tag/ranking/bangumi",
                dataType: "jsonp",
                success: function(a) {
                    var e = a.data || {};
                    o(e, i)
                },
                error: function() {}
            });
            return i
        }
    }
    function k(i, e) {
        if (e) {
            var n;
            for (var t in i) {
                if (i.hasOwnProperty(t)) {
                    n = i[t];
                    e.append('<a class="tag" target="_blank" href="http://search.bilibili.com/all?keyword=' + encodeURI(n.tag_name) + '">' + n.tag_name + "</a>")
                }
            }
        } else {
            e = $('<div class="tag-recommend-banner"><div class="icon">   ű ǩ</div></div>');
            _({
                url: "//api.bilibili.com/x/tag/ranking/hots",
                dataType: "jsonp",
                data: {
                    type: b(a[0]),
                    jsonp: "jsonp"
                },
                success: function(a) {
                    var i = a.data || {};
                    k(i, e)
                }
            });
            return e
        }
    }
    function d(a) {
        var i = $('<div class="video-block"><a class="cover" target="_blank"><img><div class="total-time"></div></a><div class="info-area"><a class="title" target="_blank"></a><ul class="info"><li><i class=\'b-icon b-icon-v-play\'></i><span></span></li><li><i class=\'b-icon b-icon-v-dm\'></i><span></span></li><li class="author-holder"><i class=\'b-icon b-icon-v-author\'></i><span></span></li></ul></div></div>');
        $(".total-time", i).text(a.duration);
        $(".cover", i).attr("href", "http://www.bilibili.com/video/av" + a.aid);
        $(".cover img", i).attr("src", a.pic);
        $(".title", i).text(a.title).attr("href", "http://www.bilibili.com/video/av" + a.aid);
        $(".b-icon-v-play+span", i).text(a.play);
        $(".b-icon-v-dm+span", i).text(a.video_review);
        $(".b-icon-v-author+span", i).text(a.author);
        return i
    }
    function _(a) {
        var i = {
            data: {
                jsonp: "jsonp"
            },
            xhrFields: {
                withCredentials: true
            },
            dataType: "jsonp",
            type: "get"
        };
        $.extend(i, a);
        $.ajax(i)
    }
    function g(a) {
        var i = {
            0: "all",
            1: "animel",
            33: "bangumi",
            3: "music",
            129: "dance",
            4: "game",
            36: "tech",
            119: "kichiku",
            160: "life",
            5: "ent",
            155: "fashion",
            23: "film",
            11: "tv"
        };
        return i[a]
    }
    function b(a) {
        a = v(a);
        var i = {
            all: 0,
            animel: 1,
            bangumi: 33,
            music: 3,
            dance: 129,
            game: 4,
            tech: 36,
            kichiku: 119,
            life: 160,
            ent: 5,
            fashion: 155,
            film: 23,
            tv: 11
        };
        return i[a]
    }
    function v(a) {
        var i;
        if (!a || !a[0]) {
            return ""
        } else {
            i = a.match(/^#(.*)/);
            i = i && i[1] || a;
            return i
        }
    }
    function m(a) {
        if (a < 1e4) {
            return a
        } else {
            return parseInt(a / 1e4, 10) + "." + parseInt(a / 1e3 % 10, 10) + "  "
        }
    }
    function u() {
        return $("<div class='loading-holder'><div class='loading '></div><span>    ʧ  </span></div>")
    }
    var f = {
        store: [],
        watch: function(a, i) {
            this.store.push({
                ele: a,
                top: a.offset() && a.offset().top || 0,
                obj: i
            })
        },
        patrol: function() {
            var i, e, n, t, l, r, c = [];
            var s = this.store;
            var o = $(window).scrollTop() + $(window).height();
            for (var k in s) {
                if (s.hasOwnProperty(k)) {
                    if (!s[k]) {
                        continue
                    }
                    n = s[k];
                    r = n.ele.offset().top;
                    if (r < o + 100 || navigator.userAgent.match(/MSIE/i)) {
                        t = $(".loading-holder", n.ele);
                        l = n.ele;
                        i = n.obj;
                        s[k] = null;
                        if (i.is_tag) {
                            e = "//www.bilibili.com/index/tag/" + b(a[0]) + "/" + i.target_id + "/rank.json"
                        } else {
                            if (i.target_id) {
                                e = "//www.bilibili.com/index/rank/all-1-" + i.target_id + ".json"
                            } else {
                                e = "//www.bilibili.com/index/rank/all-1.json"
                            }
                        }
                        this.crow(e, l)
                    }
                }
            }
            if (s.filter) {
                s.filter(function(a) {
                    return !!a
                })
            } else {
                for (var d in this.store) {
                    if (this.store.hasOwnProperty(d) || this.store[d]) {
                        c.push(this.store[d])
                    }
                }
                this.store = c;
                c = []
            }
        },
        crow: function(a, i) {
            $.getJSON(a, function(a) {
                var e = a && a.rank && a.rank.list || a.list || [];
                var n;
                for (var t in e) {
                    if (e.hasOwnProperty(t)) {
                        n = $("ul.list li", i).eq(t);
                        n.removeClass("empty");
                        $("a", n).attr("href", "http://www.bilibili.com/av" + e[t].aid);
                        $(".title a", n).text(e[t].title);
                        $("img", n).attr("src", e[t].pic);
                        switch (e[t].trend) {
                            case -1:
                                $(".rank-arrow", n).addClass("down");
                                break;
                            case 0:
                                $(".rank-arrow", n).addClass("moewiki");
                                break;
                            case 1:
                                $(".rank-arrow", n).addClass("up");
                                break
                        }
                        $(".rank-arrow", n).attr("title", " ۺ     :" + e[t].pts + "Pt");
                        if (t == 9) {
                            break
                        }
                    }
                }
                $(".loading-holder", i).remove();
                i.children().show()
            }).error(function() {
                $(".loading-holder,.loading", i).addClass("die")
            })
        }
    };
    $(document).scroll(function() {
        f.patrol()
    });
    (function() {
        $(window).on("hashchange", function(a) {
            var a = a.originalEvent;
            var e = a.newURL.match(/#(.*)/);
            e = e && e[1].split("/");
            var n = a.oldURL.match(/#(.*)/);
            var t;
            n = n && n[1].split("/");
            if (e.length > 1 && n.length > 1 && e[0] == n[0]) {
                return
            } else {
                if (e.length <= 1) {
                    switch (e[0]) {
                        case "all":
                            t = "ranking_index_view";
                            break;
                        case "animel":
                            t = "ranking_animel_view";
                            break;
                        case "music":
                            t = "ranking_music_view";
                            break;
                        case "dance":
                            t = "ranking_dance_view";
                            break;
                        case "game":
                            t = "ranking_game_view";
                            break;
                        case "tech":
                            t = "ranking_tech_view";
                            break;
                        case "kichiku":
                            t = "ranking_kichiku_view";
                            break;
                        case "life":
                            t = "ranking_life_view";
                            break;
                        case "ent":
                            t = "ranking_ent_view";
                            break;
                        case "fashion":
                            t = "ranking_fashion_view";
                            break;
                        case "film":
                            t = "ranking_film_view";
                            break;
                        case "tv":
                            t = "ranking_tv_view";
                            break;
                        default:
                            t = ""
                    }
                } else {
                    switch (e[0]) {
                        case "all":
                            t = "ranking_hot_all_view";
                            break;
                        case "animel":
                            t = "ranking_animel_all_view";
                            break;
                        case "bangumi":
                            t = "ranking_bangumi_all_view";
                            break;
                        case "music":
                            t = "ranking_music_all_view";
                            break;
                        case "dance":
                            t = "ranking_dance_all_view";
                            break;
                        case "game":
                            t = "ranking_game_all_view";
                            break;
                        case "tech":
                            t = "ranking_tech_all_view";
                            break;
                        case "kichiku":
                            t = "ranking_kichiku_all_view";
                            break;
                        case "life":
                            t = "ranking_life_all_view";
                            break;
                        case "ent":
                            t = "ranking_ent_all_view";
                            break;
                        case "fashion":
                            t = "ranking_fashion_all_view";
                            break;
                        case "film":
                            t = "ranking_film_all_view";
                            break;
                        case "tv":
                            t = "ranking_tv_all_view";
                            break;
                        default:
                            t = ""
                    }
                }
                i(t)
            }
        });
        $(document).on("click", ".main .ranking-list .title-wrap .more,.main .ranking-list .title-wrap .title,.main .ranking-list .foot .desc-button,", function(e) {
            var n;
            var t = $(e.currentTarget);
            var l = t.attr("data-id");
            var r = t.attr("data-istag");
            if (a.length === 0 || a[0] === "all") {
                if (r == "1") {
                    n = "ranking_index_tagrank_morebutton_click"
                } else if (l === "all") {
                    n = "ranking_index_hot_morebutton_click"
                } else {
                    n = "ranking_index_videorank_morebutton_click"
                }
            } else {
                if (r == "1") {
                    switch (a[0]) {
                        case "animel":
                            n = "ranking_animel_tagrank_morebutton_click";
                            break;
                        case "bangumi":
                            n = "ranking_bangumi_tagrank_morebutton_click";
                            break;
                        case "music":
                            n = "ranking_music_tagrank_morebutton_click";
                            break;
                        case "dance":
                            n = "ranking_dance_tagrank_morebutton_click";
                            break;
                        case "game":
                            n = "ranking_game_tagrank_morebutton_click";
                            break;
                        case "tech":
                            n = "ranking_tech_tagrank_morebutton_click";
                            break;
                        case "kichiku":
                            n = "ranking_kichiku_tagrank_morebutton_click";
                            break;
                        case "life":
                            n = "ranking_life_tagrank_morebutton_click";
                            break;
                        case "ent":
                            n = "ranking_ent_tagrank_morebutton_click";
                            break;
                        case "fashion":
                            n = "ranking_fashion_tagrank_morebutton_click";
                            break;
                        case "film":
                            n = "ranking_film_tagrank_morebutton_click";
                            break;
                        case "tv":
                            n = "ranking_tv_tagrank_morebutton_click";
                            break;
                        default:
                            n = ""
                    }
                } else {
                    switch (a[0]) {
                        case "animel":
                            n = "ranking_animel_videorank_morebutton_click";
                            break;
                        case "bangumi":
                            n = "ranking_bangumi_videorank_morebutton_click";
                            break;
                        case "music":
                            n = "ranking_music_videorank_morebutton_click";
                            break;
                        case "dance":
                            n = "ranking_dance_videorank_morebutton_click";
                            break;
                        case "game":
                            n = "ranking_game_videorank_morebutton_click";
                            break;
                        case "tech":
                            n = "ranking_tech_videorank_morebutton_click";
                            break;
                        case "kichiku":
                            n = "ranking_kichiku_videorank_morebutton_click";
                            break;
                        case "life":
                            n = "ranking_life_videorank_morebutton_click";
                            break;
                        case "ent":
                            n = "ranking_ent_videorank_morebutton_click";
                            break;
                        case "fashion":
                            n = "ranking_fashion_videorank_morebutton_click";
                            break;
                        case "film":
                            n = "ranking_film_videorank_morebutton_click";
                            break;
                        case "tv":
                            n = "ranking_tv_videorank_morebutton_click";
                            break;
                        default:
                            n = ""
                    }
                }
            }
            i(n)
        });
        $(document).on("click", ".main .ranking-list ul.list a", function(n) {
            var t;
            var l;
            l = $(".foot a", $(n.currentTarget).parents(".ranking-list")).attr("data-istag");
            if (a.length === 0 || a[0] === "all") {
                if (l == "1") {
                    t = "ranking_index_tagrank_video_click"
                } else if (e === "all") {
                    t = "ranking_index_hot_video_click"
                } else {
                    t = "ranking_index_videorank_video_click"
                }
            } else {
                if (l == "1") {
                    switch (a[0]) {
                        case "animel":
                            t = "ranking_animel_tagrank_video_click";
                            break;
                        case "bangumi":
                            t = "ranking_bangumi_tagrank_video_click";
                            break;
                        case "music":
                            t = "ranking_music_tagrank_video_click";
                            break;
                        case "dance":
                            t = "ranking_dance_tagrank_video_click";
                            break;
                        case "game":
                            t = "ranking_game_tagrank_video_click";
                            break;
                        case "tech":
                            t = "ranking_tech_tagrank_video_click";
                            break;
                        case "kichiku":
                            t = "ranking_kichiku_tagrank_video_click";
                            break;
                        case "life":
                            t = "ranking_life_tagrank_video_click";
                            break;
                        case "ent":
                            t = "ranking_ent_tagrank_video_click";
                            break;
                        case "fashion":
                            t = "ranking_fashion_tagrank_video_click";
                            break;
                        case "film":
                            t = "ranking_film_tagrank_video_click";
                            break;
                        case "tv":
                            t = "ranking_tv_tagrank_video_click";
                            break;
                        default:
                            t = ""
                    }
                } else {
                    switch (a[0]) {
                        case "animel":
                            t = "ranking_animel_videorank_video_click";
                            break;
                        case "bangumi":
                            t = "ranking_bangumi_videorank_video_click";
                            break;
                        case "music":
                            t = "ranking_music_videorank_video_click";
                            break;
                        case "dance":
                            t = "ranking_dance_videorank_video_click";
                            break;
                        case "game":
                            t = "ranking_game_videorank_video_click";
                            break;
                        case "tech":
                            t = "ranking_tech_videorank_video_click";
                            break;
                        case "kichiku":
                            t = "ranking_kichiku_videorank_video_click";
                            break;
                        case "life":
                            t = "ranking_life_videorank_video_click";
                            break;
                        case "ent":
                            t = "ranking_ent_videorank_video_click";
                            break;
                        case "fashion":
                            t = "ranking_fashion_videorank_video_click";
                            break;
                        case "film":
                            t = "ranking_film_videorank_video_click";
                            break;
                        case "tv":
                            t = "ranking_tv_videorank_video_click";
                            break;
                        default:
                            t = ""
                    }
                }
            }
            i(t)
        });
        $(document).on("click", ".bangumi-block", function(a) {
            i("ranking_index_bangumi_click")
        });
        $(document).on("click", ".bangumi-block .tag-list a", function(a) {
            a.stopPropagation();
            i("ranking_index_bangumitag_click")
        });
        $(document).on("click", ".tag-recommend-banner a", function() {
            i("ranking_index_tag_click")
        });
        $(document).on("click", ".main.video-list", function() {
            var e;
            switch (a[0]) {
                case "all":
                    e = "ranking_hot_all_video_click";
                    break;
                case "animel":
                    e = "ranking_animel_all_video_click";
                    break;
                case "bangumi":
                    e = "ranking_bangumi_all_video_click";
                    break;
                case "music":
                    e = "ranking_music_all_video_click";
                    break;
                case "dance":
                    e = "ranking_dance_all_video_click";
                    break;
                case "game":
                    e = "ranking_game_all_video_click";
                    break;
                case "tech":
                    e = "ranking_tech_all_video_click";
                    break;
                case "kichiku":
                    e = "ranking_kichiku_all_video_click";
                    break;
                case "life":
                    e = "ranking_life_all_video_click";
                    break;
                case "ent":
                    e = "ranking_ent_all_video_click";
                    break;
                case "fashion":
                    e = "ranking_fashion_all_video_click";
                    break;
                case "film":
                    e = "ranking_film_all_video_click";
                    break;
                case "tv":
                    e = "ranking_tv_all_video_click";
                    break;
                default:
                    e = ""
            }
            i(e)
        });
        $(document).on("click", ".filter-dock .left", function() {
            var e;
            switch (a[0]) {
                case "animel":
                    e = "ranking_animel_all_typetab_click";
                    break;
                case "music":
                    e = "ranking_music_all_typetab_click";
                    break;
                case "dance":
                    e = "ranking_dance_all_typetab_click";
                    break;
                case "game":
                    e = "ranking_game_all_typetab_click";
                    break;
                case "tech":
                    e = "ranking_tech_all_typetab_click";
                    break;
                case "kichiku":
                    e = "ranking_kichiku_all_typetab_click";
                    break;
                case "life":
                    e = "ranking_life_all_typetab_click";
                    break;
                case "ent":
                    e = "ranking_ent_all_typetab_click";
                    break;
                case "fashion":
                    e = "ranking_fashion_all_typetab_click";
                    break;
                case "film":
                    e = "ranking_film_all_typetab_click";
                    break;
                case "tv":
                    e = "ranking_tv_all_typetab_click";
                    break;
                default:
                    e = ""
            }
            i(e)
        });
        $(document).on("click", ".filter-dock .right", function() {
            var e;
            switch (a[0]) {
                case "all":
                    e = "ranking_hot_all_timetab_click";
                    break;
                case "animel":
                    e = "ranking_animel_all_timetab_click";
                    break;
                case "bangumi":
                    e = "ranking_bangumi_all_timetab_click";
                    break;
                case "music":
                    e = "ranking_music_all_timetab_click";
                    break;
                case "dance":
                    e = "ranking_dance_all_timetab_click";
                    break;
                case "game":
                    e = "ranking_game_all_timetab_click";
                    break;
                case "tech":
                    e = "ranking_tech_all_timetab_click";
                    break;
                case "kichiku":
                    e = "ranking_kichiku_all_timetab_click";
                    break;
                case "life":
                    e = "ranking_life_all_timetab_click";
                    break;
                case "ent":
                    e = "ranking_ent_all_timetab_click";
                    break;
                case "fashion":
                    e = "ranking_fashion_all_timetab_click";
                    break;
                case "film":
                    e = "ranking_film_all_timetab_click";
                    break;
                case "tv":
                    e = "ranking_tv_all_timetab_click";
                    break;
                default:
                    e = ""
            }
            i(e)
        });
        function i(a) {
            if (a && a != "") {
                rec_rp("event", a)
            }
        }
    })()
})();
