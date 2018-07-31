/**
 * 存放公共的Js函数
 */

// 重载 Ext.data.Store.prototype.applySort 函数以修复 DataStore 对汉字排序异常的问题
// 重载 applySort
Ext.data.Store.prototype.applySort = function() {
	if (this.sortInfo && !this.remoteSort) {
		var s = this.sortInfo, f = s.field;
		var st = this.fields.get(f).sortType;
		var fn = function(r1, r2) {
			var v1 = st(r1.data[f]), v2 = st(r2.data[f]);
			// 添加:修复汉字排序异常的Bug
			if (typeof(v1) == "string") { // 若为字符串，
				return v1.localeCompare(v2);// 则用 localeCompare 比较汉字字符串, Firefox
				// 与 IE 均支持
			}
			// 添加结束
			return v1 > v2 ? 1 : (v1 < v2 ? -1 : 0);

		};
		this.data.sort(s.direction, fn);
		if (this.snapshot && this.snapshot != this.data) {
			this.snapshot.sort(s.direction, fn);
		}
	}

};

/**
 * 
 * @param {}
 *            reallyDo 被搜索的子字符串
 * @param {}
 *            replaceWith 用于替换的子字符串
 * @param {}
 *            ignoreCase
 * @return {} 正则表达式实现replaceAll
 */
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
	if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
		return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")),
				replaceWith);
	} else {
		return this.replace(reallyDo, replaceWith);
	}
}

/**
 * 
 * @param {}
 *            sndAction start or stop
 * @param {}
 *            sndObj
 * 
 * FF下需安装能播放embed的插件 <embed name='MM_controlSound1'
 * src='alarmSound/Emergency.wav' loop=false autostart=false hidden=true width=0
 * height=0> </embed>
 */
function MM_controlSound(sndAction, sndObj) {
	if (eval(sndObj) != null) {
		if (navigator.appName == 'Netscape') {
			eval(sndObj + ((sndAction == 'stop') ? '.stop()' : '.play(false)'));
		} else if (eval(sndObj + ".FileName")) {
			eval(sndObj + ((sndAction == 'stop') ? '.stop()' : '.play()'));
		}
	}
}
