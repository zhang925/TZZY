

    Ext.onReady(function() {
        Ext.QuickTips.init(); //快速提示通过在html上添加特定的属性就可以体现出来，比较方便，只需要在代码里面通过如下方式初始化：
        //初始化Ext状态管理器，在Cookie中记录用户的操作状态，
        // 如果不启用，象刷新时就不会保存当前的状态，而是重新加载
       // Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));

        function change(val) {
            //,renderer: function(val){change(val)}
            if (val == '男') {
                return '<span style="color:green;">' + val + '</span>';
            } else {
                return '<span style="color:red;">' + val + '</span>';
            }
        }

        var user = Ext.define('user', {
                extend: 'Ext.data.Model',
                fields: [
                    {name: 'uid', type: 'string'},
                    {name: 'username',  type: 'string'},
                    {name: 'name',       type: 'string'},
                    {name: 'sex',  type: 'string'},
                    {name: 'borntime',  type: 'string'}
                ]
            });

        var store = Ext.create('Ext.data.Store', {
            model: 'user',
            pageSize: 5,
            proxy: {
                type: 'ajax',
                url : 'userController/userlist',
                reader: {
                    type: 'json',
                    root: 'rows' //对应 json 根节点 一般为rows
                }
            },
            autoLoad: true //自动加载数据
        });

        //展示数据列
        var grid = Ext.create('Ext.grid.Panel', {
            store: store,//加载数据
            columnLines: true,//表格之间的边线
            stateId: 'stateGrid',
            selType: "checkboxmodel",//显示checkbox
            selModel: {
                injectCheckbox: 0,
                mode: "SINGLE"     //"SINGLE"/"SIMPLE"/"MULTI"
                //,checkOnly: true     //只能通过checkbox选择
            },

            columns: [
                    //{xtype: 'rownumberer',width: 40}, //显示行号
                    Ext.create('Ext.grid.RowNumberer'), //显示行号
                    {text: '用户ID',sortable : true,dataIndex: 'uid'},
                    {text: '登录名',dataIndex: 'username'},
                    {text: '用户名',dataIndex: 'name',
                        editor: {//该单元格 可编辑
                            xtype: "textfield",//textfield numberfield
                            decimalPrecision: 0,
                            selectOnFocus: true
                        },
                        menuDisabled:true,//默认 true 升序降序 以及是否有 显示其他列 的功能
                        sortable:false //默认 true 否是排序
                    },
                    {text: '性别',renderer : change,dataIndex: 'sex'},
                    {text: '出生日期',dataIndex: 'borntime'}
            ],
            height: 350,
            width: 600,
            title: '用户信息列表',
            renderTo: 'userGrid',//渲染到 哪个地方 Ext.getBody()
            viewConfig: {
                stripeRows:true,//在表格中显示斑马线
                enableTextSelection:true //可以复制单元格文字
            },
            iconCls:'icon-grid',//指 定 系统 或自定义[testiconCls] 的 图片
           // icon:'css/feed_add.png'  直接指定路径即可
            plugins: [
                Ext.create('Ext.grid.plugin.CellEditing', {
                    clicksToEdit: 1
                })
            ],
            listeners: { //内部监听
                /*itemdblclick: function (me, record, item, index, e, eOpts) {
                    //双击事件的操作
                },
                itemclick: function (view, record, item, index, e) {
                    //单击事件的操作
                }*/
            },
            bbar: { xtype: "pagingtoolbar", store: store, displayInfo: true } , //分页工具栏
            dockedItems: [{
                xtype: 'toolbar',
                items: [{
                    text:'添加',
                    //tooltip:'添加',
                    iconCls:'add',
                    handler:function () {
                        add();
                    }
                }, '-', {
                    text:'查看',
                    //tooltip:'查看',
                    iconCls:'option',
                    handler:function () {
                        check();
                    }
                }, '-', {
                    text:'修改',
                    //tooltip:'修改',
                    iconCls:'option',
                    handler:function () {
                        update();
                    }
                },'-',{
                    itemId: 'removeButton',
                    text:'删除',
                    //tooltip:'删除',
                    iconCls:'remove',
                    //disabled: true,
                    handler:function () {
                        del();
                    }
                }]
            },
                {//在底部添加按钮
                    xtype: 'toolbar',
                    dock: 'bottom',
                    ui: 'footer',
                    layout: {
                        pack: 'center'
                    },
                    items: [{
                        minWidth: 80,
                        text: '确定'
                    },{
                        minWidth: 80,
                        text: '取消'
                    }]
                }

            ],
        });



    
        function panel(uid) {
            var goSrc = "userController/goInfo.do";
            if(uid){
                goSrc = goSrc + "?uid="+uid;
            }
            var pan = new Ext.Panel({
                width: "100%",
                items: [{
                    html: "<iframe src='"+goSrc+"' scrolling='yes' frameborder=0 width=100% height='330px'></iframe>"
                }]
            });
            return pan;
        }



        function newWin(winTitle,winItem,winType){
            var userinfoEditWin;
            if(!userinfoEditWin){
                userinfoEditWin = new Ext.Window({
                    title:winTitle,
                    width: 500,
                    height:400,
                    layout: 'fit',
                    bodyStyle:'padding:5px;',
                    draggable: true,//是否启动拖动效果
                    closable:true,//关闭按钮
                    maximizable:true,   //（是否增加最大化，默认没有）
                    closeAction:'hide',
                    plain:true,
                    modal:true,//父页面不可编辑
                    resizable:false,//false不可拖动边框的大小
                    tools: [{
                        id: "save", handler: function () {
                            Ext.Msg.alert("提示","这是个提示");
                        }}],
                    constrainHeader: true,
                    constrain: true,
                    items: [winItem],
                    buttons: [
                        {text: "确定", handler: function () {
                               if(winType=="check"){
                                   userinfoEditWin.hide();
                               }else if(winType=="add"){
                                   alert("执行添加！");
                                   //document.getElementById("userform").submit();
                               }else if(winType=="update"){
                                   alert("执行修改！");
                               }
                            }
                        },
                        {text: "关闭", handler: function () {
                                userinfoEditWin.hide();
                            }
                        }
                        ],
                });
            }
            userinfoEditWin.show();
        }


    function add() {//添加
        newWin("用户信息 >> 添加",MyformPanel,"add");
    }
    //查看
    function check() {//单选
        //获取选中的行
        var records = grid.getSelectionModel().getSelection();
        if(records.length>0){
            if(records.length>1){
                Ext.Msg.alert("提示","只能选择一条数据！");
            }else{
                //执行查看操作
                var uid = records[0].data.uid;
                newWin("用户信息 >> 查看",panel(uid),"check");
            }
        }else{
            Ext.Msg.alert("提示","请选择一条数据！");
        }
    }
    function update(){//修改
        //获取选中的行
        var records = grid.getSelectionModel().getSelection();
        if(records.length>0){
            if(records.length>1){
                Ext.Msg.alert("提示","只能选择一条数据！");
            }else{
                //执行查看操作
                var uid = records[0].data.uid;
                newWin("用户信息 >> 修改",panel(uid),"update");
            }
        }else{
            Ext.Msg.alert("提示","请选择一条数据！");
        }
    }

        function update(){//删除
            //获取选中的行
            var records = grid.getSelectionModel().getSelection();
            if(records.length>0){
                if(records.length>1){
                    Ext.Msg.alert("提示","只能选择一条数据！");
                }else{
                    //执行查看操作
                    var uid = records[0].data.uid;
                    newWin("用户信息 >> 修改",panel(uid),"update");
                }
            }else{
                Ext.Msg.alert("提示","请选择一条数据！");
            }
        }



        //创建表单面板
        var MyformPanel=Ext.create('Ext.form.Panel', {
            frame: true,
            title: '添加用户',
            width: 340,
            bodyPadding: 5,
            renderTo:Ext.getBody(),    //渲染到页面的form中去
            fieldDefaults: {
                labelAlign: 'left',
                labelWidth: 90,
                anchor: '100%',
                //错误提示显示在下方，还可以配置为side、title、none
                msgTarget: 'under'
            },
            items:[{
                xtype:'fieldset',
                title:'用户信息',   //外框的title
                collapsible:true,
                autoHeight:true,
                autoWidth:true,
                defaults:{width:150,allowBlank:false,xtype:'textfield'},//提取共同属性
                items: [{
                    xtype: 'textfield',
                    name: 'textfield1',
                    fieldLabel: '必须输入',
                    //不允许为空验证
                    allowBlank: false //1
                }, {
                    xtype: 'textfield',
                    name: 'textfield2',
                    fieldLabel: '最多两个字符',
                    //输入的字符长度验证（至少输入2个字符）
                    minLength: 2 //2
                }, {
                    xtype: 'textfield',
                    name: 'textfield3',
                    fieldLabel: '最长5个字符',
                    //输入的字符长度验证（最多输入2个字符）
                    maxLength: 5 //3
                }, {
                    xtype: 'textfield',
                    name: 'textfield7',
                    fieldLabel: '正则表达式验证电话号码',
                    //通过正则表达式验证
                    regex: /^\d{3}-\d{3}-\d{4}$/, //4
                    regexText: 'Must be in the format xxx-xxx-xxxx'
                }, {
                    xtype: 'textfield',
                    name: 'textfield4',
                    fieldLabel: '验证用户输入的是否为email',
                    //已经定义好的验证，请通过文档查看vtype
                    vtype: 'email' //5
                }, {
                    xtype: 'textfield',
                    name: 'textfield6',
                    fieldLabel: '验证用户输入的是否是URL',
                    vtype: 'url' //8
                }]
            }],
            buttons:[{text:"确定",handler:function(){
                //获取按钮的父表单
                var form=this.up("form").getForm();
                //alert(form);
                if(form.isValid())  //判断是否通过验证
                {
                    //获取页面的表单转化为dom对象后提交
                    Ext.get("panel22").dom.submit();
//获取页面的表单元素后提交
                };
            }
            },{text:"取消",handler:reset}],
            buttonAlign:'center'
        });
//            function logins(){
//              alert("aaaaa");
//         MyformPanel.form.submit();//提交
//              //alert("sdha");
//         }
        function reset(){
            MyformPanel.form.reset();//取消
            //alert("取消");
        }




    });










    /*  function newWin(){
         var win=new Ext.Window({
             title:"用户信息",
             width:400,
             height:300,
             maximizable:true,
             plain:true, // //true则主体背景透明，false则主体有小差别的背景色，默认为false
                resizable:false,(是否可以改变大小，默认可以)
           maximizable:true,（是否增加最大化，默认没有）
     draggable:false,（是否可以拖动，默认可以）
     minimizable:true,（是否增加最小化，默认无）
    closable:false,（是否显示关闭，默认有关闭）
    几个前面没有介绍的参数
            1.closeAction:枚举值为：close(默认值)，当点击关闭后，关闭window窗口   hide,关闭后，只是hidden窗口
            3.constrain：true则强制此window控制在viewport，默认为false
            4.modal:true为模式窗口，后面的内容都不能操作，默认为false
            5.plain：

        });
        win.show();
    }

            /*var config = {
                title:"飞出的消息框",
                msg:"这是一个自定义对话框，是飞出来的哦",
                width:400,
                multiline:true,
                closable:false,
                buttons:Ext.MessageBox.YESNOCANCEL,
                icon:Ext.MessageBox.QUESTION,
                animEl:"fly"
            };
            Ext.MessageBox.show(config);*/
    /*  var tabs = new Ext.TabPanel({
                 "region": "center",
                 activeTab: 0,
                 items: [{
                     title: "T1",
                     html: "c1"
                 }]
             });
     */

/*
.testiconCls{
    background-image: url(feed_add.png)!important;
}
*/

/*
columns 的 列属性
    xtype：列类型
    text：列头显示的文字
    dataIndex：绑定的字段名
    width：宽度
    flex：自动适应的宽度
    sortable：是否可排序，默认为是
    hideable：是否可隐藏，默认为是
    locked：锁定列，将列锁定在grid的开头，当grid出现滚动条的时候该属性比较有用。默认为否
    lockable：是否可锁定，默认为否
    format：格式化字符串，常用于日期、数字的格式化。日期：'Y-m-d'；日期时间：'Y-m-d H:i:s'；数字：'0,000.00'（带有千位分隔符、保留两位小数）、'0.00'（保留两位小数），'0'（不保留小数）
    renderer：自定义绘制方法，可以是Ext.util.Format中定义好的方法名称，也可以是自定义否function，该方法接收下面的参数：value、metadata、record、rowIndex、colIndex、store、view，并需要一个用来显示的返回值。
    editor：编辑器，当使用编辑插件的时候才会起作用。

//选择行，并保持其他行的选择状态
grid.getSelectionModel().select(records, true);
//选择所有
grid.getSelectionModel().selectAll();
//根据row index选择
grid.getSelectionModel().selectRange(startRow, endRow, true)


var records = grid.getSelectionModel().getSelection();

1、选中行点击事件
 listeners: { 'itemclick': function (view, record, item, index, e) {
               Ext.MessageBox.alert("标题",record.data.cataId);
            }
2、外部操作选中行
function showAlert (){
             var selectedData=grid.getSelectionModel().getSelection()[0].data;
             Ext.MessageBox.alert("标题",selectedData.cataId);
         }
3、外部操作多行选中
 var rows = grid.getView().getSelectionModel().getSelection();
                var msg = "";
                for (var i = 0; i < rows.length; i++) {
                    msg = msg + rows[i].get('cataId') + ',';
                }



*/

