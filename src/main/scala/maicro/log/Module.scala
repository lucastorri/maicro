package maicro.log

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

trait Module {

  def log(handler: maicro.Handler)(req: maicro.Request)(implicit exec: ExecutionContext): Future[maicro.Response] = {
    val reqDescription = s"${req.method} ${req.path} =>"
    val resFuture = handler(req)
    resFuture.onComplete {
      case Success(res) =>
        println(s"$reqDescription ${res.status}")
      case Failure(err) =>
        println(s"$reqDescription ERROR: ${err.getMessage}")
    }
    resFuture
  }

}
