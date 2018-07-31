

    Ext.onReady(function() {
        //改变页面上的值
        Ext.QuickTips.init();
        function change(val) {
            //,renderer: function(val){change(val)}
            if (val == '男') {
                return '<span style="color:green;">' + val + '</span>';
            } else {
                return '<span style="color:red;">' + val + '</span>';
            }
        }
        //定义一个实体
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
        //数据来源
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
            id:"stateGrid",
            selType: "checkboxmodel",//显示checkbox
            selModel: {
                injectCheckbox: 0,
                mode: "MULTI"     //"SINGLE"/"SIMPLE"/"MULTI"
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
            dockedItems: [
                {
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
                },'-',{

                }]
            }],
            //自定义搜索
            tbar: [
                {xtype:'label',text:'请输入关键词：'},
                {xtype:'textfield',id:'id123',name:"name123"},//这个id是父级的id这里要用name
                {text:'搜索',handler:function(){
                    var searchVal = document.getElementsByName("name123")[0].value;
                    alert(searchVal);
                    //var store = Ext.getCmp('stateGrid').getStore();          //获取grid的store
                    var store = Ext.getCmp('stateGrid').getStore() || this.getStore();
                    //这样就把参数传到后台了，后台自作处理。[可能会出现乱码]
                    //s = new String (s.getBytes("ISO-8859-1"),"UTF-8");
                    store.load({ params: { names: searchVal} });

                }
             }]

        });






        function add() {//添加
            showFormAdd();
        }
    //查看
    function check() {//查看
        //获取选中的行
        var records = grid.getSelectionModel().getSelection();
        if(records.length>0){
            if(records.length>1){
                Ext.Msg.alert("提示","只能选择一条数据！");
            }else{
                //执行查看操作
                var uid = records[0].data.uid;
                $.ajax({
                    url:"userController/getUserById.do",
                    type:"POST",
                    data:{"uid":uid},
                    dataType:"json",
                    async:false,
                    success:function (data) {
                        showFormCheckOrUpdate(data.obj,"check");
                    }
                });

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
                    $.ajax({
                        url:"userController/getUserById.do",
                        type:"POST",
                        data:{"uid":uid,type:"update"},
                        dataType:"json",
                        async:false,
                        success:function (data) {
                            showFormCheckOrUpdate(data.obj,"update");
                        }
                    });
                }
            }else{
                Ext.Msg.alert("提示","请选择一条数据！");
            }
        }


        function del() {
            //判断是否确定删除
            var records = grid.getSelectionModel().getSelection();
            var uidArray = [];
            if(records.length>0){
                Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
                    if (button == "yes") {
                        for(var i = 0 ; i < records.length ; i++ ){
                            uidArray.push(records[i].data.uid);
                        }
                        $.ajax({
                            url:"userController/deluser.do",
                            type:"POST",
                            data:{"uids":uidArray.join(",")},
                            dataType:"json",
                            async:false,
                            success:function (data) {
                                store.load();//刷新数据
                                Ext.Msg.alert("提示", data.msg);
                                setTimeout(function () {Ext.Msg.hide();},1500);
                            }
                        });
                    }
                });
             }else{
                Ext.Msg.alert("提示","请选择要删除的数据！");
            }

        }




        function showFormCheckOrUpdate(user,doType) {
            function isMale() {
                if(user.sex=="男"){
                    return true;
                }else{
                    return false;
                }
            }
            function isFeMale() {
                if(user.sex=="女"){
                    return true;
                }else{
                    return false;
                }
            }
            function isReadOnly() {//是否加readlyonly 【disabled样式不好看】
                if(doType=="check"){//查看
                    return true;
                }else {
                    return false;
                }
            }

            function tipTitle(){
                if(doType=="check"){//查看
                    return "查看用户";
                }else {
                    return "修改用户";
                }
            }

            var showform=function(){
                var add_winForm =  Ext.create('Ext.form.Panel', {
                    frame: true,   //frame属性
                    width: 340,
                    bodyPadding:5,
                    fieldDefaults: {
                        labelAlign: 'left',
                        labelWidth: 90,
                        anchor: '100%'
                    },
                    items: [ {
                        //输入文本框
                        xtype: 'textfield',
                        name: 'uid',
                        fieldLabel: '用户的UID',
                        allowBlank: false,
                        readOnly:isReadOnly(),
                        value:user.uid,
                        blankText:"uid不能为空！"
                    }, {
                        //输入文本框
                        xtype: 'textfield',
                        readOnly:isReadOnly(),
                        name: 'username',
                        value:user.username,
                        fieldLabel: '用户登陆名'
                    }, {
                        //输入文本框
                        xtype: 'textfield',
                        readOnly:isReadOnly(),
                        name: 'name',
                        value:user.name,
                        fieldLabel: '用户名'
                    },{
                        //输入密码的文本框，输入的字符都会展现为.
                        xtype: 'textfield',
                        readOnly:isReadOnly(),
                        name: 'password',
                        value:user.password,
                        //inputType: 'password',
                        fieldLabel: '密码'
                    }, {
                        //单选框，注意name和下面的单选框相同
                        xtype: 'radiofield',
                        readOnly:isReadOnly(),
                        name: 'sex',
                        checked : isMale(),
                        value: '男',
                        inputValue: '男',
                        fieldLabel: '性别',
                        boxLabel: '男'
                    }, {
                        //单选框，注意name和上面的单选框相同
                        xtype: 'radiofield',
                        readOnly:isReadOnly(),
                        name: 'sex',
                        value: '女',
                        checked :isFeMale(),
                        inputValue:"女",
                        fieldLabel: '',
                        labelSeparator: '',
                        hideEmptyLabel: false,
                        boxLabel: '女'
                    }, {
                        //日期文本框
                        xtype: 'datefield',
                        readOnly:isReadOnly(),
                        name: 'borntime',
                        fieldLabel: '出生日期',
                        value:user.borntime
                    },  {
                        xtype: 'textfield',
                        readOnly:isReadOnly(),
                        name: 'phone',
                        fieldLabel: '手机号码',
                        value:user.phone,
                        //通过正则表达式验证
                        regex: /^\d{11}$/,
                        regexText: '请填写11位手机号'
                    },{
                        //下拉列表框
                        xtype: 'combobox', //9
                        fieldLabel: '用户类别',
                        readOnly:isReadOnly(),
                        name: 'state',
                        value:user.state,
                        displayField: 'name',
                        store: Ext.create('Ext.data.Store', {
                            fields: [
                                { type: 'string', name: 'name' }
                            ],
                            data: [
                                { "name": "0" },
                                { "name": "admin" },
                                { "name": "del" }
                            ]
                        }),
                        queryMode: 'local',
                        typeAhead: true
                    }/*
                    带上 上传功能，表单提升为 文件 提交 比较麻烦！
                    , {
                        //上传文件文本框
                        xtype: 'filefield',
                        name: 'photoid',
                        fieldLabel: '头像'
                    }*/

                    ]
                });

                //alert(add_winForm);
                //创建window面板，表单面板是依托window面板显示的
                var syswin = Ext.create('Ext.window.Window',{
                    title : tipTitle(),
                    width : 350,
                    //height : 120,
                    //plain : true,
                    iconCls : "addicon",
                    // 不可以随意改变大小
                    resizable : false,
                    // 是否可以拖动
                    // draggable:false,
                    collapsible : true, // 允许缩放条
                    closeAction : 'close',
                    closable : true,
                    // 弹出模态窗体
                    modal : 'true',
                    buttonAlign : "center",
                    bodyStyle : "padding:0 0 0 0",
                    items : [add_winForm],
                    buttons : [{
                        text : "确定",
                        minWidth : 70,
                        handler : function() {
                            if(doType=="check"){
                                syswin.close();
                           }else {
                               if (add_winForm.getForm().isValid()) {
                                   add_winForm.getForm().submit({
                                       url :'userController/addOrUpdate.do',
                                       //等待时显示 等待
                                       waitTitle: '请稍等...',
                                       waitMsg: '正在提交信息...',
                                       params: {
                                           type: doType
                                       },
                                       success: function(fp, data) {
                                           syswin.close(); //关闭窗口
                                           store.load();//刷新数据
                                           Ext.Msg.alert("提示", data.result.msg);
                                           setTimeout(function () {Ext.Msg.hide();},1500);
                                       },
                                       failure: function(fp,data) {
                                           //很奇怪，不报任何错误,
                                           // 但是springmv的方法只要有json返回值就会走这个方法
                                           syswin.close(); //关闭窗口
                                           store.load();//刷新数据
                                           Ext.Msg.alert("提示", data.result.msg);
                                           setTimeout(function () {Ext.Msg.hide();},1500);
                                       }
                                   });
                               }
                           }
                        }
                    }, {
                        text : "关闭",
                        minWidth : 70,
                        handler : function() {
                            syswin.close();
                        }
                    }]
                });
                syswin.show();
            };
            showform(); //调用showform显示整个包含表单面板的window面板
        }







        function showFormAdd() {
            var showform=function(){
                var add_winForm =  Ext.create('Ext.form.Panel', {
                    frame: true,   //frame属性
                    width: 340,
                    bodyPadding:5,
                    fieldDefaults: {
                        labelAlign: 'left',
                        labelWidth: 90,
                        anchor: '100%'
                    },
                    items: [ {
                        //输入文本框
                        xtype: 'textfield',
                        name: 'uid',
                        fieldLabel: '用户的UID',
                        allowBlank: false,
                        blankText:"uid不能为空！"
                    }, {
                        //输入文本框
                        xtype: 'textfield',
                        name: 'username',
                        fieldLabel: '用户登陆名'
                    }, {
                        //输入文本框
                        xtype: 'textfield',
                        name: 'name',
                        fieldLabel: '用户名'
                    },{
                        //输入密码的文本框，输入的字符都会展现为.
                        xtype: 'textfield',
                        name: 'password',
                        inputType: 'password',
                        fieldLabel: '密码'
                    }, {
                        //单选框，注意name和下面的单选框相同
                        xtype: 'radiofield',
                        name: 'sex',
                        checked : true,
                        value: '男',
                        inputValue: '男',
                        fieldLabel: '性别',
                        boxLabel: '男'
                    }, {
                        //单选框，注意name和上面的单选框相同
                        xtype: 'radiofield',
                        name: 'sex',
                        value: '女',
                        inputValue:"女",
                        fieldLabel: '',
                        labelSeparator: '',
                        hideEmptyLabel: false,
                        boxLabel: '女'
                    }, {
                        //日期文本框
                        xtype: 'datefield',
                        name: 'borntime',
                        fieldLabel: '出生日期',
                        value: new Date()
                    },  {
                        xtype: 'textfield',
                        name: 'phone',
                        fieldLabel: '手机号码',
                        //通过正则表达式验证
                        regex: /^\d{11}$/,
                        regexText: '请填写11位手机号'
                    },{
                        //下拉列表框
                        xtype: 'combobox', //9
                        fieldLabel: '用户类别',
                        name: 'state',
                        displayField: 'name',
                        store: Ext.create('Ext.data.Store', {
                            fields: [
                                { type: 'string', name: 'name' }
                            ],
                            data: [
                                { "name": "0" },
                                { "name": "admin" },
                                { "name": "del" }
                            ]
                        }),
                        queryMode: 'local',
                        typeAhead: true
                    }/*
                    带上 上传功能，表单提升为 文件 提交 比较麻烦！
                    , {
                        //上传文件文本框
                        xtype: 'filefield',
                        name: 'photoid',
                        fieldLabel: '头像'
                    }*/

                    ]
                });

                //alert(add_winForm);
                //创建window面板，表单面板是依托window面板显示的
                var syswin = Ext.create('Ext.window.Window',{
                    title : "添加用户",
                    width : 350,
                    //height : 120,
                    //plain : true,
                    iconCls : "addicon",
                    // 不可以随意改变大小
                    resizable : false,
                    // 是否可以拖动
                    // draggable:false,
                    collapsible : true, // 允许缩放条
                    closeAction : 'close',
                    closable : true,
                    // 弹出模态窗体
                    modal : 'true',
                    buttonAlign : "center",
                    bodyStyle : "padding:0 0 0 0",
                    items : [add_winForm],
                    buttons : [{
                        text : "确定",
                        minWidth : 70,
                        handler : function() {
                            if (add_winForm.getForm().isValid()) {
                                add_winForm.getForm().submit({
                                    url :'userController/addOrUpdate.do',
                                    //等待时显示 等待
                                    waitTitle: '请稍等...',
                                    waitMsg: '正在提交信息...',
                                    params: {
                                        type: "add"
                                    },
                                    success: function(fp, data) {
                                        syswin.close(); //关闭窗口
                                        store.load();//刷新数据
                                        Ext.Msg.alert("提示", data.result.msg);
                                        setTimeout(function () {Ext.Msg.hide();},1500);
                                    },
                                    failure: function(fp,data) {
                                        //很奇怪，不报任何错误,
                                        // 但是springmv的方法只要有json返回值就会走这个方法
                                        syswin.close(); //关闭窗口
                                        store.load();//刷新数据
                                        Ext.Msg.alert("提示", data.result.msg);
                                        setTimeout(function () {Ext.Msg.hide();},1500);
                                    }
                                });
                            }
                        }
                    }, {
                        text : "关闭",
                        minWidth : 70,
                        handler : function() {
                            syswin.close();
                        }
                    }]
                });
                syswin.show();
            };
            showform(); //调用showform显示整个包含表单面板的window面板
        }


    });





