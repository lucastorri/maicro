package maicro

import java.io.InputStream

import _root_.maicro.format.Writer

trait Entity

trait Request extends Entity {
  def path: String
  def method: String
  def input: InputStream
}

trait Response extends Entity {
  def status: Int
  def contentType: String
  def output: InputStream
}

case class WrittenResponse[C](status: Int, content: C, writer: Writer[C]) extends Response {
  override def contentType: String = writer.contentType

  override def output: InputStream = writer.write(content)
}
