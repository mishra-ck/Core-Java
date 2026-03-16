### NOTE:
If very few line of the code required synchronization, then it is not recommended to declare entire method as synchronized.
we need to enclose those few lines  of the code by using synchronized block.

The main advantage of synchronized block over synchronized method is it reduces waiting time of thread and improves performance of the system.

 
### We can declare synchronized block as follows

1. Syntax: Too get lock of current object 
synchronized(this)  
{
	//code needs to be sync
}
If a thread got lock of current object then only it is allowed to execute this area.

2. Syntax : To lock of particular object b
synchronized( b )
{
	// code needs to be sync
}
If a thread got lock of this particular object b then only it is allowed to execute area.

3. Syntax : To get class level lock.
synchronized ( Display.class)
{
	//code needs to be sync
}
If a thread got class level lock of Display.class then it is only it is allowed to execute this.
