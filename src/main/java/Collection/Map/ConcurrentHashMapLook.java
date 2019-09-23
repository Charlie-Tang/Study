package Collection.Map;
/**
* @author 作者 tangqichang
* @version 创建时间：2019年9月20日 上午10:16:36
* 类说明
* 
* 在看ConcurrentHashMap的时候发现该类继承了AbstaractMap 并实例化了接口ConcurrebtMap
* 那么直接看源码就完事了
* 
* 现在先说一下我自己原本对该集合的理解，它是使用Segement同步锁锁去分块锁住存储的数据的，和HashTable一样都是线程安全的
* 而且由于它是HashMap，那么扩容方式应该和HashMap的扩容方式差不多，接下来看源码
* 
* https://www.cnblogs.com/zerotomax/p/8687425.html
* https://blog.csdn.net/sinat_34976604/article/details/80971620
*/

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapLook{
	
	public static void main(String[] args) {
		//首先是Map所有方法的源码
		//首先我们得知道Map只是一个接口  他需要通过其他类来实现  靠自己是无法创建的
		ConcurrentHashMap<String, String> map1 = new ConcurrentHashMap<String, String>();
		
		//可以查看源码 看看存入是如何进行的  看putVal()这个方法
		//查看源码我们可以知道在存储数据时，首先先对键值对判空，如果为空则直接抛出异常
		//而后可以看到ConcurrentHashMap底层也是使用链表数组实现的：先获取该键的hash值，并初始化该位置的计数为0
		//而后判断临时使用的Node<K,V> []为不为空，如果为空则直接创建一个新的链表数组，调用方法initTable()
		
		//在这里具体讲一下initTable()即初始化链表数组是如何保持同步的。
		//首先将sizeCtl赋值给局部变量sc，之后当一个线程置sizeCtl值为-1成功，之后的线程都将被拒绝，通过执行Thread.yield()
		//这里如果sizeCtl没有初始化值，则直接初始化一个大小为16的链表数组，否则就为sc的值
		//数组创建后sizeCtl = n = (n >>> 2) ，相当于原先值的0.75即阈值
		
		//这里注意 >>是带符号右移   而>>>则是无符号右移 (这是二进制右移)
		//eg
//		System.out.println(4 >>> 2);
		
		//而后使用(tab.length - 1)*hash去计算该位置的hash值，如果该位置为空则使用casTabAt()方法去填写
		//而后如果该位置的hash值为MOVED  说明此时正在进行数组扩容的数据复制阶段  此时当前线程也会参与复制
		//如果该位置是有元素的话，采用synchronized加锁  
		
		//而后如果它是链表的话，则遍历链表，如果找到hash值和key值相同的节点，直接替换该节点的val值
		//当使用putIfAbsent的时候，只有在这个key没有设置值得时候才设置
		//如果是遇到hash值相同的节点，而key不同，则判断该节点有没有next节点，如果有的话则将要加入的节点当作该节点的后继节点
		
		//而如果此时它已经是红黑树的话，则调用putTreeVal()方法将该节点加入树上
		//如果此时同一节点的个数大于等于TREEIFY_THRESHOLD时，则使用treeifyBin()方法将其转换成红黑树
		
		//addCount()方法是对节点计数，计算节点个数
		
		//看了下源码  可以知道当初始化的链表数组某一位置的hash为空时插入数据时，是无锁的。因为它所使用的是无锁的同步方法 CAS
		map1.put("AAA", "11Asa");
		
		//这部分则是获取ConcurrentHashMap存储的值
		//可以看到程序先获取该键的hash值，当链表数组不为空，长度不为0，并且计算后的hash值不为空时，进行下一步
		//可以看到遍历链表数组后查找键相同和hash值相同的节点，如果有直接返回值
		//如果hash值为负数，则去遍历该节点的链表，如果有hash值相同的，则去遍历该链表获取相同的节点
		//或者直接遍历没扩容的链表，看看该节点的后继节点是否有该值
		map1.get("AAA");
		
		
	}
	
}

