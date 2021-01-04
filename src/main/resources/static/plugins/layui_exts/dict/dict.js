/**
 * 扩展layui组件 jingjin，用于数据字典专用
 */
layui.define(["element","jquery"], function(exports) {
    var $ = layui.$,
        element = layui.element;
    dict = function () {
        this.v = '1.1.0';
    }
    dict.prototype.render = function (opt) {
        // $.ajaxSettings.async = false; //设置为同步，否则layui会先渲染表格，导致数据显示不出来
        //此处拿到的dict即字典code，可以通过此字典code去数据库或者redis中查询相应的字典数据并加载到select中的option中。
        $('.selDict').each(function () {
            var _this = $(this);
            var dict = _this.attr("dict");
            var value = _this.attr("value");//默认选中值
            //alert(JSON.stringify(value));
            kvfKit.get(api.sys.dictByCodeListData,{code:dict},function(data){
                if (data.data != null) {
                    $.each(data.data, function (index, m) {
                        _this.append("<option value='" + m.value + "'>" + m.name + "</option>");
                        if(value!=null){
                            _this.val(value);
                        }
                    });
                }
            });
            // $.get("/sys/dict/getDictItems/" + dict, function (data) {//后台获取数据，这里需要根据返回数据，自己调整。我这里返回数据见下面代码
            //     if (data.data != null) {
            //         $.each(data.data, function (index, m) {
            //             _this.append("<option value='" + m.value + "'>" + m.title + "</option>");
            //             _this.val(value);
            //         });
            //     }
            // });
        })
        // $.ajaxSettings.async = true; //ajax恢复为异步
    }
    var dict = new dict();
    exports("dict", dict);
})