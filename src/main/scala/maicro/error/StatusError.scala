package maicro.error

trait StatusError { self: Throwable =>
  def status: Int
}
