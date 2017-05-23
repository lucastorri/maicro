package maicro.router

import java.io.InputStream

class RequestWithParams(request: maicro.Request, matchingPath: Path) extends maicro.Request {

  override def path: String = request.path
  override def method: String = request.method
  override def input: InputStream = request.input

  def params: Map[String, String] = matchingPath.params(path)

}
