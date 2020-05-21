package samples.gentypes

// como essa classe ate o presente momento nao eh uma subclasse de Seriable nem Comparable
open class Point2d(val x: Double, val y: Double, override val name: String) : AbstractGeomElement(name, 2) {
    override fun toString(): String {
        return "P($x, $y) TAG: $name"
    }
}

data class Point2dTruncated(val xi: Int, val yi: Int, override val name: String) : Point2d(xi * 1.0, yi * 1.0, name)

data class Point3d(val x: Double, val y: Double, val z: Double, override val name: String) : AbstractGeomElement(name, 3)

open class AbstractGeomElement(open val name: String, val dimension: Int)