package maicro.router

import scala.concurrent.Future

trait Module {

  case class RoutedHandler(method: Method, path: Path, handler: ParameterizedHandler) {

    def matches(req: maicro.Request): Boolean =
      method.matches(req.method) && path.matches(req.path)

  }

  def route(routes: RoutedHandler*): maicro.Handler = (req: maicro.Request) => {
    routes
      .find(_.matches(req))
      .map(h => h.handler(new RequestWithParams(req, h.path)))
      .getOrElse(notFound(req))
  }

  def notFound(req: maicro.Request): Future[maicro.Response] = Future.failed(NotFoundError)

  case object get extends Method("GET")
  case object post extends Method("POST")
  case object any extends Method(Method.Any)

  implicit class RouteBuilder(method: Method) {

    def apply(path: String, handler: ParameterizedHandler): RoutedHandler =
      RoutedHandler(method, Path(path), handler)

  }

}
