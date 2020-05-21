package samples.gentypes

import java.io.Serializable

data class Node<T>(val value: T, private val customComparator: (Node<T>.(Node<T>) -> Int)? = null) :
    Comparable<Node<T>> where T : Serializable, T : Comparable<T> {

    override fun compareTo(other: Node<T>): Int {
        return customComparator?.invoke(this, other) ?: value.compareTo(other.value)
    }
}


fun testCompareString() {
    val compareString: (p: Node<String>, q: Node<String>) -> Int = { p, q ->
        println(String.format("Log: (%s) -> (%s)", p, q))
        p.value.compareTo(q.value)
    }

    val node = Node("chris", compareString)
    println(node.compareTo(Node("cgris")))
}

fun testCompareSomeClass() {
    // nao compila. nao poderemos criar um Node<Point2D> pois Point2D nao eh uma subclasse Comparable
    //val node : Node<Point2D>
}

fun main(args: Array<String>) {
    testCompareString()
}