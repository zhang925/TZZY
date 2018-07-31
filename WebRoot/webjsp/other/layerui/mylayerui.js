/*layui.config({
    dir: '' //layui.js 所在路径（注意，如果是script单独引入layui.js，无需设定该参数。），一般情况下可以无视
    ,version: false //一般用于更新模块缓存，默认不开启。设为true即让浏览器不缓存。也可以设为一个固定的值，如：201610
    ,debug: false //用于开启调试模式，默认false，如果设为true，则JS模块的节点会保留在页面
    ,base: '' //设定扩展的Layui模块的所在目录，一般用于外部模块扩展
});*/



layui.define(['layer', 'form'], function(exports){
    var layer = layui.layer ,form = layui.form;

    // layer.msg('Hello World'); //弹出一个 提示框 不能点击 自动消失

    /*
    本地数据操作 存到 了 local storage 里面
    //【增】：向test表插入一个nickname字段，如果该表不存在，则自动建立。
        layui.data('test', {
            key: 'nickname'
            ,value: '贤心'
        });
    //【删】：删除test表的nickname字段
        layui.data('test', {
            key: 'nickname'
            ,remove: true
        });
        layui.data('test', null); //删除test表

    //【改】：同【增】，会覆盖已经存储的数据

    //【查】：向test表读取全部的数据
        var localTest = layui.data('test');
        console.log(localTest.nickname); //获得“贤心”
    */

    //var device = layui.device();//获取当前登陆是什么设备
    //console.log(device);







    exports('mylayerui', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});

/*
非模块 加载方法 【直接加载所有的模块 】

直接在页面上 放入 即为 非 模块加载
 layui.use(['layer', 'form'], function(){
                var layer = layui.layer  ,form = layui.form;

                //页面出现一个提示消息 不可点击 自动消失
                //layer.msg('Hello World');


            });


 */