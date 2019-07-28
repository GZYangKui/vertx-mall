/**
 *
 *
 * 监控分类变化
 *
 *
 *
 */

!function (window) {
    "use strict";

    let doc = window.document
        , ydui = {};

    $(window).on('load', function () {
    });

    let util = ydui.util = {

        parseOptions: function (string) {
        },

    };

    function storage(ls) {
    }

    if (typeof define === 'function') {
        define(ydui);
    } else {
        window.YDUI = ydui;
    }

    function ScrollTab(element, options) {
        this.$element = $(element);
        this.options = $.extend({}, ScrollTab.DEFAULTS, options || {});
        this.init();
    }

    ScrollTab.DEFAULTS = {
        navItem: '.scrolltab-item',
        content: '.scrolltab-content',
        contentItem: '.scrolltab-content-item',
        initIndex: 0
    };

    ScrollTab.prototype.init = function () {
        let _this = this
            , $element = _this.$element
            , options = _this.options;

        _this.$navItem = $element.find(options.navItem);
        _this.$content = $element.find(options.content);
        _this.$contentItem = $element.find(options.contentItem);

        _this.scrolling = false;
        _this.contentOffsetTop = _this.$content.offset().top;

        _this.bindEvent();

        _this.movePosition(_this.options.initIndex, false);
    };

    ScrollTab.prototype.bindEvent = function () {
        let _this = this;

        _this.$content.on('resize.ydui.scrolltab scroll.ydui.scrolltab', function () {
            _this.checkInView();
        });

        _this.$navItem.on('click.ydui.scrolltab', function () {
            _this.movePosition($(this).index(), true);
        });
    }
    ;

    ScrollTab.prototype.movePosition = function (index, animate) {
        let _this = this;

        if (_this.scrolling)
            return;
        _this.scrolling = true;

        _this.$navItem.removeClass('crt');
        _this.$navItem.eq(index).addClass('crt');

        let $item = _this.$contentItem.eq(index);
        if (!$item[0])
            return;

        let offset = $item.offset().top;

        let top = offset + _this.$content.scrollTop() - _this.contentOffsetTop + 1;

        _this.$content.stop().animate({
            scrollTop: top
        }, animate ? 200 : 0, function () {
            _this.scrolling = false;
        });
    }
    ;

    ScrollTab.prototype.checkInView = function () {
        let _this = this;

        if (_this.scrolling)
            return;

        if (_this.isScrollTop()) {
            _this.setClass(0);
            return;
        }

        if (_this.isScrollBottom()) {
            _this.setClass(_this.$navItem.length - 1);
            return;
        }

        _this.$contentItem.each(function () {
            let $this = $(this);

            if ($this.offset().top <= _this.contentOffsetTop) {
                _this.setClass($this.index());
            }
        });
    }
    ;

    ScrollTab.prototype.setClass = function (index) {
        this.$navItem.removeClass('crt').eq(index).addClass('crt');
    }
    ;

    ScrollTab.prototype.isScrollTop = function () {
        return this.$content.scrollTop() === 0;
    }
    ;

    ScrollTab.prototype.isScrollBottom = function () {
        let _this = this;

        return _this.$content.scrollTop() + 3 >= _this.$contentItem.height() * _this.$contentItem.length - _this.$content.height();
    }
    ;

    function Plugin(option) {
        let args = Array.prototype.slice.call(arguments, 1);

        return this.each(function () {
            let target = this
                , $this = $(target)
                , scrollTab = $this.data('ydui.scrolltab');

            if (!scrollTab) {
                $this.data('ydui.scrolltab', (scrollTab = new ScrollTab(target, option)));
            }

            if (typeof option == 'string') {
                scrollTab[option] && scrollTab[option].apply(scrollTab, args);
            }
        });
    }

    $.fn.scrollTab = Plugin;

    $('[data-ydui-scrolltab]').each(function () {
        let $this = $(this);
        $this.scrollTab(window.YDUI.util.parseOptions($this.data('ydui-scrolltab')));
    });


}(window);
