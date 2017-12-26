$('#tt').treegrid({    
    url:'static/data.json',    
    idField:'id',    
    method: 'get',
    checkbox:true,
//    fitColumns: true,
    rownumbers: true,
	animate: true,
    treeField:'name',
//    checkbox: function(row){
//		var names = ['Java','eclipse.exe','eclipse.ini'];
//		if ($.inArray(row.name, names)>=0){
//			return true;
//		}
//	},
    columns:[[    
        {field:'name',title:'Name',width:300},    
        {field:'size',title:'Size',align:'right'},    
        {field:'date',title:'Modified Date'} 
    ]]    
}); 