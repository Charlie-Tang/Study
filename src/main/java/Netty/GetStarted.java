package Netty;
/**
* @author 作者 tangqichang
* @version 创建时间：2019年9月23日 上午10:22:30
* 类说明
* 
* 新手入门Netty  
* 之前有写过一个服务器Demo  但是已经完全不记得了
* 
* https://www.cnblogs.com/imstudy/p/9908791.html
*/
public class GetStarted {
	
	//Netty作为异步高性能的通信框架，往往作为基础通信组件被这些 RPC 框架使用
	//Netty 作为高性能的基础通信组件，它本身提供了 TCP/UDP 和 HTTP 协议栈。非常方便定制和开发私有协议栈
	
	//Netty 作为异步事件驱动的网络，高性能之处主要来自于其 I/O 模型和线程处理模型，前者决定如何收发数据，后者决定如何处理数据。
	
	//传统堵塞型I/O BIO
	//特点如下：每个请求都需要独立的线程完成数据 Read，业务处理，数据 Write 的完整操作问题。
	//当并发数较大时，需要创建大量线程来处理连接，系统资源占用较大。
	//连接建立后，如果当前线程暂时没有数据可读，则线程就阻塞在 Read 操作上，造成线程资源浪费。
	
	//非堵塞I/O NIO
	//在多个线程的使用时会用到Select，它可以同时堵塞多个I/O操作
	//而且可以同时对多个读操作，多个写操作的 I/O 函数进行检测，直到有数据可读或可写时，才真正调用 I/O 操作函数。
	
	//Netty 的 IO 线程 NioEventLoop 由于聚合了多路复用器 Selector，可以同时并发处理成百上千个客户端连接。
	//当线程从某客户端 Socket 通道进行读写数据时，若没有数据可用时，该线程可以进行其他任务。
	//线程通常将非阻塞 IO 的空闲时间用于在其他通道上执行 IO 操作，所以单独的线程可以管理多个输入和输出通道。
	//由于读写操作都是非阻塞的，这就可以充分提升 IO 线程的运行效率，避免由于频繁 I/O 阻塞导致的线程挂起。
	//一个 I/O 线程可以并发处理 N 个客户端连接和读写操作，这从根本上解决了传统同步阻塞 I/O 一连接一线程模型，架构的性能、弹性伸缩能力和可靠性都得到了极大的提升。

	//基于Buffer模型
	//传统的 I/O 是面向字节流或字符流的，以流式的方式顺序地从一个 Stream 中读取一个或多个字节, 因此也就不能随意改变读取指针的位置。
	//在 NIO 中，抛弃了传统的 I/O 流，而是引入了 Channel 和 Buffer 的概念。在 NIO 中，只能从 Channel 中读取数据到 Buffer 中或将数据从 Buffer 中写入到 Channel。
	//基于 Buffer 操作不像传统 IO 的顺序操作，NIO 中可以随意地读取任意位置的数据。

}