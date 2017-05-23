package maicro.format

import java.io.{ByteArrayInputStream, InputStream}
import java.nio.charset.StandardCharsets

trait Writer[C] {
  def contentType: String
  def write(content: C): InputStream
}

class ToStringWriter[Nothing] extends Writer[Nothing] {
  override def contentType: String = "text/plain"

  override def write(content: Nothing): InputStream =
    new ByteArrayInputStream(content.toString.getBytes(StandardCharsets.UTF_8))
}
