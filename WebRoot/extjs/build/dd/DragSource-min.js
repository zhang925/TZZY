/*
 * Ext JS Library 2.0.2
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */

Ext.dd.DragSource=function(B,A){this.el=Ext.get(B);if(!this.dragData){this.dragData={}}Ext.apply(this,A);if(!this.proxy){this.proxy=new Ext.dd.StatusProxy()}Ext.dd.DragSource.superclass.constructor.call(this,this.el.dom,this.ddGroup||this.group,{dragElId:this.proxy.id,resizeFrame:false,isTarget:false,scroll:this.scroll===true});this.dragging=false};Ext.extend(Ext.dd.DragSource,Ext.dd.DDProxy,{dropAllowed:"x-dd-drop-ok",dropNotAllowed:"x-dd-drop-nodrop",getDragData:function(A){return this.dragData},onDragEnter:function(C,D){var B=Ext.dd.DragDropMgr.getDDById(D);this.cachedTarget=B;if(this.beforeDragEnter(B,C,D)!==false){if(B.isNotifyTarget){var A=B.notifyEnter(this,C,this.dragData);this.proxy.setStatus(A)}else{this.proxy.setStatus(this.dropAllowed)}if(this.afterDragEnter){this.afterDragEnter(B,C,D)}}},beforeDragEnter:function(B,A,C){return true},alignElWithMouse:function(){Ext.dd.DragSource.superclass.alignElWithMouse.apply(this,arguments);this.proxy.sync()},onDragOver:function(C,D){var B=this.cachedTarget||Ext.dd.DragDropMgr.getDDById(D);if(this.beforeDragOver(B,C,D)!==false){if(B.isNotifyTarget){var A=B.notifyOver(this,C,this.dragData);this.proxy.setStatus(A)}if(this.afterDragOver){this.afterDragOver(B,C,D)}}},beforeDragOver:function(B,A,C){return true},onDragOut:function(B,C){var A=this.cachedTarget||Ext.dd.DragDropMgr.getDDById(C);if(this.beforeDragOut(A,B,C)!==false){if(A.isNotifyTarget){A.notifyOut(this,B,this.dragData)}this.proxy.reset();if(this.afterDragOut){this.afterDragOut(A,B,C)}}this.cachedTarget=null},beforeDragOut:function(B,A,C){return true},onDragDrop:function(B,C){var A=this.cachedTarget||Ext.dd.DragDropMgr.getDDById(C);if(this.beforeDragDrop(A,B,C)!==false){if(A.isNotifyTarget){if(A.notifyDrop(this,B,this.dragData)){this.onValidDrop(A,B,C)}else{this.onInvalidDrop(A,B,C)}}else{this.onValidDrop(A,B,C)}if(this.afterDragDrop){this.afterDragDrop(A,B,C)}}delete this.cachedTarget},beforeDragDrop:function(B,A,C){return true},onValidDrop:function(B,A,C){this.hideProxy();if(this.afterValidDrop){this.afterValidDrop(B,A,C)}},getRepairXY:function(B,A){return this.el.getXY()},onInvalidDrop:function(B,A,C){this.beforeInvalidDrop(B,A,C);if(this.cachedTarget){if(this.cachedTarget.isNotifyTarget){this.cachedTarget.notifyOut(this,A,this.dragData)}this.cacheTarget=null}this.proxy.repair(this.getRepairXY(A,this.dragData),this.afterRepair,this);if(this.afterInvalidDrop){this.afterInvalidDrop(A,C)}},afterRepair:function(){if(Ext.enableFx){this.el.highlight(this.hlColor||"c3daf9")}this.dragging=false},beforeInvalidDrop:function(B,A,C){return true},handleMouseDown:function(B){if(this.dragging){return }var A=this.getDragData(B);if(A&&this.onBeforeDrag(A,B)!==false){this.dragData=A;this.proxy.stop();Ext.dd.DragSource.superclass.handleMouseDown.apply(this,arguments)}},onBeforeDrag:function(A,B){return true},onStartDrag:Ext.emptyFn,startDrag:function(A,B){this.proxy.reset();this.dragging=true;this.proxy.update("");this.onInitDrag(A,B);this.proxy.show()},onInitDrag:function(A,C){var B=this.el.dom.cloneNode(true);B.id=Ext.id();this.proxy.update(B);this.onStartDrag(A,C);return true},getProxy:function(){return this.proxy},hideProxy:function(){this.proxy.hide();this.proxy.reset(true);this.dragging=false},triggerCacheRefresh:function(){Ext.dd.DDM.refreshCache(this.groups)},b4EndDrag:function(A){},endDrag:function(A){this.onEndDrag(this.dragData,A)},onEndDrag:function(A,B){},autoOffset:function(A,B){this.setDelta(-12,-20)}});