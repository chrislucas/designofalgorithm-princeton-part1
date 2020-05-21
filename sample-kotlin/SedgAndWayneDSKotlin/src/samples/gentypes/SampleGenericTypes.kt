package samples.gentypes

data class Clazz<Item>(val value: Item? = null) {
    companion object {
        // uma forma de demonstrar que eh possivel criar um metodo membro de classe que defina um tipo
        // generico que nao foi definido na classe
        @JvmStatic
        fun <T> getInstance(value: T): Clazz<T> =
            Clazz(value)
    }

    // um segundo exemplo. Nao que essemetodo tenha alguma utilidade
    fun <T> execute(value: T, fn: (T) -> Clazz<T>): Clazz<T> = fn.invoke(value)
}


fun main() {
    val item = Clazz.getInstance(10)
    println(item)
    val anotherItem = Clazz<String>()
    anotherItem.execute("chris") {
        val instance = Clazz(it)
        println(instance)
        instance
    }

}