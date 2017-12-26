var setting = {
	treeNodeKey: "id",
	//在isSimpleData格式下，当前节点id属性    
	treeNodeParentKey: "pId",
	//在isSimpleData格式下，当前节点的父节点id属性    
	showLine: true,
	//是否显示节点间的连线    
	checkable: true //每个节点上是否显示 CheckBox    
};
//进行zNodes的设置，对它进行赋值，也可以从后台获取，为方便在这里采用直接赋值；  
var zNodes = [{
	name: "父节点1",
	open: true,
	children: [{
		name: "子节点1"
	}, {
		name: "子节点2"
	}]
}, {
	name: "父节点2",
	open: true,
	children: [{
		name: "子节点1"
	}, {
		name: "子节点2"
	}]
}];
//页面加载后，将zNodes数据放到setting设置的树结构中，显示在html中id=treeDemo的容器中。  
$(document).ready(function() {
	zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
});