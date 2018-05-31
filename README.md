# Student Information Management System
使用建立一个Student类，利用数据库来存储多个Student，写完一个方法在main中写一段测试代码，运行以保证目前所做工作的正确性。有以下方法：
    （1）add(Student stu)：可以向其中增加新的学生，并保存在数据库中。
     测试add方法是否正确：用add方法向数据库增加一个新的学生，然后在数据库的图形管理界面中查询，确认是否增加。
    （2）dispAll()：可以显示所有的学生信息。
    （3）findById(long id)：可以按照学号来查找，并显示符合条件的学生信息，查无该人的话显示错误信息。
    （4）findByName(String name)：可以按照姓名查找学生，找到后显示其信息，查无此人显示错误信息。
    （5）delById(long id)：可以按照id删除学生的信息，然后显示找到该人。若查无此人，显示相应的错误信息。    
    （6）sortByXXX：可以按照指定的字段排序，并显示排序后的信息。

