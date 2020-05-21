package samples

/**
 *
 * */

inline fun <reified T> makeArray(sizeInit: Int, noinline init: (Int) -> T? =
    { _ -> null}) : Array<T?> = Array(sizeInit, init)