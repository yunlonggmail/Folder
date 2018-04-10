# List

## ArrayList

1. 底层是数组。
2. 长于随机访问元素。但是在List中间和插入和移除元素时较慢。
3. Since JDK1.2
4. 判断元素相等的方式，是equals，其中用到判断元素相等的有indexOf，remove
5. 在长度扩展时一次扩展原来长度的一半或者扩大到最大值(Interger.MaxValue-8)，还有其他异常判断，最大可以增到Integer.MaxValue
6. 如果一开始就知道集合的大小，那么指定初始数组长度，此时能够提高性能。

### 插入元素

#### 1、末尾插入元素

1. 确认数组的空间足够。
2. 默认数组空间大小为10.
3. 如果数组空间不够，增加数组空间，增加的大小为原来数组的空间的一半。 4.
4. 在Arrays.copyOf方法中创建新的数组，将老数组中的元素拷贝到新数组中，返回新数组
5. 在size+1位置插入元素

#### 在index位置插入元素。

1. 检查index是否越界。
2. 确认数组空间，同末尾插入元素1-4
3. 调用System.copyOf[native]方法，将数组后续位移拷贝。
4. 在数组位置index处放入元素。

### 移除元素

#### remove(index)

1. 将index位置元素取出返回。
2. 将后续数组[index+1]中的元素copyOf到index
3. 将最后一位置空

#### remove(T object)

1. 如果object为空，则移除第一个空
2. 如果不为空，则移除object对应位置元素，同上

### 修改元素

#### set(int index,T object)

1. 检查index是否越界
2. 取出OldValue
3. 将index位置数据修改
4. 返回OldValue

### 查询元素

#### get(index)

1. 检查index是否越界，取出数组中的元素返回

#### indexOf(T object)

1. 如果object==null返回第一个null对应的位置
2. 如果object!=null返回object对应的位置。

#### contains(T object)

1. indexOf(object)>=0;

### 迭代器

1. 有两种迭代器：普通迭代器和ListIterator
2. ListIterator能够向前移动，能够修改和增加元素
3. 迭代remove是指向初始位置为-1，如果需要remove，需要调用next修改remove指向
4. cursor初始值为0，在next之后cursor自动+1。remove之后cursor-1;
5. previous之后cursor自动-1，add之后自动+1

### 遍历元素

#### 使用普通迭代器 iterator()方法

1. 该方法返回的是普通迭代器，只能遍历并且是从前到后遍历
2. 可以使用remove方法，但是必须有lastRet位置信息才能移除，位置信息一般在next移动后赋值，表示刚刚获取到的元素

#### 使用ListIterator迭代器 listIterator()或者listIterator(int index)方法

1. 使用index方法是使得Iterator的游标移到相应的位置。
2. 第一个方法默认游标位置为0，该迭代器继承了普通的迭代器

#### 随机访问

1. 实现了RandomAccess接口，该接口是标记接口，主要标记可以快速随机访问，在进行普通for循环时会比较快速
2. 继承了该接口后，表示使用普通的遍历循环时速度较快，优于迭代器

#### for-each 遍历

1. 使用随机访问效率最高，使用该方法次之，使用迭代器最低

## LinkedList

1. 底层是双向链表
2. 它通过较低代价在List中间进行插入和删除操作。提供了优化的顺序访问
3. 随机访问相对较慢，特性集较ArrayList更大
4. Since1.5|1.6 实现了Queue的子接口Deque[Since 1.6]，1.6 表现了Stack的一些方法
5. 使用ListNode实现双线链表节点

### 增加

#### add(E e)

1. 将元素插入到末尾,linkLast()
2. 实现的是Collection接口方法
3. 当做List使用时使用该方法
4. 在使用add(int index ,E e)方法进行元素插入中间时，使用linkBefore方法，采用该方法之前需要查找到index位置的元素。

#### offer(E e)

1. 调用add(E e);
2. 当做Queue使用该方法。
3. Since 1.5

#### push(E e)

1. 调用addFirst(E e)方法
2. 当做Stack使用时使用该方法
3. Since 1.6

#### addFirst(E e)

1. 在首位添加元素，linkFirst
2. 属于双端队列的方法

#### addLast(E e)

1. 在末位添加元素，调用私有方法linkLast
2. 属于双端队列的方法

#### offerFirst(E e)

1. 在首位添加元素，addFirst
2. 属于双端队列的方法

#### offerLast(E e)

1. 在末位添加元素，addLast
2. 属于双端队列的方法

### 删除元素

#### remove(E e)

1. 判断是否为空，执行unlink(E e)方法，进行ListNode pre next值修改
2. 类似方法有removeFirst和removeLast

#### pop()

1. removeFirst
2. Since 1.6
3. 当做Stack使用

#### removeFirst()

1. 移除并返回第一个元素，调用私有unlinkFirst方法
2. 属于Dqueue

#### removeLast()

1. 移除并返回最后一个元素，调用私有unlinkLast方法
2. 属于Dqueue

#### poll()

1. first==null 返回Null，否则返回unlinkFirst
2. Since1.5

#### pollFirst()

1. first==null 返回Null，否则返回unlinkFirst
2. Since1.6

#### pollLast()

1. first!=null 返回Null，否则返回unlinkLast
2. Since1.6

### 修改元素

#### set(int index, E e)

1. 获取index元素，替换元素

### 查询元素

#### get(int index)

1. 关键是：node(index)，该方法优化处理过，在元素位置在前半部分时从前向后查，在元素位置在后半部分是从后往前查。
2. 其他相近方法 getLast getFirst等

#### element()

1. 实际是getFirst 有可能抛出异常，如first为空
2. since 1.5 Queue

#### peek()

1. 实际是返回first值，在first为空时返回空。
2. since 1.5

### 遍历方式

1. 使用List迭代器listIterator()：效率最高
2. 使用倒序的迭代器descendingIterator() since1.6针对双向队列加入的
3. 使用随机访问(普通for循环)，性能最差，效率最低
4. 使用for-each循环
5. 使用pollFirst遍历，正序遍历，但是此时链表里的数据被移除
6. 使用pollLast遍历，倒序遍历，但是此时链表里的数组被移除
7. 使用removeFirst遍历，同5
8. 使用removeLast遍历，同6

## Vector

1. 与ArrayList相同底层实现同样是数组，最小空间同样是10

2. 不同，增长空间是原空间相同大小，可以设定空间默认增长大小。

3. 部分方法是线程同步的，addElementAt(T)是线程同步的,add(T)是线程同步的，add(int,T)是非同步的

4. Stack继承Vector，使用有些问题因为Stack可以使用Vector的所有方法。在1.0版本开始有，前期写法稍微有些问题，后来使用LinkedList替代了

5. Vector是线程安全的，ArrayList是线程不安全的。

6. Vector性能比ArrayList差

## 比较

1. ArrayList底层实现时数组，随机访问效率高，随机插入随机删除效率低。遍历数组时建议使用随机访问(普通for循环)遍历
2. LinkedList底层是一个双向链表，它同时可以被当做堆栈，队列，双端队列进行操作(Since 1.6)。LinkedList随机访问效率低，但随机插入、随机删除效率高。LinkedList应该采用迭代器方式遍历。
3. 如果涉及到"动态数组"、"栈"、"队列"、"链表"等数据结构，应该考虑使用List，具体选用哪个List，按照下面的标准选择

  3.1.1 对于需要快速插入、删除元素，使用LinkedList

  3.1.2 对于快速访问元素，应该使用ArrayList

  3.1.3 对于多线程环境应该使用Vector
