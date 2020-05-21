package samples.gentypes

/**
 * https://kotlinlang.org/docs/tutorials/kotlin-for-py/generics.html#variance
 *
 * vamos ao seguinte exemplo
 *
 * Temos abaixo uma classe que encapsula um Array Generico
 *
 * Seja C uma classe qualquer SubC uma sub classe de C
 * Em kotlin Wrapper<SubC> nao eh uma subclasse de Wrapper<C>
 *     Pois se fosse, alguns erros poderiam ser implementados como a seguir:
 *
 *     val wrapper = Wrapper<Point2dTruncate> { null }
 *     //
 *     fun addPoint2dToWrapper(wrapper: Wrapper<Point2d>, p: Point2d)  = wrapper.add(p)
 *     // essa linha nao pode compilar pois o metodo espera um Wrapper de Point2D
 *     // se compila-se
 *     addPoint2dToWrapper(wrapper, Point2dTruncated(...))
 *
 *      // isso poderia seria ser implementado
 *      val point2d: Point2D = wrapper[i]
 *      // entrentando nos nao adicionamos uma instancia de Point2D
 *
 *
 *
 * */


class Wrapper<T>(init: (idx: Int) -> T?) {
    private var current = 0
    private var array: Array<Any?> = Array(2, init)

    // o uso do 'run' eu aprendi com a IDE
    operator fun get(idx: Int) = run {
        if (idx in 0 until current)
            array[idx] as T
        else throw IllegalArgumentException("")
    }

    fun last() = array[current]

    fun add(value: T) {
        array[current++] = value
    }
}

// uma funcao utilitaria
fun addPoint2dToWrapper(wrapper: Wrapper<Point2d>, p: Point2d) = wrapper.add(p)

fun testVarianceInAddPoint2dToWrapper() {
    // Point2dTruncated eh uma subclasse de Point2d
    val wrapperPoint2dTruncated = Wrapper<Point2dTruncated> { null }
    /**
     *  Wrapper<Point2dTruncated> nao eh uma subclasse de Wrapper<Point2D>
     *      se a linha abaixo compila-se poderiamos adicionar qualquer subclasse
     *      de Point2d a estrutura de dados encapsulada pela classe Wrapper<Point2d>
     *      e quando implementassemos algo assim:
     *         'val p: Point2D = wrapper[i]
     *     A Runtime lancaria uma excecao de casting de classe
     * */

    //addPoint2dToWrapper(wrapperPoint2dTruncated, Point2d(10.0, 0.0, "a0"))

    val wrapperPoint2d = Wrapper { Point2d(Double.NaN, Double.NaN, "blank") }
    addPoint2dToWrapper(wrapperPoint2d, Point2d(0.0, 0.0, "a0"))
    println(wrapperPoint2d[0])
}

fun testWrapper() {
    // por inferencia da funcao de inicializacao temos um Wrapper<Int>
    val wrapper = Wrapper { 0 }
    // um wrapper de inteiros nao vai permitir armazenar Doubles
    //wrapper.add(0.0)
    //val value = wrapper.last()

    val wrapper2 = Wrapper<AbstractGeomElement> { null }
    wrapper2.add(Point2d(10.0, 10.0, "a2d"))
    wrapper2.add(Point3d(10.0, 10.0, 10.0, "a3d"))

}

fun main(args: Array<String>) {
    testVarianceInAddPoint2dToWrapper()
}