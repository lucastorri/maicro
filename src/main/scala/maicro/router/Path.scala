package maicro.router

import java.util.regex.Pattern

case class Path(self: String) {

  private val regex = {
    "^" + Pattern.compile(":(?<name>\\w+)").matcher(self).replaceAll("(?<${name}>[^/]+)")
  }

  def matches(path: String): Boolean = path.matches(regex)

  def params(path: String): Map[String, String] = {

    val matcher = Pattern.compile(regex).matcher(path)

    if (matcher.matches()) {
      val names = ":\\w+".r.findAllIn(self).toList.map(_.stripPrefix(":"))
      names.map(name => name -> matcher.group(name)).toMap
    } else {
      Map.empty
    }
  }

}
