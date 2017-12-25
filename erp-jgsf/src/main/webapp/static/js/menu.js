//$ = layui.jquery; 
  
  layui.tree({
	title:'ddd'
	,height: 332
    ,elem: '#demo1' //指定元素
    ,target: '_blank' //是否新选项卡打开（比如节点返回href才有效）
    ,click: function(item){ //点击节点回调
      layer.msg('当前节名称：'+ item.name + '<br>全部参数：'+ JSON.stringify(item));
      console.log(item);
    }
    ,nodes: [ //节点
      {
        name: '常用文件夹'
        ,id: 1
        ,alias: 'changyong'
        ,children: [
          {
            name: '所有未读（设置跳转）'
            ,id: 11
            ,href: 'http://www.layui.com/'
            ,alias: 'weidu'
          }, {
            name: '置顶邮件'
            ,id: 12
          }, {
            name: '标签邮件'
            ,id: 13
          }
        ]
      }, {
        name: '我的邮箱'
        ,id: 2
        ,spread: true
        ,children: [
          {
            name: 'QQ邮箱'
            ,id: 21
            ,spread: true
            ,children: [
              {
                name: '收件箱'
                ,id: 211
                ,children: [
                  {
                    name: '所有未读'
                    ,id: 2111
                  }, {
                    name: '置顶邮件'
                    ,id: 2112
                  }, {
                    name: '标签邮件'
                    ,id: 2113
                  }
                ]
              }, {
                name: '已发出的邮件'
                ,id: 212
              }, {
                name: '垃圾邮件'
                ,id: 213
              }
            ]
          }, {
            name: '阿里云邮'
            ,id: 22
            ,children: [
              {
                name: '收件箱'
                ,id: 221
              }, {
                name: '已发出的邮件'
                ,id: 222
              }, {
                name: '垃圾邮件'
                ,id: 223
              }
            ]
          }
        ]
      }
      ,{
        name: '收藏夹'
        ,id: 3
        ,alias: 'changyong'
        ,children: [
          {
            name: '爱情动作片'
            ,id: 31
            ,alias: 'love'
          }, {
            name: '技术栈'
            ,id: 12
            ,children: [
              {
                name: '前端'
                ,id: 121
              }
              ,{
                name: '全端'
                ,id: 122
              }
            ]
          }
        ]
      }
    ]
  });
  
  var table = layui.table;
//执行一个 table 实例
  table.render({
    elem: '#test'
    ,height: 330
    ,url: 'static/data.json' //数据接口
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
      ,{field: 'username', title: '用户名', width:80}
      ,{field: 'sex', title: '性别', width:80, sort: true}
      ,{field: 'city', title: '城市', width:80} 
      ,{field: 'sign', title: '签名', width: 170}
      ,{field: 'experience', title: '积分', width: 80, sort: true}
      ,{field: 'score', title: '评分', width: 80, sort: true}
      ,{field: 'classify', title: '职业', width: 80}
      ,{field: 'wealth', title: '财富', width: 135, sort: true}
      ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
    ]]
  });
  
  
  //监听工具条
  table.on('tool(demo)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    var data = obj.data //获得当前行数据
    ,layEvent = obj.event; //获得 lay-event 对应的值
    if(layEvent === 'detail'){
      layer.msg('查看操作');
    } else if(layEvent === 'del'){
      layer.confirm('真的删除行么', function(index){
        obj.del(); //删除对应行（tr）的DOM结构
        layer.close(index);
        //向服务端发送删除指令
      });
    } else if(layEvent === 'edit'){
      layer.msg('编辑操作');
    }
  });