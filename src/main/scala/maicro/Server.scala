package maicro

trait Server {
  def start(port: Int = 7000): Unit
  def stop(): Unit
}
