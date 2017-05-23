package maicro.router

abstract class Method(val name: String) {

  def matches(current: String): Boolean =
    name == Method.Any || current == name

  override def hashCode(): Int = name.hashCode

  override def equals(obj: scala.Any): Boolean = obj match {
    case Method(other) => other == name
    case _ => false
  }

}

object Method {

  val Any = "*"

  def unapply(method: Method): Option[String] = Some(method.name)

}
