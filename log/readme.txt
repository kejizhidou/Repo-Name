开启mac SVN 服务 

svnserve -d -r /Users/sc/svn/mycode

可以设置自动启动     目前没有设置



这个文件夹非常重要 不要轻易删除   谢谢



更新SVN 目录  地址  

1.  采用指令方式

cd  /Users/sc/Desktop

svn update  

2.  cornerstone  方式      working copies  点击 mycode  update 即可   



working copies  是从SVN仓库检录下来的项目版本，一旦修改会在仓库中显示出修改文件数量信息；

cornstone  左上角 添加 working copies   方便查看项目的开发进度  

添加文件是import   到处export也可以用    与 checkout 的区别是  export出的版本，之后做出项目修改的

文件  不会再与仓库中的版本有任何的关联，不会在cornstone 中查看观察到



重要的文章说明：

http://blog.csdn.net/q199109106q/article/details/8655204


http://blog.csdn.net/langzi7758521/article/details/51690066



