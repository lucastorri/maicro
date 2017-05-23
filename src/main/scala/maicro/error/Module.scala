package maicro.error

import maicro.format._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

trait Module {

  val defaultErrorStatus = 500

  def handleErrors(handler: maicro.Handler)(req: maicro.Request)(implicit exec: ExecutionContext): Future[maicro.Response] = {
    Try(handler(req)) match {
      case Success(response) =>
        response.recoverWith(PartialFunction(onError))
      case Failure(error) =>
        onError(error)
    }
  }

  def onError(error: Throwable): Future[maicro.Response] = error match {
    case withStatus: StatusError =>
      send(error.getMessage, withStatus.status)
    case _ =>
      send(error.getMessage, defaultErrorStatus)
  }

}
