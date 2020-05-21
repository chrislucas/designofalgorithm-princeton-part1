package ds.arrays

// https://www.ime.usp.br/~pf/estruturas-de-dados/aulas/stack.html#resizing-array
class GenericResizableArray<Item>(sizeInit: Int = 2, init: (Int) -> Item? = { _ -> null }) {
     private var items: Array<Any?> = Array(sizeInit, init)

     private var current = 0

     operator fun get(idx: Int): Item? = if(idx < 0 || idx > current) null else items[idx] as Item

     fun add(item: Item) {
          items[current++] = item
     }

     init {
          items = Array(sizeInit, init = init)
     }
}

fun testResizableArray() {
     val gen = GenericResizableArray<String>()

     gen.add("1")
     gen.add("2")

     println(gen[1])
}


fun main(args: Array<String>) {
     testResizableArray()
}