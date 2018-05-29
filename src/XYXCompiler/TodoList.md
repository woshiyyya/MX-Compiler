# To do list
- 类成员变量的初始化
- 缺少构造函数，要强制构建构造函数。
    - new A[10] / new A
    - 不必要在构造函数里面加alloc，因为已经分配连续空间了
    - CF(baseaddr + offset)

- 在数组new时需在头部存其size，string亦然