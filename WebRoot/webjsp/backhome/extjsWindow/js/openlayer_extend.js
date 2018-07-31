var readerCoo=new Array();//基站坐标

function updateFormats(map) {        
            var in_options = {
                'internalProjection': map.baseLayer.projection,
                'externalProjection': new OpenLayers.Projection('EPSG:4326')
            };   
            var out_options = {
                'internalProjection': map.baseLayer.projection,
                'externalProjection': new OpenLayers.Projection('EPSG:4326')
            };
            formats = {
              'in': {
                geojson: new OpenLayers.Format.GeoJSON(in_options)
              }, 
              'out': {
                geojson: new OpenLayers.Format.GeoJSON(out_options)
              }
            };
        }
        var DeleteFeature = OpenLayers.Class(OpenLayers.Control, {
            initialize: function(layer, options) {
                OpenLayers.Control.prototype.initialize.apply(this, [options]);
                this.layer = layer;
                this.handler = new OpenLayers.Handler.Feature(
                    this, layer, {click: this.clickFeature}
                );
            },
            clickFeature: function(feature) {
                // if feature doesn't have a fid, destroy it
                if(feature.fid == undefined) {
                    this.layer.destroyFeatures([feature]);
                } else {
                    feature.state = OpenLayers.State.DELETE;
                    this.layer.events.triggerEvent("afterfeaturemodified", 
                                                   {feature: feature});
                    feature.renderIntent = "select";
                    this.layer.drawFeature(feature);
                }
                statusPolygon.innerHTML += "<p>删除一个图形</p>";
 				statusPolygon.scrollTop = statusPolygon.scrollHeight;
            },
            setMap: function(map) {
                this.handler.setMap(map);
                OpenLayers.Control.prototype.setMap.apply(this, arguments);
            },
            CLASS_NAME: "OpenLayers.Control.DeleteFeature"
        });


//在地图上显示基站坐标
        function markTower(map){
        	tower = new OpenLayers.Layer.Vector("基站坐标图层", {
        		//下面是基站的样式
                styleMap: new OpenLayers.StyleMap({'default':{
                    //strokeColor: "#000000",
                    //strokeOpacity: 1,
                    //strokeWidth: 1,
                    //fillColor: "#000000",
                    //fillOpacity: 0.5,
                    //pointRadius: 6,
                    //graphicName:"star",
                    //pointerEvents: "visiblePainted",
                    pointRadius: 12,//图片大小
                    externalGraphic: "js/img/reader.bmp",//图片位置
                    label : "ID: ${id}",//图片说明
                    fontColor: "#000000",//文字颜色
                    fontSize: "10px",//文字大小
                    labelAlign:"lb"//文字位置
                }})
                }
        	);
        	map.addLayer(tower);
        	
			for(var i=0;i<readerCoo.length;i++){				
	            var point = new OpenLayers.Geometry.Point(readerCoo[i][0], readerCoo[i][1]);
	            var pointFeature = new OpenLayers.Feature.Vector(point);
	            pointFeature.attributes = {
	                id: readerCoo[i][2]
	            };
	            tower.addFeatures([pointFeature]);
            }
        }
        
        //xy是坐标，type表示起点还是终点[start|end]      比较传入的xy坐标和每一个基站坐标的距离
		function getNearCoo(x,y,type){
			var params='';
			//第一个点单独处理一下
			var tempX=readerCoo[0][0]-x;
			var tempY=readerCoo[0][1]-y;
			var temp=Math.sqrt(tempX*tempX+tempY*tempY);//算出距离
			//暂存第一个基站到此点的距离为最小距离，并记录它的id，xy坐标，距离等信息
			var min=temp;
			var tempID=readerCoo[0][2];
			var tempName=readerCoo[0][3];
			params='&'+type+'.distance='+temp+'&'+type+'.id='+readerCoo[0][2];
			params+=('&'+type+'.x='+readerCoo[0][0]+'&'+type+'.y='+readerCoo[0][1]);
			if(min==0){
			 return params;
			}else{
				for(var i=1;i<readerCoo.length;i++){
					tempX=readerCoo[i][0]-x;
					tempY=readerCoo[i][1]-y;
					temp=Math.sqrt(tempX*tempX+tempY*tempY);
					
					if(min>temp){//找到了更小的距离
						tempID=readerCoo[i][2];
						tempName=readerCoo[i][3];
						min=temp;
						params='&'+type+'.distance='+temp+'&'+type+'.id='+readerCoo[i][2];
						params+=('&'+type+'.x='+readerCoo[i][0]+'&'+type+'.y='+readerCoo[i][1]);
						if(min==0){
						 break;
						}
					}
				}
			}
			//if(min>1){}//在坐标系中,用户画的线路的点距离最近的基站坐标大于1（如果比例尺是1：1000M，那么这个距离的最大误差就是1000M）
			var pp=new Array();//返回的数组
			pp.push(params);
			pp.push(min);
			pp.push(tempID);
			pp.push(tempName);
			if(type=='start'){
				pp.push("起点");
			}else{
				pp.push("终点");
			}
			return pp;
		}
//比较两个date类型的日期的先后  -1：小于  0等于  1 大于
        function dateCompare(date1,date2){
        	if(date1.getFullYear()<date2.getFullYear()){
        		return -1;
        	}else if(date1.getMonth()<date2.getMonth()){
        		return -1;
        	}else if(date1.getDate()<date2.getDate()){
        		return -1;
        	}else if(date1.getDate()==date2.getDate()){
        		return 0;
        	}else{
        		return 1;
        	}
        }
        //比较两个24小时制的事件的先后
        function timeCompare(time1,time2){
        	var t1=time1.split(':');
        	var t2=time2.split(':');
        	if(parseInt(t1[0])<parseInt(t2[0])){
        		return parseInt('-1');
        	}else if((parseInt(t1[0])==parseInt(t2[0]))&&(parseInt(t1[1])<parseInt(t2[1]))){
        		return parseInt('-1');
        	}else if((parseInt(t1[0])==parseInt(t2[0]))&&(parseInt(t1[1])==parseInt(t2[1]))){
        		return parseInt('0');
        	}else if((parseInt(t1[0])==parseInt(t2[0]))&&(parseInt(t1[1])>parseInt(t2[1]))){
        		return parseInt('1');
        	}else{
        		return parseInt('1');
        	}        	
        }        
        //把某个div的zindex置成最大
        function popupToTop(index,evt,base_line_id){
        
        	var type='';//本页面的名称,在这里仅仅为了是   为feature。id取一个唯一的id值
        	if(base_line_id=='already_line_used'){
        		type='_manage';
        	}else if(base_line_id=='patrol_used_rule'){
        		type='_staff';
        	}
        
        	var maxZIndex=0;
        	
        	var i=1;
        	var tempDiv=document.getElementById('pop'+type+i+'_popup');
        	
        	while(tempDiv!=null){
        		if(maxZIndex<tempDiv.style.zIndex){
        			maxZIndex=tempDiv.style.zIndex;
        		}
        		tempDiv=document.getElementById('pop'+type+(++i)+'_popup');
        	}
        	
        	document.getElementById('pop'+type+index+'_popup').style.zIndex=1+parseInt(maxZIndex);
        	
        }
        //某些情况下editpanelgrid修改后的值并不能显示在页面上，调用这个函数即可解决问题
        function afterEdit(obj){
			var r=obj.record;	
			s=r.data[obj.dataIndex];
			r.set(obj.dataIndex,s);
		}
        //在提交服务器的参数中加入 路线名称  和  干警id
		function getAssist(staffs){
			
			var staffNum=0;
			var params='';
			for(var check=0;check<staffs.length;check++){
				if(staffs[check]=='clear'||staffs[check]=='null'){
					continue;
				}else{
					params+=('&staff'+staffNum+'='+staffs[check]);
					staffNum++;
				}
			}
			params+="&staffNum="+staffNum;
			var arr=new Array();//0存放提交给服务器的参数  1存放staff个数
			arr[0]=params;
			arr[1]=staffNum;
			return arr;
		}
        
        
        
        
        