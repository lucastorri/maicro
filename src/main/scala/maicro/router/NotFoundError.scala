package maicro.router

import _root_.maicro.error.StatusError

case object NotFoundError extends Exception with StatusError {
  override def status: Int = 404
}
