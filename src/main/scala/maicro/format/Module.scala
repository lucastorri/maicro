package maicro.format

import maicro.{Request, Response, WrittenResponse}
import play.api.libs.json.{JsValue, Json}

import scala.concurrent.Future

trait Module {

  def receive[C: Reader](req: Request): C =
    implicitly[Reader[C]].read(req)

  def send[C: Writer](content: C, status: Int = 200): Future[Response] =
    Future.successful(write(content, status))

  def write[C: Writer](content: C, status: Int = 200): Response =
    WrittenResponse(status, content, implicitly[Writer[C]])

  implicit object JsValueReader extends Reader[JsValue] {
    override def read(req: Request): JsValue = Json.parse(req.input)
  }

  implicit def stringWriter: Writer[String] = new ToStringWriter

}
