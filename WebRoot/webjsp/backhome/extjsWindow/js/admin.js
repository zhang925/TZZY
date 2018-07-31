//设置树的点击事件
function treeClick(node,event) {
	 if(node.isLeaf()){ //如果不是叶子节点则不处理
		event.stopEvent();
		var n = tab.getComponent(node.id);
		
		
		if (!n) {//判断是否已经打开该面板
			var  n = tab.add({
				'id' : node.id,
			    'title' : node.text,
			    autoScroll:true,
				closable:true,
				closeAction:'close',
				html : "<iframe id='eachColliery' src='"+node.attributes.href+"' width='100%' height='100%' ></iframe>"
				//autoLoad:{url:node.attributes.href, scripts:true }//href在json格式定义的字符串的属性
				});
				
		}
		
		tab.setActiveTab(n);
	 }
}

//生成标签页
var tab = new Ext.TabPanel({
		region:'center',
		deferredRender:false,
		activeTab:0,
		resizeTabs:true, 
		minTabWidth: 115,
		tabWidth:135,
		enableTabScroll:true,
		frame:true,
		listeners:{
                     //传进去的三个参数分别为:这个tabpanel(tabsDemo),当前标签页,事件对象e
                    "contextmenu":function(tdemo,myitem,e){
                                menu=new Ext.menu.Menu([{
                                         text:"关闭当前页",
                                         handler:function(){
											
                                            tdemo.remove(myitem);
                                         }
                                },{
                                         text:"关闭其他页",
                                         handler:function(){
                                            //循环遍历
                                            tdemo.items.each(function(item){
                                                 if(item.closable&&item!=myitem)
                                                 {
                                                    //可以关闭的其他所有标签页全部关掉
                                                    tdemo.remove(item);
                                                    
                                                 }
                                            });
                                         }
                                }]);
                                //显示在当前位置
                                menu.showAt(e.getPoint());
                     }
            },

		items:[  
             new Ext.Panel({
	            title: '新疆地图',   
	            id: 'index',
	            bodyStyle : 'padding:10px',
				html : "<iframe id='eachColliery' src='main.html' width='100%' height='100%' ></iframe>",
				/*
				 * div变形，并且在有的机器上报警信息显示不出来
				 * autoLoad : { url : 'main.jsp', scripts : true },
				 */
				closable : false,
				autoScroll : true
	            //layout: 'fit'  
           }) 
          ]
    
});

Ext.onReady(function(){
	
   Ext.BLANK_IMAGE_URL = 'ext2.1/resources/images/default/s.gif';//原本是连接http://extjs.com/s.gif
   Ext.lib.Ajax.defaultPostHeader += '; charset=UTF-8';//解决乱码	
   Ext.QuickTips.init();//开启表单提示
   
   var viewport = new Ext.Viewport({
		layout:'border',
		items:[
			new Ext.BoxComponent({
				region:'north',
				el: 'north',
				height:50
			}),new Ext.BoxComponent({
				region:'south',
				el: 'south',
				height:50
			}),{
			region:'west',
			id:'west-panel',
			split:true,
			width: 200,
			minSize: 185,
			maxSize: 400,
			margins:'0 0 0 0',
			layout:'accordion',
			title:'功能菜单',
			collapsible :true,
			layoutConfig:{
				animate:true
				},
		    items: [
		       {
					title:'主菜单',
					border:false,
					html:'<div id="tree_systemConfig" style="overflow:auto;width:100%;height:100%"></div>'
			    },{
					title:'信息维护',
					border:false,
					html:'<div id="xinxiweihu" style="overflow:auto;width:100%;height:100%"></div>'
			    }]
			},
	        tab//初始标签页
		 ]
	});
   
      var Tree = Ext.tree;
      
      var tree = new Tree.TreePanel({
            el:'tree_systemConfig',//填充到的区域
            autoScroll:true,//自动滚动
            animate:true, //开启动画效果
            enableDD:false,//允许子节点拖动（true）
            containerScroll: true,
            rootVisible:false,//设为false将隐藏根节点，很多情况下，我们选择隐藏根节点增加美观性
            dropConfig: {appendOnly:true},
            loader: new Tree.TreeLoader()

            
        });
        

 
        var root = new Tree.AsyncTreeNode({
            text: '主菜单', 
            draggable:false, // 不允许拖拽
            id:'index',
			children:[{text:'用户管理',children:[{id:'1001',text:'用户设置',href:'userinfo3.html',leaf:true},
				                                {id:'1002',text:'用户查询',href:'userinfo2.html',leaf:true}]},
				      {text:'权限管理',children:[{id:'1003',text:'权限设置',href:'userinfo.html',leaf:true}]}
			]
        });
        
        tree.setRootNode(root); //将改该节点设置为tree的根节点
        tree.render(); //对tree进行渲染，
        root.expand(false,false); //避免节点无限展开下去,只显示根节点
        tree.on('click',treeClick);  
        
});