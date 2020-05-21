package ds.trees


/**
 * AnyClass<T: Class> ou AnyClass<T> where T: Class1, T: Class1
 * */

class BinaryTree<Value: Comparable<Value>> (
    private var value: Value, private var lf: BinaryTree<Value>? = null
    , private var ri: BinaryTree<Value>? = null) {

    fun add(value: Value) {
        add(value, this)
    }

    private fun add(value: Value, root: BinaryTree<Value>?) {
        root?.let {
            if (it.value > value) {
                if (it.lf == null) {
                    it.lf = BinaryTree(value)
                }
                else {
                    add(value, it.lf)
                }
            }
            else {
                if (it.ri == null) {
                    it.ri = BinaryTree(value)
                }
                else {
                    add(value, it.ri)
                }
            }
        }
    }

    override fun toString(): String = "Root: $value, L: ${lf?.value}, R: ${ri?.value}"
}


fun testAdd() {
    val tree = BinaryTree(18)
    tree.add(15)
    tree.add(16)
    tree.add(20)
    tree.add(19)
}

fun main(args: Array<String>) {
    testAdd()
}