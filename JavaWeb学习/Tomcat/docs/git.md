# git的使用
## 1. git的安装
在ubuntu系统上输入命令sudo apt install git

## 2. git的配置
1. 输入git用户名：
git config \--global user.name \"Your Name\"

2. 输入git邮箱：
git config \--global user.email "email@example.com\"
注意：git config命令的\--global参数，用了这个参数，表示你这台机器上所有的Git仓库都会使用这个配置，当然也可以对某个仓库指定不同的用户名和Email地址。

3. 查看git配置
git config \--list

## 3. 创建版本库
1. git init
通过git init命令把这个目录变成Git可以管理的仓库，当前目录下多了一个.git的目录，这个目录是Git来跟踪管理版本库的。所有的版本控制系统，其实只能跟踪文本文件的改动。Microsoft的Word格式是二进制格式，因此，版本控制系统是没法跟踪Word文件的改动的。如果要真正使用版本控制系统，就要以纯文本方式编写文件。

2. git add
把文件添加到仓库（暂存区）

3. git commit -m  \"   \"
把文件提交到仓库（暂存区的内容提交到当前分支）

&emsp;&emsp;  为什么Git添加文件需要add，commit一共两步呢？
&emsp;&emsp; 因为commit可以一次提交很多文件，所以你可以多次add不同的文件，

4. git status
查看仓库当前的状态

5. git diff
查看变化

6. git rm
删除文件

## 4. 版本回退
1. git log
显示从最近到最远的提交日志，查看提交历史

2. git reset \--hard commit_id
版本回退到commit_id版

3. git reflog
查看命令历史，用途：版本回到未来的某个版本

## 5. 撤销修改
场景1：当改乱了工作区某个文件的内容，想直接丢弃工作区的修改时，用命令git checkout \-- file。

场景2：当你不但改乱了工作区某个文件的内容，还添加到了暂存区时，想丢弃修改，分两步，第一步用命令git reset HEAD <file>，就回到了场景1，第二步按场景1操作。

场景3：已经提交了不合适的修改到版本库时，想要撤销本次提交，参考版本回退一节，不过前提是没有推送到远程库。

## 6. 远程仓库
1. 创建SSH Key
ssh-keygen -t rsa -C "youremail@example.com\"

2. 登陆GitHub，打开“Account settings”，“SSH Keys”页面，然后，点“Add SSH Key”，填上任意Title，在Key文本框里粘贴id_rsa.pub文件的内容

验证Key是否可以正常工作
ssh -T git@github.com

3. 添加远程库
git remote add origin git@github.com:michaelliao/learngit.git
添加后，远程库的名字就是origin，这是Git默认的叫法，也可以改成别的，但是origin这个名字一看就知道是远程库。

4. 推送内容到远程库
git push -u origin master

5. 在推送过程中，如果出现错误的话，可能是远程仓库的文件在本地并不存在，此时需要输入以下命令将内容和并
git pull \--rebase origin master

## 7. 分支
1. git checkout -b dev
创建dev分支，然后切换到dev分支

	-b参数表示创建并切换，相当于以下两条命令：
git branch dev
git checkout dev

2.  git branch
查看当前分支，git branch命令会列出所有分支，当前分支前面会标一个*号。

3. git merge dev
把dev分支的工作成果合并到master分支上，git merge命令用于合并指定分支到当前分支。

4. git branch -d dev
合并完成后，就可以删除dev分支了

如果要丢弃一个没有被合并过的分支，可以通过git branch -D <name>强行删除。

## 8. 多人协作
1. 分支管理


    master分支是主分支，因此要时刻与远程同步；

    dev分支是开发分支，团队所有成员都需要在上面工作，所以也需要与远程同步；

    bug分支只用于在本地修复bug，就没必要推到远程了，除非老板要看看你每周到底修复了几个bug；

    feature分支是否推到远程，取决于你是否和你的小伙伴合作在上面开发。
    
  2. 多人协作的工作模式通常是这样：
  

    首先，可以试图用git push origin <branch-name>推送自己的修改；

    如果推送失败，则因为远程分支比你的本地更新，需要先用git pull试图合并；

    如果合并有冲突，则解决冲突，并在本地提交；

    没有冲突或者解决掉冲突后，再用git push origin <branch-name>推送就能成功！
    
## 9. 创建标签
tag就是一个让人容易记住的有意义的名字，它跟某个commit绑在一起。
1. git tag < tagname>
新建一个标签，默认为HEAD，也可以指定一个commit id；

2. git tag < tagname> commit_id
新建一个标签，指定commit id；

3. git tag -a < tagname> -m "blablabla..." commit_id
指定标签信息

4. git tag
查看所有标签

5. git tag -d <  tagname>
删除标签

6. git push origin < tagname>
推送某个标签到远程

##10.删除github中某个文件夹
在github上只能删除仓库,却无法删除文件夹或文件, 所以只能通过命令来解决
1. git rm -r --cached target 
	删除target文件夹
2. git commit -m '删除了target' 
提交,添加操作说明
3. git push -u origin master 
将本次更改更新到github项目上去

注:本地项目中的target文件夹不收操作影响,删除的只是远程仓库中的target, 可放心删除
每次增加文件或删除文件，都要commit 然后直接 git push -u origin master，就可以同步到github上了











