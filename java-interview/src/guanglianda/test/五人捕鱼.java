package guanglianda.test;

/*
* A、B、C、D、E五个人去捕鱼。第二天，A将捕到的鱼分成五份，把多余的一条扔掉，并拿走自己的一份。
* 接下来的四个人用同样的方法都能拿走鱼。问他们总共捕了至少多少条鱼。
* */

public class 五人捕鱼 {
    public static void main(String[] args){
        int num = 1;
        for(int i=0;i<5;i++){
            num = 5*num+1;
        }
        System.out.println(num);
    }

    /*
    * public static void main(String[] args){
    *   for(n=5;;n++){
    *       j = 4*(n-1)/5;
    *       k = 4*(j-1)/5;
    *       l = 4*(k-1)/5;
    *       m = 4*(l-1)/5;
    *       if(n%5==1&&j%5==1&&k%5==1&&l%5==1&&m%5==1){
    *           System.out.println(m);
    *           break;
    *       }
    *   }
    * }
    *
    * #include<iostream>
using namespace std;
int fun(int num)
{
	int i=0;
	while((num-1)%5==0)
	{
		num=num-(num-1)/5;
		i++;
	}
	if(i==5)
		return 1;
	return 0;

}
int main()
{
	int num;
	for(num=5;num<100000;num++)
	{
		if(fun(num))
		{
			cout<<num<<endl;
			break;
		}
	}
	return 0;
}

    *
    * */
}
